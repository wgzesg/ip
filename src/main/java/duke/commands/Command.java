package duke.commands;

public abstract class Command {
    public String args;

    /**
     * Executes the relevant method based on the command to manipulate the database.
     * @return 0 if the program continues; -1 if the program terminates (e.g. Bye command)
     */
    public int execute() {
        return 0;
    }

    /**
     * Clear arguments for next command.
     */
    protected void clearArgs() {
        args = null;
    }
}
