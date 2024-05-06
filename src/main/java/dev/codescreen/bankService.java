package dev.codescreen;


public class bankService {
    private final EventStore eventStore = new EventStore();

    public bankAccount createAccount(String accountId) {
        return new bankAccount(accountId, eventStore);
    }

    // Other methods to interact with bankAccount objects
}