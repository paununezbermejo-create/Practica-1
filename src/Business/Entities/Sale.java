package Business.Entities;

public class Sale {
    int clientId;
    String productId;
    double price;
    long purchaseDate;

    public Sale(int clientId, String productId, double price, long purchaseDate) {
        this.clientId = clientId;
        this.productId = productId;
        this.price = price;
        this.purchaseDate = purchaseDate;
    }

    public void setClientId(int clientId) {}
    public int getClientId() {
        return clientId;
    }
    public void setProductId(String productId) {}
    public String  getProductId() {
        return productId;
    }
    public void setPrice(double price) {}
    public double getPrice() {
        return price;
    }
    public void setPurchaseDate(long purchaseDate) {}
    public long getPurchaseDate() {
        return purchaseDate;
    }

}
