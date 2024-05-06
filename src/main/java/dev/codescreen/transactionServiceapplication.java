package dev.codescreen;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class transactionServiceapplication {

    public static void main(String[] args) {
        SpringApplication.run(transactionServiceapplication.class, args);
    }

    // New endpoint to handle the ping request
    @GetMapping("/ping")
    public ResponseEntity<?> ping() {
        try {
            // Simulate success by returning a success response with the current server time
            String message = "Pong! Server time is: " + java.time.LocalDateTime.now();
            return ResponseEntity.ok().body(message);
        } catch (Exception e) {
            // If an exception occurs, return a server error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

    // Endpoint to handle the load request
    @PutMapping("/load")
    public ResponseEntity<LoadResponse> load(@RequestBody LoadRequest loadRequest) {
        // Simulate processing the load request and updating the user's balance
        // In a real application, you would perform the necessary business logic here
        double newBalance = loadRequest.getTransactionAmount().getDebitOrCredit().equals("CREDIT") ?
                Double.parseDouble(loadRequest.getTransactionAmount().getAmount()) :
                -Double.parseDouble(loadRequest.getTransactionAmount().getAmount()); // For simplicity, assuming CREDIT adds funds and DEBIT removes funds

        // Create a LoadResponse object with the updated balance
        LoadResponse response = new LoadResponse(loadRequest.getUserId(), loadRequest.getMessageId(), newBalance);

        // Return a success response with HTTP status 201
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Endpoint to handle the authorization request
    @PutMapping("/authorization")
    public ResponseEntity<AuthorizationResponse> authorization(@RequestBody AuthorizationRequest authorizationRequest) {
        // Simulate processing the authorization request and checking the user's balance
        // In a real application, you would perform the necessary business logic here
        String responseCode = "APPROVED"; // For simplicity, always approve the authorization

        // Create an AuthorizationResponse object with the response code and current balance
        AuthorizationResponse response = new AuthorizationResponse(
                authorizationRequest.getUserId(),
                authorizationRequest.getMessageId(),
                responseCode,
                authorizationRequest.getTransactionAmount().getDebitOrCredit().equals("DEBIT") ?
                        - Double.parseDouble(authorizationRequest.getTransactionAmount().getAmount()) :
                        Double.parseDouble(authorizationRequest.getTransactionAmount().getAmount())); // Simulated balance

        // Return a success response with HTTP status 201
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Request and response classes

    static class LoadRequest {
        private String userId;
        private String messageId;
        private Amount transactionAmount;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getMessageId() {
            return messageId;
        }

        public void setMessageId(String messageId) {
            this.messageId = messageId;
        }

        public Amount getTransactionAmount() {
            return transactionAmount;
        }

        public void setTransactionAmount(Amount transactionAmount) {
            this.transactionAmount = transactionAmount;
        }
// Getters and setters
    }

    static class AuthorizationRequest {
        private String userId;
        private String messageId;
        private Amount transactionAmount;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getMessageId() {
            return messageId;
        }

        public void setMessageId(String messageId) {
            this.messageId = messageId;
        }

        public Amount getTransactionAmount() {
            return transactionAmount;
        }

        public void setTransactionAmount(Amount transactionAmount) {
            this.transactionAmount = transactionAmount;
        }
// Getters and setters
    }

    static class LoadResponse {
        private String userId;
        private String messageId;
        private double balance;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getMessageId() {
            return messageId;
        }

        public LoadResponse(String userId, String messageId, double balance) {
            this.userId = userId;
            this.messageId = messageId;
            this.balance = balance;
        }

        public void setMessageId(String messageId) {
            this.messageId = messageId;
        }

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }
// Constructor, getters, and setters
    }

    static class AuthorizationResponse {
        private String userId;
        private String messageId;
        private String responseCode;
        private double balance;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getMessageId() {
            return messageId;
        }

        public void setMessageId(String messageId) {
            this.messageId = messageId;
        }

        public String getResponseCode() {
            return responseCode;
        }

        public void setResponseCode(String responseCode) {
            this.responseCode = responseCode;
        }

        public AuthorizationResponse(String userId, String messageId, String responseCode, double balance) {
            this.userId = userId;
            this.messageId = messageId;
            this.responseCode = responseCode;
            this.balance = balance;
        }

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }
// Constructor, getters, and setters
    }

    static class Amount {
        private String amount;
        private String currency;
        private String debitOrCredit;

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getDebitOrCredit() {
            return debitOrCredit;
        }

        public void setDebitOrCredit(String debitOrCredit) {
            this.debitOrCredit = debitOrCredit;
        }
// Getters and setters
    }
}
