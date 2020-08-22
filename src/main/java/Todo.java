public class Todo {
    public String taskName;
    public boolean isDone;
    public Todo(){
        taskName = "";
        isDone = false;
    }

    public Todo(String task){
        taskName = task;
        isDone = false;
    }

    public String printTask(){
        return "[" + (isDone ? "\u2713" : "\u2718") + "] " + taskName;
    }

    public void markAsDone(){
        isDone = true;
    }
}
