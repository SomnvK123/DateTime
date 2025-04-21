package Validator;

import Annotation.*;
import Entity.Event;
import java.lang.reflect.*;
import java.time.LocalDateTime;
import java.util.List;

public class Validator {

    public static void validateEvent(Event event, List<Event> events) throws IllegalArgumentException {
        Class<?> clazz = event.getClass();

        // Kiểm tra @StartBeforeEnd
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(StartBeforeEnd.class)) {
                field.setAccessible(true); // if access modifier is private
                try {
                    LocalDateTime startTime = (LocalDateTime) field.get(event);
                    LocalDateTime endTime = (LocalDateTime) clazz.getDeclaredField("endTime").get(event);
                    if (startTime.isEqual(endTime) || startTime.isAfter(endTime)) {
                        throw new IllegalArgumentException("Start time must be before end time.");
                    }
                } catch (IllegalAccessException | NoSuchFieldException e) {
                    e.printStackTrace();
                }
            }
        }

        // Kiểm tra @OverLap
        if (clazz.isAnnotationPresent(OverLap.class)) {
            for (Event e : events) {
                if (e.getStartTime().isBefore(event.getEndTime()) && event.getStartTime().isBefore(e.getEndTime())) {
                    throw new IllegalArgumentException("Event times overlap with existing event.");
                }
            }
        }
    }
}
