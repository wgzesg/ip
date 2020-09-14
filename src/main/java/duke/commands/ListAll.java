package duke.commands;


import duke.storage.Database;

public class ListAll extends Command {
    @Override
    public int execute() {
        Database.listAll();
        clearArgs();
        return 0;
    }
}
