package Business;

import Business.Entities.Cart;

import java.util.ArrayList;
import java.util.List;

public class CartManager {
    List<Cart> carts;

    public CartManager() {
        carts = new ArrayList<>();
    }

    public void addToCart(int clientId, String productId, int providerId, double price){
        Cart cart = new Cart(clientId, productId, providerId, price);
        this.carts.add(cart);
    }

    public List<Cart> getCarts(){
        return this.carts;
    }

    public String getProductId (String productId){
        for (Cart cart : this.carts){
            if (cart.getProductId().equals(productId)){
                return cart.getProductId();
            }
        }
        return null;
    }

    public int getClientId (int id){
        for (Cart cart : this.carts){
            if (cart.getClientId() == id){
                return cart.getClientId();
            }
        }
        return 0;
    }
    public int getProviderId (int id){
        for (Cart cart : this.carts){
            if (cart.getProviderId() == id){
                return cart.getProviderId();
            }
        }
        return 0;
    }

    public double getPrice (int id){
        for (Cart cart : this.carts){
            if (cart.getPrice() == id){
                return cart.getPrice();
            }
        }
        return 0;
    }
    public void showCartAll() {
        for (Cart cart : carts) {
            System.out.println(cart.getProductId());
        }
    }

    public Cart findCart(int id){
        double total = 0;
        for (Cart cart : carts) {
            if(id == cart.getClientId()){
                //Retornar cart
                return cart;
            }
        }
        return null;
    }

    public void addToCart(int clientId, String productId, int providerId, float price){
        Cart cart = new Cart(clientId, productId, providerId, price);
    }

    public void clearCarts() {
        carts.clear();
    }

}
