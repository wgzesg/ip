package duke.commands;

import duke.storage.Storage;

/**
 * Command Delete can be executed through calling this.
 */
public class Delete extends Command {

    @Override
    public int execute() {
        Storage.delete(args);
        clearArgs();
        return 0;
    }
}
