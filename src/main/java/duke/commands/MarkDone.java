package duke.commands;

import duke.core.Database;

public class MarkDone extends Command {
    @Override
    public int execute() {
        Database.markDone(args);
        clearArgs();
        return 0;
    }
}
