package Manager;

import Entity.Event;
import Validator.Validator;
import java.time.*;
import java.util.*;

public class Calendar {
    private List<Event> events = new ArrayList<>();

    public void addEvent(Event event) {
        try {
            Validator.validateEvent(event, events);
            events.add(event);
        } catch (IllegalArgumentException e) {
            System.out.println("Error adding event: " + e.getMessage());
        }
    }

    public boolean editEvent(String title, Event event) {
        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).getTitle().equals(title)) { // tim event theo title
                try {
                    Validator.validateEvent(event, events); // kiem tra event moi
                    events.set(i, event); // thay the event cu bang event moi
                    return true;
                } catch (IllegalArgumentException e) {
                    System.out.println("Error editing event: " + e.getMessage());
                    return false; // tra ve false neu co loi
                }
            }
        }
        return false;
    }

    public boolean deleteEvent(String title) {
        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).getTitle().equals(title)) {
                events.remove(i);
                return true;
            }
        }
        return false;
    }

    public List<Event> listEvents() {
        return events; // tra ve danh sach cac event
    }

    public void timeToEvent() {
        if (!events.isEmpty()) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter the title of the event: ");
            String eventTitle = sc.nextLine(); // nguoi dung nhap ten event

            for (Event event : events) {
                if (event.getTitle().equals(eventTitle)) { // tim event theo ten
                    LocalDateTime today = LocalDateTime.now(); // lay ngay hien tai
                    LocalDateTime eventStartTime = event.getStartTime(); // lay thoi gian bat dau cua event

                    // Tinh khoang thoi gian giua ngay hien tai va ngay bat dau cua event
                    long daysUntil = Duration.between(today, eventStartTime).toDays();
                    long hoursUntil = Duration.between(today, eventStartTime).toHours() % 24;
                    long minutesUntil = Duration.between(today, eventStartTime).toMinutes() % 60;

                    System.out.println("Event: " + eventTitle);
                    System.out.println("Time until event: " + daysUntil + " days, " + hoursUntil + " hours, "
                            + minutesUntil + " minutes.");
                    return;
                }
            }
            System.out.println("Event not found.");
        } else {
            System.out.println("No events available.");
        }
    }

    public List<Event> addEventWithRecurrence(Event event) {
        List<Event> newEvents = new ArrayList<>(); // danh sach cac event moi
        LocalDateTime startTime = event.getStartTime(); // thoi gian bat dau cua event
        LocalDateTime endTime = event.getEndTime(); // thoi gian ket thuc cua event

        while (!startTime.toLocalDate().isAfter(event.getRecurrenceEndDate())) { // lap cho den khi thoi gian bat dau
                                                                                 // vuot qua ngay ket thuc
            Event newEvent = new Event(event.getTitle(), startTime, endTime, event.getRecurrence(),
                    event.getRecurrenceEndDate());
            newEvents.add(newEvent);

            switch (newEvent.getRecurrence()) {
                case DAILY:
                    startTime = startTime.plusDays(1); // tang thoi gian bat dau 1 ngay
                    endTime = endTime.plusDays(1); // tang thoi gian ket thuc 1 ngay
                    break;

                case WEEKLY:
                    startTime = startTime.plusWeeks(1); // tang thoi gian bat dau 1 tuan
                    endTime = endTime.plusWeeks(1); // tang thoi gian ket thuc 1 tuan
                    break;

                case MONTHLY:
                    startTime = startTime.plusMonths(1); // tang thoi gian bat dau 1 thang
                    endTime = endTime.plusMonths(1); // tang thoi gian ket thuc 1 thang
                    break;

                default:
                    break;
            }
        }
        return newEvents;
    }

    public void timeBetweenEvent() {
        if (!events.isEmpty()) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter the title of the first event: ");
            String firstEvent = sc.nextLine(); // nguoi dung nhap ten event

            System.out.print("Enter the title of the second event: ");
            String secondEvent = sc.nextLine(); // nguoi dung nhap ten event

            Event event1 = null;
            Event event2 = null;

            for (Event event : events) {
                if (event.getTitle().equals(firstEvent)) { // tim event theo ten
                    event1 = event; // gan event1
                } else if (event.getTitle().equals(secondEvent)) { // tim event theo ten
                    event2 = event; // gan event2
                }
            }

            if (event1 != null && event2 != null) {
                // Tinh khoang thoi gian giua 2 event
                Long daysBetween = Duration.between(event1.getEndTime(), event2.getStartTime()).toDays();
                Long hoursBetween = Duration.between(event1.getEndTime(), event2.getStartTime()).toHours() % 24;
                Long minutesBetween = Duration.between(event1.getEndTime(), event2.getStartTime()).toMinutes() % 60;

                System.out.println("Time between events: " + daysBetween + " days, " + hoursBetween + " hours, "
                        + minutesBetween + " minutes.");
            }
        } else {
            System.out.println("No events available.");
        }
    }

    public void listEventsByWeekMonth() {
        if (!events.isEmpty()) {

        }
    }
}
