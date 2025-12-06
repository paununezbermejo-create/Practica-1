package Business.Entities;

public class ProductProvider {
    String product_id;
    double selling_price;
    int units_in_stock;
    public ProductProvider(String productId, double price, int stock) {
        this.product_id = productId;
        this.selling_price = price;
        this.units_in_stock = stock;
    }

    public String getProductId() {
        return this.product_id;
    }

    public double getPrice() {
        return this.selling_price;
    }

    public int getStock() {
        return this.units_in_stock;
    }

    public void reduceStock() {
        if (checkStock()){
            this.units_in_stock -= 1;
        }
    }

    public boolean checkStock() {
        return this.units_in_stock != 0;
    }
}
