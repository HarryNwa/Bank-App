package BankApp;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.SecureRandom;
import java.util.Base64;

public class payLink {

        public static void main(String[] args) {
            // Example: Generate and secure a payment link
            String paymentDetails = "amount=100&invoice=12345";
            String securedPaymentLink = generateAndSecurePaymentLink(paymentDetails);

            System.out.println("Secured Payment Link: " + securedPaymentLink);
        }

        public static String generateAndSecurePaymentLink(String paymentDetails) {
            // Step 1: Generate a unique payment ID
            String paymentID = generateUniquePaymentID();

            // Step 2: Encrypt payment details
            String encryptedPaymentDetails = encryptPaymentDetails(paymentDetails);

            // Step 3: Combine payment ID and encrypted details to create a secure payment link
            String securedPaymentLink = paymentID + "&data=" + encryptedPaymentDetails;

            return securedPaymentLink;
        }

        public static String generateUniquePaymentID() {
            // Generate a unique payment ID (you can use a more sophisticated method)
            SecureRandom secureRandom = new SecureRandom();
            byte[] randomBytes = new byte[16];
            secureRandom.nextBytes(randomBytes);
            return Base64.getEncoder().encodeToString(randomBytes);
        }

        public static String encryptPaymentDetails(String paymentDetails) {
            try {
                // Generate a secret key for encryption (you can use a more secure method)
                KeyGenerator keyGen = KeyGenerator.getInstance("AES");
                keyGen.init(128); // 128-bit key
                SecretKey secretKey = keyGen.generateKey();

                // Encrypt payment details using AES encryption
                Cipher cipher = Cipher.getInstance("AES");
                cipher.init(Cipher.ENCRYPT_MODE, secretKey);
                byte[] encryptedBytes = cipher.doFinal(paymentDetails.getBytes());

                // Encode the encrypted data to a base64 string
                String encryptedPaymentDetails = Base64.getEncoder().encodeToString(encryptedBytes);

                return encryptedPaymentDetails;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }


