package presentation;

import model.Product;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
/**
 * This is the ProductTableModel class, responsible for managing the product table model of the application.
 * It extends AbstractTableModel.
 *

 */
public class ProductTableModel extends AbstractTableModel {
    private final String[] columnNames = {"ID", "Name", "Price", "Stock"};
    private List<Product> products;
    /**
     * This is the constructor of the ProductTableModel class.
     * It initializes the products list.
     */
    public ProductTableModel() {
        products = new ArrayList<>();
    }

    public void setProducts(List<Product> products) {
        this.products = products;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return products.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    /**
     * This method is used to get the value at a specific cell in the table.
     *
     * @param rowIndex This is the index of the row.
     * @param columnIndex This is the index of the column.
     * @return Object This is the value at the specified cell.
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Product product = products.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return product.getId();
            case 1:
                return product.getName();
            case 2:
                return product.getPrice();
            case 3:
                return product.getStock();
            default:
                return null;
        }
    }
}
