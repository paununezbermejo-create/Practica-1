package Presentation;

import Business.Entities.*;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class ConsoleUIManager {
    //Diferents missatges que es mostraran dins el programa
    private final String SHUTTING_DOWN_MESSAGE = "Shutting Down...";
    private final String MENU_PRINCIPAL_OPTIONS =
            "\t\t1) Login\n" +
            "\t\t2) Sign up\n\n" +
            "\t\t0) Exit \n\n";
    private final String MENU_USER_OPTIONS =
            "\t\t1) User profile \n" +
            "\t\t2) Find products by name \n" +
            "\t\t3) Find products by provider \n" +
            "\t\t4) Shopping cart \n\n" +
            "\t\t0) Logout\n\n";
    private final String WELCOME_MESSAGE = "\nWelcome ";
    private final String STARTING_MESSAGE = "Starting program...\n";
    private final String ENTER_OPTION_MESSAGE = "Choose an option: ";
    private final String ENTER_LOGIN_ID = "Client ID: ";
    private final String enterName = "Full Name: ";
    private final String ENTER_NUM_PHONES = "How many phone number do you have ? ";
    private final String enterPhoneNumber = "Enter Phone Number: ";
    private final String recuestKeyword = "Search criteria: ";
    private final String enterComfirmationShoppinCart = "Do you want to add this product to the shopping cart? ";
    private final String enterComfirmationPurchase = "Do you want to proceed with the purchase? ";
    private final String producAddedMessage = "Product added to shopping cart.\n";
    private final String purchaseMadeMessage = "Thank you for your purchase!\n";
    private final String exitMessage = "We hope to see you again!\n";
    private final String ERROR_MESSAGE_FILE_PRODUCT = "Error file data/produscts.json\n";
    private final String ERROR_MESSAGE_FILE_PROVIDER = "Error file data/providers.json\n";
    private final String ERROR_MESSAGE_INVALID_PHONE = "Error invalid phone number!\n";
    private final String ERROR_MESSAGE_ID_NOT_FOUND = "Error invalid id, please try again!\n";
    private final String NO_PRODUCTS_IN_CART_MESSAGE = "There are no products in the shopping cart.\n";
    private final String NO_PURCHASED_MADE_MESSAGE = "No purchasses made.\n";

    //Variables scanner i error
    private final Scanner input = new Scanner(System.in);
    private Error error;

    //Procediment que es mostra al iniciar el programa
    public void showPresentationMessage (){
        System.out.println(
                " _   _           _        _____             ____            _           _   \n" +
                        "| \\ | |_   _  __| | ___  | ____|   _  ___  |  _ \\ _ __ ___ (_) ___  ___| |_ \n" +
                        "|  \\| | | | |/ _` |/ _ \\ |  _|| | | |/ _ \\ | |_) | '__/ _ \\| |/ _ \\/ __| __|\n" +
                        "| |\\  | |_| | (_| |  __/ | |__| |_| |  __/ |  __/| | | (_) | |  __/ (__| |_ \n" +
                        "|_| \\_|\\__,_|\\__,_|\\___| |_____\\__, |\\___| |_|   |_|  \\___// |\\___|\\___|\\__|\n" +
                        "                               |___/                     |__/               \n" +
                        "Welcome to Nude Eye Project.\n" +
                        "“by glass wearers for glass wearers”\n" +
                        "Verifying local files..."
        );
    }

    //Procediment que mostra el missatge de comprovacio de fitxers
    public void showValidFiles () {
        System.out.println("Files Ok.");
        System.out.println(statingMessage);
    }

    //Procediment que mostra el missatge de fi del programa
    public void showShuttingDown () {
        System.out.println(SHUTTING_DOWN_MESSAGE);
    }

    //Funcio que mostra el menu de inici de sesio del programa
    public MenuPrincipal getMenu1 (){
        switch (statingMessage){}
        //Bucle fins que l'usuari esculli una opcio correcta
        do {
            System.out.print(menu1Message);
            System.out.print(enterOption);
            try {
                int option = input.nextInt();
                switch (option) {
                    case 0: return MenuPrincipal.Exit;
                    case 1: return MenuPrincipal.Login;
                    case 2: return MenuPrincipal.Sign_up;
                    default: System.out.print(error.ERROR_WRONG_OPTION.getMessage());
                }
            } catch (NumberFormatException e) {
                System.out.print(error.ERROR_WRONG_OPTION.getMessage());
            }
        } while(true);
    }

    //Funcio que retorna un id demanat al usuari
    public int recuestIdLogin (){
        System.out.print(enterLoginId);
        try {
            int id = input.nextInt();
            return id;
        } catch (NumberFormatException e) {
            System.out.print(ERROR_MESSAGE_ID_NOT_FOUND);
            return 0;
        }
    }

    //Funcio que mostra el missatge de benvinguda al usuari
    public void showWelcomeMessage (String name){
        System.out.print(welcomeMessage + name + "!\n\n");
    }

    //Funcio que mostra error si el id no existeix
    public void showErrorIDNotFound(){
        System.out.print(ERROR_MESSAGE_FILE_PROVIDER);
    }

    //Procediment que demana un nom
    public String recuetSignInName (){
        String trash = input.nextLine();
        System.out.print(enterName);
        return input.nextLine();
    }

    //Procediment que demana un numero
    public int recuestNumPhones (){
        System.out.print(ENTER_NUM_PHONES);
        return Integer.parseInt(input.nextLine());
    }

    //Procediment que demana un conjunt de numeros
    public PhoneNumber recuestPhoneNumber (){
        System.out.print(enterPhoneNumber);
        boolean valid = false;
        //Bucle fins que l'usuari introdueix un numero valid
        while(!valid){
            String phoneNumber = input.nextLine();
            String[] s = phoneNumber.split(" ");
            if (s[0].length() == 3){
                if (s[1].length() == 3 && s[2].length() == 3 && s[3].length() == 3){
                    String phone = s[1] + " " + s[2]  + " " + s[3];
                    PhoneNumber ph = new PhoneNumber(s[0], phone);
                    valid = true;
                    return ph;
                }else{
                    System.out.print(ERROR_MESSAGE_INVALID_PHONE);
                }
            }else{
                System.out.print(ERROR_MESSAGE_INVALID_PHONE);
            }
        }
        return null;
    }

    //Funcio que mostra el menu d'opcions d'usuari
    public MenuUser getMenu2 (){
        do {
            System.out.print(menu2Message);
            System.out.print(enterOption);
            try {
                int option = input.nextInt();
                switch (option) {
                    case 0: return MenuUser.Exit;
                    case 1: return MenuUser.User_Profile;
                    case 2: return MenuUser.Find_Products_By_Name;
                    case 3: return MenuUser.Find_Products_By_Provider;
                    case 4: return MenuUser.Shopping_Cart;
                    default: System.out.print(error.ERROR_WRONG_OPTION.getMessage());
                }
            }catch (NumberFormatException e) {
                System.out.print(error.ERROR_WRONG_OPTION.getMessage());
            }
        }while(true);
    }

    //Procediment que mostra per pantalla el client escollit
    public void showClientProfile (Client client, List<Sale> sales, List <Product> products) {
        System.out.print("\n\n--| User Profile |--\n\n");
        System.out.println("Client ID: " +client.getClientID());
        System.out.println("Full name: " +client.getName());
        System.out.println("Phone numbers:\n");
        for (PhoneNumber phoneNumber : client.getPhones()){
            System.out.print("\t("+phoneNumber.getPrefix()+")" + " " + phoneNumber.getNumber() + "\n");
        }
        System.out.print("\nShopping history:\n");
    }

    //Funcio que retorna el id del producte
    public String recuestProductKeyWord (){
        String trash = input.nextLine();
        System.out.print(recuestKeyword);
        return input.nextLine();
    }

    //Procediment que mostra tots els productes escollits
    public void showListProducts (List<Product> products){

        int i = 1;
        System.out.print("\n");
        //Bucle que acaba al mostrar tots els productes escollits
        for (Product product : products){
            System.out.print("\t"+i+") " +product.getProductID() + " - " + product.getProductName() + "\n");
            i++;
        }
        System.out.print("\n\t0) Back\n\n");
    }

    //Procediment que mostra la informacio d'un producte
    public void showProductInfo (Product product){
        System.out.println("Product ID: " + product.getProductID() + ",");
        System.out.println("Name: " + product.getProductName() + ",");
        System.out.println("Brand: " + product.getBrand() + ",");
        System.out.println("Model: " + product.getModel() + ",");
        System.out.println("Providers:\n");
    }

    //Procediment que mostra la informacio dels proveidors d'un producte
    public void showProductInfoByProviders (List<Provider> provider, Product product){
        int i = 1;
        for (Provider p : provider){
            System.out.print("\t"+i+") " + p.getCompanyName() + "\n");
            for(ProductProvider pv : p.getProductProviders()){
                if (pv.getProductId().equals(product.getProductID())){
                    System.out.println("\t\t - Sale price: " + pv.getPrice() + "€,");
                    System.out.println("\t\t - Available stock: " + pv.getStock());
                }
            }
            i++;
        }
        System.out.print("\n");
        System.out.print("\t0) Back\n\n");
    }

    //Funcio que demana a l'usuari una opcio a escollir
    public int recuestOption (){
        System.out.print(enterOption);
        return input.nextInt();
    }

    //Procediment que mostra tots els proveidors guardats dins el programa
    public void showListProviders (List<Provider> providers){
        int i = 1;
        System.out.print("\n");
        //Bucle que acaba al mostrar tots els proveidors
        for (Provider provider : providers){
            System.out.print("\t"+i+") " + provider.getCompanyName() + "\n");
            i++;
        }
        System.out.print("\n");
        System.out.print("\t0) Back\n\n");
    }

    //Procediment que mostra tota la informacio d'un proveidor
    public void showProviderInfo(Provider provider){
        System.out.println("\nName: " + provider.getCompanyName() + ",");
        System.out.println("CIF: " + provider.getCif() + ",");
        System.out.println("Contact person: " + provider.getContactName() + ",");
        System.out.println("Phone number: " + provider.getPhone() + ",");
        System.out.println("Contact email: " + provider.getEmail() + ",");
    }

    //Procediment que mostra tos els productes d'un proveider
    public void showListlProductsProvider (List<ProductProvider> productsP, List<Product> products){
        int x = 1;
        System.out.println("Provider products:\n");
        //Bucle que acaba al mostrar tots els productes del proveidor
        for (int i = 0; i < productsP.size(); i++){
            //Bucle que acaba al mostrar tots els productes del proveidor
            for(int j = 0; j < products.size(); j++){
                if (products.get(j).getProductID().equals(products.get(i).getProductID()) && productsP.get(i).checkStock()){
                    System.out.print("\t"+x+") "
                            + products.get(j).getProductID() + " - "
                            + products.get(j).getProductName() +"\n");
                    System.out.print("\t\t" + " - Sale price: "
                            + productsP.get(i).getPrice() + "€,\n");
                    System.out.print("\t\t" + " - Available stock: "
                            + productsP.get(i).getStock() + "\n");
                    x++;
                }
            }
        }
        System.out.print("\n");
        System.out.print("\t0) Back\n\n");
    }

    //Procediment que mostra tots els objectes dintre els carrets
    public void showCart (List<Cart> carts, List<Product> products, List<Provider> providers) {
        double total = 0;
        if (!carts.isEmpty()){
            //Bucle que acaba al mostrar tots els carrets
            for (Cart cart : carts){
                //Bucle que acaba al mostrar tots els productes dins els carrets
                for (Product product : products){
                    if (cart.getProductId().equals(product.getProductID())){
                        //Bucle que acaba al mostrar tots els proveidors dels productes dins els carrets
                        for (Provider provider : providers){
                            if (cart.getProviderId() == provider.getProviderId()){
                                System.out.print("\t + "+product.getProductID()+" - "+product.getProductName()+ " \t\t(" + provider.getCompanyName()+ ")\t\t\t" + cart.getPrice() + "€\n");
                                total += cart.getPrice();
                            }
                        }
                    }
                }
            }
            System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t  TOTAL PRICE: " + total + "€\n\n");
        }else{
            System.out.println("\n" + NO_PRODUCTS_IN_CART_MESSAGE);
        }
    }

    //Funcio que retorna l'opcio de l'usuari de crear un carret
    public boolean recuetComfirmationShoppinCart (){
        String trash = input.nextLine();
        System.out.print(enterComfirmationShoppinCart);
        if (input.nextLine().equals("Yes")){
            return true;
        }else{
            return false;
        }
    }

    //Funcio que retorna l'opcio de l'usuari de comprar un carret
    public boolean recuetComfirmationPurchase (){
        String trash = input.nextLine();
        System.out.print(enterComfirmationPurchase);
        if (input.nextLine().equals("Yes")){
            return true;
        }else{
            return false;
        }
    }

    //Procediment que mostra el historial de ventes
    public void showHistorial(List<Sale> sales, List<Product> products){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy – HH:mm");

        if (!sales.isEmpty()){
            //Bucle que acaba al mostrar totes les ventes
            for (Sale sale : sales){
                //Bucle que acaba al mostrar tots els productes dins les ventes
                for (Product product : products){
                    if (sale.getProductId().equals(product.getProductID())){
                        Date date = new Date(sale.getPurchaseDate() * 1000L);;
                        System.out.print("\t "
                                + product.getProductName()
                                + " - " + product.getBrand()
                                + " (" + sale.getProductId() + ") €"
                                + sale.getPrice() + " - "
                                + sdf.format(date) + "\n");
                    }
                }
            }
            System.out.print("\n");
        } else {
            System.out.println(NO_PURCHASED_MADE_MESSAGE);
        }
    }

    //Procediment que mostra per pantalla un missatge de confirmacio
    public void showProductAddedMessage (){
        System.out.println(producAddedMessage);
    }
    //Procediment que mostra per pantalla un missatge de confirmacio
    public void showPurchaseMadeMessage (){
        System.out.println(purchaseMadeMessage);
    }
    //Procediment que mostra per pantalla un missatge de confirmacio
    public void showExitMessage (){
        System.out.println(exitMessage);
    }
    //Procediment que mostra per pantalla un missatge de error
    public void showErrorMessageFileProduct (){
        System.out.println(ERROR_MESSAGE_FILE_PRODUCT);
    }
    //Procediment que mostra per pantalla un missatge de error
    public void showErrorMessageFileProvider (){
        System.out.println(ERROR_MESSAGE_FILE_PROVIDER);
    }
}
