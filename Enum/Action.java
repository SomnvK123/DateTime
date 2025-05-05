package Enum;

public enum Action {
    ADD_EVENT(1, "ADD EVENT"),
    EDIT_EVENT(2, "EDIT EVENT"),
    DELETE_EVENT(3, "DELETE EVENT"),
    SHOW_EVENTS(4, "SHOW EVENTS"),
    TIME_TO_NEXT_EVENT(5, "TIME TO NEXT EVENT"),
    TIME_BETWEEN_EVENTS(6, "TIME BETWEEN EVENTS"),
    EXIT(0, "EXIT");

    private final int id;
    private final String name;

    Action(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static Action getAction(int id) {
        for (Action action : Action.values()) {
            if (action.getId() == id) {
                return action;
            }
        }
        throw new IllegalArgumentException("Invalid action ID: " + id);
    }

}