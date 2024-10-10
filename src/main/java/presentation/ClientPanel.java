package presentation;

import bussinesslogic.ClientBLL;
import model.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
/**
 * This is the ClientPanel class, responsible for creating the client panel in the GUI.
 * It extends JPanel.
 *
 *
 */
public class ClientPanel extends JPanel {
    private ClientBLL clientBLL;
    private JTable clientTable;
    private ClientTableModel clientTableModel;
    private JTextField nameField, addressField, emailField, phoneField;
    private JButton addButton;
    private Client selectedClient;
    /**
     * This is the constructor of the ClientPanel class.
     * It initializes the ClientBLL object, sets the layout, creates the table and input panel, and loads the clients.
     */
    public ClientPanel() {
        clientBLL = new ClientBLL();
        setLayout(new BorderLayout());

        clientTableModel = new ClientTableModel();
        clientTable = new JTable(clientTableModel);
        JScrollPane scrollPane = new JScrollPane(clientTable);
        add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new GridLayout(6, 2));


        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Address:"));
        addressField = new JTextField();
        inputPanel.add(addressField);

        inputPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        inputPanel.add(emailField);

        inputPanel.add(new JLabel("Phone:"));
        phoneField = new JTextField();
        inputPanel.add(phoneField);

        addButton = new JButton("Add Client");
        //addButton.addActionListener(new addClientListener());
        inputPanel.add(addButton);

        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(new RefreshListener());
        inputPanel.add(refreshButton);

        JButton deleteButton = new JButton("Delete Client");
        deleteButton.addActionListener(new DeleteClientListener());
        inputPanel.add(deleteButton);

        JButton editButton = new JButton("Edit Client");
        editButton.addActionListener(new EditClientListener());
        inputPanel.add(editButton);

        add(inputPanel, BorderLayout.SOUTH);

        loadClients();
    }
    /**
     * This method is used to load all clients into the table.
     */
    public void loadClients() {
        List<Client> clients = clientBLL.findAllClients();
        ClientTableModel model = new ClientTableModel(clients);
        clientTable.setModel(model);
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JTextField getAddressField() {
        return addressField;
    }

    public JTextField getEmailField() {
        return emailField;
    }

    public JTextField getPhoneField() {
        return phoneField;
    }
    /**
     * This method is used to add a listener to the add client button.
     *
     * @param listenForAddButton This is the listener for the add client button.
     */
    public void addClientListener(ActionListener listenForAddButton) {
        addButton.addActionListener(listenForAddButton);
    }

    /**
     * This is the AddClientListener class, responsible for adding a client to the table.
     * It implements the ActionListener interface.
     *
     * @param e This is the action event.
     * @return void This method returns nothing.
     * @see ActionListener
     * @see Client
     * @see ClientBLL
     */
    private class EditClientListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = clientTable.getSelectedRow();
            if (selectedRow != -1) {
                int clientId = (int) clientTable.getValueAt(selectedRow, 0);
                selectedClient = clientBLL.findClientById(clientId);
                if (selectedClient != null) {
                    if(nameField.getText().isEmpty() || addressField.getText().isEmpty() || emailField.getText().isEmpty() || phoneField.getText().isEmpty()){
                    nameField.setText(selectedClient.getName());
                    addressField.setText(selectedClient.getAddress());
                    emailField.setText(selectedClient.getEmail());
                    phoneField.setText(selectedClient.getPhone());
                    }
                    selectedClient.setName(nameField.getText());
                    selectedClient.setAddress(addressField.getText());
                    selectedClient.setEmail(emailField.getText());
                    selectedClient.setPhone(phoneField.getText());
                    clientBLL.updateClient(selectedClient);
                    loadClients();
                    selectedClient = null;
                } else {
                    JOptionPane.showMessageDialog(ClientPanel.this, "Client not found.");
                }
            } else {
                JOptionPane.showMessageDialog(ClientPanel.this, "Please select a client to edit.");
            }
        }
    }

    /**
     * This is the DeleteClientListener class, responsible for deleting a client from the table.
     * It implements the ActionListener interface.
     *
     * @param e This is the action event.
     * @return void This method returns nothing.
     */
    private class DeleteClientListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = clientTable.getSelectedRow();
            if (selectedRow != -1) {
                int clientId = (int) clientTable.getValueAt(selectedRow, 0);
                clientBLL.deleteClient(clientId);
                loadClients();
            } else {
                JOptionPane.showMessageDialog(ClientPanel.this, "Please select a client to delete.");
            }
        }
    }

    /**
     * This is the RefreshListener class, responsible for refreshing the clients in the table.
     * It implements the ActionListener interface.
     *
     * @param e This is the action event.
     * @return void This method returns nothing.
     * @see ActionListener
     */
    private class RefreshListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            loadClients();
        }
    }
}