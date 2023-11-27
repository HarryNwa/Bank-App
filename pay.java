//package BankApp;
//
//public class pay {
//    import java.util.UUID;
//
//    public class PaymentProcessor {
//
//        public String generatePaymentLink(double amount, String invoiceId, String paymentService) {
//            // Simulate a payment link generation process
//
//            // Validate payment details (amount, invoiceId, and payment service)
//            if (amount <= 0 || invoiceId == null || paymentService == null) {
//                throw new IllegalArgumentException("Invalid payment details.");
//            }
//
//            // Generate a unique payment reference or transaction ID (simulated here with a UUID)
//            String transactionId = UUID.randomUUID().toString();
//
//            // Construct a payment link using the payment service and transaction ID
//            String paymentLink = "https://" + paymentService + ".com/pay?invoice=" + invoiceId + "&amount=" + amount + "&txnId=" + transactionId;
//
//            return paymentLink;
//        }
//
//        public static void main(String[] args) {
//            PaymentProcessor paymentProcessor = new PaymentProcessor();
//
//            // Example payment details
//            double amount = 100.0;
//            String invoiceId = "12345";
//            String paymentService = "examplepaymentgateway";
//
//            // Generate a payment link
//            String paymentLink = paymentProcessor.generatePaymentLink(amount, invoiceId, paymentService);
//
//            System.out.println("Payment Link: " + paymentLink);
//        }
//    }
//
//}
