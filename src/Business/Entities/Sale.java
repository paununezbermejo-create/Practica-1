package Business.Entities;

public class Sale {
    int clientId;
    String productId;
    double price;
    long purchaseDate;

    //Constructor del sale
    public Sale(int clientId, String productId, double price, long purchaseDate) {
        this.clientId = clientId;
        this.productId = productId;
        this.price = price;
        this.purchaseDate = purchaseDate;
    }

    //Funcio que coloca el id del client de la venta
    public void setClientId(int clientId) {}

    //Funcio que retorna el id del client de la venta
    public int getClientId() {
        return clientId;
    }

    //Funcio que coloca el id del producte de la venta
    public void setProductId(String productId) {}

    //Funcio que retorna el id del producte de la venta
    public String  getProductId() {
        return productId;
    }

    //Funcio que coloca el preu de la venta
    public void setPrice(double price) {}

    //Funcio que retorna el preu de la venta
    public double getPrice() {
        return price;
    }

    //Funcio que coloca el dia de compra de la venta
    public void setPurchaseDate(long purchaseDate) {}

    //Funcio que retorna el dia de compra de la venta
    public long getPurchaseDate() {
        return purchaseDate;
    }

}
