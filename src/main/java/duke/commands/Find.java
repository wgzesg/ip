package duke.commands;

import duke.storage.Storage;

/**
 * Command Find can be executed through calling this.
 */
public class Find extends Command {

    @Override
    public int execute() {
        Storage.find(args);
        clearArgs();
        return 0;
    }
}
