package commands;


import core.Database;

public class ListAll extends Command {
    @Override
    public int execute() {
        Database.listAll();
        return 0;
    }
}
