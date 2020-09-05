package commands;

import core.Database;

public class PrintBye extends Command {
    @Override
    public int execute() {
        Database.printBye();
        return -1;
    }
}
