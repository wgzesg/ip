package duke.commands;

import duke.storage.Database;

/**
 * Command Delete can be executed through calling this.
 */
public class Delete extends Command {

    @Override
    public int execute() {
        Database.delete(args);
        clearArgs();
        return 0;
    }
}
