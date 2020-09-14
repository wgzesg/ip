package duke.commands;

import duke.storage.Database;

public class PrintBye extends Command {
    @Override
    public int execute() {

        Database.handleBye();

        clearArgs();
        return -1;
    }
}
