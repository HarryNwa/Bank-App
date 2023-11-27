package BankApp;

import javax.swing.*;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Notifications {
    Transactions transactions = new Transactions();


    public void accountOpeningNotification(String firstName, String lastName, String ID, String escrowAccountNumber){

        String name = "Hello, " + firstName + " " + lastName + ", welcome to Tranxactrust. " +
                "\nWe are happy to help you with safe payment online. " +
                "\nYour Tranxactrust wallet ID number is: " + ID + " " +
                "\nYour Tranxactrust escrow ID number is: " + escrowAccountNumber + " " +
                "\nAccount created on: " + getDate() + " " + getTime();

        display(name);
    }
    public String getCurrentFormattedDateTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // Define your custom format here
        return currentDateTime.format(formatter);
    }
    public String getDate() {
        Locale locale = new Locale("en", "US");
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, locale);
        String date = dateFormat.format(new Date());
//        System.out.print(date);
        return date;
    }
    public String getTime() {
        Locale locale = new Locale("en", "US");
        DateFormat dateFormat = DateFormat.getTimeInstance(DateFormat.DEFAULT, locale);
        String date = dateFormat.format(new Date());
//        System.out.print(date);
        return date;
    }
    public String getDeposit(String getBank, String firstName, String lastName, String accountNumber, String transactionID, double depositAmount, String payerName, double balance){
        String info = getBank +
                "\nTransaction type: Credit" +
                "\nAccount number: " + accountNumber +
                "\nTransaction ID: " + transactionID +
                "\nDescription: " + firstName + " " + lastName +
                "\nAmount: $" + depositAmount +
                "\nSender: " + payerName +
                "\nBalance: $" + balance +
                "\nDate: " + getDate() + " " + getTime();
        display(info);

        return info;
    }
    public String getEscrowDeposit(String getBank, String firstName, String lastName, String accountNumber, String transactionID, double depositAmount, String payerName, String escrowToken, double balance){

        String info = getBank +
                "\nTransaction type: Escrow credit" +
                "\nAccount number: " + accountNumber +
                "\nTransaction ID: " + transactionID +
                "\nDescription: " + firstName + " " + lastName +
                "\nAmount: $" + depositAmount +
                "\nSender: " + payerName +
                "\nTransaction Token: " + escrowToken +
                "\nBalance: $" + balance +
                "\nDate: " + getDate() + " " + getTime();
        display(info);
        transactions = new Transactions(getBank, firstName, lastName, accountNumber, transactionID, depositAmount,
                payerName, escrowToken, balance);

        return info;
    }
    public void goFindAccount(String display){

        display(display);
    }
    public void goFindAccountUser(String display, String another){

        display(display);
    }
    public void getWithdraw(String getBank, String firstName, String lastName, String description, String transactionID,
                            double amount, String withDrawerNumber, double balance){
        display(getBank +
                "\nTransaction type: Debit" +
                "\nAccount number: " + withDrawerNumber +
                "\nDescription: " + firstName + " " + lastName + "/" + description +
                "\nTransaction ID: " + transactionID +
                "\nAmount: $" + amount +
                "\nBalance: $" + balance +
                "\nDate: " + getDate() + " " + getTime());
    }
    public void getEscrowWithdraw(String getBank, String firstName, String lastName, String description,
                                  String transactionID, double amount, String withDrawerNumber, double withDrawerBalance){
        display(getBank +
                "\nTransaction type: Debit" +
                "\nAccount number: " + withDrawerNumber +
                "\nDescription: " + firstName + " " + lastName + "/" + description +
                "\nTransaction ID: " + transactionID +
                "\nAmount: $" + amount +
                "\nBalance: $" + withDrawerBalance +
                "\nDate: " + getDate() + " " + getTime());
    }

    public void display(String message){
        JOptionPane.showMessageDialog(null, message);
    }

    public void getTransfer(String getBank, String receiver, String depositor, String description,
                            String receiverAccountNumber, String depositorAccountNumber, String transactionID,
                            double amount, double balance) {
        display(getBank +
                "\nTransaction type: Credit Alert" +
                "\nSender's Name: " + depositor +
                "\nSender Account Number: " + depositorAccountNumber +
                "\nDestination Name: " + receiver +
                "\nReceiver Account NUmber: " + receiverAccountNumber +
                "\nDescription: " + description +
                "\nTransaction ID: " + transactionID +
                "\nAmount: $" + amount +
                "\nBalance: $" + balance +
                "\nDate: " + getDate() + " " + getTime());


        display(getBank +
                "\nTransaction type: Transfer" +
                "\nDestination Name: " + receiver +
                "\nReceiver Account NUmber: " + receiverAccountNumber +
                "\nDescription: " + description +
                "\nTransaction ID: " + transactionID +
                "\nAmount: $" + amount +
                "\nBalance: $" + balance +
                "\nDate: " + getDate() + " " + getTime());

    }

    public Transactions getTransferToEscrowDebit(String getBank, String receiver, String depositor, String description,
                                          String receiverAccountNumber, String transactionID,
                                          double amount, double balance) {
        display(getBank +
                "\nTransaction type: Debit Alert" +
                "\nSender's " + depositor +
                "\nDestination Name: " + receiver +
                "\nReceiver Account Number: " + receiverAccountNumber +
                "\nDescription: " + description +
                "\nTransaction ID: " + transactionID +
                "\nAmount: $" + amount +
                "\nBalance: $" + balance +
                "\nDate: " + getDate() + " " + getTime());



        return null;
    }
    public Transactions getTransfersToEscrowCredit(String getBank, String receiver, String depositor, String description,
                                          String receiverAccountNumber, String transactionID,
                                          double amount, double balance) {
        display(getBank +
                "\nTransaction type: Escrow Credit Transfer" +
                "\nDestination Name: " + receiver +
                "\nSender " + depositor +
                "\nReceiver Account Number: " + receiverAccountNumber +
                "\nDescription: " + description +
                "\nTransaction ID: " + transactionID +
                "\nAmount: $" + amount +
                "\nBalance: $" + balance +
                "\nDate: " + getDate() + " " + getTime());
        return null;
    }
    public Transactions getTransfersToAccountCredit(String getBank, String receiver, String depositor, String description,
                                          String receiverAccountNumber, String transactionID,
                                          double amount) {
        display(getBank +
                "\nTransaction type: Account Credit Transfer" +
                "\nDestination Name: " + receiver +
                "\nSender " + depositor +
                "\nReceiver Account Number: " + receiverAccountNumber +
                "\nDescription: " + description +
                "\nTransaction ID: " + transactionID +
                "\nAmount: $" + amount +
                "\nDate: " + getDate() + " " + getTime());
        return null;
    }
    public Transactions getTransferFromEscrowDebit(String getBank, String receiver, String depositor, String description,
                                                 String depositorAccountNumber, String transactionID,
                                                 double amount, double balance) {
        display(getBank +
                "\nTransaction type: Debit Alert" +
                "\nSender's " + depositor +
                "\nDestination Name: " + receiver +
                "\nSender Account Number: " + depositorAccountNumber +
                "\nDescription: " + description +
                "\nTransaction ID: " + transactionID +
                "\nAmount: $" + amount +
                "\nBalance: $" + balance +
                "\nDate: " + getDate() + " " + getTime());



        return null;
    }
}
