package commands;

import exceptions.NullArgumentException;

abstract public class Command {
    public String args;

    public int execute() {
        return 0;
    }
}
