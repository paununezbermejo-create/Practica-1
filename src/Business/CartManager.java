package Business;

import Business.Entities.Cart;

import java.util.List;

public class CartManager {
    List<Cart> cart;

    public void showCartAll() {
        for (Cart cart : cart) {
            System.out.println(cart.getProductId());
        }
    }

    public Cart findCart(int id){
        double total = 0;
        for (Cart cart : cart) {
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
}
