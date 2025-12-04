package Business;

import Business.Entities.Cart;

import java.util.List;

public class CartManager {
    List<Cart> cart;
    public void addToCart(int clientId, String productId, int providerId, double price){
        Cart cart = new Cart(clientId, productId, providerId, price);
        this.cart.add(cart);
    }
    public String getProductId (String productId){
        for (Cart cart : this.cart){
            if (cart.getProductId().equals(productId)){
                return cart.getProductId();
            }
        }
        return null;
    }

    public int getClientId (int id){
        for (Cart cart : this.cart){
            if (cart.getClientId() == id){
                return cart.getClientId();
            }
        }
        return 0;
    }
    public int getProviderId (int id){
        for (Cart cart : this.cart){
            if (cart.getProviderId() == id){
                return cart.getProviderId();
            }
        }
        return 0;
    }

    public double getPrice (int id){
        for (Cart cart : this.cart){
            if (cart.getPrice() == id){
                return cart.getPrice();
            }
        }
        return 0;
    }
}
