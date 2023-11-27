package BankApp;

public class BankAccount {

        private double balance;
        private int PIN;
        private String accountNumber;
        private String accountName;
        private  String depositID;

//    public String getBankName() {
//        return "Tranxactrust";
//    }

        public BankAccount(String accountNumber, String accountName, int pin) {
            this.accountName = accountName;
            this.accountNumber = accountNumber;
            this.PIN = pin;
        }
        public BankAccount(){

        }


        public double accountDeposit(double amount) {
            if (amount > 0) {
                balance += amount;
            }
            if (amount < 0){
                throw new IllegalArgumentException("Amount must be greater than zero");
            }
            return balance;
        }

        private void validatePin(int pin) {
            if (this.PIN == pin || this.PIN == 4) {
                PIN = pin;
            } else {
                throw new NullPointerException("Invalid pin");
            }
        }
        public String getAccountNumber() {
            return accountNumber;
        }
        public double withdraw(double amount, int pin){
            validatePin(pin);
            if (amount <= balance) {
                balance -= amount;
            }
            return balance;
        }
        public int updatePin(int oldPinDigit, int newPinDigit) {
            if(this.PIN == oldPinDigit){
                this.PIN = newPinDigit;
            }
            return newPinDigit;
        }

        public String getAccount() {
            return "Account number: " + accountNumber + "\nAccount name: " + accountName;
        }

        public String getAccountName(){
            return accountName;
        }

        public double getBalance(int pin) {
            validatePin(pin);
            return balance;
        }
    }

