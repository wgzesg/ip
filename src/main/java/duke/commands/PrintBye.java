package duke.commands;

import duke.storage.Storage;


/**
 * Command PrintBye can be executed through calling this.
 */
public class PrintBye extends Command {
    @Override
    public int execute() {

        Storage.handleBye();

        clearArgs();
        return -1;
    }
}
