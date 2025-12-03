package Business;

import Business.Entities.Cart;
import Persistance.CartJsonDao;

import java.util.List;

public class CartManager {
    private CartJsonDao cartJsonDao;
    List<Cart> cart;
    public CartManager(CartJsonDao cartJsonDao) {
        this.cartJsonDao = cartJsonDao;
        cart = cartJsonDao.readCartFile();
    }

}
