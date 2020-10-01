package duke.commands;

import duke.storage.Database;


/**
 * Command PrintBye can be executed through calling this.
 */
public class PrintBye extends Command {
    @Override
    public int execute() {

        Database.handleBye();

        clearArgs();
        return -1;
    }
}
