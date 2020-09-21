package duke.commands;

import duke.storage.Database;

public class MarkDone extends Command {
    @Override
    public int execute() {
        try {
            Database.markDone(args);
            clearArgs();
        }  catch (NumberFormatException e) {
            System.out.println("The index given is not a number.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("The index is out of range. You don't have a task at that number.");
        }
        return 0;
    }
}
