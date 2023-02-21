package interaction;

import products.Product;
import products.instrument.musical.keyboard.Accordion;
import products.instrument.musical.keyboard.Piano;
import products.instrument.musical.string.Dombyra;
import products.instrument.musical.string.guitar.AcousticGuitar;
import products.instrument.musical.string.guitar.BassGuitar;
import products.technic.electric.headphones.FullSizeHeadphone;
import products.technic.electric.headphones.InnerHeadphone;
import products.technic.electric.microphone.CondenserMic;
import products.technic.electric.microphone.DynamicMic;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private User user;
    private Database db;
    Scanner sc = new Scanner(System.in);

    //------------------------------------------//
    // Singleton menu object creation
    private static Menu instance;

    private Menu(Database db) {
        this.db = db;
    }

    public static Menu initMenu(Database db) {
        if (instance == null) {
            instance = new Menu(db);
        }
        return instance;
    }

    //------------------------------------------//
    // Entry menu page
    public void entry(){
        System.out.println("""
                =====================================
                 Welcome, User! What would you like:
                      1. Log in      2.  Sign up  \s
                =====================================""");
        int choice = sc.nextInt();
        if (choice == 1) login();
        else if (choice == 2) signup();
        else {
            System.out.println(" Please, choose from given options");
            entry();
        }
    }

    // log in menu page
    private void login(){
        System.out.print( "================Log in===============\n" +
                          " Login: ");
        String login = sc.next();
        System.out.print( " Password: ");
        String password = sc.next();

        if (login.equals("root") && password.equals("admin")) {
            System.out.println(" You're loged in as Admin\n" +
                               "=====================================");
            adminPanel();
        }

        if (user != null) {
            if (login.equals(user.getLogin()) && password.equals(user.getPassword())) {
                System.out.println(" Hello, " + user.getLogin() + "!\n" +
                                   "=====================================");
                shop();
            } else {
                System.out.println(" Incorrect password or login\n" +
                                   "=====================================");
                entry();
            }
        }
        else {
            System.out.println(" Can't find such a user. Try creating account first\n" +
                               "=====================================");
            entry();
        }
    }

    // Sign up menu page
    private void signup(){
        System.out.print( "===============Sign up===============\n" +
                          " Login: ");
        String login = sc.next();
        System.out.print( " Password: ");
        String password = sc.next();

        if (user == null) {
            System.out.println("Account created successfully!\n" +
                               "=====================================");
            user = new User(login, password);
            login();
        }
        else {
            System.out.println(" User already exist \n" +
                               "=====================================");
            entry();
        }
    }

    // Admin panel page
    private void adminPanel() {
        System.out.println("""
                =================Admin===============
                 1. Add new product
                 2. Delete product
                 3. Log out
                =====================================""");
        int option = sc.nextInt();
        switch (option) {
            case 1 -> {
                System.out.print("=================Create==============\n" +
                        " Product name: ");
                sc.nextLine();
                String name = sc.nextLine();
                System.out.print(" Price: ");
                double price = sc.nextDouble();
                System.out.println("""
                           Choose the type:
                          1. Accordion     2. Acoustic Guitar
                          3. Bass Guitar   4. Dombyra\s
                          5. Piano         6. Condenser Mic
                          7. Dynamic Mic   8. Full-size Headphone
                          9. Inner Headphone\s""");
                int type = sc.nextInt();
                switch (type) {
                    case 1 -> db.insertProduct(new Accordion(name, price));
                    case 2 -> db.insertProduct(new AcousticGuitar(name, price));
                    case 3 -> db.insertProduct(new BassGuitar(name, price));
                    case 4 -> db.insertProduct(new Dombyra(name, price));
                    case 5 -> db.insertProduct(new Piano(name, price));
                    case 6 -> db.insertProduct(new CondenserMic(name, price));
                    case 7 -> db.insertProduct(new DynamicMic(name, price));
                    case 8 -> db.insertProduct(new FullSizeHeadphone(name, price));
                    case 9 -> db.insertProduct(new InnerHeadphone(name, price));
                    default -> {
                        System.out.println(" Please, Choose from the given options");
                        adminPanel();
                    }
                }
                System.out.println(" Product created successfully!\n" +
                        "=====================================");
            }

            case 2 -> {
                System.out.print("================Delete==============\n" +
                        "Product ID: ");
                int id = sc.nextInt();
                db.deleteProduct(id);
                System.out.println(" Product deleted successfully!" +
                        "=====================================");
            }

            case 3 -> entry();
            default -> System.out.println(" Please, choose from given options");
        }
        adminPanel();
    }

    // Shop main menu
    private void shop() {
        System.out.println("""
                ==================Shop===============
                 1. Browse products
                 2. Your cart
                 3. Log out
                =====================================""");
        int option = sc.nextInt();

        switch (option) {
            case 1 -> {
                System.out.println("==================Shop===============\n" +
                                   "What category you want to see?\n" +
                                   " 1. Musical instruments\n" +
                                   " 2. Studio gear\n" +
                                   " 3. Back");
                ArrayList<Product> pr = new ArrayList<>();
                int choice = sc.nextInt();
                switch (choice){
                    case 1 -> pr = instruments();
                    case 2 -> pr = technic();
                    case 3 -> shop();
                    default -> {
                        System.out.println(" Please, choose from given options");
                        shop();
                    }
                }

                System.out.print("=====================================\n" +
                                   " What would you buy? \n" +
                                   " Enter ID's here: ");
                sc.nextLine();
                String[] strid = sc.nextLine().split("\\D+");
                for (String s : strid) {
                    int id = Integer.parseInt(s);
                    try {
                        user.addToCart(pr.get(id-1));
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(" ID: " + id + " out of bound");
                    }
                }
                System.out.println(" Products added to cart successfully!\n" +
                                   "=====================================");
            }

            case 2 -> {
                System.out.println("==================Cart===============");
                if (user.getCart() != null) {
                    for (int i = 0; i < user.getCart().size(); i++) {
                        System.out.print(user.getCart().get(i).toString());
                    }
                    System.out.println("=====================================\n" +
                            "    Would you like to buy them?\n" +
                            " Your balance: " + user.getBalance() + " | Cost: " + user.cartCost() + "\n" +
                            "          1. Yes      2. No   \n" +
                            "=====================================");
                    int choice = sc.nextInt();
                    if (choice == 1) {
                        if (user.purchaseCart()) {
                            System.out.println(" Thank you for shopping! Products was sent to your address");
                        } else {
                            System.out.println(" Not enough money on balance");
                        }
                    } else if (choice == 2) {
                        shop();
                    } else {
                        System.out.println(" Please, choose from given options");
                    }
                }
                else {
                    System.out.println(" Your cart is empty. Maybe it's time for shopping?\n" +
                                       "=====================================");
                }
            }

            case 3 -> entry();
            default -> System.out.println(" Please, choose from given options");
        }
        shop();
    }

    private ArrayList<Product> instruments(){
        System.out.print("""
                 Available instruments: 
                  1. Accordion
                  2. Acoustic Guitar
                  3. Bass Guitar
                  4. Dombyra
                  5. Piano
                  6. Back
                 
                 What would you choose: """);
        int choice = sc.nextInt();
        ArrayList<Product> pr = new ArrayList<>();
        switch (choice){
            case 1 -> pr = db.selectSpecificProducts("Accordion");
            case 2 -> pr = db.selectSpecificProducts("AcousticGuitar");
            case 3 -> pr = db.selectSpecificProducts("BassGuitar");
            case 4 -> pr = db.selectSpecificProducts("Dombyra");
            case 5 -> pr = db.selectSpecificProducts("Piano");
            case 6 -> shop();
            default -> {
                System.out.println(" Please, Choose from the given options");
                instruments();
            }
        }
        for (Product product : pr) {
            System.out.print(product.toString());
        }
        return pr;
    }

    private ArrayList<Product> technic(){
        System.out.print("""
                 Available gear: 
                  1. Condenser Mic
                  2. Dynamic Mic
                  3. Full-size Headphone
                  4. Inner Headphone
                  5. Back
                 
                 What would you choose: """);
        int choice = sc.nextInt();
        ArrayList<Product> pr = new ArrayList<>();
        switch (choice){
            case 1 -> pr = db.selectSpecificProducts("CondenserMic");
            case 2 -> pr = db.selectSpecificProducts("DynamicMic");
            case 3 -> pr = db.selectSpecificProducts("FullSizeHeadphone");
            case 4 -> pr = db.selectSpecificProducts("InnerHeadphone");
            case 5 -> shop();
            default -> {
                System.out.println(" Please, Choose from the given options");
                instruments();
            }
        }
        for (Product product : pr) {
            System.out.print(product.toString());
        }
        return pr;
    }
}
