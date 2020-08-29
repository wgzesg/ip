import java.util.ArrayList;
import java.util.StringJoiner;

public class ActionLib {
    public static ArrayList<Todo> todolist = new ArrayList<>();

    public static void markDone(String arg) {
        try{
            int index = Integer.parseInt(arg);
            todolist.get(index - 1).markAsDone();
            System.out.println("Nice! I've marked this task as done: ");
            System.out.println(todolist.get(index - 1).printTask());
        } catch(NumberFormatException e) {
            System.out.println("The command is not recognised. Do you wish to add this as task? (y/n)");
        } catch (IndexOutOfBoundsException e){
            System.out.println("The index is out of range. This action is aborted.");
        }
    }

    public static void listAll(){
        int i = 1;
        for(Todo t: todolist){

            System.out.println(i + ". " + t.printTask());
            i++;
        }
    }

    public static void addToTodoList(String[] args){
        StringJoiner joiner = new StringJoiner(" ");
        for (String arg : args) {
            joiner.add(arg);
        }
        String taskName = joiner.toString();
        todolist.add(new Todo(taskName));
        System.out.println("added: " + taskName);
    }

    public static void printBye(){
        System.out.println("Bye. Hope to see you again soon!");
    }
}
