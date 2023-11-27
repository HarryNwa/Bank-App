package BankApp;

import java.security.SecureRandom;
//
public class tranx {
//        public static void main(String[] args) {
//            // Generate a secure random transaction ID
//            String transactionID = generateSecureTransactionID();
//
//            // Use the transactionID in your payment or transaction processing
//            System.out.println("Transaction ID: " + transactionID);
//        }
//
//        public static String generateSecureTransactionID() {
//            SecureRandom secureRandom = new SecureRandom();
//            byte[] randomBytes = new byte[16]; // 16 bytes = 128 bits, providing a strong level of security
//
//            secureRandom.nextBytes(randomBytes);
//
//            // Convert the random bytes to a hexadecimal representation
//            StringBuilder transactionIDBuilder = new StringBuilder();
//            for (byte b : randomBytes) {
//                transactionIDBuilder.append(String.format("%02x", b));
//            }
//
//            return transactionIDBuilder.toString();
//        }
//    }
//

//
//    public static void main(String[] args) {
//        // Generate a secure random integer-based transaction ID
//        int transactionID = generateSecureTransactionID();
//
//        // Use the transactionID in your payment or transaction processing
//        System.out.println("Transaction ID: " + transactionID);
//    }
//
//    public static int generateSecureTransactionID() {
//        SecureRandom secureRandom = new SecureRandom();
//        byte[] randomBytes = new byte[16]; // 4 bytes = 32 bits (one integer)
//
//        secureRandom.nextBytes(randomBytes);
//
//        // Convert the random bytes to an integer
//        int transactionID = byteArrayToInt(randomBytes);
//
//        return transactionID;
//    }
//
//    public static int byteArrayToInt(byte[] bytes) {
//        int value = 0;
//        for (int i = 0; i < bytes.length; i++) {
//            value = (value << 8) | (bytes[i] & 0xFF);
//        }
//        return value;
//    }

        public static void main(String[] args) {
            // Generate a secure random 16-digit integer-based transaction ID
            long transactionID = generateSecureTransactionID();

            // Use the transactionID in your payment or transaction processing
            System.out.println("Transaction ID: " + transactionID);
        }

        public static long generateSecureTransactionID() {
            SecureRandom secureRandom = new SecureRandom();
            StringBuilder transactionIDBuilder = new StringBuilder(16);

            while (transactionIDBuilder.length() < 16) {
                // Generate a random 9-digit integer
                int randomInt = 100_000_000 + secureRandom.nextInt(900_000_000);
                transactionIDBuilder.append(randomInt);
            }

            // Ensure the length is exactly 16 digits
            if (transactionIDBuilder.length() > 16) {
                transactionIDBuilder.delete(16, transactionIDBuilder.length());
            }

            // Convert the StringBuilder to a long integer
            long transactionID = Long.parseLong(transactionIDBuilder.toString());

            return transactionID;
        }
    }


