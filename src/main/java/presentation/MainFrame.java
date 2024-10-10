package presentation;

import javax.swing.*;
import java.awt.*;
/**
 * This is the MainFrame class, responsible for creating the main frame of the application.
 * It extends JFrame.
 *
 */
public class MainFrame extends JFrame {
    /**
     * This is the constructor of the MainFrame class.
     * It sets the title, size, default close operation, and location of the frame, and adds a tabbed pane to it.
     */
    public MainFrame() {
        setTitle("Orders Management");
        setSize(1000, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Clients", new ClientPanel());
        tabbedPane.addTab("Products", new ProductPanel());

        add(tabbedPane, BorderLayout.CENTER);
    }
}
