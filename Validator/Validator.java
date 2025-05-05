package Validator;

import Annotation.*;
import Entity.Event;
import java.lang.reflect.*;
import java.time.LocalDateTime;
import java.util.List;

public class Validator {

    public static void validateEvent(Event event, List<Event> events) throws IllegalArgumentException {
        Class<?> clazz = event.getClass();

        // kiem tra @StartBeforeEnd
        if (clazz.isAnnotationPresent(StartBeforeEnd.class)) {
            try {
                Field startTime = clazz.getDeclaredField("startTime");
                Field endTime = clazz.getDeclaredField("endTime");
                startTime.setAccessible(true);
                endTime.setAccessible(true);

                LocalDateTime start = (LocalDateTime) startTime.get(event); // lay ra startTime cua event
                LocalDateTime end = (LocalDateTime) endTime.get(event); // lay ra endTime cua event

                if (start != null && end != null && !start.isBefore(end)) { // kiem tra startTime phai truoc endTime
                    throw new IllegalArgumentException("Start time must be before end time.");
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new IllegalArgumentException("Error accessing fields: " + e.getMessage());
            }

        }

        // kiem tra @OverLap
        if (clazz.isAnnotationPresent(OverLap.class)) {
            for (Event e : events) {
                if (e != event) { // kiem tra cac event khac nhau
                    // kiem tra thoi gian bat dau va ket thuc cua event co chong len nhau hay khong
                    if ((event.getStartTime().isBefore(e.getEndTime())
                            && event.getEndTime().isAfter(e.getStartTime()))) {
                        throw new IllegalArgumentException("Event times overlap with existing event.");
                    }
                }
            }
        }
    }
}
