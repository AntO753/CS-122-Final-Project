import java.util.Scanner;
import java.util.ArrayList;

public class User extends Person {
    private double userAmount;
    private double checkingAccountBalance;
    private ArrayList<Double> carPurchases;
    private ArrayList<Double> travelPurchases;
    private ArrayList<Double> rentPurchases;
    private ArrayList<Double> foodPurchases;
    private ArrayList<ArrayList<Double>> budget; // 0 = car purchases; 1 = travel; 2 = rent; 3 = food
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
        Integer choice = 0;
        //Display Categories
        System.out.println("Select the budget catergory you'd like to add to: ");
        System.out.println("1. Car Purchases");
        System.out.println("2. Travel Purchases");
        System.out.println("3. Rent Purchases");
        System.out.println("4. Food Purchases");
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Please enter a number from 1-4.");
            choice = 0;
        }
        if (choice >= 1 && choice <= 4) {
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
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
    }
    
    public void displayTransactions(){
        System.out.println("Select the budget catergory you'd like to view transactions for");
        System.out.println("1. Car Purchases");
        System.out.println("2. Travel Purchases");
        System.out.println("3. Rent Purchases");
        System.out.println("4. Food Purchases");
        Integer choice = 0;
        try {choice = Integer.parseInt(scanner.nextLine());} catch (Exception e) {System.out.println("Please enter a number from 1-4."); choice = 0;}

        if (choice == 1) {
            if (budget.get(0).size() == 0) {
                System.out.println("This category is empty.");
            } else {
                System.out.println("Transactions for Car Purchases:");
                for (int i = 0; i < budget.get(0).size(); i++) {
                    System.out.printf("Transaction %d: $%.2f\n", i + 1, budget.get(0).get(i));
                }
            }
        } else if (choice == 2) {
            if (budget.get(1).size() == 0) {
                System.out.println("This category is empty.");
            } else {
                System.out.println("Transactions for Travel Purchases:");
                for (int i = 0; i < budget.get(1).size(); i++) {
                    System.out.printf("Transaction %d: $%.2f\n", i + 1, budget.get(1).get(i));
                }
            }
        } else if (choice == 3) {
            if (budget.get(2).size() == 0) {
                System.out.println("This category is empty.");
            } else {
                System.out.println("Transactions for Rent Purchases:");
                for (int i = 0; i < budget.get(2).size(); i++) {
                    System.out.printf("Transaction %d: $%.2f\n", i + 1, budget.get(2).get(i));
                }
            }
        } else if (choice == 4) {
            if (budget.get(3).size() == 0) {
                System.out.println("This category is empty.");
            } else {
                System.out.println("Transactions for Food Purchases:");
                for (int i = 0; i < budget.get(3).size(); i++) {
                    System.out.printf("Transaction %d: $%.2f\n", i + 1, budget.get(3).get(i));
                }
            }
        } else {
            System.out.println("Try again. Please enter a number from 1-4.");
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
        boolean validUser;
        boolean validPass;
        User loggedInUser;
        Integer loggedInUserIndex = -1;
        boolean flag = true;
        while (true) {
            validUser = false;
            validPass = false;
            loggedInUser = new User();
            loggedInUser.setFirstName("-1");
            for (int i = 0; i < usersList.size(); i++) {
                if (username.equals(usersList.get(i).username)) {
                    validUser = true;
                    loggedInUserIndex = i;
                    break;
                }
            }
            try {
                if (password.equals(usersList.get(loggedInUserIndex).password)) {
                    validPass = true;
                }
            } catch (IndexOutOfBoundsException e) {System.out.println("Your username is incorrect. Please try again."); flag = true; break;}
            if (validUser == true && validPass == true) {
                loggedInUser = usersList.get(loggedInUserIndex);
                flag = true;
                break;
            }
            if (flag == true) {
                break;
            }
        }
        return loggedInUser;
    }
}

