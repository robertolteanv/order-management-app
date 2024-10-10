package dataaccess;

import connection.ConnectionFactory;
import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * This is the ProductDAO class, responsible for database operations related to the Product table.
 *
 *
 */
public class ProductDAO {
    private static final String INSERT_PRODUCT_SQL = "INSERT INTO Product (name, price, stock) VALUES (?, ?, ?)";
    private static final String SELECT_PRODUCT_BY_ID = "SELECT * FROM Product WHERE id = ?";
    private static final String SELECT_ALL_PRODUCTS = "SELECT * FROM Product";
    private static final String DELETE_PRODUCT_SQL = "DELETE FROM Product WHERE id = ?";
    private static final String UPDATE_PRODUCT_SQL = "UPDATE Product SET name = ?, price = ?, stock = ? WHERE id = ?";
    /**
     * This method is used to insert a product into the database.
     * It uses a PreparedStatement to execute the SQL query.
     *
     * @param product This is the product to be inserted.
     */
    public void insertProduct(Product product) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT_SQL)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getStock());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * This method is used to find a product by its ID.
     * It uses a PreparedStatement to execute the SQL query.
     *
     * @param id This is the ID of the product to be found.
     * @return Product This returns the product with the given ID.
     */
    public Product findById(int id) {
        Product product = null;
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                product = new Product(rs.getInt("id"), rs.getString("name"), rs.getDouble("price"),
                        rs.getInt("stock"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }
    /**
     * This method is used to get all products from the database.
     * It uses a PreparedStatement to execute the SQL query.
     *
     * @return List<Product> This returns a list of all products.
     */
    public List<Product> selectAllProducts() {
        List<Product> products = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTS)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                products.add(new Product(rs.getInt("id"), rs.getString("name"), rs.getDouble("price"),
                        rs.getInt("stock")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
    /**
     * This method is used to delete a product from the database by its ID.
     * It uses a PreparedStatement to execute the SQL query.
     *
     * @param id This is the ID of the product to be deleted.
     * @return boolean This returns true if the product was deleted, false otherwise.
     */
    public boolean deleteProduct(int id) {
        boolean rowDeleted = false;
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCT_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowDeleted;
    }
    /**
     * This method is used to update a product in the database.
     * It uses a PreparedStatement to execute the SQL query.
     *
     * @param product This is the product to be updated.
     * @return boolean This returns true if the product was updated, false otherwise.
     */
    public boolean updateProduct(Product product) {
        boolean rowUpdated = false;
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCT_SQL)) {
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setInt(3, product.getStock());
            statement.setInt(4, product.getId());
            rowUpdated = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }
}
