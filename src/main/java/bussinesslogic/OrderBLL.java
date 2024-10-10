package bussinesslogic;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import dataaccess.OrderDAO;
import model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 * This is the constructor of the OrderBLL class.
 * It initializes the OrderDAO object.
 */
public class OrderBLL {

    private OrderDAO orderDAO;
    /**
     * This is the constructor of the OrderBLL class.
     * It initializes the OrderDAO object.
     */
    public OrderBLL() {
        orderDAO = new OrderDAO();
    }
    /**
     * This is the constructor of the OrderBLL class.
     * It initializes the OrderDAO object.
     */
    public void insertOrder(Order order) {
        orderDAO.insertOrder(order);
    }



}
