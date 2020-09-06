package duke.commands;


import duke.core.Database;

public class AddDeadline extends Command {
    @Override
    public int execute() {
        Database.addDeadline(args);
        clearArgs();
        return 0;
    }
}
