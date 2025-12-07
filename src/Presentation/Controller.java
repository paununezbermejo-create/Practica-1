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

    //Constructor del controler
    public Controller(ClientManager cm, ProductManager pm, ProviderManager providerManager,
                      SaleManager sm, CartManager cmart, ConsoleUIManager uiManager) {
        this.cm = cm;
        this.pm = pm;
        this.providerManager = providerManager;
        this.sm = sm;
        this.cmart = cmart;
        this.ui = uiManager;
    }

    //Procediment que inicialitza el programa
    public void Start() {
        boolean active_1 = true;
        int id = 0;

        ui.showPresentationMessage();
        //Es comprova que els fitxers necessaris per que el programa funcioni exisdteixen
        if (providerManager.cheeckFile() && pm.cheeckFile()) {
            ui.showValidFiles();
            //Bucle fins que l'usuari esculli l'opcio exit
            do {
                //Switch que classifica les diferents opcions d'inici de sesio
                switch (ui.getMenu1()) {

                    case Login:
                        //Cas on el usuari fa login
                        id = loginUser();
                        ui.showWelcomeMessage(cm.getNameById(id));
                        startMenu2(id);
                        break;

                    case Sign_up:
                        //Cas on el usuari fa signup
                        id = signUpUser();
                        ui.showWelcomeMessage(cm.getNameById(id));
                        startMenu2(id);
                        break;

                    case Exit:
                        //Cas on el usuari fa exit
                        active_1 = false;
                        break;
                }

            } while (active_1);

        } else {
            //Missatges d'error en cas que els fitxers necessaris no existeixin
            if (!providerManager.cheeckFile()) ui.showErrorMessageFileProvider();
            if (!pm.cheeckFile()) ui.showErrorMessageFileProduct();
            ui.showShuttingDown();
        }
    }

    //Funcio que retorna el id i on es comprova si el usuari existeix dintre el programa
    private int loginUser() {
        int id;
        //Bucle que recorre tots els id dintre del sistema per comprovar si esta dintre o no
        do {
            id = ui.recuestIdLogin();
            if (cm.logIn(id) == null) {
                ui.showErrorIDNotFound();
            }
        } while (cm.logIn(id) == null);

        return id;
    }

    //Funcio que crea el usuari i retorna el seu id assignat
    private int signUpUser() {

        String name = ui.recuetSignInName();

        List <PhoneNumber> phones = new ArrayList<>();
        int i = 1;
        int x = ui.recuestNumPhones();
        //Bucle que demana tots els mobils del nou usuari
        for (int j = 0; j < x; j++){
            phones.add(ui.recuestPhoneNumber());
        }
        //Bucle que decideix quin id tindra el nou usuari
        while (cm.getClientById(i) != null){
            i++;
        }
        cm.signUp(i, name, phones);
        return i;
    }

    //Procediment que s'inicialitza un cop s'ha iniciat sesio o s'ha registrat l'usuari
    private void startMenu2(int id) {

        boolean active_2 = true;
        int op, op_2;

        List<Product> p = new ArrayList<>();
        List<Provider> pv = new ArrayList<>();
        List<ProductProvider> pmv = new ArrayList<>();

        //Bucle fins que l'usuari esculli l'opcio de exit
        do {
            //Switch que classifica les diferents opcions del usuari
            switch (ui.getMenu2()) {

                case User_Profile:
                    //Cas on l'usuari vol veure el perfil d'un altre usuari
                    ui.showClientProfile(cm.getClientById(id), sm.getSales(), pm.getProductList());
                    ui.showHistorial(sm.getHistorialClient(id), pm.getProductList());
                    break;

                case Find_Products_By_Name:
                    //Cas on l'usuari vol buscar productes pel nom
                    p = pm.getProductByName(ui.recuestProductKeyWord());
                    ui.showListProducts(p);
                    op = ui.recuestOption();

                    //Controlador on es comprova que l'usuari no ha escollit sortir de la opcio
                    if (op != 0) {
                        pv = providerManager.getProvidersByProduct(p.get(op -1));
                        ui.showProductInfo(p.get(op-1));
                        ui.showProductInfoByProviders(pv, p.get(op-1));
                        op_2 = ui.recuestOption();
                        //Controlador on es comprova que l'usuari no ha escollir sortir de la opcio
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
                    //Cas on l'usuari busca un producte a partir dels possibles proveidors
                    pv = providerManager.getProviders();
                    ui.showListProviders(pv);

                    op = ui.recuestOption();
                    //Controlador on es comprova que l'usuari no ha escollir sortir de la opcio
                    if (op != 0) {
                        ui.showProviderInfo(pv.get(op-1));
                        pmv = providerManager.getProductsProvider(pv.get(op -1).getProviderId());
                        p = pm.getProductList();

                        ui.showListlProductsProvider(pmv, p);

                        op_2 = ui.recuestOption();
                        //Controlador on es comprova que l'usuari no ha escollir sortir de la opcio
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
                    //Cas on es mostra els productes guardats al carret per comprar
                    pv = providerManager.getProviders();
                    p = pm.getProductList();

                    ui.showCart(cmart.getCarts(), p, pv);
                    //Controlador que s'activa si el carret no esta vuit
                    if (!cmart.getCarts().isEmpty()) {
                        //Controlador de desicio si l'usuari decideix comprar el carret
                        if (ui.recuetComfirmationPurchase()) {
                            long ts = System.currentTimeMillis() / 1000L;;

                            //Bucle que transforma tots els productes del carret en ventes
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
                    //Cas on l'usuari surt de la sessio
                    cmart.clearCarts();
                    ui.showExitMessage();
                    active_2 = false;
                    break;
            }

        } while (active_2);
    }

}
