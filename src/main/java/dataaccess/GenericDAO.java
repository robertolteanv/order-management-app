package dataaccess;

import connection.ConnectionFactory;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the GenericDAO class, responsible for performing generic database operations.
 * It uses Java Reflection API to perform operations on any type of object.
 *
 * @param <T> This is the type of object on which the database operations are performed.
 *
 */
public class GenericDAO<T> {
    private final Class<T> type;
    /**
     * This is the constructor of the GenericDAO class.
     * It initializes the type of object on which the database operations are performed.
     *
     * @param type This is the type of object on which the database operations are performed.
     */
    public GenericDAO(Class<T> type) {
        this.type = type;
    }
    /**
     * This method is used to insert an object into the database.
     * It uses a PreparedStatement to execute the SQL query.
     *
     * @param object This is the object to be inserted.
     */
    public void insert(T object) {
        StringBuilder query = new StringBuilder("INSERT INTO ");
        query.append(type.getSimpleName()).append(" (");
        Field[] fields = type.getDeclaredFields();
        for (Field field : fields) {
            query.append(field.getName()).append(",");
        }
        query.deleteCharAt(query.length() - 1);
        query.append(") VALUES (");
        for (int i = 0; i < fields.length; i++) {
            query.append("?,");
        }
        query.deleteCharAt(query.length() - 1);
        query.append(")");

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query.toString())) {
            setPreparedStatementParameters(statement, object, fields);
            statement.executeUpdate();
        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    /**
     * This method is used to find an object by its ID.
     * It uses a PreparedStatement to execute the SQL query.
     *
     * @param id This is the ID of the object to be found.
     * @return T This returns the object with the given ID.
     */
    public T findById(int id) {
        StringBuilder query = new StringBuilder("SELECT * FROM ");
        query.append(type.getSimpleName());
        query.append(" WHERE id = ?");

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query.toString())) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return createObjectFromResultSet(resultSet);
            }
        } catch (SQLException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * This method is used to update an object in the database.
     * It uses a PreparedStatement to execute the SQL query.
     *
     * @param object This is the object to be updated.
     */
    public void update(T object) {
        StringBuilder query = new StringBuilder("UPDATE ");
        query.append(type.getSimpleName());
        query.append(" SET ");
        Field[] fields = type.getDeclaredFields();
        for (Field field : fields) {
            query.append(field.getName()).append("=?,");
        }
        query.deleteCharAt(query.length() - 1);
        query.append(" WHERE id=?");

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query.toString())) {
            setPreparedStatementParameters(statement, object, fields);
            Field idField = type.getDeclaredField("id");
            idField.setAccessible(true);
            statement.setObject(fields.length + 1, idField.get(object));
            statement.executeUpdate();
        } catch (SQLException | IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
    /**
     * This method is used to delete an object from the database by its ID.
     * It uses a PreparedStatement to execute the SQL query.
     *
     * @param id This is the ID of the object to be deleted.
     */
    public void delete(int id) {
        String query = "DELETE FROM " + type.getSimpleName() + " WHERE id=?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * This method is used to get all objects from the database.
     * It uses a PreparedStatement to execute the SQL query.
     *
     * @return List<T> This returns a list of all objects.
     */
    public List<T> findAll() {
        String query = "SELECT * FROM " + type.getSimpleName();
        List<T> objects = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                objects.add(createObjectFromResultSet(resultSet));
            }
        } catch (SQLException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return objects;
    }

    private void setPreparedStatementParameters(PreparedStatement statement, T object, Field[] fields) throws SQLException, IllegalAccessException {
        int index = 1;
        for (Field field : fields) {
            field.setAccessible(true);
            statement.setObject(index++, field.get(object));
        }
    }

    private T createObjectFromResultSet(ResultSet resultSet) throws SQLException, IllegalAccessException, InstantiationException {
        T object = type.newInstance();
        for (Field field : type.getDeclaredFields()) {
            field.setAccessible(true);
            field.set(object, resultSet.getObject(field.getName()));
        }
        return object;
    }
}
