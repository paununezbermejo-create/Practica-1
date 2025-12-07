package Business;

import Business.Entities.Product;
import Persistance.ProductJsonDao;

import java.util.ArrayList;
import java.util.List;

public class ProductManager {
    private List<Product> productList;
    private ProductJsonDao productJsonDao;

    //Constructor del productManager
    public ProductManager(ProductJsonDao productJsonDao) {
        this.productJsonDao = new ProductJsonDao();
        productList = productJsonDao.readFile();
    }

    //Funcio que retorna tots els productes guardats dins el programa
    public List<Product> getProductList(){
        return productList;
    }

    //Funcio que busca el producte pel nom
    public List<Product> getProductByName(String name) {
        List<Product> p = new ArrayList<>();
        //Bucle que recorre tot el product
        for (Product product : productList) {
            if (product.getProductName().contains(name)) {
                p.add(product);
            }
        }
        return p;
    }

    //Funcio que busca un producte per el seu id
    public Product getProductById(String id) {
        //Bucle que recorre tot el product
        for (Product product : productList) {
            if (product.getProductID().equals(id)) {
                return product;
            }
        }
        return null;
    }

    //Funcio que retorna la comprovacio de que el fitxer existeix
    public boolean cheeckFile (){
        ProductJsonDao productJsonDao = new ProductJsonDao();
        return productJsonDao.cheekFile();
    }

}
