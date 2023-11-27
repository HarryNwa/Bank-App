package BankApp;

public class AdminAccount {
        private String adminAccountId;
        private String accountNumber;
        private String accountName;

        public String getAdminAccountId() {
            return adminAccountId;
        }

        public AdminAccount(String accountNumber, String accountName, String adminAccountId) {
            this.accountName = accountName;
            this.accountNumber = accountNumber;
            this.adminAccountId = adminAccountId;
        }

        public void setAdminAccountId(String adminAccountId) {
            this.adminAccountId = adminAccountId;
        }

        public String getAccountNumber() {
            return accountNumber;
        }

        public void setAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
        }

        public String getAccountName() {
            return accountName;
        }

        public void setAccountName(String accountName) {
            this.accountName = accountName;
        }

}
