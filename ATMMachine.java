package BankApp;

import Exceptions.IllegalTransactionId;

import javax.swing.*;

import static java.lang.System.exit;

public class ATMMachine {
    private int userInput;
    private String userName;
    private String firstName;
    private String lastName;
    private int pin;
    private double balance;
    private String account;
    private  String escrowToken;
    Bank bank = new Bank("Tranxactrust");
    Notifications notifications = new Notifications();
    Transactions transactions = new Transactions();
//    BankAccount bankAccount = new BankAccount();


    public static void main(String[] args) {

        ATMMachine machine = new ATMMachine();
        machine.welcomeMenu();
        machine.mainMenu();
    }
    public void welcomeMenu() {
        try {

            userInput = Integer.parseInt(prompt("""
                    HELLO! WELCOME TO TRANXACTRUST
                             
                                        
                    1. Create a free account
                                        
                    2. Log in
                                        
                    3. Exit"""));
            if (userInput == 1) openAccount();
            if (userInput == 2) login();
            if (userInput == 3) exitBank();
            if (userInput == 0) exit(0);
            else {
                welcomeMenu();
            }
        } catch (Exception error) {
            display(error.getMessage());
            welcomeMenu();
        }
    }

    public void exitBank() {
        try {

            int exit = JOptionPane.showConfirmDialog(null, "Click yes to log out or no to return to app menu");
            if (exit == 0) {
                display(":( Tranxactrust app is closing>>>>\n Goodbye!");
                exit(0);
            }
            if (exit == 1) {
                welcomeMenu();
            }
        } catch (Exception error) {
            display(error.getMessage());
            exitBank();
        }
    }

    public void login() throws NullPointerException {
        try {

            String userLogin = prompt("Enter username");
            userName = userLogin;
            int userPassword = Integer.parseInt(prompt("Enter password"));
            pin = userPassword;
            new BankAccount(account, userName, pin);
            if (!userName.equals(userLogin)) {
                display("Username does not exist");
                login();
            }
            if (pin != userPassword) {
                display("Invalid password");
                welcomeMenu();
            }
            display("Login successful");
        } catch (NullPointerException error) {
            display(error.getMessage());
            welcomeMenu();
        }
        mainMenu();
    }
    public void mainMenu() {
        try {
            userInput = Integer.parseInt(prompt("""
                    Welcome to Tranxactrust!
                    press:
                                        
                    1-> deposit
                    2-> withdraw
                    3-> transfer
                    4-> checkBalance
                    5-> findAccount
                    6-> findAccountUser
                    7-> findEscrowAccountUser
                    8-> escrowRemitter
                    9-> depositEscrow
                    10-> transferToEscrow
                    11-> transferFromEscrow
                    12-> verifyEscrowToken
                    13-> checkEscrowBalance
                    14-> Logout"""));
            if (userInput == 1) deposit();
            if (userInput == 2) withdraw();
            if (userInput == 3) transfer();
            if (userInput == 4) checkBalance();
            if (userInput == 5) findAccount();
            if (userInput == 6) findAccountUser();
            if (userInput == 7) findEscrowAccountUser();
            if (userInput == 8) escrowRemitter();
            if (userInput == 9) depositEscrow();
            if (userInput == 10) transferToEscrow();
            if (userInput == 11) transferFromEscrow();
            if (userInput == 12) verifyEscrowToken();
            if (userInput == 13) checkEscrowBalance();
            if (userInput == 14) logout();

            else {
                mainMenu();
            }
        } catch (Exception error) {
            display(error.getMessage());
            mainMenu();
        }
    }


    public void logout() {
        try {
            int logoutOption = JOptionPane.showConfirmDialog(null,
                    "Click yes to log out or no to return to app menu");
            if (logoutOption == 0) {
                welcomeMenu();
            }
            if (logoutOption == 1) {
                mainMenu();
            }
            if (logoutOption == 2) {
                exit(0);
            }
        } catch (Exception error) {
            display(error.getMessage());
            logout();
        }
    }

    public void checkBalance() {
        try {
            int pin = Integer.parseInt(prompt("Enter pin"));
            String number = prompt("Enter account number");
            String balance = String.valueOf(bank.getBalance(number, pin));
            display("Account balance for " + number + " is " + balance);
            mainMenu();
        } catch (Exception error) {
            display(error.getMessage());
            checkBalance();
        }
    }
        public void checkEscrowBalance () {
            try {
                String number = prompt("Enter account number");
                String balance = String.valueOf(bank.getEscrowBalance(number));
                display("Account balance for " + number + " is " + balance);
                mainMenu();
            } catch (Exception error) {
                display(error.getMessage());
                checkBalance();
            }
        }


    public void transfer() {
        try {
            int pinNumber = Integer.parseInt(prompt("Enter pin number"));
            String receiverAccountNumber = prompt("Enter receiver account number");
            String depositorAccountNumber = prompt("Enter depositor account number");
            double amount = Double.parseDouble(prompt("Enter amount"));
            String description = prompt("Enter description");

            bank.transfer(amount, depositorAccountNumber, receiverAccountNumber, pinNumber);
            String receiver = bank.findAccount(receiverAccountNumber).getAccount();
            String depositor = bank.findAccount(depositorAccountNumber).getAccount();

            String getBank = bank.getBank();
            String transactionID = bank.transfer(amount, depositorAccountNumber, receiverAccountNumber, pinNumber);

            notifications.getTransfer(getBank, receiver, depositor, description, receiverAccountNumber,
                    depositorAccountNumber, transactionID, amount, balance);


            mainMenu();
        } catch (Exception error) {
            display(error.getMessage());
            transfer();
        }
    }
    public void transferToEscrow() {
        try {
            int pinNumber = Integer.parseInt(prompt("Enter pin number here"));
            String receiverAccountNumber = prompt("Enter receiver account number");
            String depositorAccountNumber = prompt("Enter depositor account number");
            double amount = Double.parseDouble(prompt("Enter amount"));
            String description = prompt("Enter description");

            String receiver = bank.findEscrowAccount(receiverAccountNumber).getEscrowAccountName();
            String depositor = bank.findAccount(depositorAccountNumber).getAccount();
            String transactionID = bank.transferToEscrow(amount, depositorAccountNumber,
                    receiverAccountNumber, pinNumber);

            double currentBalance = bank.getBalance(depositorAccountNumber, pinNumber);
            double escrowBalance = bank.getEscrowBalance(receiverAccountNumber);
            String getBank = bank.getBank();

            notifications.getTransferToEscrowDebit(getBank, receiver, depositor,
                    description, receiverAccountNumber, transactionID, amount, currentBalance);
            JOptionPane.showConfirmDialog(null,"Dear Esteemed Shopper, kindly go here to see " +
                    "your payment verification code. Kindly note that you need this code for your purchase clearance " +
                    "and collection from your vendor.");
            display(bank.getTransferToEscrowToken());

            notifications.getTransfersToEscrowCredit(getBank, receiver, depositor,
                    description, receiverAccountNumber, transactionID, amount, escrowBalance);

            mainMenu();
        } catch (Exception error) {
            display(error.getMessage());
            transferToEscrow();
        }
    }public void transferFromEscrow() {
        try {
            verifyEscrowToken();
            String receiverAccountNumber = prompt("Enter receiver account number");
            String depositorAccountNumber = prompt("Enter depositor account number");
            double amount = Double.parseDouble(prompt("Enter amount"));
            String description = prompt("Enter description");

            String receiver = bank.findAccount(receiverAccountNumber).getAccountName();
            String depositor = bank.findEscrowAccount(depositorAccountNumber).getEscrowAccount();
            String transactionID = bank.transferFromEscrow(amount, depositorAccountNumber,
                    receiverAccountNumber);

            double escrowBalance = bank.getEscrowBalance(depositorAccountNumber);
            String getBank = bank.getBank();

            notifications.getTransferFromEscrowDebit(getBank, receiver, depositor,
                    description, depositorAccountNumber,
                    transactionID, amount, escrowBalance);

            notifications.getTransfersToAccountCredit(getBank, receiver, depositor,
                    description, receiverAccountNumber,
                    transactionID, amount);

            mainMenu();
        } catch (Exception error) {
            display(error.getMessage());
            transferToEscrow();
        }
    }

    public void withdraw() {
        try {
            int pinNumber = Integer.parseInt(prompt("Enter pin number"));
            String withDrawerNumber = prompt("Enter account number");
            double amount = Double.parseDouble(prompt("Enter amount"));
            String description = prompt("Enter description");
            bank.findAccount(withDrawerNumber).getAccount();
            bank.withdraw(amount, withDrawerNumber, pinNumber);

            balance = bank.getBalance(withDrawerNumber, pinNumber);
            String getBank = bank.getBank();
            String transactionID = bank.withdraw(amount, withDrawerNumber, pinNumber);
            notifications.getWithdraw(getBank, firstName, lastName, description,
                    transactionID, amount, withDrawerNumber, balance);


            mainMenu();
        } catch (Exception error) {
            display(error.getMessage());
            withdraw();
        }
    }
    public void escrowRemitter() {
        try {
            verifyEscrowToken();
            verifyTransactionId();
//            String transactionId = prompt("Enter transaction Id");
            String withDrawerNumber = prompt("Enter your account number");
            double amount = Double.parseDouble(prompt("Enter expected amount"));
            String description = prompt("Enter description (What you sold)");
            bank.findAccount(withDrawerNumber).getAccount();
            escrowToken = bank.getToken();
            String transactionID = bank.withdrawFromEscrow(amount, withDrawerNumber, escrowToken);

            double withDrawerBalance = bank.getEscrowBalance(withDrawerNumber);
            String getBank = bank.getBank();
            notifications.getEscrowWithdraw(getBank, firstName, lastName, description,
                    transactionID, amount, withDrawerNumber, withDrawerBalance);

            mainMenu();
        } catch (Exception error) {
            display(error.getMessage());
            escrowRemitter();
        }
    }

    public void verifyEscrowToken(){
        try {
            int count = 0;
            String token = prompt("Enter PVC");
            bank.findEscrowToken(token);
                count++;
                if (count > 1){
                    display("Token already verified");
                }
                display("Token successfully verified!!!");

        } catch (Exception error) {
            display(error.getMessage());
            verifyEscrowToken();
        }
    }
    private void verifyTransactionId() {
        try {
            int counter = 0;
            String idVerifier = prompt("Enter transaction ID");
            String transact = bank.getEscrowTransaction_ByTransactionId(idVerifier);
            System.out.println(transact);
            counter++;
            if (counter > 1){
                display("Incorrect transaction Id");
            }else {
                display("Transaction ID verification successful.");
            }
        } catch (IllegalTransactionId e) {
            throw new RuntimeException(e);
        }
    }
    public void findAccount() {
        try {
            String number = prompt("Enter account number");
            String display = bank.findAccount(number).getAccount();
            notifications.goFindAccount(display);
//            display(display);

        } catch (Exception error) {
            display(error.getMessage());
            findAccount();
        }
    }
    public void findAccountUser() {
        try {
            String name = prompt("Enter account name");
            String display = bank.findAccountByUsername(name).getAccount();
//            BankAccount bankAccount = new BankAccount();
//            String another = bank.findAccount().getAccount();
            notifications.goFindAccount(display);
//            display(display);

        } catch (Exception error) {
            display(error.getMessage());
            findAccountUser();
        }
    }
    public void findEscrowAccountUser() {
        try {
            String name = prompt("Enter account name");
            String display = bank.findAEscrowAccountByUsername(name).getEscrowAccount();
//            BankAccount bankAccount = new BankAccount();
//            String another = bank.findAccount().getAccount();
            notifications.goFindAccount(display);
//            display(display);

        } catch (Exception error) {
            display(error.getMessage());
            findEscrowAccountUser();
        }
    }

    public void deposit() {
        try {
            int pinNumber = Integer.parseInt(prompt("Enter your pin"));
            if (pin != pinNumber){
                display("Incorrect pin. Please retry");
                deposit();
            }
            else {
                String payerName = prompt("Enter your name");
                String accountNumber = (prompt("Enter account number "));
                double deposit = Integer.parseInt(prompt("Enter amount"));

                bank.deposit(accountNumber, deposit);
                balance = bank.getBalance(accountNumber, pinNumber);
                String getBank = bank.getBank();

                String transactionID = String.valueOf(bank.generateTransactionId());

                notifications.getDeposit(getBank, firstName, lastName, accountNumber, transactionID, deposit, payerName, balance);

                mainMenu();
            }
        } catch (Exception error) {
            display(error.getMessage());
            deposit();
        }
    }
    public void depositEscrow() {
        try {
            String payerName = prompt("Enter your name");
                String accountNumber = (prompt("Enter account number "));
                double depositAmount = Integer.parseInt(prompt("Enter amount"));

                escrowToken = bank.depositEscrow(accountNumber, depositAmount);
                balance = bank.getEscrowBalance(accountNumber);
                String getBank = bank.getBank();


            String transactionID = String.valueOf(bank.generateTransactionId());

            notifications.getEscrowDeposit(getBank, firstName, lastName, accountNumber, transactionID, depositAmount,
                    payerName, escrowToken, balance);

                mainMenu();

            } catch (Exception error) {
            display(error.getMessage());
            depositEscrow();
        }
    }

    public void openAccount() {
        try {
            firstName = prompt("Enter first name");
            lastName = prompt("Enter last name");
            pin = Integer.parseInt(prompt("Create pin"));


            this.userName = firstName + " " + lastName;
            account = bank.register(firstName, lastName, pin);
            String escrowAccountNumber = bank.createEscrow();

            notifications.accountOpeningNotification(firstName, lastName, account, escrowAccountNumber);

            mainMenu();
        }
        catch(Exception error){
            display(error.getMessage());
            openAccount();
        }
    }

    public void display(String message){
        JOptionPane.showMessageDialog(null, message);
    }

    public String prompt(String input){
        return JOptionPane.showInputDialog(input);
    }

}

