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
}
