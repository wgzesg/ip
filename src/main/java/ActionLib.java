import java.util.ArrayList;

public class ActionLib {
    public static ArrayList<Task> todolist = new ArrayList<>();

    public static void markDone(String arg) {
        try{
            int index = Integer.parseInt(arg);
            todolist.get(index - 1).markAsDone();
            System.out.println("Nice! I've marked this task as done: ");
            System.out.println(todolist.get(index - 1));
        } catch(NumberFormatException e) {
            System.out.println("The command is not recognised. Do you wish to add this as task? (y/n)");
        } catch (IndexOutOfBoundsException e){
            System.out.println("The index is out of range. This action is aborted.");
        }
    }

    public static void listAll(){
        int i = 1;
        for(Task task: todolist){

            System.out.println(i + ". " + task);
            i++;
        }
    }

    public static void addToDo(String args){
        Task todo = new ToDo(args);
        todolist.add(todo);
        addedToListResponse(todo);
    }

    public static void addDeadline(String args){
        String[] parts = args.split(" /by ");
        Task ddl;
        try{
            ddl = new Deadline(parts[0], parts[1]);
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Not provided sufficient arguments!");
            return;
        }
        todolist.add(ddl);
        addedToListResponse(ddl);
    }

    public static void addEvent(String args){
        String[] parts = args.split(" /at ");
        Task event;
        try{
            event = new Events(parts[0], parts[1]);
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Not provided sufficient arguments!");
            return;
        }
        todolist.add(event);
        addedToListResponse(event);
    }

    public static void printBye(){
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void addedToListResponse(Task task){
        System.out.print(
                "Got it. I've added this task: \n" +
                task +
                "\nNow you have " + todolist.size() + " tasks in the list.\n"
        );
    }
}
