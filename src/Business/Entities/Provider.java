package Business.Entities;

import java.util.ArrayList;

public class Provider {
    int provider_id;
    String company_name;
    String cif;
    String contact_name;
    String phone;
    String email;
    ArrayList<ProductProvider> products_for_sale;

    //Constructor del provider
    public Provider(int provider_id, String company_name, String cif, String contact_name, String phone, String email, ArrayList<ProductProvider> products_for_sale) {
        this.provider_id = provider_id;
        this.company_name = company_name;
        this.cif = cif;
        this.contact_name = contact_name;
        this.phone = phone;
        this.email = email;
        this.products_for_sale = products_for_sale;
    }

    //Funcio que retorna el id del proveidor
    public int getProviderId() {
        return this.provider_id;
    }

    //Funcio que retorna el nom de la companyia del proveidor
    public String getCompanyName() {
        return this.company_name;
    }

    //Funcio que retorna el Cif del proveidor
    public String getCif() {
        return this.cif;
    }

    //Funcio que retorna el nom de contacte del proveidor
    public String getContactName() {
        return this.contact_name;
    }

    //Funcio que retorna el telefon del proveidor
    public String getPhone() {
        return this.phone;
    }

    //Funcio que retorna el email del proveidor
    public String getEmail() {
        return this.email;
    }

    //Funcio que retorna els productes del proveidor
    public ArrayList<ProductProvider> getProductProviders() {
        return this.products_for_sale;
    }

    ////Funcio que retorna el producte del proveidor segons el id
    public ProductProvider getProductById(String id){
        for (ProductProvider p : this.products_for_sale) {
            if (p.getProductId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    //Funcio que comprova el stock del producte
    public boolean cheekStock (String product_id) {
        if (getProductById(product_id).checkStock()) {
            return true;
        }else{
            return false;
        }
    }

    //Funcio que redueix el stock del producte
    public void reduceStock(String id) {
        if (cheekStock(id)) {
            getProductById(id).reduceStock();
        }
    }


}
