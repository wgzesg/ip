package duke.core;

import duke.storage.Database;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static duke.core.Constants.FILEPATH;

public class Duke {

    private static final CommandLib commandLib = new CommandLib();

    public static void main(String[] args) throws IOException {
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
            Database.writeToStorage();
            if (result == -1) {
                break;
            }
        }

        writeToStorage();
        in.close();
    }

    private static void writeToStorage() {
        System.out.println("Saving your changes...");
        try {
            Database.writeToStorage();
            System.out.println("All changes are saved!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("The changes cannot be saved. Your progress will be lost.");
        }
    }

    private static void printOpening() {
        printLogo();
        printSeparationLine();
        printGreetings();
    }

    private static void printLogo() {
        System.out.println("Hello from\n" + Constants.LOGO);
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
