package duke.commands;

import duke.storage.Database;

public class DoneBy extends Command{

    @Override
    public int execute() {
        Database.doneBy(args);
        clearArgs();
        return 0;
    }
}
