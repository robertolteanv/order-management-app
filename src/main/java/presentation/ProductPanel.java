package presentation;

import bussinesslogic.ProductBLL;
import model.Client;
import model.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
/**
 * This is the ProductPanel class, responsible for managing the product panel of the application.
 * It extends JPanel.
 *
 *
 */
public class ProductPanel extends JPanel {
    private ProductBLL productBLL;
    private JTable productTable;
    private ProductTableModel productTableModel;
    private JTextField nameField, priceField, stockField;
    private Product selectedProduct;
    /**
     * This is the constructor of the ProductPanel class.
     * It initializes the ProductBLL object, sets the layout, and adds components to the panel.
     */
    public ProductPanel() {
        productBLL = new ProductBLL();
        setLayout(new BorderLayout());

        productTableModel = new ProductTableModel();
        productTable = new JTable(productTableModel);
        JScrollPane scrollPane = new JScrollPane(productTable);
        add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new GridLayout(5, 2));

        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Price:"));
        priceField = new JTextField();
        inputPanel.add(priceField);

        inputPanel.add(new JLabel("Stock:"));
        stockField = new JTextField();
        inputPanel.add(stockField);

        JButton addButton = new JButton("Add Product");
        addButton.addActionListener(new AddProductListener());
        inputPanel.add(addButton);

        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(new RefreshListener());
        inputPanel.add(refreshButton);


        JButton deleteButton = new JButton("Delete Product");
        deleteButton.addActionListener(new DeleteProductListener());
        inputPanel.add(deleteButton);

        JButton editButton = new JButton("Edit Product");
        editButton.addActionListener(new EditProductListener());
        inputPanel.add(editButton);

        add(inputPanel, BorderLayout.SOUTH);

        loadProducts();
    }
    /**
     * This method is used to load all products into the product table.
     */
    private void loadProducts() {
        List<Product> products = productBLL.findAllProducts();
        productTableModel.setProducts(products);
    }
    /**
     * This is the DeleteProductListener class, responsible for handling the action of deleting a product.
     * It implements the ActionListener interface.
     */
    private class DeleteProductListener implements ActionListener {
        /**
         * This method is called when the delete product button is clicked.
         * It gets the selected product and deletes it from the database.
         *
         * @param e This is the action event.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = productTable.getSelectedRow();
            if (selectedRow != -1) {
                int productId = (int) productTable.getValueAt(selectedRow, 0);
                productBLL.deleteProduct(productId);
                loadProducts();
            } else {
                JOptionPane.showMessageDialog(ProductPanel.this, "Please select a product to delete.");
            }
        }
    }
    /**
     * This is the AddProductListener class, responsible for handling the action of adding a product.
     * It implements the ActionListener interface.
     */
    private class AddProductListener implements ActionListener {
        /**
         * This method is called when the add product button is clicked.
         * It gets the product data from the input fields, creates a new product, and inserts it into the database.
         *
         * @param e This is the action event.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();
            double price = Double.parseDouble(priceField.getText());
            int stock = Integer.parseInt(stockField.getText());

            Product product = new Product(0, name, price, stock);
            productBLL.insertProduct(product);
            loadProducts();
        }
    }
    /**
     * This is the EditProductListener class, responsible for handling the action of editing a product.
     * It implements the ActionListener interface.
     */
    private class EditProductListener implements ActionListener {
        /**
         * This method is called when the edit product button is clicked.
         * It gets the selected product, updates its data with the data from the input fields, and updates it in the database.
         *
         * @param e This is the action event.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = productTable.getSelectedRow();
            if (selectedRow != -1) {
                int productId = (int) productTable.getValueAt(selectedRow, 0);
                selectedProduct = productBLL.findProductById(productId);
                if (selectedProduct != null) {
                    if(nameField.getText().isEmpty() || priceField.getText().isEmpty() || stockField.getText().isEmpty()){
                        nameField.setText(selectedProduct.getName());
                        priceField.setText(String.valueOf(selectedProduct.getPrice()));
                        stockField.setText(String.valueOf(selectedProduct.getStock()));
                    }
                    selectedProduct.setName(nameField.getText());
                    selectedProduct.setPrice(Double.parseDouble(priceField.getText()));
                    selectedProduct.setStock(Integer.parseInt(stockField.getText()));
                    productBLL.updateProduct(selectedProduct);
                    loadProducts();
                    selectedProduct = null;
                } else {
                    JOptionPane.showMessageDialog(ProductPanel.this, "Product not found.");
                }
            } else {
                JOptionPane.showMessageDialog(ProductPanel.this, "Please select a product to edit.");
            }
        }
    }
    /**
     * This is the RefreshListener class, responsible for handling the action of refreshing the product table.
     * It implements the ActionListener interface.
     */
    private class RefreshListener implements ActionListener {
        /**
         * This method is called when the refresh button is clicked.
         * It reloads the products in the product table.
         *
         * @param e This is the action event.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            loadProducts();
        }
    }
}
