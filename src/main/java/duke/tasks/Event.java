package duke.tasks;

import duke.parser.DateParser;

import java.time.LocalDateTime;

/**
 * Event task.
 */
public class Event extends Task {
    protected String at;
    public LocalDateTime time;

    /**
     * Constructor.
     * @param description task description.
     * @param at the event occurs at this time.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
        type = "Event";
        time = DateParser.parseDate(at);
    }

    public Event(String description, String by, boolean isDone) {
        this(description, by);
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
