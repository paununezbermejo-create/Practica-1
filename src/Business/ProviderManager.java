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

    public ProviderManager(ProviderJsonDao providerJsonDao) {
        this.providerJsonDao = new ProviderJsonDao();
        this.providers = providerJsonDao.readFile();
    }

    public List<Provider> getProviders() {
        return providers;
    }

    public List<Provider> getProvidersByProduct(Product product) {
        List<Provider> providerList = new ArrayList<>();
        for (Provider provider : providers) {
            if (provider.getProductProviders().contains(product)) {
                providerList.add(provider);
            }
        }
        return providerList;
    }

    public List<ProductProvider> getProductsProvider(int idProvider) {

        List<ProductProvider> productProviderList = new ArrayList<>();
        for  (Provider provider : providers) {
            if(idProvider == provider.getProviderId()) {
                productProviderList = provider.getProductProviders();
                break;
            }
        }
            return  productProviderList;
    }

    public double getPrice (int idProvider, String product_id) {
        for (Provider provider : providers) {
            if (provider.getProviderId() == idProvider) {
                for (ProductProvider productProvider : provider.getProductProviders()) {
                    if (productProvider.getProductId().equals(product_id)) {
                        return productProvider.getPrice();
                    }
                }
            }
        }
        return 0;
    }

    public int getStock (int idProvider, String product_id) {
        for (Provider provider : providers) {
            if (provider.getProviderId() == idProvider) {
                for (ProductProvider productProvider : provider.getProductProviders()) {
                    if (productProvider.getProductId().equals(product_id)) {
                        return productProvider.getStock();
                    }
                }
            }
        }
        return 0;
    }

    public void reduceStock (int idProvider, String product_id, int quantity) {
        for  (Provider provider : providers) {
            if (provider.getProviderId() == idProvider) {
                for (ProductProvider productProvider : provider.getProductProviders()) {
                    if (productProvider.getProductId().equals(product_id)) {
                        productProvider.reduceStock(quantity);
                    }
                }
            }
        }
    }

    public Boolean cheekStock (int idProvider, String product_id) {
        for (Provider provider : providers) {
            if (provider.getProviderId() == idProvider) {
                for (ProductProvider productProvider : provider.getProductProviders()) {
                    if (productProvider.getProductId().equals(product_id)) {
                        return productProvider.checkStock();
                    }

                }
            }
        }
        return false;
    }

    public boolean cheeckFile(){
        ProviderJsonDao providerJsonDao = new ProviderJsonDao();
        return providerJsonDao.cheekFile();
    }
}
