package presentation;

import bussinesslogic.ClientBLL;
import bussinesslogic.ProductBLL;
import bussinesslogic.OrderBLL;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import model.Client;
import model.Order;
import model.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
/**
 * This is the OrderPanel class, responsible for managing the order panel of the application.
 * It extends JPanel.
 *
 */
public class OrderPanel extends JPanel {
    private ClientBLL clientBLL;
    private ProductBLL productBLL;
    private JComboBox<Client> clientComboBox;
    private JComboBox<Product> productComboBox;
    private JTextField quantityField;
    private OrderBLL orderBLL;
    /**
     * This is the constructor of the OrderPanel class.
     * It initializes the BLL objects, sets the layout, and adds components to the panel.
     */
    public OrderPanel() {

        orderBLL = new OrderBLL();
        clientBLL = new ClientBLL();
        productBLL = new ProductBLL();
        setLayout(new GridLayout(5, 2));

        add(new JLabel("Client:"));
        clientComboBox = new JComboBox<>();
        loadClients();
        add(clientComboBox);

        add(new JLabel("Product:"));
        productComboBox = new JComboBox<>();
        loadProducts();
        add(productComboBox);

        add(new JLabel("Quantity:"));
        quantityField = new JTextField();
        add(quantityField);

        JButton orderButton = new JButton("Order");
        orderButton.addActionListener(new OrderListener());
        add(orderButton);

        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(new RefreshListener());
        add(refreshButton);

        //add(new JLabel(" "));
        add(new JLabel(" "));
        add(new JLabel(" "));



    }
    /**
     * This method is used to load all clients into the client combo box.
     */
    private void loadClients() {
        List<Client> clients = clientBLL.findAllClients();
        for (Client client : clients) {
            clientComboBox.addItem(client);
        }
    }
    /**
     * This method is used to load all products into the product combo box.
     */
    private void loadProducts() {
        List<Product> products = productBLL.findAllProducts();
        for (Product product : products) {
            productComboBox.addItem(product);
        }
    }
    /**
     * This is the OrderListener class, responsible for handling the action of ordering a product.
     * It implements the ActionListener interface.
     */
    private class OrderListener implements ActionListener {
        /**
         * This method is called when the order button is clicked.
         * It gets the selected client and product, and the quantity from the input field, checks if the product is in stock,
         * updates the product's stock, creates a new order, and inserts it into the database.
         *
         * @param e This is the action event.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            Client client = (Client) clientComboBox.getSelectedItem();
            Product product = (Product) productComboBox.getSelectedItem();
            int quantity = Integer.parseInt(quantityField.getText());

            if (product.getStock() < quantity || quantity < 0) {
                JOptionPane.showMessageDialog(OrderPanel.this, "Not enough products in stock.");
            } else {
                product.setStock(product.getStock() - quantity);
                productBLL.updateProduct(product);

                Order order = new Order(); // Use your own Order class
                order.setBuyer(client.getName());
                order.setOrderedProducts(product.getName() + " x " + quantity);
                orderBLL.insertOrder(order);

                JOptionPane.showMessageDialog(OrderPanel.this, "Order created successfully.");
            }
        }
    }
    /**
     * This is the RefreshListener class, responsible for handling the action of refreshing the client and product combo boxes.
     * It implements the ActionListener interface.
     */
    private class RefreshListener implements ActionListener {
        /**
         * This method is called when the refresh button is clicked.
         * It removes all items from the client and product combo boxes, and reloads the clients and products.
         *
         * @param e This is the action event.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            clientComboBox.removeAllItems();
            loadClients();
            productComboBox.removeAllItems();
            loadProducts();
        }
    }


}