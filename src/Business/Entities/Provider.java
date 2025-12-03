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

    public Provider(int provider_id, String company_name, String cif, String contact_name, String phone, String email, ArrayList<ProductProvider> products_for_sale) {
        this.provider_id = provider_id;
        this.company_name = company_name;
        this.cif = cif;
        this.contact_name = contact_name;
        this.phone = phone;
        this.email = email;
        this.products_for_sale = products_for_sale;
    }

    public int getProviderId() {
        return this.provider_id;
    }
    public String getCompanyName() {
        return this.company_name;
    }
    public String getCif() {
        return this.cif;
    }
    public String getContactName() {
        return this.contact_name;
    }
    public String getPhone() {
        return this.phone;
    }
    public String getEmail() {
        return this.email;
    }
    public ArrayList<ProductProvider> getProductProviders() {
        return this.products_for_sale;
    }

    public void reduceStock(String id, int quantity) {
        for (ProductProvider p : this.products_for_sale) {
            if (p.getProductId().equals(id)) {
                if (p.checkStock()){
                    p.reduceStock(quantity);
                    break;
                }
            }
        }
    }

    public ProductProvider getPtoductById(String id){
        for (ProductProvider p : this.products_for_sale) {
            if (p.getProductId().equals(id)) {
                return p;
            }
        }
        return null;
    }
}
