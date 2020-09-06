package core;

import exceptions.NullArgumentException;
import tasks.Deadline;
import tasks.Events;
import tasks.Task;
import tasks.ToDo;

import java.util.ArrayList;

public class Database {
    public static ArrayList<Task> taskList = new ArrayList<>();

    public static void markDone(String arg) {
        try {
            int index = Integer.parseInt(arg);
            taskList.get(index - 1).markAsDone();
            markDoneResponse(index);
        } catch (NumberFormatException e) {
            System.out.println("The command is not recognised.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("The index is out of range. This action is aborted.");
        }
    }

    public static void listAll() {
        int i = 1;
        for (Task task: taskList) {

            System.out.println(i + ". " + task);
            i++;
        }
    }

    public static void addToDo(String args) {
        Task todo = new ToDo(args);
        taskList.add(todo);
        addedToListResponse(todo);
    }

    public static void addDeadline(String args) {
        String[] parts = args.split(Constants.BY_PARSER);

        if (parts.length <2 || parts[0].isBlank() || parts[1].isBlank()) {
            throw new NullArgumentException("☹ OOPS!!! Not provided sufficient arguments to create an event.");
        }

        Task ddl;
        ddl = new Deadline(parts[0], parts[1]);
        taskList.add(ddl);
        addedToListResponse(ddl);
    }

    public static void addEvent(String args) {
        String[] parts = args.split(Constants.AT_PARSER);

        if (parts.length <2 || parts[0].isBlank() || parts[1].isBlank()) {
            throw new NullArgumentException("☹ OOPS!!! Event description and time cannot be empty.");
        }

        Task event;
        event = new Events(parts[0], parts[1]);
        taskList.add(event);
        addedToListResponse(event);
    }

    public static void printBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void markDoneResponse(int index) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskList.get(index - 1));
    }

    private static void addedToListResponse(Task task) {
        System.out.print(
                "Got it. I've added this task:\n" +
                task +
                "\nNow you have " + taskList.size() + " tasks in the list.\n"
        );
    }
}
