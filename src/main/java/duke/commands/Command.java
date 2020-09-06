package duke.commands;

abstract public class Command {
    public String args;

    public int execute() {
        return 0;
    }

    public void clearArgs() {
        args = null;
    }
}
