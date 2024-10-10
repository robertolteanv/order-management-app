package presentation;

import bussinesslogic.ClientBLL;
import model.Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * This is the Controller class, responsible for managing the interaction between the View and the Model.
 *
 *
 */
public class Controller {
    private ClientBLL clientBLL;
    private View view;
    /**
     * This is the constructor of the Controller class.
     * It initializes the ClientBLL object and the View, and adds a listener to the add client button.
     *
     * @param view This is the View object.
     */
    public Controller(View view) {
        this.view = view;
        this.clientBLL = new ClientBLL();

        this.view.getClientPanel().addClientListener(new AddClientListener());
    }
    /**
     * This is the AddClientListener class, responsible for handling the action of adding a client.
     * It implements the ActionListener interface.
     */
    class AddClientListener implements ActionListener {
        /**
         * This method is called when the add client button is clicked.
         * It gets the client data from the input fields, creates a new client, inserts it into the database, and reloads the clients in the table.
         *
         * @param e This is the action event.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = view.getClientPanel().getNameField().getText();
            String address = view.getClientPanel().getAddressField().getText();
            String email = view.getClientPanel().getEmailField().getText();
            String phone = view.getClientPanel().getPhoneField().getText();

            Client client = new Client(0, name, address, email, phone);
            clientBLL.insertClient(client);
            view.getClientPanel().loadClients();
        }
    }
}
