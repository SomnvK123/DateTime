package Manager;

import Entity.Event;
import java.util.ArrayList;
import java.util.List;

public class Calendar {
    private List<Event> events = new ArrayList<>();

    public void addEvent(Event event) {
        if (event.getStartTime().isAfter(event.getEndTime())) {
            throw new IllegalArgumentException("Start time must be before end time.");
        }
        for (Event e : events) {
            if (e.getStartTime().isBefore(event.getEndTime()) && event.getStartTime().isBefore(e.getEndTime())) {
                throw new IllegalArgumentException("Event times overlap with existing event.");
            }
        }
        events.add(event);
    }
}
