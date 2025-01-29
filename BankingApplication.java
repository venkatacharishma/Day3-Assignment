// Banking Application

// Base Account class
abstract class Account {
    protected String accountNumber;
    protected double balance;

    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful. New balance: " + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawal successful. New balance: " + balance);
        } else {
            System.out.println("Insufficient balance or invalid amount.");
        }
    }

    public abstract void displayAccountType();
}

// SavingsAccount class
class SavingsAccount extends Account {
    private double interestRate;

    public SavingsAccount(String accountNumber, double balance, double interestRate) {
        super(accountNumber, balance);
        this.interestRate = interestRate;
    }

    public void addInterest() {
        double interest = balance * (interestRate / 100);
        balance += interest;
        System.out.println("Interest added. New balance: " + balance);
    }

    @Override
    public void displayAccountType() {
        System.out.println("Account Type: Savings Account");
    }
}

// CurrentAccount class
class CurrentAccount extends Account {
    private double overdraftLimit;

    public CurrentAccount(String accountNumber, double balance, double overdraftLimit) {
        super(accountNumber, balance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && (balance + overdraftLimit) >= amount) {
            balance -= amount;
            System.out.println("Withdrawal successful. New balance: " + balance);
        } else {
            System.out.println("Overdraft limit exceeded or invalid amount.");
        }
    }

    @Override
    public void displayAccountType() {
        System.out.println("Account Type: Current Account");
    }
}

// Customer class
class Customer {
    private String name;
    private Account account;

    public Customer(String name, Account account) {
        this.name = name;
        this.account = account;
    }

    public void deposit(double amount) {
        account.deposit(amount);
    }

    public void withdraw(double amount) {
        account.withdraw(amount);
    }

    public void transferFunds(Customer recipient, double amount) {
        if (amount > 0 && account.getBalance() >= amount) {
            account.withdraw(amount);
            recipient.account.deposit(amount);
            System.out.println("Transfer successful. Transferred " + amount + " to " + recipient.name);
        } else {
            System.out.println("Transfer failed due to insufficient balance or invalid amount.");
        }
    }

    public void displayAccountDetails() {
        System.out.println("Customer Name: " + name);
        System.out.println("Account Number: " + account.getAccountNumber());
        System.out.println("Account Balance: " + account.getBalance());
        account.displayAccountType();
    }
}

// Main class
public class BankingApplication {
    public static void main(String[] args) {
        // Create accounts
        SavingsAccount savings = new SavingsAccount("SA123", 5000, 3.5);
        CurrentAccount current = new CurrentAccount("CA456", 10000, 2000);

        // Create customers
        Customer customer1 = new Customer("Alice", savings);
        Customer customer2 = new Customer("Bob", current);

        // Operations
        customer1.displayAccountDetails();
        customer1.deposit(2000);
        customer1.withdraw(1000);
        savings.addInterest();

        customer2.displayAccountDetails();
        customer2.withdraw(11000);
        customer2.deposit(500);

        // Transfer funds
        customer1.transferFunds(customer2, 1500);

        // Final account details
        customer1.displayAccountDetails();
        customer2.displayAccountDetails();
    }
}