package duke.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import duke.core.Constants;
import duke.exceptions.NullArgumentException;
import duke.tasks.Deadline;
import duke.tasks.Events;
import duke.tasks.Task;
import duke.tasks.ToDo;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static duke.core.Constants.FILEPATH;

public class Database {
    private static ArrayList<Task> taskList = new ArrayList<>();
    private static Gson gson;

    public static void initialise() throws IOException {
        System.out.println("Trying to load user data...");
        RuntimeTypeAdapterFactory<Task> taskAdapterFactory = RuntimeTypeAdapterFactory.of(Task.class, "type", true)
                .registerSubtype(ToDo.class, "Todo")
                .registerSubtype(Deadline.class, "Deadline")
                .registerSubtype(Events.class, "Events");
        gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapterFactory(taskAdapterFactory).create();

        try {
            readFileContents();
        } catch (FileNotFoundException e) {
            System.out.println("The file is not found");
            fileNotFoundHandler();
        }
        System.out.println("Loading completed.");
    }

    private static void readFileContents() throws FileNotFoundException {
        Type taskListType = new TypeToken<ArrayList<Task>>(){}.getType();

        JsonReader reader = new JsonReader(new FileReader(FILEPATH));

        taskList = gson.fromJson(reader, taskListType);
    }

    public static void writeToStorage() throws IOException {
        System.out.println("Saving your changes...");
        FileWriter writer;
        try {
            writer = new FileWriter(FILEPATH);
        } catch (IOException e) {
            fileNotFoundHandler();
            writer = new FileWriter(FILEPATH);
        }
        gson.toJson(taskList, writer);
        writer.flush();
        writer.close();
        System.out.println("All changes are saved!");
    }

    private static void fileNotFoundHandler() throws IOException {
        File dir = new File("data/");
        dir.mkdir();
        File yourFile = new File(FILEPATH);
        yourFile.createNewFile();
    }

    public static void markDone(String arg) {
        int offset = 1;
        try {
            int index = Integer.parseInt(arg);
            taskList.get(index - offset).markAsDone();
            markDoneResponse(index);
        } catch (NumberFormatException e) {
            System.out.println("The index given is not a number.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("The index is out of range. You don't have a task at that number.");
        }
    }

    public static void listAll() {
        int index = 1;
        for (Task task: taskList) {

            System.out.println(index + ". " + task);
            index++;
        }
    }

    public static void addToDo(String args) {
        if (args == null || args.isBlank() || args.isEmpty()) {
            throw new NullArgumentException("☹ OOPS!!! The description of a todo cannot be empty.");
        }

        Task todo = new ToDo(args);
        taskList.add(todo);
        addedToListResponse(todo);
    }

    public static void addDeadline(String args) {
        String[] argumentParts = args.split(Constants.BY_PARSER);

        if (argumentParts.length < 2 || argumentParts[Constants.DESCRIPTION].isBlank() || argumentParts[Constants.TIME].isBlank()) {
            throw new NullArgumentException(
                    "☹ OOPS!!! Not provided sufficient arguments to create an deadline.");
        }

        Task ddl = new Deadline(argumentParts[Constants.DESCRIPTION], argumentParts[Constants.TIME]);
        taskList.add(ddl);
        addedToListResponse(ddl);
    }

    public static void addEvent(String args) {
        String[] argumentParts = args.split(Constants.AT_PARSER);

        if (argumentParts.length < 2 || argumentParts[Constants.DESCRIPTION].isBlank() || argumentParts[Constants.TIME].isBlank()) {
            throw new NullArgumentException(
                    "☹ OOPS!!! Not provided sufficient arguments to create an event.");
        }

        Task event = new Events(argumentParts[Constants.DESCRIPTION], argumentParts[Constants.TIME]);
        taskList.add(event);
        addedToListResponse(event);
    }

    public static void handleBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void delete(String args) {
        int index;
        try {
            index = Integer.parseInt(args);
        } catch (NumberFormatException e){
            System.out.println("Your input is not an integer!");
            return;
        }
        try {
            Task ts = taskList.get(index - 1);
            taskList.remove(index - 1);
            deleteResponse(ts);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("You do not have such a task yet");
        }
    }

    public static void deleteResponse(Task task) {
        System.out.print(
                "Got it. I've removed this task:\n" +
                task +
                "\nNow you have " + taskList.size() + " tasks in the list.\n"
        );
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
