package commands;


import core.Database;

public class AddDeadline extends Command {
    @Override
    public int execute() {
        Database.addDeadline(args);
        return 0;
    }
}
