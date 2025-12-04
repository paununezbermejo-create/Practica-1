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

    public List<Product> getProductList(){
        return productList;
    }

    public List<Product> getProductByName(String name) {
        for (Product product : productList) {
            if (product.getProductName().contains(name)) {
                return productList;
            }
        }
        return null;
    }

    public Product getProductById(String id) {
        for (Product product : productList) {
            if (product.getProductID().equals(id)) {
                return product;
            }
        }
        return null;
    }

    public boolean cheeckFile (){
        ProductJsonDao productJsonDao = new ProductJsonDao();
        return productJsonDao.cheekFile();
    }

}
