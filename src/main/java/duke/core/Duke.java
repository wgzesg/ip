package duke.core;

import duke.storage.Database;

import java.io.IOException;
import java.util.Scanner;

public class Duke {

    private static final CommandLib commandLib = new CommandLib();

    public static void main(String[] args) {
        try {
            Database.initialise();
        } catch (IOException e) {
            System.out.println("We cannot create a file for local storage. You may not be able to save your changes.");
        }
        printOpening();
        Scanner in = new Scanner(System.in);

        while (true) {
            String command = in.nextLine();
            int result = processCommand(command);
            if (result == -1) {
                break;
            }
        }
        try {
            Database.writeToStorage();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("The changes cannot be saved. Your progress will be lost.");
        }
        in.close();
    }

    private static void printOpening() {
        printLogo();
        printSeparationLine();
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
        printSeparationLine();
        int result = commandLib.execute(command);
        printSeparationLine();
        return result;
    }

    static void printGreetings() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printSeparationLine();
    }

    static void printSeparationLine() {
        System.out.println(Constants.SEPLINE);
    }
}
