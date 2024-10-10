package bussinesslogic;

import dataaccess.ProductDAO;
import model.Product;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * This is the ProductBLL class, responsible for business logic related to products.
 * It uses the ProductDAO class for database operations related to the Product table.
 *
 *
 */
public class ProductBLL {
    private ProductDAO productDAO;

    /**
     * This is the constructor of the ProductBLL class.
     * It initializes the ProductDAO object.
     */
    public ProductBLL() {
        productDAO = new ProductDAO();
    }

    /**
     * This method is used to find a product by its ID.
     * It calls the findById method of the ProductDAO object.
     *
     * @param id This is the ID of the product to be found.
     * @return Product This returns the product with the given ID.
     * @throws NoSuchElementException If the product with the given ID is not found.
     */
    public Product findProductById(int id) {
        Product product = productDAO.findById(id);
        if (product == null) {
            throw new NoSuchElementException("The product with id " + id + " was not found!");
        }
        return product;
    }

    /**
     * This method is used to get all products from the database.
     * It calls the selectAllProducts method of the ProductDAO object.
     *
     * @return List<Product> This returns a list of all products.
     */
    public List<Product> findAllProducts() {
        return productDAO.selectAllProducts();
    }

    /**
     * This method is used to insert a product into the database.
     * It calls the insertProduct method of the ProductDAO object.
     *
     * @param product This is the product to be inserted.
     */
    public void insertProduct(Product product) {
        productDAO.insertProduct(product);
    }

    /**
     * This method is used to delete a product from the database by its ID.
     * It calls the deleteProduct method of the ProductDAO object.
     *
     * @param id This is the ID of the product to be deleted.
     */
    public void deleteProduct(int id) {
        productDAO.deleteProduct(id);
    }

    /**
     * This method is used to update a product in the database.
     * It calls the updateProduct method of the ProductDAO object.
     *
     * @param product This is the product to be updated.
     */
    public void updateProduct(Product product) {
        productDAO.updateProduct(product);
    }
}