import java.util.Scanner;
import java.util.ArrayList;
// import javax.crypto.SecretKey;
// import javax.crypto.Cipher;
// import javax.crypto.KeyGenerator;

public class User extends Person {
    private double userAmount;
    private double checkingAccountBalance;
    private ArrayList<Double> carPurchases;
    private ArrayList<Double> travelPurchases;
    private ArrayList<Double> rentPurchases;
    private ArrayList<Double> foodPurchases;
    private ArrayList<ArrayList<Double>> budget; // 0 = car purchases; 1 = travel; 2 = rent; 3 = food
    // private SecretKey myDesKey;
    // private byte[] byteArr;
    // private byte[] loginByteArr;
    ArrayList<User> usersList = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    public User() {
        this.firstName = "";
        this.lastName = "";
        this.username = "";
        this.password = "";
    }
    public User(String firstName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        // encryptPassword();
        this.userAmount = 0;
        this.checkingAccountBalance = 25000.0;
        this.carPurchases = new ArrayList<>();
        this.travelPurchases = new ArrayList<>();
        this.rentPurchases = new ArrayList<>();
        this.foodPurchases = new ArrayList<>();
        this.budget = new ArrayList<>(); this.budget.add(this.carPurchases); this.budget.add(this.travelPurchases); this.budget.add(this.rentPurchases); this.budget.add(foodPurchases);
    }
    public void checkingAccount(){
        System.out.println("Your primary checking account has acquired a total of $25,000.");
    }

    public double getCheckingAccountBalance(){
        return checkingAccountBalance;
    }

    public double askUserAmount() {
        System.out.println("Enter the amount you'd like to add: ");
        double userAmount = Double.parseDouble(scanner.nextLine());
        return userAmount; 
    }
    
    public void addMoneyToCategory(double amount) {
        int choice;
        //Display Categories
        System.out.println("Select the budget catergory you'd like to add to: ");
        System.out.println("1. Car Purchases");
        System.out.println("2. Travel Purchases");
        System.out.println("3. Rent Purchases");
        System.out.println("4. Food Purchases");
        choice = scanner.nextInt();

        if (choice < 1 || choice > 4) {
            System.out.println("Invalid choice. Try again.");
        }
        if (checkingAccountBalance < amount) {
            System.out.println("Transaction incomplete. Insufficient funds in the checking account.");
            System.out.printf("Current checking account balance: $%.2f\n", checkingAccountBalance);
        }
        if (userAmount + amount > 8000){
            System.out.println("Sorry. This amount exceeds the transfer limit of $8000. Please enter a lower amount.");
        }

        switch (choice) {
            case 1:
                budget.get(0).add(amount);
                System.out.printf("You have added $%.2f into Car Purchases. \n", amount);
                break;
            case 2:
                budget.get(1).add(amount);
                System.out.printf("You have added $%.2f into Travel Purchases. \n", amount);
                break;
            case 3:
                budget.get(2).add(amount);
                System.out.printf("You have added $%.2f into Rent Purchases. \n", amount);
                break;
            case 4:
                budget.get(3).add(amount);
                System.out.printf("You have added $%.2f into Food Purchases. \n", amount);
                break;
            default:
                break;
        }
        userAmount += amount;
        checkingAccountBalance -= amount;
        System.out.printf("Transaction complete! Remaining balance in checking: $%.2f\n", checkingAccountBalance);
    }

    public void displayTransactions(){
        System.out.println("Select the budget catergory you'd like to view transactions for");
        System.out.println("1. Car Purchases");
        System.out.println("2. Travel Purchases");
        System.out.println("3. Rent Purchases");
        System.out.println("4. Food Purchases");
        int choice = scanner.nextInt();
    
        ArrayList<Double> selectedCategory = new ArrayList<>(); budget.get(choice - 1);
        switch (choice) {
            case 1:
                System.out.println("Transactions for Car Purchases");
                break;
            case 2:
                System.out.println("Transactions for Travel Purchases");
                break;
            case 3:
                System.out.println("Transactions for Rent Purchases.");
                break;
            case 4:
                System.out.println("Transaction for Food Purchases");
                break;
            default:
                System.out.println("Invalid choice. Try again.");
                return;
        }
        
        if (selectedCategory.isEmpty()) {
            System.out.println("No transactions available.");
        } else {
            for (int i = 0; i < selectedCategory.size(); i ++) {
                System.out.printf("Transaction %d: $%.2f\n", i + 1, selectedCategory.get(i));
                
            }
        } 
    }
    
    public void createUser() {
        char[] spChars = {'!', '@', '#', '$', '%', '^', '&', '*', '(', ')'};
        char[] nums = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
        char[] uppercaseLetters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        while (true) {
            String firstName = null;
            String lastName = null;
            String userName = null;
            String password = null;
            System.out.println("Welcome to the account creation tool. To begin, please enter your first name: ");
            firstName = scanner.nextLine();
            System.out.println("Please enter your last name: ");
            lastName = scanner.nextLine();
            System.out.println("Please enter a username. You will need this to log in to your account: ");
            userName = scanner.nextLine();
            while (true) {
                boolean containsSpChar = false;
                boolean containsNum = false;
                boolean containsUppercaseLetter = false;
                System.out.println("Lastly, please enter a password. Your password must be 8 characters long, contain a special character, a number, and an uppercase letter: ");
                password = scanner.nextLine();
                for (int i = 0; i < spChars.length; i++) {
                    for (int j = 0; j < password.length(); j++) {
                        if (password.charAt(j) == spChars[i]) {
                            containsSpChar = true;
                            break;
                        }
                    }
                }
                for (int k = 0; k < nums.length; k++) {
                    for (int l = 0; l < password.length(); l++) {
                        if (password.charAt(l) == nums[k]) {
                            containsNum = true;
                            break;
                        }
                    }
                }
                for (int m = 0; m < nums.length; m++) {
                    for (int n = 0; n < password.length(); n++) {
                        if (password.charAt(n) == uppercaseLetters[m]) {
                            containsUppercaseLetter = true;
                            break;
                        }
                    }
                }
                if (password.length() >= 8 && containsSpChar == true && containsNum == true && containsUppercaseLetter == true) {
                    break;
                } else {
                    System.out.println("Invalid password. Please try again.");
                }
            }
            usersList.add(new User(firstName, lastName, userName, password));
            break;
        }
    } 
    public User login(String username, String password) {
        // encryptLoginPassword(password);
        boolean validUser = false;
        boolean validPass = false;
        Integer loggedInUserIndex = -1;
        User loggedInUser = new User();
        loggedInUser.setFirstName("-1");
        for (int i = 0; i < usersList.size(); i++) {
            if (username.equals(usersList.get(i).username)) {
                validUser = true;
                loggedInUserIndex = i;
                break;
            }
        }
        if (password.equals(usersList.get(loggedInUserIndex).password)) {
            validPass = true;
        } 
        // for (int i = 0; i < usersList.size(); i++) {
        //     if (username == usersList.get(i).username) {
        //         validUser = true;
        //         loggedInUserIndex = i;
        //         break;
        //     }
        // }
        // for (int i = 0; i < byteArr.length; i++) {
        //     try {
        //         if (usersList.get(loggedInUserIndex).byteArr == loginByteArr) {
        //             validPass = true;
        //         }
        //     } catch (IndexOutOfBoundsException e) {validPass = false; break;}
        // }
        if (validUser == true && validPass == true) {
            loggedInUser = usersList.get(loggedInUserIndex);
        }
        return loggedInUser;
    }
    // private void encryptPassword() {
    //     try {
    //         KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
    //         myDesKey = keygenerator.generateKey();
    //         Cipher desCipher = Cipher.getInstance("DES");
    //         byte[] text = usersList.getLast().password.getBytes("UTF8");
    //         desCipher.init(Cipher.ENCRYPT_MODE, myDesKey);
    //         byte[] textEncrypted = desCipher.doFinal(text);
    //         usersList.getLast().password = new String(textEncrypted);
    //         usersList.getLast().setByteArr(textEncrypted);
    //     } catch (Exception e) {}
    // }
    // private void encryptLoginPassword(String password) {
    //     try {
    //         Cipher desCipher = Cipher.getInstance("DES");
    //         byte[] text = password.getBytes("UTF8");
    //         desCipher.init(Cipher.ENCRYPT_MODE, myDesKey);
    //         byte[] textEncrypted = desCipher.doFinal(text);
    //         setLoginByteArr(textEncrypted);
    //     } catch (Exception e) {}
    // }
    // private void setByteArr(byte[] newArr) {
    //     usersList.getLast().byteArr = newArr;
    //     System.out.println("Saved password from acc creation");
    //     for (int i = 0; i < byteArr.length; i++) {
    //         System.out.println(usersList.getLast().byteArr);
    //     }
    // }
    // private void setLoginByteArr(byte[] newArr) {
    //     loginByteArr = newArr;
    //     System.out.println("new password from login");
    //     for (int i = 0; i < loginByteArr.length; i++) {
    //         System.out.println(loginByteArr[i]);
    //     }
    // }
}

