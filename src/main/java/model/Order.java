package model;
/**
 * This is the Order class, representing an order in the system.
 *
 *
 */
public class Order {
    private int id_order;
    private String buyer;
    private String ordered_products;

    // getters and setters
    public int getId_order() {
        return id_order;
    }

    public void setId_order(int id_order) {
        this.id_order = id_order;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getOrdered_products() {
        return ordered_products;
    }

    public void setOrderedProducts(String ordered_products) {
        this.ordered_products = ordered_products;
    }

    public String getOrderedProducts() {
        return ordered_products;
    }
}