package BankApp;

import Exceptions.IllegalTransactionId;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Transactions {
        private double amount;
        private String accountNumber;
        private String bank;
        private String accountName;
        private String payerName;
        private String escrowToken;
        private double balance;
        private LocalDateTime transactionDate = LocalDateTime.now();
        private String transactionId;

        List<Transactions> transactions = new ArrayList<>();
        EscrowAccount escrowAccount = new EscrowAccount();
        Bank bank1 = new Bank(bank);
        public Transactions(String getBank, String firstName, String lastName, String accountNumber, String transactionID, double depositAmount, String payerName, String escrowToken, double balance){
            this.bank = getBank;
            this.accountName = firstName + " " + lastName;
            this.accountNumber = accountNumber;
            this.transactionId = transactionID;
            this.amount = depositAmount;
            this.payerName = payerName;
            this.escrowToken = escrowToken;
            this.balance = balance;
    }


        public Transactions(double amount, String transactionId){
            this.amount = amount;
            this.transactionId = transactionId;
        }
        public Transactions(double amount, String depositEscrowToken, String transactionId){
            this.amount = amount;
            this.escrowToken = depositEscrowToken;
            this.transactionId = transactionId;
        }
        public Transactions(double amount, String receiver, String depositEscrowToken, String transactionId){
            this.amount = amount;
            this.accountNumber = receiver;
            this.escrowToken = depositEscrowToken;
            this.transactionId = transactionId;
        }
        public Transactions(){


        }

    public Transactions findTransactionId(String transactionId) throws IllegalTransactionId {
        for (Transactions transactions : this.transactions) {
            if (transactions.getTransactionId().equals(transactionId)) {
                return transactions;
            }
        }
        throw new IllegalTransactionId("Transaction Id mismatch");
    }

    public String generateTransactionId() {
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder transactionIDBuilder = new StringBuilder(16);

        while (transactionIDBuilder.length() < 16) {
            int randomInt = 100_000_000 + secureRandom.nextInt(900_000_000);
            transactionIDBuilder.append(randomInt);
        }

        if (transactionIDBuilder.length() > 16) {
            transactionIDBuilder.delete(16, transactionIDBuilder.length());
        }

        return transactionIDBuilder.toString();
    }


        public Iterable<Transactions> findAll () {
            return transactions;
        }


        public String getTransactionId(){
            return transactions.toString();
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public String getAccount() {
            return accountNumber;
        }

        public void setAccount(String account) {
            this.accountNumber = account;
        }


    public String getEscrowToken(String escrowToken) {
        return bank1.findEscrowToken(escrowToken);
    }
}

