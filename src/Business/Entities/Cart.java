package Business.Entities;

public class Cart {
    int client_id;
    String product_id;
    int provider_id;
    double price;

    //Constuctor del cart
    public Cart(int client_id, String product_id, int provider_id, double price) {
        this.client_id = client_id;
        this.product_id = product_id;
        this.provider_id = provider_id;
        this.price = price;
    }

    //Funcio que retorna el id del producte
    public String getProductId() {
        return this.product_id;
    }

    //Funcio que retorna el id del client
    public int getClientId() {
        return this.client_id;
    }

    //Funcio que retorna el id del proveidor
    public int getProviderId() {
        return this.provider_id;
    }

    //Funcio que retorna el preu del producte
    public double getPrice() {
        return this.price;
    }

}
