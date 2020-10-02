package duke.commands;

import duke.storage.Storage;

/**
 * Command AddEvent can be executed through calling this.
 */
public class AddEvent extends Command {
    @Override
    public int execute() {
        Storage.addEvent(args);
        clearArgs();
        return 0;
    }
}
