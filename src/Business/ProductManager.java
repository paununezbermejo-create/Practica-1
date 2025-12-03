package Business;

import Business.Entities.Product;
import Persistance.ProductJsonDao;

import java.util.List;

public class ProductManager {
    private List<Product> productList;
    private ProductJsonDao productJsonDao;

    public ProductManager(ProductJsonDao productJsonDao) {
        this.productJsonDao = new ProductJsonDao();
        productList = productJsonDao.readFile();
    }


}
