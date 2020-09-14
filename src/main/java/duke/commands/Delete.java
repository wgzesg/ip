package duke.commands;

import duke.storage.Database;

public class Delete extends Command{

    @Override
    public int execute() {
        Database.delete(args);
        clearArgs();
        return 0;
    }
}
