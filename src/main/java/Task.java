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
        return "[" + (isDone ? "\u2713" : "\u2718") + "] " + taskName;
    }

    public void markAsDone(){
        isDone = true;
    }
}
