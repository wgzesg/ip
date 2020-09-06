package duke.commands;

import duke.core.Database;

public class AddEvent extends Command {
    @Override
    public int execute() {
        Database.addEvent(args);
        clearArgs();
        return 0;
    }
}
