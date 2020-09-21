package duke.tasks;

import duke.parser.DateParser;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected String by;
    public LocalDateTime time;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        type = "Deadline";
        time = DateParser.parseDate(by);
    }

    public Deadline(String description, String by, boolean isDone) {
        this(description, by);
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
