package duke.commands;

import duke.storage.Storage;

/**
 * Command DoneBy can be executed through calling this.
 */
public class DoneBy extends Command {

    @Override
    public int execute() {
        Storage.doneBy(args);
        clearArgs();
        return 0;
    }
}
