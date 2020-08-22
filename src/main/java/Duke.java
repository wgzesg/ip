import java.util.ArrayList;
import java.util.List;
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
            switch (command){
            case "list":
                listAll();
                break;
            case "bye":
                printBye();
                break;
            default:
                addToTodoList(command);
                //echo(command);
            }
        }
    }

    static void listAll(){
        printSeperationLine();
        int i = 1;
        for(Todo t: todolist){
            System.out.println(i + ". " + t.taskName);
            i++;
        }
        printSeperationLine();
    }

    static void addToTodoList(String task){
        todolist.add(new Todo(task));
        printSeperationLine();
        System.out.println("added: " + task);
        printSeperationLine();
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
        printSeperationLine();
    }
}
