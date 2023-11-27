package BankApp;

import Exceptions.IllegalTransactionId;

import java.util.ArrayList;
import java.util.List;

public class Admin {
        private String adminId;
        private String adminName;
        private String adminPassword;
        private String bank;
        List<AdminAccount> admins = new ArrayList<>();

        public Admin(String adminId, String adminName, String adminPassword, String bank){
            this.adminId = adminId;
            this.adminName = adminName;
            this.adminPassword = adminPassword;
            this.bank = bank;
        }

        public Admin(){

        }
        public String register(String adminFirstName, String adminLastName, String adminPassword){
            String adminName = adminFirstName + " " + adminLastName;
            String adminId = generateAdminId();
            AdminAccount adminAccount = new AdminAccount(adminId, adminName, adminPassword);
            admins.add(adminAccount);

            return adminId;
        }

        public String generateAdminId() {
            return "" + (int) (Math.random() * 100) + (int) (Math.random() * 100);
//        return admins.size() + 1 + adminName;
        }
        public Transactions findTransactionId(String transactionId) throws IllegalTransactionId {
            Transactions transactions = new Transactions();
            transactions = transactions.findTransactionId(transactionId);

            return transactions;
        }

        public String getAccount(String userName){
            Bank bank1 = new Bank(bank);
            return bank1.findAccount(userName).getAccount();
        }
        public double getBalanceRecord(int pin, String userAccount) {
            Bank bank1 = new Bank(bank);
            return bank1.getBalance(userAccount, pin);
        }

}
