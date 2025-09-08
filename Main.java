import java.util.*;

class Utils {
    public static Scanner sc = new Scanner(System.in);
}

class UserAuthentication {
    private static String registeredUsername;
    private static int registeredPin;

    public static void Register() {
        System.out.println("=== Register New Account ===");
        System.out.print("Enter new username: ");
        registeredUsername = Utils.sc.next();
        System.out.print("Enter new PIN: ");
        registeredPin = Utils.sc.nextInt();
        System.out.println("Account created successfully!\n");
    }

    public static boolean LogIn() {
        System.out.println("=== Log In ===");
        System.out.print("Enter username: ");
        String username = Utils.sc.next();
        System.out.print("Enter PIN: ");
        int userPin = Utils.sc.nextInt();

        if (username.equals(registeredUsername) && userPin == registeredPin) {
            System.out.println("Login successful!\n");
            return true;
        } else {
            System.out.println("Invalid credentials. Try again.\n");
            return false;
        }
    }
}

class ATMOperations {
    private double balance;
    private List<String> history;

    public ATMOperations() {
        this.balance = 0.0;
        this.history = new ArrayList<>();
    }

    public void withdraw() {
        System.out.print("Enter withdrawal amount: ");
        double amount = Utils.sc.nextDouble();
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            history.add("Withdrew: " + amount);
            System.out.println("Withdrawal successful! Remaining Balance: " + balance);
        } else {
            System.out.println("Invalid or insufficient funds!");
        }
    }

    public void deposit() {
        System.out.print("Enter deposit amount: ");
        double amount = Utils.sc.nextDouble();
        if (amount > 0) {
            balance += amount;
            history.add("Deposited: " + amount);
            System.out.println("Deposit successful! New Balance: " + balance);
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }

    public void checkBalance() {
        System.out.println("Your current balance is: " + balance);
    }

    public void showHistory() {
        System.out.println("=== Transaction History ===");
        if (history.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            for (String record : history) {
                System.out.println(record);
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Welcome to ATM System ===");
        System.out.println("Press 1 to Register, 2 to Log In");
        int choice = Utils.sc.nextInt();

        if (choice == 1) {
            UserAuthentication.Register();
        }

        if (!UserAuthentication.LogIn()) {
            System.out.println("Exiting system. Try again later.");
            return;
        }

        ATMOperations atm = new ATMOperations();
        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Check Balance");
            System.out.println("4. Transaction History");
            System.out.println("5. Exit");
            int option = Utils.sc.nextInt();

            switch (option) {
                case 1 -> atm.withdraw();
                case 2 -> atm.deposit();
                case 3 -> atm.checkBalance();
                case 4 -> atm.showHistory();
                case 5 -> {
                    System.out.println("Thank you for using ATM. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option!");
            }
        }
    }
}
