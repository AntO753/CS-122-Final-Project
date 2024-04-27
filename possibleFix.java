import java.util.Scanner;

public class possibleFix {
    public static void main(String[] args) {
        User currUser = new User();
        Scanner in = new Scanner(System.in);
        boolean loggedIn = false;

        while (true) {
            if (loggedIn) {
                // User is logged in, provide menu options
                System.out.printf("Welcome %s, %s. Please select which option you would like to perform today:\n", currUser.getLastName(), currUser.getFirstName());
                System.out.println("1) Add a Transaction\n2) View Balance\n3) Print ");
                int choice = getIntInput(in);
                if (choice == 1) {
                    Double amt = 0.0;
                    while (true) {
                        System.out.println("Please enter the amount of money you would like to add:");
                        amt = getDoubleInput(in);
                        if (amt != null) {
                            break;
                        }
                    }
                    currUser.addMoneyToCategory(amt);
                } else if (choice == 2) {
                    Double balance = currUser.getCheckingAccountBalance();
                    System.out.printf("Your current balance is %f\n", balance);
                } else if (choice == 3) {
                    // Perform printing operation if needed
                } else {
                    System.out.println("Invalid choice. Please enter a number from 1 to 3.");
                }
            } else {
                // User is not logged in, show login options
                System.out.println("Welcome! Please enter the number next to the choice you would like to perform:");
                System.out.println("1) Create a new account\n2) Login\n3) Exit");
                int choice = getIntInput(in);

                if (choice == 1) {
                    currUser.createUser();
                } else if (choice == 2) {
                    while (true) {
                        System.out.println("Please enter your username:");
                        String username = in.nextLine();
                        System.out.println("Please enter your password:");
                        String password = in.nextLine();
                        currUser = currUser.login(username, password);
                        if (currUser != null) {
                            loggedIn = true;
                            break;
                        } else {
                            System.out.println("Incorrect username or password. Please try again.");
                        }
                    }
                } else if (choice == 3) {
                    System.out.println("Exiting...");
                    break;
                } else {
                    System.out.println("Invalid choice. Please enter a number from 1 to 3.");
                }
            }
        }
        in.close();
    }

    // Helper method to get integer input
    public static int getIntInput(Scanner scanner) {
        int input = -1;
        while (true) {
            try {
                input = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
        return input;
    }

    // Helper method to get double input
    public static Double getDoubleInput(Scanner scanner) {
        Double input = null;
        while (true) {
            try {
                input = Double.parseDouble(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
        return input;
    }
}
