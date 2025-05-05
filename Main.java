import Entity.Event;
import Enum.Action;
import Enum.RecurrenceType;
import Manager.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static void main(String[] args) {
        Calendar manager = new Calendar();
        Scanner sc = new Scanner(System.in);

        while (true) {
            showMenu();
            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
                Action action = Action.getAction(choice);
                switch (action) {
                    case ADD_EVENT:
                        addEvent(manager);
                        break;
                    case EDIT_EVENT:
                        editEvent(manager);
                        break;
                    case DELETE_EVENT:
                        deleteEvent(manager);
                        break;
                    case SHOW_EVENTS:
                        showEvents(manager);
                        break;
                    case TIME_TO_NEXT_EVENT:
                        manager.timeToEvent();
                        break;
                    case TIME_BETWEEN_EVENTS:
                        manager.timeBetweenEvent();
                        break;
                    case EXIT:
                        System.out.println("Exiting the program.");
                        return;
                    default:
                        System.out.println("Invalid choice, please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please enter a number.");
            }
        }

    }

    private static void showMenu() {
        System.out.println("\nCALENDAR MANAGEMENT MENU");
        for (Action action : Action.values()) {
            System.out.println(action.getId() + ": " + action.getName());
        }
        System.out.print("Enter your choice: ");
    }

    private static LocalDateTime inputDateTime(String message) {
        while (true) {
            try {
                System.out.print(message);
                String input = scanner.nextLine();
                return LocalDateTime.parse(input, formatter);
            } catch (Exception e) {
                System.out.println("Invalid format, please try again.");
            }
        }
    }

    private static RecurrenceType chooseRecurrenceType() {
        System.out.println("Select recurrence type:");
        System.out.println("1. None");
        System.out.println("2. Daily");
        System.out.println("3. Weekly");
        System.out.println("4. Monthly");
        System.out.print("Your choice: ");
        int choice = Integer.parseInt(scanner.nextLine());

        RecurrenceType type = RecurrenceType.NONE;
        switch (choice) {
            case 2:
                type = RecurrenceType.DAILY;
                break;
            case 3:
                type = RecurrenceType.WEEKLY;
                break;
            case 4:
                type = RecurrenceType.MONTHLY;
                break;
            case 1:
            default:
                type = RecurrenceType.NONE;
                break;
        }

        return type;
    }

    private static void addEvent(Calendar manager) {
        System.out.print("Enter event title: ");
        String title = scanner.nextLine();
        LocalDateTime start = inputDateTime("Enter start time (yyyy-MM-dd HH:mm): ");
        LocalDateTime end = inputDateTime("Enter end time (yyyy-MM-dd HH:mm): ");
        RecurrenceType recurrence = chooseRecurrenceType();
        LocalDate recurrenceEndDate = null;

        if (recurrence != RecurrenceType.NONE) {
            System.out.print("Enter recurrence end date (yyyy-MM-dd): ");
            recurrenceEndDate = LocalDate.parse(scanner.nextLine());
        }
        Event event = new Event(title, start, end, recurrence, recurrenceEndDate);
        manager.addEvent(event);
    }

    private static void editEvent(Calendar manager) {
        System.out.print("Enter the title of the event to edit: ");
        String title = scanner.nextLine();
        LocalDateTime start = inputDateTime("Enter new start time (yyyy-MM-dd HH:mm): ");
        LocalDateTime end = inputDateTime("Enter new end time (yyyy-MM-dd HH:mm): ");
        RecurrenceType recurrence = chooseRecurrenceType();
        LocalDate recurrenceEndDate = null;

        if (recurrence != RecurrenceType.NONE) {
            System.out.print("Enter recurrence end date (yyyy-MM-dd): ");
            recurrenceEndDate = LocalDate.parse(scanner.nextLine());
        }
        Event event = new Event(title, start, end, recurrence, recurrenceEndDate);
        manager.editEvent(title, event);
    }

    private static void deleteEvent(Calendar manager) {
        System.out.print("Enter the title of the event to delete: ");
        String title = scanner.nextLine();
        manager.deleteEvent(title);
    }

    private static void showEvents(Calendar manager) {
        System.out.println("\n--- Event List ---");
        List<Event> events = manager.listEvents();

        if (events.isEmpty()) {
            System.out.println("(No events available)");
            return;
        }

        for (Event e : events) {
            String recurrenceInfo = (e.getRecurrence() != null && e.getRecurrence() != RecurrenceType.NONE)
                    ? " [Recurs: " + e.getRecurrence() + "]"
                    : "";

            System.out.printf("• %s: %s → %s%s%n",
                    e.getTitle(),
                    e.getStartTime().format(formatter),
                    e.getEndTime().format(formatter),
                    recurrenceInfo);
        }
    }

}
