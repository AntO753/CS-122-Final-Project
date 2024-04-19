import javax.crypto.SecretKey;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.util.Scanner;
import java.util.ArrayList;

abstract class Person {
    protected String firstName;
    protected String lastName;
    protected String username;
    protected String password;
    protected SecretKey myDesKey;
    protected byte[] byteArr;
    protected byte[] loginByteArr;
    public Person() {
        this.firstName = "";
        this.lastName = "";
        this.username = "";
        this.password = "";
    }
    public Person(String firstName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setFirstName(String newFirstName) {
        this.firstName = newFirstName;
    }
    public void setLastName(String newLastName){
        this.lastName = newLastName;
    }
    public void setUsername(String newUsername) {
        this.username = newUsername; 
    }
    public void setPassword(String newPassword) {
        this.password = newPassword;
    }
    public void displayInfo(){ 
        System.out.printf("First Name: %s ", firstName);
        System.out.printf("Last Name: %s ", lastName);
        System.out.printf("Username: %s ", username);
    }
    protected void encryptPassword() {
        try {
            KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
            myDesKey = keygenerator.generateKey();
            Cipher desCipher = Cipher.getInstance("DES");
            byte[] text = this.password.getBytes("UTF8");
            desCipher.init(Cipher.ENCRYPT_MODE, myDesKey);
            byte[] textEncrypted = desCipher.doFinal(text);
            this.password = new String(textEncrypted);
            setByteArr(textEncrypted);
        } catch (Exception e) {}
    }
    protected void encryptLoginPassword(String password) {
        try {
            Cipher desCipher = Cipher.getInstance("DES");
            byte[] text = password.getBytes("UTF8");
            desCipher.init(Cipher.ENCRYPT_MODE, myDesKey);
            byte[] textEncrypted = desCipher.doFinal(text);
            setLoginByteArr(textEncrypted);
        } catch (Exception e) {}
    }
    public void setByteArr(byte[] newArr) {
        this.byteArr = newArr;
    }
    public void setLoginByteArr(byte[] newArr) {
        this.loginByteArr = newArr;
    }
}
class User extends Person {
    private double userAmount;
    private ArrayList<Double> carPurchases;
    private ArrayList<Double> travelPurchases;
    private ArrayList<Double> rentPurchases;
    private ArrayList<Double> foodPurchases;
    private ArrayList<ArrayList<Double>> budget; // 0 = car purchases; 1 = travel; 2 = rent; 3 = food
    ArrayList<User> usersList = new ArrayList<>();
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
        encryptPassword();
        this.userAmount = 0;
        this.carPurchases = new ArrayList<>();
        this.travelPurchases = new ArrayList<>();
        this.rentPurchases = new ArrayList<>();
        this.foodPurchases = new ArrayList<>();
        this.budget = new ArrayList<>(); this.budget.add(this.carPurchases); this.budget.add(this.travelPurchases); this.budget.add(this.rentPurchases); this.budget.add(foodPurchases);
    }
    public double askUserAmount() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the amount you'd like to add: ");
        double userAmount = Double.parseDouble(scanner.nextLine());
        scanner.close();
        return userAmount; 
        
    }
    
    // Set maximum amount for User's checking account to 5000. 
    // After transaction is complete, print remaining balace in checking account
    // Rename variables "amount" to checkingAccount?

    public void addMoneyToCategory(double amount){
        Scanner scanner = new Scanner(System.in);
        int choice;

        //Display Categories
        System.out.println("Select the budget catergory you'd like to add to: ");
        System.out.println("1. Car Purchases");
        System.out.println("2. Travel Purchases");
        System.out.println("3. Rent Purchases");
        System.out.println("4. Food uchases");
        choice = scanner.nextInt();

        if (choice < 1 || choice > 4) {
            System.out.println("Invalid choice. Try again.");
        }
        if (userAmount + amount > 3000){
            System.out.println("Transaction incomplete. Insufficient funds.");
        }

        switch (choice) {
            case 1:
                budget.get(0).add(amount);
                break;
            case 2:
                budget.get(1).add(amount);
                break;
            case 3:
                budget.get(2).add(amount);
                break;
            case 4:
                budget.get(3).add(amount);
                break;
            default:
                break;
        }
        userAmount += amount;
        System.out.println("Transaction complete!");

        scanner.close();
    }

    public void createUser() {
        Scanner in2 = new Scanner(System.in);
        char[] spChars = {'!', '@', '#', '$', '%', '^', '&', '*', '(', ')'};
        char[] nums = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
        char[] uppercaseLetters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        while (true) {
            String firstName = null;
            String lastName = null;
            String userName = null;
            String password = null;
            System.out.println("Welcome to the account creation tool. To begin, please enter your first name: ");
            firstName = in2.nextLine();
            System.out.println("Please enter your last name: ");
            lastName = in2.nextLine();
            System.out.println("Please enter a username. You will need this to log in to your account: ");
            userName = in2.nextLine();
            while (true) {
                boolean containsSpChar = false;
                boolean containsNum = false;
                boolean containsUppercaseLetter = false;
                System.out.println("Lastly, please enter a password. Your password must be 8 characters long, contain a special character, a number, and an uppercase letter: ");
                password = in2.nextLine();
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
            in2.close();
            break;
        }
    } 
    public User login(String username, String password) {
        encryptLoginPassword(password);
        boolean validUser = false;
        boolean validPass = false;
        Integer loggedInUserIndex = -1;
        Integer numOfCorrectChars = 0;
        User loggedInUser = new User();
        loggedInUser.setFirstName("-1");
        for (int i = 0; i < usersList.size(); i++) {
            if (username == usersList.get(i).username) {
                validUser = true;
                loggedInUserIndex = i;
                break;
            }
        }
        for (int i = 0; i < this.byteArr.length; i++) {
            try {
                if (this.byteArr[i] == this.loginByteArr[i]) {
                    numOfCorrectChars += 1;
                }
            } catch (IndexOutOfBoundsException e) {validPass = false; break;}
        }
        if (numOfCorrectChars == this.byteArr.length) {
            validPass = true;
        }
        if (validUser == true && validPass == true) {
            loggedInUser = usersList.get(loggedInUserIndex);
        }
        numOfCorrectChars = 0;
        return loggedInUser;
    } 
}
    
// pass the scanner for multiple input methods.

public class Main {
    public static void main(String[] args) {
        User currUser = new User();
        currUser.setFirstName("-1");
        Scanner in = new Scanner(System.in);
        while (true) {
            Integer uInp = 0;
            System.out.println("Welcome! Please enter the number next to the choice you would like to perform:\n1) Create a new account\n2) Login\n3) Exit");
            String input = in.nextLine();
            try {uInp = Integer.parseInt(input);} catch (NumberFormatException e) {System.out.println("Please enter a number"); continue;} // Exception in thread "main" java.util.NoSuchElementException: No line found
            if (uInp == 1) {
                currUser.createUser();
            } else if (uInp == 2) {
                while (true) {
                    String username = null;
                    String password = null;
                    System.out.println("Please enter your username:");
                    username = in.nextLine();
                    System.out.println("Please enter your password:");
                    password = in.nextLine();
                    currUser = currUser.login(username, password);
                    if (currUser.getFirstName().equals("-1")) {
                        System.out.println("Either your username or password is incorrect");
                        continue;
                    } else {
                        System.out.printf("Welcome %s, %s!\n", currUser.getLastName(), currUser.getFirstName());
                        break;
                    }
                }
            } else if (uInp == 3) {
                break;
            } else {
                System.out.println("Please enter a number from 1 to 3");
                continue;
            }
        }
        while (true) {
            if (currUser.getFirstName().equals("-1")) {
                break;
            } else {
                System.out.printf("Welcome %s, %s. Please select which option you would like to perform today:\n", currUser.getLastName(), currUser.getFirstName());
                System.out.println("1) Adding money to a budgeting category\n");
            }
        }
        in.close();
    }
}