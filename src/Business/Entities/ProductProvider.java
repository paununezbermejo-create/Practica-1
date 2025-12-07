package Business.Entities;

public class ProductProvider {
    String product_id;
    double selling_price;
    int units_in_stock;

    //Constructor del productProvider
    public ProductProvider(String productId, double price, int stock) {
        this.product_id = productId;
        this.selling_price = price;
        this.units_in_stock = stock;
    }

    //Funcio que retorna el id del producte
    public String getProductId() {
        return this.product_id;
    }

    //Funcio que retorna el preu del producte
    public double getPrice() {
        return this.selling_price;
    }

    //Funcio que retorna el stock del producte
    public int getStock() {
        return this.units_in_stock;
    }

    //Funcio que redueix el stock del producte
    public void reduceStock() {
        if (checkStock()){
            this.units_in_stock -= 1;
        }
    }

    //Funcio que comprova que el producte tingui stock
    public boolean checkStock() {
        return this.units_in_stock != 0;
    }
}
