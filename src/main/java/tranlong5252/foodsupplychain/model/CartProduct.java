package tranlong5252.foodsupplychain.model;

public class CartProduct {
    private int id;
    private Product product;
    private int quantity;
    private double subtotal;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.subtotal = product.getPrice() * quantity;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public CartProduct(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.subtotal = product.getPrice() * quantity;
    }
}
