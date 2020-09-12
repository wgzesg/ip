package duke.commands;

import duke.core.Database;

public class Delete extends Command{

    @Override
    public int execute() {
        Database.delete(args);
        clearArgs();
        return 0;
    }
}
