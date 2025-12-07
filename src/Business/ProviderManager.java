package Business;

import Business.Entities.Product;
import Business.Entities.ProductProvider;
import Business.Entities.Provider;
import Persistance.ProviderJsonDao;

import java.util.ArrayList;
import java.util.List;

public class ProviderManager {
    private ProviderJsonDao providerJsonDao;
    private List<Provider> providers;

    //Constructor del providerManager
    public ProviderManager(ProviderJsonDao providerJsonDao) {
        this.providerJsonDao = new ProviderJsonDao();
        this.providers = providerJsonDao.readFile();
    }

    //Funcio que retorna tots els proveidors que hi han dins el programa
    public List<Provider> getProviders() {
        return providers;
    }

    //Funcio que retorna tots els proveidors a partir del producte
    public List<Provider> getProvidersByProduct(Product product) {
        List<Provider> providerList = new ArrayList<>();
        //Bucle que recorre tot el provider
        for (Provider provider : providers) {
            //Bucle que recorre tots dels productes de cada provider
            for(ProductProvider p : provider.getProductProviders()) {
                if (p.getProductId().equals(product.getProductID())){
                    providerList.add(provider);
                }
            }
        }
        return providerList;
    }

    //Funcio que retorna tots els productes d'un provider
    public List<ProductProvider> getProductsProvider(int idProvider) {

        List<ProductProvider> productProviderList = new ArrayList<>();
        //Bucle que recorre tot el provider
        for  (Provider provider : providers) {
            if(idProvider == provider.getProviderId()) {
                productProviderList = provider.getProductProviders();
                break;
            }
        }
            return  productProviderList;
    }

    //Funcio que retorna el preu de un producte
    public double getPrice (int idProvider, String product_id) {
        //Bucle que recorre tot el provider
        for (Provider provider : providers) {
            if (provider.getProviderId() == idProvider) {
                //Bucle que recorre tots dels productes de cada provider
                for (ProductProvider productProvider : provider.getProductProviders()) {
                    if (productProvider.getProductId().equals(product_id)) {
                        return productProvider.getPrice()*1.21;
                    }
                }
            }
        }
        return 0;
    }

    //Procediment que modifica la quantitat de stock d'un producte
    public void reduceStock (int idProvider, String product_id) {
        if (cheekStock(idProvider, product_id)){
            //Bucle que recorre tot el provider
            for  (Provider provider : providers) {
                if (provider.getProviderId() == idProvider) {
                    //Bucle que recorre tots dels productes de cada provider
                    for (ProductProvider productProvider : provider.getProductProviders()) {
                        if (productProvider.getProductId().equals(product_id)) {
                            productProvider.reduceStock();
                        }
                    }
                }
            }
        }
    }

    //Funcio que revisa si hi ha stock d'un producte
    public Boolean cheekStock (int idProvider, String product_id) {
        //Bucle que recorre tot el provider
        for (Provider provider : providers) {
            if (provider.getProviderId() == idProvider) {
                //Bucle que recorre tots dels productes de cada provider
                for (ProductProvider productProvider : provider.getProductProviders()) {
                    if (productProvider.getProductId().equals(product_id)) {
                        return productProvider.checkStock();
                    }

                }
            }
        }
        return false;
    }

    //Procediment que actualitza el fitxer amb la nova informacio
    public void actualizaStockFile (){
        providerJsonDao.actualizaStock(this.providers);
    }

    //Funcio que retorna la comprovacio de que el fitxer existeix
    public boolean cheeckFile(){
        ProviderJsonDao providerJsonDao = new ProviderJsonDao();
        return providerJsonDao.cheekFile();
    }
}
