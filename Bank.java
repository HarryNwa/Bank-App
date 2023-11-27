package BankApp;

import Exceptions.IllegalTransactionId;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class Bank {
    private String bank;
    private String getToken;
    private String transactionID;
    private BankAccount sender;
    private String generatedEscrowAccountNumber;

    List<BankAccount> accountNumber = new ArrayList<>();
    List<EscrowAccount> escrowAccounts = new ArrayList<>();
    List<String> escrowTokens = new ArrayList<>();
    List<Transactions> transactions = new ArrayList<>();
    List<AccountUser> users = new ArrayList<>();

    Admin admin = new Admin();

    public Bank(String bank) {
        this.bank = bank;
//        isGetToken = getToken;
    }

    public String register(String firstName, String lastName, int pin) {
        String accountName = firstName + " " + lastName;
        String generatedAccountNumber = generateAccountNumber();
        generatedEscrowAccountNumber = generateAccountNumber();
//        createEscrow(generatedEscrowAccountNumber, accountName);
        EscrowAccount escrow = new EscrowAccount(generatedEscrowAccountNumber, accountName);
        escrowAccounts.add(escrow);
        BankAccount account = new BankAccount(generatedAccountNumber, accountName, pin);
        accountNumber.add(account);
        AccountUser accountUser = new AccountUser(accountName);
        users.add(accountUser);
        return generatedAccountNumber;
    }
        public String createEscrow(){
//        EscrowAccount escrow = new EscrowAccount(generatedEscrowAccountNumber, accountName);
//        escrowAccounts.add(escrow);
        return generatedEscrowAccountNumber;

    }

    public String getBank() {
        return "Tranxactrust";
    }

    public String generateAccountNumber() {
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder transactionIDBuilder = new StringBuilder(10);

        while (transactionIDBuilder.length() < 10) {
            String randomInt = "7" + secureRandom.nextInt(900_000_000);
            transactionIDBuilder.append(randomInt);
        }

        if (transactionIDBuilder.length() > 10) {
            transactionIDBuilder.delete(10, transactionIDBuilder.length());
        }

        return transactionIDBuilder.toString();
    }
//        return  "21" + (int) (Math.random() * 10000) + (int) (Math.random() * 10000);
//        return accountNumber.size() + 1 + "";
//    }
    public String generateTransactionId(){
        Transactions transaction = new Transactions();
        return transaction.generateTransactionId();
//        return "" + (int) (Math.random() * 10000000) + (int) (Math.random() * 10000000);
    }

    public String generateToken() {
        return "TRNXT" + (int) (Math.random() * 1000) + (int) (Math.random() * 100);
//        return (escrowTokens.size() + 1 ) + "";

    }

    public double getBalance(String accountNumber, int pin) {
        return findAccount(accountNumber).getBalance(pin);

    }

    public double getEscrowBalance(String accountNumber) {
        return findEscrowAccount(accountNumber).getEscrowBalance();
    }

    public void deposit(String accNum, double amount) {
        if (amount > 0) {
            double depositAccount = findAccount(accNum).accountDeposit(amount);
            String transactionId = generateTransactionId();
//            transactionID = generateTransactionId();
            Transactions transaction = new Transactions(depositAccount, transactionId);
            transactions.add(transaction);
        }

    }

    public String depositEscrow(String accNum, double amount) {
        if (amount > 0) {
            double depositEscrow = findEscrowAccount(accNum).escrowDeposit(amount);
            getToken = generateToken();
            String transactionId = generateTransactionId();
            Transactions transaction = new Transactions(depositEscrow, getToken, transactionId);
            transactions.add(transaction);
            System.out.println(getToken);
            escrowTokens.add(getToken);

        }
        return getToken;
    }



    public String withdraw(double amount, String accNo, int pin) {
        double withdraw = findAccount(accNo).withdraw(amount, pin);
        String transactionId = generateTransactionId();
        Transactions transaction = new Transactions(withdraw, transactionId);
        transactions.add(transaction);
        return transactionId;
    }
    public String getEscrowTransaction_ByTransactionId(String transactionId) throws IllegalTransactionId {
        Transactions transaction = new Transactions();
        return transaction.findTransactionId(transactionId).getTransactionId();
    }

    public String withdrawFromEscrow(double amount, String from, String to) {
        BankAccount receiver = findAccount(to);
        receiver.accountDeposit(amount);
        EscrowAccount escrowSender = findEscrowAccount(from);
        double send = escrowSender.escrowRemit(amount);
        String transactionsId = generateTransactionId();
        Transactions transaction = new Transactions(send, receiver.getAccountNumber(), transactionsId);
        transactions.add(transaction);

        return transactionsId;
    }

    public String transfer(double amount, String from, String to, int pin) {
        BankAccount sender = findAccount(from);
        double send = sender.withdraw(amount, pin);
        BankAccount receiver = findAccount(to);
        String receive = String.valueOf(receiver.accountDeposit(amount));
        String transactionId = generateTransactionId();
        Transactions transaction = new Transactions(send, receive, transactionId);
        transactions.add(transaction);
        return transactionId;
    }
    public String transferToEscrow(double amount, String from, String to, int pin) {
        sender = findAccount(from);
        double send = sender.withdraw(amount, pin);
        EscrowAccount receiver = findEscrowAccount(to);
        receiver.escrowDeposit(amount);
        getToken = generateToken();
        escrowTokens.add(getToken);
        String transactionId = generateTransactionId();
        Transactions transaction = new Transactions(send, receiver.getEscrowAccountNumber(), getToken, transactionId);
        transactions.add(transaction);

        return transactionId;
    }
    public BankAccount getTransferToEscrowAccountNumber(){
        return sender;
    }
    public String getTransferToEscrowToken(){
        return "\nYour Payment Verification Code (PVC) is: " + getToken + "\nYou are to safeguard this code until delivery " +
                "is complete. You are required to release this PVC to your vendor for your purchase clearance and collection. " +
                "Thank you for choosing Tranxactrust.";
    }

    public  String getToken(){
        return getToken;
    }
    public String transferFromEscrow(double amount, String from, String to) {
        BankAccount receiver = findAccount(to);
        receiver.accountDeposit(amount);
        EscrowAccount escrowSender = findEscrowAccount(from);
        double send = escrowSender.escrowRemit(amount);
//        findEscrowToken(token);
        String transactionId = generateTransactionId();
        Transactions transaction = new Transactions(send, receiver.getAccountNumber(), transactionId);
        transactions.add(transaction);

        return transactionId;
    }

    public BankAccount findAccount(String accNum) {
        for (BankAccount account : accountNumber) {
            if (account.getAccountNumber().equals(accNum)) {
                return account;
            }
        }
        throw new IllegalArgumentException("Account does not exist");
    }
    public BankAccount findAccountByUsername(String accName) {
        for (BankAccount account : accountNumber) {
            if (account.getAccountName().equals(accName)) {
                return account;
            }
        }
        throw new IllegalArgumentException("Account name does not exist");
    }
    public EscrowAccount findAEscrowAccountByUsername(String accName) {
        for (EscrowAccount escrowAccount : escrowAccounts) {
            if (escrowAccount.getEscrowAccountName().equals(accName)) {
                return escrowAccount;
            }
        }
        throw new IllegalArgumentException("Account name does not exist");
    }

    public EscrowAccount findEscrowAccount(String accountNum) {
        for(EscrowAccount escrowAccount : escrowAccounts){
            if(escrowAccount.getEscrowAccountNumber().equals(accountNum)){
                return escrowAccount;
            }
        }
        throw  new IllegalArgumentException("Escrow Account Not Found");
    }

    public String findEscrowToken(String token) {
        for (String userEscrowToken : escrowTokens){
            if (userEscrowToken.equals(token)){
                return userEscrowToken;
            }
        }
        throw  new IllegalArgumentException("Token Not Found");
    }

    public String getEscrowToken () {

        return getToken;
    }

}
