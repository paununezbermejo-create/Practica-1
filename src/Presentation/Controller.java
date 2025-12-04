package Presentation;

import Business.*;
import Business.Entities.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private ClientManager cm;
    private ProductManager pm;
    private ProviderManager providerManager;
    private SaleManager sm;
    private CartManager cmart;
    private ConsoleUIManager ui;

    public Controller(ClientManager cm, ProductManager pm, ProviderManager providerManager, SaleManager sm, CartManager cmart, UIManager ui) {
        this.cm = cm;
        this.pm = pm;
        this.providerManager = providerManager;
        this.sm = sm;
        this.cmart = cmart;
    }

    public void Start(){
        boolean active_1 = true;
        boolean active_2 = true;
        int id = 0, op = 0, op_2 = 0;
        List <Product> p = new ArrayList<>();
        List <Provider> pv = new ArrayList<>();
        List <ProductProvider> pmv = new ArrayList<>();
        ProductProvider pmv_2 = null;
        List <Sale> sale = new ArrayList<>();
        if (providerManager.cheeckFile() &&  pm.cheeckFile()){
            do {
                switch (ui.getMenu1()){
                    case Login:
                        do{
                            id = ui.recuestIdLogin();
                            if (cm.logIn(id) == null){
                                ui.showErrorIDNotFound();
                            }
                        }while (cm.logIn(id) == null);
                        do{
                            switch (ui.getMenu2()){
                                case User_Profile:
                                    ui.showClientProfile(cm.getClientById(id), sm.getSales());
                                    break;
                                case Find_Products_By_Name:
                                    p = pm.getProductByName(ui.recuestProductKeyWord());
                                    ui.showListProducts(p);
                                    op = ui.recuestOption();
                                    if (op != 0){
                                        pv = providerManager.getProvidersByProduct(p.get(op));
                                        ui.showListProviders(pv);
                                        if (op_2 != 0){

                                        }
                                    }
                                    break;
                                case Find_Products_By_Provider:
                                    pv = providerManager.getProviders();
                                    ui.showListProviders(pv);
                                    op = ui.recuestOption();
                                    if (op != 0){
                                        pmv = providerManager.getProductsProvider(pv.get(op).getProviderId());
                                        p = pm.getProductList();
                                        ui.showListlProductsProvider(pmv,p);
                                        op_2 = ui.recuestOption();
                                        if (op_2 != 0){
                                            cmart.addToCart(id, p.get(op_2).getProductID(), pv.get(op).getProviderId(), providerManager.getPrice(pv.get(op).getProviderId(), p.get(op_2).getProductID()));
                                        }
                                    }
                                    break;
                                case Shopping_Cart:
                                    break;
                                case Exit:
                                    active_2 = false;
                                    break;
                            }
                        }while (active_2);
                        break;
                    case Sign_up:
                        do{
                            switch (ui.getMenu2()){

                            }
                        }while (active_2);
                        break;
                    case Exit:
                        active_1 = false;
                        break;
                }
            }while (active_1);
        }else{
            if (!providerManager.cheeckFile()){
                ui.showErrorMessageFileProvider();
            }
            if (!pm.cheeckFile()){
                ui.showErrorMessageFileProduct();
            }
        }

    }
}
