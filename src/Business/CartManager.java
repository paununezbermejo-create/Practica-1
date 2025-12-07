package Business;

import Business.Entities.Cart;

import java.util.ArrayList;
import java.util.List;

public class CartManager {
    List<Cart> carts;

    //Constructor del cartManager
    public CartManager() {
        carts = new ArrayList<>();
    }

    //Procediment que afegeix un producte nou al carret
    public void addToCart(int clientId, String productId, int providerId, double price){
        Cart cart = new Cart(clientId, productId, providerId, price);
        this.carts.add(cart);
    }

    //Funcio que retorna tots el carret dins el sistema
    public List<Cart> getCarts(){
        return this.carts;
    }

    //Funcio que retorna el id del producte dins el carret
    public String getProductId (String productId){
        //Bucle que recorre tot el carret
        for (Cart cart : this.carts){
            if (cart.getProductId().equals(productId)){
                return cart.getProductId();
            }
        }
        return null;
    }

    //Funcio que retorna el id del producte dins el carret
    public int getClientId (int id){
        //Bucle que recorre tot el carret
        for (Cart cart : this.carts){
            if (cart.getClientId() == id){
                return cart.getClientId();
            }
        }
        return 0;
    }

    //Funcio que retorna el id del proveidor dins el carret
    public int getProviderId (int id){
        //Bucle que recorre tot el carret
        for (Cart cart : this.carts){
            if (cart.getProviderId() == id){
                return cart.getProviderId();
            }
        }
        return 0;
    }

    //Funcio que retorna el preu d'un producte dins el carret
    public double getPrice (int id){
        //Bucle que recorre tot el carret
        for (Cart cart : this.carts){
            if (cart.getPrice() == id){
                return cart.getPrice();
            }
        }
        return 0;
    }

    //Procediment que mosotra tots els productes dins el carret
    public void showCartAll() {
        //Bucle que recorre tot el carret
        for (Cart cart : carts) {
            System.out.println(cart.getProductId());
        }
    }

    //Funcio que retorna el carret buscat segons el id
    public Cart findCart(int id){
        double total = 0;
        //Bucle que recorre tot el carret
        for (Cart cart : carts) {
            if(id == cart.getClientId()){
                return cart;
            }
        }
        return null;
    }

    //Procediment que elimina tots els carrets al sortir de la sessio
    public void clearCarts() {
        carts.clear();
    }

}
