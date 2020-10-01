package duke.commands;

import duke.storage.Database;

/**
 * Command Find can be executed through calling this.
 */
public class Find extends Command {

    @Override
    public int execute() {
        Database.find(args);
        clearArgs();
        return 0;
    }
}
