import Business.*;
import Persistance.*;
import Presentation.Controller;

import javax.swing.*;

public class Main{
    void main() {
        ClientJsonDao clientDao = new ClientJsonDao();
        ProductJsonDao productDao = new ProductJsonDao();
        ProviderJsonDao providerDao = new ProviderJsonDao();
        SaleCsvDao saleDao = new SaleCsvDao();

        ClientManager clientManager = new ClientManager(clientDao);
        ProductManager productManager = new ProductManager(productDao);
        ProviderManager providerManager = new ProviderManager(providerDao);
        SaleManager saleManager = new SaleManager(saleDao);
        UIManager uiManager = new UIManager();

        Controller c = new Controller (clientManager, productManager, providerManager, saleManager, cartManager, uiManager);
        c.Start();

    }
}//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
