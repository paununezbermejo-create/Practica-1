package Business.Entities;

public class Product {
    String product_id;
    String product_name;
    String brand;
    String model;

    //Constructor del product
    public Product(String product_id, String product_name, String brand, String model) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.brand = brand;
        this.model = model;
    }

    //Funcio que retorna el id del producte
    public String getProductID() {
        return this.product_id;
    }

    //Funcio que retorna el nom del producte
    public String getProductName() {
        return this.product_name;
    }

    //Funcio que retorna la marca del producte
    public String getBrand() {
        return this.brand;
    }

    //Funcio que retorna el model del producte
    public String getModel() {
        return this.model;
    }
}
