package Business.Entities;

public class Product {
    String product_id;
    String product_name;
    String brand;
    String model;

    public Product(String product_id, String product_name, String brand, String model) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.brand = brand;
        this.model = model;
    }

    public String getProductID() {
        return this.product_id;
    }

    public String getProductName() {
        return this.product_name;
    }

    public String getBrand() {
        return this.brand;
    }

    public String getModel() {
        return this.model;
    }
}
