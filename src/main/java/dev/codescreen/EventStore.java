package dev.codescreen;

import java.util.ArrayList;
import java.util.List;

public class EventStore {
    private final List<Event> events = new ArrayList<>();

    public void addEvent(Event event) {
        events.add(event);
    }

    public List<Event> getEvents() {
        return events;
    }
}
