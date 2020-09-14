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

import java.io.*;
import java.lang.reflect.Type;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;

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
            File yourFile = new File(Constants.FILEPATH);
            yourFile.createNewFile();
        }
        System.out.println("Loading completed.");
    }

    private static void readFileContents() throws FileNotFoundException {
        Type taskListType = new TypeToken<ArrayList<Task>>(){}.getType();

        JsonReader reader = new JsonReader(new FileReader(Constants.FILEPATH));

        taskList = gson.fromJson(reader, taskListType);
    }

    public static void writeToStorage() throws IOException {
        System.out.println("Saving your changes...");
        FileWriter writer;
        try {
            writer = new FileWriter("data/storage.json");
        } catch (IOException e) {
            File yourFile = new File(Constants.FILEPATH);
            yourFile.createNewFile();
            writer = new FileWriter("data/storage.json");
        }
        gson.toJson(taskList, writer);
        writer.flush(); //flush data to file   <---
        writer.close();
        System.out.println("All changes are saved!");
    }

    public static void markDone(String arg) {
        try {
            int index = Integer.parseInt(arg);
            taskList.get(index - 1).markAsDone();
            markDoneResponse(index);
        } catch (NumberFormatException e) {
            System.out.println("The index given is not a number.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("The index is out of range. You don't have a task at that number.");
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
        if (args == null || args.isBlank() || args.isEmpty()) {
            throw new NullArgumentException("☹ OOPS!!! The description of a todo cannot be empty.");
        }

        Task todo = new ToDo(args);
        taskList.add(todo);
        addedToListResponse(todo);
    }

    public static void addDeadline(String args) {
        String[] parts = args.split(Constants.BY_PARSER);

        if (parts.length < 2 || parts[0].isBlank() || parts[1].isBlank()) {
            throw new NullArgumentException(
                    "☹ OOPS!!! Not provided sufficient arguments to create an deadline.");
        }

        Task ddl = new Deadline(parts[0], parts[1]);
        taskList.add(ddl);
        addedToListResponse(ddl);
    }

    public static void addEvent(String args) {
        String[] parts = args.split(Constants.AT_PARSER);

        if (parts.length < 2 || parts[0].isBlank() || parts[1].isBlank()) {
            throw new NullArgumentException(
                    "☹ OOPS!!! Not provided sufficient arguments to create an event.");
        }

        Task event = new Events(parts[0], parts[1]);
        taskList.add(event);
        addedToListResponse(event);
    }

    public static void handleBye() {

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
