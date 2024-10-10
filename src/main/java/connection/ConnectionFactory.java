package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

/**
 * This is the ConnectionFactory class, responsible for managing database connections.
 * It uses the Singleton design pattern to ensure that only one instance of this class is created.
 *
 *
 */
public class ConnectionFactory {
    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DBURL = "jdbc:mysql://localhost:3306/schooldb";
    private static final String USER = "root";
    private static final String PASS = "root";

    private static ConnectionFactory singleInstance = new ConnectionFactory();

    /**
     * This is the private constructor of the ConnectionFactory class.
     * It loads the JDBC driver.
     */
    private ConnectionFactory() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is used to create a new database connection.
     *
     * @return Connection This returns a new database connection.
     */
    private Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DBURL, USER, PASS);
        } catch (SQLException e) {
            LOGGER.severe("No suitable driver found for " + DBURL);
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * This method is used to get a database connection.
     * It calls the createConnection method of the ConnectionFactory object.
     *
     * @return Connection This returns a database connection.
     */
    public static Connection getConnection() {
        return singleInstance.createConnection();
    }

    /**
     * This method is used to close a database connection.
     *
     * @param connection This is the connection to be closed.
     */
    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.severe(e.getMessage());
            }
        }
    }

    /**
     * This method is used to close a Statement.
     *
     * @param statement This is the Statement to be closed.
     */
    public static void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.severe(e.getMessage());
            }
        }
    }

    /**
     * This method is used to close a ResultSet.
     *
     * @param resultSet This is the ResultSet to be closed.
     */
    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.severe(e.getMessage());
            }
        }
    }
}