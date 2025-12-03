package Business.Entities;

public class Cart {
    int client_id;
    String product_id;
    int provider_id;
    double price;

    public Cart(int client_id, String product_id, int provider_id, double price) {
        this.client_id = client_id;
        this.product_id = product_id;
        this.provider_id = provider_id;
        this.price = price;
    }

    public String getProductId() {
        return this.product_id;
    }

    public int getClientId() {
        return this.client_id;
    }

    public int getProviderId() {
        return this.provider_id;
    }

    public double getPrice() {
        return this.price;
    }

}
