package duke.commands;

import duke.storage.Database;

/**
 * Command AddDeadline can be executed through calling this.
 */
public class AddDeadline extends Command {
    @Override
    public int execute() {
        Database.addDeadline(args);
        clearArgs();
        return 0;
    }
}
