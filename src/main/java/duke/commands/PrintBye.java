package duke.commands;

import duke.core.Database;

public class PrintBye extends Command {
    @Override
    public int execute() {
        Database.printBye();
        clearArgs();
        return -1;
    }
}
