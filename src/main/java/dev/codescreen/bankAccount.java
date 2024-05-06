package dev.codescreen;

import java.time.LocalDateTime;

public class bankAccount {
    private final String accountId;
    private double balance = 0;
    private final EventStore eventStore;

    public bankAccount(String accountId, EventStore eventStore) {
        this.accountId = accountId;
        this.eventStore = eventStore;
    }

    public double deposit(double amount) {
        Event event = new Event(accountId, amount, LocalDateTime.now(), "LOAD");
        eventStore.addEvent(event);
        balance += amount;
        return balance;
    }

    public double withdraw(double amount) {
        if (balance >= amount) {
            Event event = new Event(accountId, -amount, LocalDateTime.now(), "AUTHORIZATION");
            eventStore.addEvent(event);
            balance -= amount;
            return balance;
        } else {
            Event event = new Event(accountId, -amount, LocalDateTime.now(), "DECLINE");
            eventStore.addEvent(event);
            return balance; // Balance remains unchanged on decline
        }
    }

    public double getBalance() {
        return balance;
    }
}
