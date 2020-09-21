package duke.tasks;

import duke.Parser.DateParser;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected String at;
    public LocalDateTime time;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        type = "Event";
        try {
            time = DateParser.parseDate(at);
        } catch (DateTimeParseException e) {
        }
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
