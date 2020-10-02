package duke.commands;


import duke.storage.Storage;

/**
 * Command ListAll can be executed through calling this.
 */
public class ListAll extends Command {
    @Override
    public int execute() {
        Storage.listAll();
        clearArgs();
        return 0;
    }
}
