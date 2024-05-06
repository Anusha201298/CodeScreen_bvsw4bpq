package dev.codescreen;

/**
 * Dummy class with a dummy method.
 * Rename this class and method to a name that is more appropriate to your coding test.
 */
// public class Foo {
//
//   /**
//    *
//    *
//    * @return
//    */
//   public static int bar() {
//     return 1;
//   }
// }


public class Main {
    public static void main(String[] args) {
        EventStore eventStore = new EventStore();
        bankService bankService = new bankService();
        
        // Test cases
        testLoad(eventStore, bankService, 1, 100, "APPROVED", 100.00);
        testLoad(eventStore, bankService, 2, 3.23, "APPROVED", 103.23);
        testAuthorization(eventStore, bankService, 3, 1, 100, "APPROVED", 3.23);
        testAuthorization(eventStore, bankService, 4, 1, 10, "DENIED", 3.23);
        testAuthorization(eventStore, bankService, 5, 2, 50.01, "DENIED", 0.00);
        testLoad(eventStore, bankService, 6, 50.01, "APPROVED", 50.01);
        testAuthorization(eventStore, bankService, 7, 2, 50.01, "APPROVED", 0.00);
    }
    
    private static void testLoad(EventStore eventStore, bankService bankService, int msgId, double amount, String responseCode, double expectedBalance) {
        bankAccount account = bankService.createAccount("1");
        double balance = account.deposit(amount);
        printResult("LOAD", msgId, "CREDIT", amount, responseCode, balance, expectedBalance);
    }
    
    private static void testAuthorization(EventStore eventStore, bankService bankService, int msgId, int userId, double amount, String responseCode, double expectedBalance) {
        bankAccount account = bankService.createAccount(Integer.toString(userId));
        double balance = account.withdraw(amount);
        printResult("AUTHORIZATION", msgId, "DEBIT", amount, responseCode, balance, expectedBalance);
    }
    
    private static void printResult(String action, int msgId, String debitOrCredit, double amount, String responseCode, double balance, double expectedBalance) {
        System.out.printf("action: %s, msgId: %d, userId: 1, debitOrCredit: %s, amount: %.2f, responseCode: %s, balance: %.2f%n", 
                          action, msgId, debitOrCredit, amount, responseCode, balance);
        if (balance == expectedBalance) {
            System.out.println("Test passed!");
        } else {
            System.out.printf("Test failed! Expected balance: %.2f%n", expectedBalance);
        }
        System.out.println();
    }
}
