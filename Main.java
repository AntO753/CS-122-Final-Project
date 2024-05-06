import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        User emptyUser = new User();
        User currUser = new User();
        emptyUser.setFirstName("-1");
        Scanner in = new Scanner(System.in);
        boolean flag = false;
        boolean loggedIn = false;
        while (true) {
            Integer uInp = 0;
            if (flag == true) {
                break;
            }
            System.out.println("Welcome! Please enter the number next to the choice you would like to perform:\n1) Create a new account\n2) Login\n3) Exit");
            String input = in.nextLine();
            try {uInp = Integer.parseInt(input);} catch (NumberFormatException e) {System.out.println("Please enter a number"); continue;}
            if (uInp == 1) {
                emptyUser.createUser();
            } else if (uInp == 2) {
                while (true) {
                    String username = null;
                    String password = null;
                    System.out.println("Please enter your username:");
                    username = in.nextLine();
                    System.out.println("Please enter your password:");
                    password = in.nextLine();
                    currUser = emptyUser.login(username, password);
                    if (currUser.getFirstName().equals("-1")) {
                        System.out.println("Either your username or password is incorrect");
                        continue;
                    } else {
                        flag = true;
                        loggedIn = true;
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
        Integer uInp2 = 0;
        while (true) {
            boolean flag2 = false;
            if (emptyUser.getFirstName().equals("-1") && loggedIn == false) {
                flag2 = true;
                break;
            } else {
                System.out.printf("Welcome %s, %s. Please select which option you would like to perform today:\n", currUser.getLastName(), currUser.getFirstName());
                System.out.println("1) Transaction\n2) View Balance\n3) Get Transactions\n4) Exit");
                try {uInp2 = Integer.parseInt(in.nextLine());} catch (NumberFormatException e) {System.out.println("Please enter a number"); continue;}
                if (uInp2 == 1) {
                    Double amt = 0.0;  
                    while (true) {
                        System.out.println("Please enter the amount of money you would like to add:");
                        try {amt = Double.parseDouble(in.nextLine());} catch (NumberFormatException r) {System.out.println("Please enter a dollar amount"); continue;}
                        break;
                    }
                    currUser.addMoneyToCategory(amt);
                } else if (uInp2 == 2) {
                    Double balance = currUser.getCheckingAccountBalance();
                    System.out.printf("Your current balance is %.2f\n", balance);
                } else if (uInp2 == 3) {
                    currUser.displayTransactions();
                } else if (uInp2 == 4) {
                    System.out.println("Goodbye!");
                    flag2 = true;
                    break;
                } else {
                    System.out.println("Please enter a number from 1-3.");
                    continue;
                }
                if (flag2 == true) {
                    break;
                }
            }
            if (flag2 == true) {
                break;
            }
        }
        in.close();
    }
}