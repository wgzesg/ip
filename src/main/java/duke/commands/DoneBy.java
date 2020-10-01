package duke.commands;

import duke.storage.Database;

/**
 * Command DoneBy can be executed through calling this.
 */
public class DoneBy extends Command {

    @Override
    public int execute() {
        Database.doneBy(args);
        clearArgs();
        return 0;
    }
}
