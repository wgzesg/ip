package duke.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import duke.parser.DateParser;
import duke.core.Constants;
import duke.exceptions.NullArgumentException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static duke.core.Constants.FILEPATH;

/**
 * This class holds the data loaded during runtime and read and writes to the local storage.
 */
public class Storage {
    private static ArrayList<Task> taskList = new ArrayList<>();
    private static Gson gson;

    /**
     * Initialise the database with locally stored data.
     * If the local file is not found. It creates the relevant file and folder.
     * @throws IOException If director or file cannot be created.
     */
    public static void initialise() throws IOException {
        System.out.println("Trying to load user data...");
        RuntimeTypeAdapterFactory<Task> taskAdapterFactory = RuntimeTypeAdapterFactory
                .of(Task.class, "type", true)
                .registerSubtype(ToDo.class, "Todo")
                .registerSubtype(Deadline.class, "Deadline")
                .registerSubtype(Event.class, "Event");
        gson = new GsonBuilder().setPrettyPrinting()
                .registerTypeAdapterFactory(taskAdapterFactory).create();

        try {
            readFileContents();
        } catch (FileNotFoundException e) {
            System.out.println("The file is not found");
            fileNotFoundHandler();
        }
        System.out.println("Loading completed.");
    }

    /**
     * Write the content in TaskList to a local file.
     * If the local file is not found. It creates the relevant file and folder.
     * @throws IOException If director or file cannot be created.
     */
    public static void writeToStorage() throws IOException {
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
    }

    /**
     * Mark a task as done.
     * @param arg index of the task to be labelled as done
     */
    public static void markDone(String arg) {
        int offset = 1;
        int index = Integer.parseInt(arg);
        taskList.get(index - offset).markAsDone();
        markDoneResponse(index);
    }

    /**
     * List all the tasks in the task list.
     */
    public static void listAll() {
        printTaskList(taskList);
    }

    /**
     * Add a todo task to taskList.
     * @param args The task description of the event
     */
    public static void addToDo(String args) {
        if (args == null || args.isBlank() || args.isEmpty()) {
            throw new NullArgumentException("☹ OOPS!!! The description of a todo cannot be empty.");
        }

        Task todo = new ToDo(args);
        taskList.add(todo);
        addedToListResponse(todo);
    }

    /**
     * Add a deadline task to taskList.
     * @param args The task description and the time of the deadline
     */
    public static void addDeadline(String args) {
        String[] argumentParts = args.split(Constants.BY_PARSER);

        if (argumentParts.length < 2 || argumentParts[Constants.DESCRIPTION].isBlank() ||
                argumentParts[Constants.TIME].isBlank()) {
            throw new NullArgumentException(
                    "☹ OOPS!!! Not provided sufficient arguments to create an deadline.");
        }

        Task ddl = new Deadline(argumentParts[Constants.DESCRIPTION],
                argumentParts[Constants.TIME]);
        taskList.add(ddl);
        addedToListResponse(ddl);
    }

    /**
     * Add an event task to taskList.
     * @param args The task description and the time of the event
     */
    public static void addEvent(String args) {
        String[] argumentParts = args.split(Constants.AT_PARSER);

        if (argumentParts.length < 2 || argumentParts[Constants.DESCRIPTION].isBlank() ||
                argumentParts[Constants.TIME].isBlank()) {
            throw new NullArgumentException(
                    "☹ OOPS!!! Not provided sufficient arguments to create an event.");
        }

        Task event = new Event(argumentParts[Constants.DESCRIPTION], argumentParts[Constants.TIME]);
        taskList.add(event);
        addedToListResponse(event);
    }

    /**
     * Search for events and deadlines that occurs before a certain time.
     * @param args Time specified in one of the following formats
     *             "yyyyMMdd HH:mm", "yyyy-MM-dd HH:mm", "yyyy MM dd HH:mm", "yyyy/MM/dd HH:mm",
     *             "yyyyMMdd HHmm", "yyyy-MM-dd HHmm", "yyyy MM dd HHmm", "yyyy/MM/dd HHmm",
     *             "yyyy-MM-dd", "yyyy MM dd", "yyyy/MM/dd", "yyyyMMdd HH:mm"
     */
    public static void doneBy(String args) {
        LocalDateTime date = DateParser.parseDate(args);
        List<Task> newList;
        newList = taskList.stream()
                    .filter(t -> (t instanceof Deadline && ((Deadline) t).time != null
                            && ((Deadline) t).time.isBefore(date)
                        || (t instanceof Event && ((Event) t).time != null
                            && ((Event) t).time.isBefore(date))))
                    .sorted(Comparator.comparing((Task t) -> t.taskName))
                    .collect(Collectors.toList());
        printTaskList(newList);
    }

    /**
     * Find tasks which contains the given keyword in its descriptions.
     * @param args Filtering keyword that to be included in the task description.
     */
    public static void find(String args) {
        List<Task> newList;
        newList = taskList.stream()
                    .filter(t -> t.taskName.contains(args))
                    .sorted(Comparator.comparing((Task t) -> t.taskName))
                    .collect(Collectors.toList());
        printTaskList(newList);

    }

    private static void printTaskList(List<Task> newList) {
        int index = 1;
        for (Task t : newList) {
            System.out.println(index + ". " + t);
            index++;
        }
    }

    /**
     * Prints bye to handle bye command.
     */
    public static void handleBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Delete the item at the give index.
     * @param args The index of the element to be deleted
     */
    public static void delete(String args) {
        int index;
        try {
            index = Integer.parseInt(args);
        } catch (NumberFormatException e) {
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

    private static void deleteResponse(Task task) {
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

    private static void readFileContents() throws FileNotFoundException {
        Type taskListType = new TypeToken<ArrayList<Task>>(){}.getType();

        JsonReader reader = new JsonReader(new FileReader(FILEPATH));

        taskList = gson.fromJson(reader, taskListType);
    }

    private static void fileNotFoundHandler() throws IOException {
        File dir = new File("data/");
        dir.mkdir();
        File yourFile = new File(FILEPATH);
        yourFile.createNewFile();
    }
}
