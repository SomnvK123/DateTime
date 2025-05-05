package Entity;

import Annotation.*;
import Enum.RecurrenceType;
import java.time.LocalDate;
import java.time.LocalDateTime;

@OverLap(message = "Event times overlap with existing event.")
@StartBeforeEnd(message = "Start time must be before end time.")
public class Event {
    private String title;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private RecurrenceType recurrence = RecurrenceType.NONE;
    private LocalDate recurrenceEndDate; // Ngày kết thúc lặp

    public Event(String title, LocalDateTime startTime, LocalDateTime endTime, RecurrenceType recurrence,
            LocalDate recurrenceEndDate) {
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.recurrence = recurrence;
        this.recurrenceEndDate = recurrenceEndDate;
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

    public RecurrenceType getRecurrence() {
        return recurrence;
    }

    public void setRecurrence(RecurrenceType recurrence) {
        this.recurrence = recurrence;
    }

    @Override
    public String toString() {
        return "Event{" +
                "title='" + title + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", recurrence=" + recurrence +
                ", recurrenceEndDate=" + recurrenceEndDate +
                '}';
    }

    public LocalDate getRecurrenceEndDate() {
        return recurrenceEndDate;
    }

    public void setRecurrenceEndDate(LocalDate recurrenceEndDate) {
        this.recurrenceEndDate = recurrenceEndDate;
    }

}
