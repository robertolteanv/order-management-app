package presentation;

import model.Client;

import javax.swing.table.AbstractTableModel;
import java.util.List;
/**
 * This is the ClientTableModel class, responsible for managing the data of clients in a table format.
 * It extends AbstractTableModel.
 *
 *
 */
public class ClientTableModel extends AbstractTableModel {
    private List<Client> clients;
    private final String[] columnNames = {"ID", "Name", "Address", "Email", "Phone"};
    /**
     * This is the constructor of the ClientTableModel class.
     *
     * @param clients This is the list of clients to be displayed in the table.
     */
    public ClientTableModel(List<Client> clients) {
        this.clients = clients;
    }
    /**
     * This is the default constructor of the ClientTableModel class.
     */
    public ClientTableModel() {

    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return clients == null ? 0 : clients.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    /**
     * This method is used to get the value at a specific cell in the table.
     *
     * @param rowIndex This is the row index of the cell.
     * @param columnIndex This is the column index of the cell.
     * @return Object This returns the value at the specified cell.
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (clients == null) {
            return null;
        }
        Client client = clients.get(rowIndex);
        switch (columnIndex) {
            case 0: return client.getId();
            case 1: return client.getName();
            case 2: return client.getAddress();
            case 3: return client.getEmail();
            case 4: return client.getPhone();
        }
        return null;
    }
    /**
     * This method is used to get the name of a column.
     *
     * @param column This is the index of the column.
     * @return String This returns the name of the column.
     */
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}
