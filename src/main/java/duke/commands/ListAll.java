package duke.commands;


import duke.storage.Database;

/**
 * Command ListAll can be executed through calling this.
 */
public class ListAll extends Command {
    @Override
    public int execute() {
        Database.listAll();
        clearArgs();
        return 0;
    }
}
