package duke.commands;

import duke.storage.Storage;

/**
 * Command AddDeadline can be executed through calling this.
 */
public class AddDeadline extends Command {
    @Override
    public int execute() {
        Storage.addDeadline(args);
        clearArgs();
        return 0;
    }
}
