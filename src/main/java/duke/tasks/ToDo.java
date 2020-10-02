package duke.tasks;

/**
 * ToDo task.
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
        type = "Todo";
    }

    public ToDo(String description, boolean isDone) {
        this(description);
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
