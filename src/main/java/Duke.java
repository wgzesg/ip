import java.util.ArrayList;
import java.lang.Object;
import java.util.Scanner;

public class Duke {

    public static ArrayList<Todo> todolist = new ArrayList<Todo>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        printSeperationLine();
        printGreetings();
        Scanner in = new Scanner(System.in);
        while(true){
            String command = in.nextLine();
            int result = processCommand(command);
            if(result == -1){
                break;
            }
        }

    }

    static int processCommand(String command){
        int result = 0;
        printSeperationLine();
        String[] args = command.split(" ");
        if(args[0].equals(("done"))){
            markDone(command, args[1]);
        } else if (command.equals("list")){
            listAll();
        } else if(command.equals("bye")){
            printBye();
            result = -1;
        } else{
            addToTodoList(command.trim());
        }
        printSeperationLine();
        return result;
    }

    private static void markDone(String command, String arg) {
        try{
            int index = Integer.parseInt(arg);
            todolist.get(index - 1).markAsDone();
            System.out.println("Nice! I've marked this task as done: ");
            System.out.println(todolist.get(index - 1).printTask());
        } catch(NumberFormatException e) {
            System.out.println("The command is not recognised. Do you wish to add this as task? (y/n)");
            Scanner in = new Scanner(System.in);
            String response = in.next();
            if (response.equals("yes") || response.equals("Yes") || response.equals("y")) {
                addToTodoList(command.trim());
            } else {
                System.out.println("This action is aborted.");
            }
        } catch (IndexOutOfBoundsException e){
            System.out.println("The index is out of range. This action is aborted.");
        }
    }

    static void listAll(){
        int i = 1;
        for(Todo t: todolist){

            System.out.println(i + ". " + t.printTask());
            i++;
        }
    }

    static void addToTodoList(String task){
        todolist.add(new Todo(task));
        System.out.println("added: " + task);
    }

    static void echo(String input){
        System.out.println(input);
        printSeperationLine();
    }

    static void printGreetings(){
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printSeperationLine();
    }

    static void printSeperationLine(){
        System.out.println("------------------------------------------");
    }

    static void printBye(){
        System.out.println("Bye. Hope to see you again soon!");
    }
}
