package duke.tasks;

public class Events extends Task {
    protected String at;

    public Events(String description, String at) {
        super(description);
        this.at = at;
        type = "Events";
    }

    public Events(String description, String by, boolean isDone) {
        this(description, by);
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
