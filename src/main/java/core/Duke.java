package core;

import java.util.Scanner;

public class Duke {

    private static final CommandLib commandLib = new CommandLib();

    public static void main(String[] args) {
        printOpening();
        Scanner in = new Scanner(System.in);
        while (true) {
            String command = in.nextLine();
            int result = processCommand(command);
            if (result == -1) {
                break;
            }
        }
    }

    private static void printOpening() {
        printLogo();
        printSeperationLine();
        printGreetings();
    }

    private static void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    static int processCommand(String command) {
        printSeperationLine();
        int result = commandLib.execute(command);
        printSeperationLine();
        return result;
    }

    static void echo(String input) {
        System.out.println(input);
        printSeperationLine();
    }

    static void printGreetings() {
        System.out.println("Hello! I'm core.Duke");
        System.out.println("What can I do for you?");
        printSeperationLine();
    }

    static void printSeperationLine() {
        System.out.println(Constants.SEPLINE);
    }
}
