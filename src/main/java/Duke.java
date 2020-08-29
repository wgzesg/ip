import java.util.ArrayList;
import java.lang.Object;
import java.util.Scanner;

public class Duke {

    private static CommandLib commandLib = new CommandLib();
    //    public static ArrayList<Todo> todolist = new ArrayList<Todo>();
    public static void main(String[] args) {



        printLogo();
        printSeperationLine();
        printGreetings();
        Scanner in = new Scanner(System.in);
        while(true){
            String command = in.nextLine();
            int result = 0;
            try{
                result = processCommand(command);
            } catch (IllegalStateException e){
                System.out.println("The command is not recognised");
            }
            printSeperationLine();

            if(result == -1){
                break;
            }
        }

    }

    private static void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    static int processCommand(String command){
        int result = 0;
        printSeperationLine();

        String[] args = command.split(" ");
        try{
            result = commandLib.execute(args[0], args);
        } catch (IllegalStateException e){
            System.out.println("The command is not recognised");
        }
        return result;

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


}
