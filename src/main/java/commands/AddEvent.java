package commands;

import core.Database;

public class AddEvent extends Command {
    @Override
    public int execute() {
        Database.addEvent(args);
        return 0;
    }
}
