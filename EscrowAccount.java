package BankApp;

import java.util.ArrayList;
import java.util.List;

public class EscrowAccount {
    private String accountNumber;
    private String accountName;
    private String token;
    private double balance;

    Bank bank = new Bank("Tranxactrust");

    public EscrowAccount(String accountNumber, String accountName){
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.token = token;
    }
    public EscrowAccount(){

    }

    public double escrowDeposit(double amount){
        if (amount > 0) {
            balance += amount;
        }
        if (amount < 0){
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
        return balance;
    }

    public double escrowRemit(double amount){
        if (amount <= balance) {
            balance -= amount;
        }
        return balance;

    }

    public String getEscrowAccount() {
        return "Account number: " + accountNumber + "\nAccount name: " + accountName;
    }
    public double getEscrowBalance(){
        return balance;
    }

    public void setAccountNumber(String accountNum) {
        this.accountNumber = accountNum;
    }

    public String getEscrowAccountNumber() {
        return accountNumber;
    }
    public String getEscrowAccountName() {
        return accountName;
    }
}
