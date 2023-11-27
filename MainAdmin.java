package BankApp;

import Exceptions.IllegalTransactionId;

import javax.swing.*;

import static java.lang.System.exit;

public class MainAdmin {
        private int userInput;
        private String adminAccountID;
        private String transactionId;
        private String adminName;
        private String adminPassword;
        Bank bank = new Bank("Tranxactrust");
        Admin admin = new Admin();
        Transactions transactions = new Transactions();

        public static void main(String[] args) throws IllegalTransactionId {
            MainAdmin mainAdmin = new MainAdmin();
            mainAdmin.welcomeAdmin();
            mainAdmin.mainMenu();

        }

        public void welcomeAdmin() throws IllegalTransactionId {
            userInput = Integer.parseInt(prompt("""
                HELLO! WELCOME TO TRANXACTRUST
                         
                                    
                1. Create an admin account
                                    
                2. Log in
                                    
                3. Exit"""));
            switch (userInput) {
                case 1 -> openAdminAccount();
                case 2 -> login();
                case 3 -> exitDiary();
                default -> exit(0);

            }
        }

        public void exitDiary() throws IllegalTransactionId {
            int exit = JOptionPane.showConfirmDialog(null, "Click yes to log out or no to return to app menu");
            if (exit == 0) {
                display(":( Tranxactrust app is closing>>>>\n Goodbye!");
                exit(0);
            }
            if (exit == 1) {
                welcomeAdmin();
            }
        }

        public void login() throws IllegalTransactionId {
            try {

                String userLogin = prompt("Enter username");
                adminName = userLogin;
                String userPassword = prompt("Enter password");
                adminPassword = userPassword;
                new AdminAccount(adminAccountID, adminName, adminPassword);
                if (!adminName.equals(userLogin)) {
                    display("Username does not exist");
                    login();
                }
                if (!adminPassword.equals(userPassword)) {
                    display("Invalid password");
                    login();
                }else {
                    display("Login successful");
                }
            } catch (NullPointerException error) {
                display(error.getMessage());
                welcomeAdmin();
            }
            mainMenu();
        }

        public void mainMenu() throws IllegalTransactionId {
            userInput = Integer.parseInt(prompt("""
                Welcome to Tranxactrust!
                press:
                                        
                1-> findAccount
                2-> findTransactionAccount
                3-> findAllRecords
                4-> checkBalance
                5-> Logout"""));
            switch (userInput) {
                case 1 -> findAccount();
                case 2 -> findTransactionId();
                case 3 -> findAllRecords();
                case 4 -> checkBalance();
                case 5 -> logout();
            }
        }

        public void logout() {
            try {
                int logoutOption = JOptionPane.showConfirmDialog(null,
                        "Click yes to log out or no to return to app menu");
                if (logoutOption == 0) {
                    welcomeAdmin();
                }
                if (logoutOption == 1) {
                    mainMenu();
                }
                if (logoutOption == 2) {
                    exit(0);
                }
            } catch (Exception | IllegalTransactionId error) {
                display(error.getMessage());
                logout();
            }
        }

        public void findTransactionId() throws IllegalTransactionId {
            String transactionId = prompt("Enter transaction ID");
            String reveal = String.valueOf(admin.findTransactionId(transactionId));

            display("Dear" + adminName + "your transaction shows this records " + reveal);

        }
        public void findAllRecords() {
            String userDetail = prompt("Enter account name or number");
            admin.getAccount(userDetail);
            String userRecords = transactions.findAll().toString();
            display("Dear" + userDetail + " your account records shows the following transactions: " + userRecords);
        }
        public void checkBalance(){
            String userDetail = prompt("Enter account name or number");
            String userPin = prompt("Enter your PIN number");

            String accountHolderName = bank.findAccount(userDetail).getAccountName();
            double getBalance = admin.getBalanceRecord(Integer.parseInt(userPin), userDetail);
            display("Dear" + accountHolderName + " your account balance is: " + getBalance);

        }

        public void findAccount() {
            try {
                String number = prompt("Enter account number");
                String display = String.valueOf(admin.getAccount(number));
                display(display);

            } catch (Exception error) {
                display(error.getMessage());
                findAccount();
            }
        }

        public void openAdminAccount() {
            try {
                String firstName = prompt("Enter first name");
                String lastName = prompt("Enter last name");
                adminPassword = prompt("Create password");


                this.adminName = firstName + " " + lastName;

                String name = "Hello, " + firstName + " " + lastName + ", welcome to Tranxactrust. " +
                        "We are happy to help you with safe payment online";

                display(name);

                adminAccountID = String.valueOf(admin.register(firstName, lastName, adminPassword));
                prompt("Dear " + firstName + " " + lastName + ", your account ID is: " + adminAccountID);


                display("Dear, " + firstName + " " + lastName + ", " +
                        "your Tranxactrust wallet ID number is: " + adminAccountID);

                mainMenu();
            }
            catch(Exception | IllegalTransactionId error){
                display(error.getMessage());
                openAdminAccount();
            }
        }

        public String prompt(String message){
            return JOptionPane.showInputDialog(null, message);
        }
        public void display(String message){
            JOptionPane.showInputDialog(message);
        }
    }

