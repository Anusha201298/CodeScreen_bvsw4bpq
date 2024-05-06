package dev.codescreen;

import java.time.LocalDateTime;

public class Event {
    private final String accountId;
    private final double amount;
    private final LocalDateTime timestamp;
    private final String type; // "LOAD" or "AUTHORIZATION"

    public Event(String accountId, double amount, LocalDateTime timestamp, String type) {
        this.accountId = accountId;
        this.amount = amount;
        this.timestamp = timestamp;
        this.type = type;
    }

    public String getAccountId() {
        return accountId;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getType() {
        return type;
    }
}


