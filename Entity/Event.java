package Entity;

import Annotation.*;
import java.time.LocalDateTime;

@OverLap(message = "Event times overlap with existing event.")
public class Event {
    private String title;
    @StartBeforeEnd(message = "Start time must be before end time.")
    private LocalDateTime startTime;
    @StartBeforeEnd(message = "Start time must be before end time.")
    private LocalDateTime endTime;

    public Event(String title, LocalDateTime startTime, LocalDateTime endTime) {
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // Getters and Setters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

}
