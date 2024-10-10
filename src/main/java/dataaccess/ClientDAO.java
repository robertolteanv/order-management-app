package dataaccess;

import connection.ConnectionFactory;
import model.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the ClientDAO class, responsible for database operations related to the Client table.
 *

 */
public class ClientDAO  {
    private static final String INSERT_CLIENT_SQL = "INSERT INTO client (name, address, email, phone) VALUES (?, ?, ?, ?)";
    private static final String SELECT_CLIENT_BY_ID = "SELECT * FROM Client WHERE id = ?";
    private static final String SELECT_ALL_CLIENTS = "SELECT * FROM Client";
    private static final String DELETE_CLIENT_SQL = "DELETE FROM Client WHERE id = ?";
    private static final String UPDATE_CLIENT_SQL = "UPDATE Client SET name = ?, address = ?, email = ?, phone = ? WHERE id = ?";

    /**
     * This is the constructor of the ClientDAO class.
     */
    public ClientDAO() {
        super();
    }

    /**
     * This method is used to insert a client into the database.
     * It uses a PreparedStatement to execute the SQL query.
     *
     * @param client This is the client to be inserted.
     */
    public void  insertClient(Client client) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CLIENT_SQL)) {
            preparedStatement.setString(1, client.getName());
            preparedStatement.setString(2, client.getAddress());
            preparedStatement.setString(3, client.getEmail());
            preparedStatement.setString(4, client.getPhone());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is used to find a client by its ID.
     * It uses a PreparedStatement to execute the SQL query.
     *
     * @param id This is the ID of the client to be found.
     * @return Client This returns the client with the given ID.
     */
    public Client findById(int id) {
        Client client = null;
        try (Connection connection = (Connection) ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CLIENT_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                client = new Client(rs.getInt("id"), rs.getString("name"), rs.getString("address"),
                        rs.getString("email"), rs.getString("phone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return client;
    }

    /**
     * This method is used to get all clients from the database.
     * It uses a PreparedStatement to execute the SQL query.
     *
     * @return List<Client> This returns a list of all clients.
     */
    public List<Client> selectAllClients() {
        List<Client> clients = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CLIENTS)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                clients.add(new Client(rs.getInt("id"), rs.getString("name"), rs.getString("address"),
                        rs.getString("email"), rs.getString("phone")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    /**
     * This method is used to delete a client from the database by its ID.
     * It uses a PreparedStatement to execute the SQL query.
     *
     * @param id This is the ID of the client to be deleted.
     * @return boolean This returns true if the client was deleted, false otherwise.
     */
    public boolean deleteClient(int id) {
        boolean rowDeleted = false;
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_CLIENT_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowDeleted;
    }

    /**
     * This method is used to update a client in the database.
     * It uses a PreparedStatement to execute the SQL query.
     *
     * @param client This is the client to be updated.
     * @return boolean This returns true if the client was updated, false otherwise.
     */
    public boolean updateClient(Client client) {
        boolean rowUpdated = false;
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_CLIENT_SQL)) {
            statement.setString(1, client.getName());
            statement.setString(2, client.getAddress());
            statement.setString(3, client.getEmail());
            statement.setString(4, client.getPhone());
            statement.setInt(5, client.getId());
            rowUpdated = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }

    /**
     * This method is used to get all clients from the database.
     * It is currently not implemented.
     *
     * @return List<Client> This returns null.
     */
    public List<Client> findAll() {
        return null;
    }
}