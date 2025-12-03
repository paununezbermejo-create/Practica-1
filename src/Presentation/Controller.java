package Presentation;

import Business.*;

import javax.swing.*;

public class Controller {
    private ClientManager cm;
    private ProductManager pm;
    private ProviderManager providerManager;
    private SaleManager sm;
    private CartManager cmart;
    private UIManager ui;

    public Controller(ClientManager cm, ProductManager pm, ProviderManager providerManager, SaleManager sm, CartManager cmart, UIManager ui) {
        this.cm = cm;
        this.pm = pm;
        this.providerManager = providerManager;
        this.sm = sm;
        this.cmart = cmart;
        this.ui = ui;
    }

    public void Start(){

    }
}
