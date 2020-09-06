package duke.tasks;

import duke.core.Constants;

public class Task {
    public String taskName;
    public boolean isDone;
    public Task(){
        taskName = "";
        isDone = false;
    }

    public Task(String task){
        taskName = task;
        isDone = false;
    }

    @Override
    public String toString(){
        return "[" + (isDone ? Constants.TICK : Constants.CROSS) + "] " + taskName;
    }

    public void markAsDone(){
        isDone = true;
    }
}
