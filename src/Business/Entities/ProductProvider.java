package Business.Entities;

public class ProductProvider {
    String productId;
    double price;
    int stock;
    public ProductProvider(String productId, double price, int stock) {
        this.productId = productId;
        this.price = price;
        this.stock = stock;
    }

    public String getProductId() {
        return this.productId;
    }

    public double getPrice() {
        return this.price;
    }

    public int getStock() {
        return this.stock;
    }

    public void reduceStock(int stock) {
        this.stock -= stock;
    }

    public boolean checkStock() {
        return this.stock != 0;
    }
}
