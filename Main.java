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
    protected void setByteArr(byte[] newArr) {
        this.byteArr = newArr;
    }
    protected void setLoginByteArr(byte[] newArr) {
        this.loginByteArr = newArr;
    }
    public boolean login(String username, String password) {
        encryptLoginPassword(password);
        boolean validUser = false;
        boolean validPass = false;
        boolean validLogin = false;
        Integer numOfCorrectChars = 0;
        
        if (username == this.username) {
            validUser = true;
        }
        for (int i = 0; i < this.byteArr.length; i++) {
            try {
                if (this.byteArr[i] == this.loginByteArr[i]) {
                    numOfCorrectChars += 1;
                }
            } catch (IndexOutOfBoundsException e) {validPass = false; break;}
        }
        numOfCorrectChars = 0;
        if (numOfCorrectChars == this.byteArr.length) {
            validPass = true;
        }
        if (validUser == true && validPass == true) {
            validLogin = true;
        }
        return validLogin;
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
        this.userAmount = askInitialAmount();
        this.carPurchases = new ArrayList<>();
        this.travelPurchases = new ArrayList<>();
        this.rentPurchases = new ArrayList<>();
        this.foodPurchases = new ArrayList<>();
        this.budget = new ArrayList<>(); this.budget.add(this.carPurchases); this.budget.add(this.travelPurchases); this.budget.add(this.rentPurchases); this.budget.add(foodPurchases);
    }
    public double askInitialAmount() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the amount you'd like to add: ");
        double userAmount = Double.parseDouble(scanner.nextLine());
        scanner.close();
        return userAmount; 
        
    }

    public void addCarPurchases(double userAmount){
        carPurchases.add(userAmount);

    }
       
    public void addTravelPurchases(double userAmount){
        travelPurchases.add(userAmount);
    }
    
    public void addRentPurchases(double userAmount){
        rentPurchases.add(userAmount);
    }

    public void addFoodPurchases(double userAmount){
        foodPurchases.add(userAmount);

    }

    public void createUser() {
        Scanner in = new Scanner(System.in);
        char[] spChars = {'!', '@', '#', '$', '%', '^', '&', '*', '(', ')'};
        char[] nums = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
        char[] uppercaseLetters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        while (true) {
            String firstName = null;
            String lastName = null;
            String userName = null;
            String password = null;
            System.out.println("Welcome to the account creation tool. To begin, please enter your first name: ");
            firstName = in.nextLine();
            System.out.println("Please enter your last name: ");
            lastName = in.nextLine();
            System.out.println("Please enter a username. You will need this to log in to your account: ");
            userName = in.nextLine();
            while (true) {
                boolean containsSpChar = false;
                boolean containsNum = false;
                boolean containsUppercaseLetter = false;
                System.out.println("Lastly, please enter a password. Your password must be 8 characters long, contain a special character, a number, and an uppercase letter: ");
                password = in.nextLine();
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
}
    


public class Main {
    public static void main(String[] args) {
        User admin = new User();
        Scanner in = new Scanner(System.in);
        Integer uInp = null;
        while (true) {
            System.out.println("Welcome! Please enter the number next to the choice you would like to perform:\n1) Create a new account\n2) Login\n3) Exit");
            try {uInp = Integer.parseInt(in.nextLine());} catch (NumberFormatException e) {System.out.println("Please enter a number"); continue;}
            if (uInp == 1) {
                admin.createUser();
            } else if (uInp == 2) {
                
            } else if (uInp == 3) {
                break;
            } else {
                System.out.println("Please enter a number from 1 to 3");
                continue;
            }
        }
    }
}