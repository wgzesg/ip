package duke.tasks;

import duke.parser.DateParser;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected String at;
    public LocalDateTime time;

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
