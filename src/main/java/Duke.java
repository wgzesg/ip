import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        printSeperationLine();
        printGreetings();
        //printBye();
        Scanner in = new Scanner(System.in);
        while(true){
            String command = in.nextLine();
            switch (command){
//            case "list":
//                break;
            case "bye":
                printBye();
                break;
            default:
                echo(command);
            }
        }
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
