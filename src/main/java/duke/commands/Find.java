package duke.commands;

import duke.storage.Database;

public class Find extends Command {

    @Override
    public int execute() {
        Database.find(args);
        clearArgs();
        return 0;
    }
}
