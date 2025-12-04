package Presentation;

import Business.Entities.*;

import java.awt.*;
import java.util.List;
import java.util.Scanner;

public class ConsoleUIManager {
    private final String menu1Message = "\t\t1) Login\n" +
            "\t\t2) Sign up\n\n" +
            "\t\t0) Exit \n\n";
    private final String menu2Message = "\t\t1)  1) User profile \n" +
            "\t\t2) Find products by name \n" +
            "\t\t3) Find products by provider \n" +
            "\t\t4) Shopping cart \n" +
            "\n" +
            "\t\t0) Logout ";
    private final String welcomeMessage = "Welcome ";
    private final String statingMessage = "Starting program...\n\n";
    private final String enterOption = "Choose an option: ";
    private final String enterLoginId = "Client ID: ";
    private final String enterName = "Full Name: ";
    private final String ENTER_NUM_PHONES = "How many phone number do you have ? ";
    private final String enterPhoneNumber = "Enter Phone Number: ";
    private final String recuestKeyword = "Search criteria: ";
    private final String enterComfirmationShoppinCart = "Do you want to add this product to the shopping cart? ";
    private final String enterComfirmationPurchase = "Do you want to proceed with the purchase? ";
    private final String producAddedMessage = "product added to shopping cart.\n";
    private final String purchaseMadeMessage = "Thank you for your purchase!\n";
    private final String exitMessage = "We hope to see you again!\n";
    private final String ERROR_MESSAGE_FILE_PRODUCT = "Error file data/produscts.json\n";
    private final String ERROR_MESSAGE_FILE_PROVIDER = "Error file data/providers.json\n";
    private final String ERROR_MESSAGE_INVALID_PHONE = "Error invalid phone number!\n";
    private final String ERROR_MESSAGE_ID_NOT_FOUND = "Error invalid id, please try again!\n";
    private final Scanner input = new Scanner(System.in);
    private Error error;


    public MenuOption1 getMenu1 (){
        switch (statingMessage){}
        do {
            System.out.println(menu1Message);
            System.out.print(enterOption);
            try {
                int option = input.nextInt();
                switch (option) {
                    case 0: return MenuOption1.Exit;
                    case 1: return MenuOption1.Login;
                    case 2: return MenuOption1.Sign_up;
                    default: System.out.println(error.ERROR_WRONG_OPTION.getMessage());
                }
            } catch (NumberFormatException e) {
                System.out.println(error.ERROR_WRONG_OPTION.getMessage());
            }
        } while(true);
    }

    public int recuestIdLogin (){
        System.out.println(enterLoginId);
        try {
            int id = Integer.parseInt(input.nextLine());
            return id;
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid ID");
            return 0;
        }
    }

    public void showWelcomeMessage (String name){
        System.out.println(welcomeMessage + name + "!\n\n");
    }

    public void showErrorIDNotFound(){
        System.out.println(ERROR_MESSAGE_FILE_PROVIDER);
    }

    public String recuetSignInName (){
        System.out.println(enterName);
        return input.nextLine();
    }

    public int recuestNumPhones (){
        System.out.println(ENTER_NUM_PHONES);
        return Integer.parseInt(input.nextLine());
    }

    public PhoneNumber recuestPhoneNumber (){
        System.out.println(enterPhoneNumber);
        boolean valid = false;
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
                    System.out.println(ERROR_MESSAGE_INVALID_PHONE);
                }
            }else{
                System.out.println(ERROR_MESSAGE_INVALID_PHONE);
            }
        }
        return null;
    }

    public MenuOption2 getMenu2 (){
        do {
            System.out.println(menu2Message);
            System.out.print(enterOption);
            try {
                int option = input.nextInt();
                switch (option) {
                    case 0: return MenuOption2.Exit;
                    case 1: return MenuOption2.User_Profile;
                    case 2: return MenuOption2.Find_Products_By_Name;
                    case 3: return MenuOption2.Find_Products_By_Provider;
                    case 4: return MenuOption2.Shopping_Cart;
                    default: System.out.println(error.ERROR_WRONG_OPTION.getMessage());
                }
            }catch (NumberFormatException e) {
                System.out.println(error.ERROR_WRONG_OPTION.getMessage());
            }
        }while(true);
    }

    public void showClientProfile (Client client, List<Sale> sales){
        System.out.println("--| User Profile | --\n\n");
        System.out.println("Client ID: " +client.getClientID());
        System.out.println("Full name: " +client.getClientID());
        System.out.println("Phone numbers:\n");
        for (PhoneNumber phoneNumber : client.getPhones()){
            System.out.println("("+phoneNumber.getPrefix()+")" + " " + phoneNumber.getNumber() + "\n");
        }
        System.out.println("\n");
        System.out.println("Shopping history:");
    }


    public String recuestProductKeyWord (){
        System.out.println(recuestKeyword);
        return input.nextLine();
    }

    public void showListProducts (List<Product> products){
        int i = 1;
        for (Product product : products){
            System.out.println("\t\t"+i+") " + product.getProductName() + "\n");
        }
        System.out.println("\n");
        System.out.println("0) Back\n\n");
    }

    public int recuestOption (){
        System.out.println(enterOption);
        return Integer.parseInt(input.nextLine());
    }

    public void showListProviders (List<Provider> providers){
        int i = 1;
        for (Provider provider : providers){
            System.out.println("\t\t"+i+") " + provider.getCompanyName() + "\n");
        }
        System.out.println("\n");
        System.out.println("0) Back\n\n");
    }

    public void showListlProductsProvider (List<ProductProvider> productsP, List<Product> products){
        int j = 0;
        int x = 1;
        System.out.println("Provider products:\n");
        for (int i = 0; i < productsP.size(); i++){
            for(j = 0; j < products.size(); j++){
                if (products.get(j).getProductID().equals(products.get(i).getProductID())){
                    System.out.println("\t\t"+x+") "
                            + products.get(j).getProductID() + " - "
                            + products.get(j).getProductName() +"\n");
                    System.out.println("\t\t" + " - Sale price: "
                            + productsP.get(i).getPrice() + "€,");
                    System.out.println("\t\t" + " - Available stock: "
                            + productsP.get(i).checkStock() + "\n");
                    x++;
                }
            }
        }
        System.out.println("\n");
        System.out.println("0) Back\n\n");
    }

    public void showCart (List<Cart> carts, List<Product> products, List<Provider> providers) {
        double total = 0;
        for (Cart cart : carts){
            for (Product product : products){
                if (cart.getProductId().equals(product.getProductID())){
                    for (Provider provider : providers){
                        if (cart.getProviderId() == provider.getProviderId()){
                            System.out.println("\t\t + "+product.getProductID()+" - "+product.getProductName()+ "   (" + provider.getCompanyName()+ ")" + cart.getPrice() + "\n");
                            total = cart.getPrice();
                        }
                    }
                }
            }
        }
        System.out.println("\t\t\t\t\t\t\t TOTAL PRICE: " + total + "\n\n");
    }

    public boolean recuetComfirmationShoppinCart (){
        System.out.println(enterComfirmationShoppinCart);
        if (input.nextLine().equals("Yes")){
            return true;
        }else{
            return false;
        }
    }

    public boolean recuetComfirmationPurchase (){
        System.out.println(enterComfirmationPurchase);
        if (input.nextLine().equals("Yes")){
            return true;
        }else{
            return false;
        }
    }

    public void showProductAddedMessage (){
        System.out.println(producAddedMessage);
    }

    public void showPurchaseMadeMessage (){
        System.out.println(purchaseMadeMessage);
    }

    public void showExitMessage (){
        System.out.println(exitMessage);
    }

    public void showErrorMessageFileProduct (){
        System.out.println(ERROR_MESSAGE_FILE_PRODUCT);
    }

    public void showErrorMessageFileProvider (){
        System.out.println(ERROR_MESSAGE_FILE_PROVIDER);
    }
}
