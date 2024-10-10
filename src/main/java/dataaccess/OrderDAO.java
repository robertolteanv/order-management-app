package dataaccess;

import connection.ConnectionFactory;
import model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * This is the OrderDAO class, responsible for database operations related to the Order table.
 *
 *
 */
public class OrderDAO {
    private static final String INSERT_ORDER_SQL = "INSERT INTO `order` (buyer, ordered_products) VALUES (?, ?)";

    /**
     * This method is used to insert an order into the database.
     * It uses a PreparedStatement to execute the SQL query.
     *
     * @param order This is the order to be inserted.
     */
    public void insertOrder(Order order) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDER_SQL)) {
            preparedStatement.setString(1, order.getBuyer());
            preparedStatement.setString(2, order.getOrderedProducts());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}