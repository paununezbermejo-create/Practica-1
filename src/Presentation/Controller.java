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

    public Controller(ClientManager cm, ProductManager pm, ProviderManager providerManager,
                      SaleManager sm, CartManager cmart, ConsoleUIManager uiManager) {
        this.cm = cm;
        this.pm = pm;
        this.providerManager = providerManager;
        this.sm = sm;
        this.cmart = cmart;
        this.ui = uiManager;
    }

    public void Start() {
        boolean active_1 = true;
        int id = 0;

        ui.showPresentationMessage();
        if (providerManager.cheeckFile() && pm.cheeckFile()) {
            ui.showValidFiles();
            do {
                switch (ui.getMenu1()) {

                    case Login:
                        id = loginUser();
                        ui.showWelcomeMessage(cm.getNameById(id));
                        startMenu2(id);
                        break;

                    case Sign_up:
                        id = signUpUser();
                        ui.showWelcomeMessage(cm.getNameById(id));
                        startMenu2(id);
                        break;

                    case Exit:
                        active_1 = false;
                        break;
                }

            } while (active_1);

        } else {
            if (!providerManager.cheeckFile()) ui.showErrorMessageFileProvider();
            if (!pm.cheeckFile()) ui.showErrorMessageFileProduct();
            ui.showShuttingDown();
        }
    }

    private int loginUser() {
        int id;
        do {
            id = ui.recuestIdLogin();
            if (cm.logIn(id) == null) {
                ui.showErrorIDNotFound();
            }
        } while (cm.logIn(id) == null);

        return id;
    }

    private int signUpUser() {

        String name = ui.recuetSignInName();

        List <PhoneNumber> phones = new ArrayList<>();
        int i = 1;
        int x = ui.recuestNumPhones();
        for (int j = 0; j < x; j++){
            phones.add(ui.recuestPhoneNumber());
        }
        while (cm.getClientById(i) != null){
            i++;
        }
        cm.signUp(i, name, phones);
        return i;
    }

    private void startMenu2(int id) {

        boolean active_2 = true;
        int op, op_2;

        List<Product> p = new ArrayList<>();
        List<Provider> pv = new ArrayList<>();
        List<ProductProvider> pmv = new ArrayList<>();

        do {
            switch (ui.getMenu2()) {

                case User_Profile:
                    ui.showClientProfile(cm.getClientById(id), sm.getSales(), pm.getProductList());
                    ui.showHistorial(sm.getHistorialClient(id), pm.getProductList());
                    break;

                case Find_Products_By_Name:
                    p = pm.getProductByName(ui.recuestProductKeyWord());
                    ui.showListProducts(p);
                    op = ui.recuestOption();

                    if (op != 0) {
                        pv = providerManager.getProvidersByProduct(p.get(op -1));
                        ui.showProductInfo(p.get(op-1));
                        ui.showProductInfoByProviders(pv, p.get(op-1));
                        op_2 = ui.recuestOption();
                        if (op_2 != 0) {
                            if (ui.recuetComfirmationShoppinCart()){
                                cmart.addToCart(id,
                                        p.get(op -1).getProductID(),
                                        pv.get(op_2-1).getProviderId(),
                                        providerManager.getPrice(pv.get(op_2-1).getProviderId(), p.get(op-1).getProductID())
                                );
                                ui.showProductAddedMessage();
                            }


                        }
                    }
                    break;

                case Find_Products_By_Provider:
                    pv = providerManager.getProviders();
                    ui.showListProviders(pv);

                    op = ui.recuestOption();
                    if (op != 0) {
                        ui.showProviderInfo(pv.get(op-1));
                        pmv = providerManager.getProductsProvider(pv.get(op -1).getProviderId());
                        p = pm.getProductList();

                        ui.showListlProductsProvider(pmv, p);

                        op_2 = ui.recuestOption();
                        if (op_2 != 0) {
                            if (ui.recuetComfirmationShoppinCart()){
                                cmart.addToCart(id,
                                        p.get(op_2-1).getProductID(),
                                        pv.get(op-1).getProviderId(),
                                        pmv.get(op_2-1).getPrice()
                                );
                                ui.showProductAddedMessage();
                            }
                        }
                    }
                    break;

                case Shopping_Cart:
                    pv = providerManager.getProviders();
                    p = pm.getProductList();

                    ui.showCart(cmart.getCarts(), p, pv);
                    if (!cmart.getCarts().isEmpty()) {
                        if (ui.recuetComfirmationPurchase()) {
                            long ts = System.currentTimeMillis() / 1000L;;

                            for (Cart c : cmart.getCarts()) {
                                sm.addSale(new Sale(id, c.getProductId(), c.getPrice(), ts));
                                providerManager.reduceStock(c.getProviderId(), c.getProductId());
                            }
                            providerManager.actualizaStockFile();
                            ui.showPurchaseMadeMessage();
                        }
                    }
                    break;

                case Exit:
                    cmart.clearCarts();
                    ui.showExitMessage();
                    active_2 = false;
                    break;
            }

        } while (active_2);
    }

}
