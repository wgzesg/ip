package commands;

import core.Database;

public class MarkDone extends Command {
    @Override
    public int execute() {
        Database.markDone(args);
        return 0;
    }
}
