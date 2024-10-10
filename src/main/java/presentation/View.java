package presentation;

import javax.swing.*;
import java.awt.*;
/**
 * This is the View class, responsible for creating the main view of the application.
 * It contains panels for clients, products, and orders.
 *

 */
public class View {

    private ClientPanel clientPanel;
    private ProductPanel productPanel;
    private OrderPanel orderPanel;
    /**
     * This is the constructor of the View class.
     * It creates the main frame, adds tabs for clients, products, and orders to it, and initializes the controller.
     */
    public View() {
        // Create the main frame
        JFrame frame = new JFrame("Orders Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 450);
        frame.setLayout(new BorderLayout());

        // Create tabs for clients, products, and orders
        JTabbedPane tabbedPane = new JTabbedPane();
        clientPanel = new ClientPanel();
        productPanel = new ProductPanel();
        orderPanel = new OrderPanel();
        tabbedPane.addTab("Clients", clientPanel);
        tabbedPane.addTab("Products", productPanel);
        tabbedPane.addTab("Orders", orderPanel);

        // Add tabbedPane to the frame
        frame.add(tabbedPane, BorderLayout.CENTER);

        // Display the frame
        frame.setVisible(true);

        // Initialize controller
        new Controller(this);
    }

    public ClientPanel getClientPanel() {
        return clientPanel;
    }

    public ProductPanel getProductPanel() {
        return productPanel;
    }

    public OrderPanel getOrderPanel() {
        return orderPanel;
    }
    /**
     * This is the main method, which is used to run the GUI in the Event Dispatch Thread (EDT).
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
        // Run the GUI in the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(View::new);
    }
}
