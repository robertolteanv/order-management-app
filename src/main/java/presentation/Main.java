package presentation;

import javax.swing.*;
/**
 * This is the Main class, responsible for starting the application.
 *
 *
 */
public class Main {
    /**
     * This is the main method, the entry point of the application.
     * It creates and displays the main frame of the application.
     *
     * @param args This is an array of command-line arguments.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}
