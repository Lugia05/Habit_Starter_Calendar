
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class TaskManager {
    private String currentFileName;

    // List of every task
    private List<Task> allTasks;

    // Map
    private Map<DayOfWeek, List<TaskWeekly>> weeklyMap;

    // Task Queue
    private Queue<Task> taskQueue;

    // Stack for completed tasks
    private Stack<Task> completedStack;

    public TaskManager() {

        allTasks = new ArrayList<>();

        weeklyMap = new HashMap<>();

        taskQueue = new PriorityQueue<>();

        completedStack = new Stack<>();
    }

    //Creates a folder for saving task lists
    File folder = new File("Saves");{

        if (!folder.exists()) {
            folder.mkdir();
        }
    }


    // Add Tasks
    public void addTask(Task task) {

        allTasks.add(task);

        taskQueue.offer(task);

        // Add weekly tasks to map
        if (task instanceof TaskWeekly weeklyTask) {

            DayOfWeek day = weeklyTask.getDay();

            weeklyMap.putIfAbsent(day, new ArrayList<>());

            weeklyMap.get(day).add(weeklyTask);
        }
    }

    // Display tasks
    public void displayTasks() {

        if (allTasks.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }

        for (int i = 0; i < allTasks.size(); i++) {

            System.out.println((i + 1) + ". " + allTasks.get(i));
        }
    }

    // COMPLETE TASK
    public void completeTask(int index) {

        if (index < 0 || index >= allTasks.size()) {
            System.out.println("Invalid task.");
            return;
        }

        Task task = allTasks.get(index);

        task.markComplete();

        completedStack.push(task);

        System.out.println("Completed: " + task.getTitle());
    }

    // UNDO COMPLETE
    public void undoComplete() {

        if (completedStack.isEmpty()) {
            System.out.println("Nothing to undo.");
            return;
        }

        Task task = completedStack.pop();

        task.completed = false;

        System.out.println("Undo complete: " + task.getTitle());
    }

    // CUSTOM INSERTION SORT
    public void sortTasks() {

        for (int i = 1; i < allTasks.size(); i++) {

            Task key = allTasks.get(i);

            int j = i - 1;

            while (j >= 0 && allTasks.get(j).compareTo(key) > 0) {

                allTasks.set(j + 1, allTasks.get(j));

                j--;
            }

            allTasks.set(j + 1, key);
        }

        System.out.println("Tasks sorted.");
    }

    // SHOW NEXT TASK FROM QUEUE
    public void showNextTask() {

        if (taskQueue.isEmpty()) {
            System.out.println("Queue empty.");
            return;
        }

        System.out.println("Next Task: " + taskQueue.peek());
    }


    //Search for a Specific Task
    public void searchTask(String keyword) {

    boolean found = false;

    for (Task task : allTasks) {

            if (task.getTitle()
                    .toLowerCase()
                    .contains(keyword.toLowerCase())) {

                System.out.println(task);

                found = true;
            }
        }

        if (!found) {

            System.out.println("No matching tasks.");
        }
    }

    //Delete a task
    public void removeTask(int index) {

        if (index < 0 || index >= allTasks.size()) {

            System.out.println("Invalid index.");
            return;
        }

        Task removed = allTasks.remove(index);

        System.out.println("Removed: " + removed.getTitle());
    }

    /*
    //First options
    public void createTask(Scanner scanner) {

        System.out.println("1. Daily");
        System.out.println("2. Weekly");
        System.out.println("3. Calendar");

        int type = scanner.nextInt();

        scanner.nextLine();

        System.out.print("Title: ");
        String title = scanner.nextLine();

        // etc...
    } 
    */  

    //SaveAs option for saving a new file
    public void saveAs(Scanner scanner) {

        System.out.print("Enter file name: ");

        String name = scanner.nextLine();

        currentFileName = name + ".txt";

        save();
    }

    //Save option for saving to an existing file
    public void save() {

        if (currentFileName == null) {
                System.out.println("No current file. Use Save As first.");
                return;
        }
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("Saves/" + currentFileName));
            for (Task task : allTasks) {
                writer.println(taskToFileString(task));
            }
            writer.close();
            System.out.println("Saved to " + currentFileName);
        } catch (IOException e) {
            System.out.println("Error saving file.");
        }
    }

    //Converting everything to a file format
    public String taskToFileString(Task task) {

        if (task instanceof TaskWeekly weekly) {

            return "WEEKLY,"
                    + weekly.title + ","
                    + weekly.description + ","
                    + weekly.priority + ","
                    + weekly.dueTime + ","
                    + weekly.completed + ","
                    + weekly.getDay();
        }

        else if (task instanceof TaskDaily) {

            return "DAILY,"
                    + task.title + ","
                    + task.description + ","
                    + task.priority + ","
                    + task.dueTime + ","
                    + task.completed;
        }

        return "";
    }

    //load up a file
    public void load(Scanner scanner) {

        File folder = new File("Saves");

        File[] files = folder.listFiles();

        if (files == null || files.length == 0) {

            System.out.println("No save files found.");
            return;
        }

        System.out.println("\n===== SAVE FILES =====");

        for (int i = 0; i < files.length; i++) {

            System.out.println(
                    (i + 1) + ". "+ files[i].getName()
            );
        }

        System.out.print("Choose file: ");

        int choice =
                Main.getIntInput(scanner);

        if (choice < 1 || choice > files.length) {

            System.out.println("Invalid file.");

            return;
        }

        File selected = files[choice - 1];

        loadFromFile(selected);

        currentFileName = selected.getName();
    }

//actually lading from file, instead of the UI
    public void loadFromFile(File file) {

        try {

            Scanner reader = new Scanner(file);

            allTasks.clear();

            while (reader.hasNextLine()) {

                String line = reader.nextLine();

                String[] parts = line.split(",");

                if (parts[0].equals("DAILY")) {

                    TaskDaily daily =
                            new TaskDaily(
                                    parts[1],
                                    parts[2],
                                    Integer.parseInt(parts[3]),
                                    Integer.parseInt(parts[4])
                            );

                    if (Boolean.parseBoolean(parts[5])) {
                        daily.markComplete();
                    }

                    addTask(daily);
                }

                else if (parts[0].equals("WEEKLY")) {

                    TaskWeekly weekly =
                            new TaskWeekly(
                                    parts[1],
                                    parts[2],
                                    Integer.parseInt(parts[3]),
                                    Integer.parseInt(parts[4]),
                                    DayOfWeek.valueOf(parts[6])
                            );

                    if (Boolean.parseBoolean(parts[5])) {
                        weekly.markComplete();
                    }

                    addTask(weekly);
                }
            }

            reader.close();

            System.out.println(
                    "Loaded " + file.getName()
            );

        } catch (Exception e) {

            System.out.println("Error loading file.");
        }
    }

    public void fileMenu(Scanner scanner) {

    boolean inMenu = true;

    while (inMenu) {

        System.out.println("\n===== FILE MENU =====");

        System.out.println("1. Save");
        System.out.println("2. Save As");
        System.out.println("3. Load");
        System.out.println("4. Back");

        System.out.print("Choose: ");

        int choice = Main.getIntInput(scanner);

        switch (choice) {

            case 1 -> save();

            case 2 -> saveAs(scanner);

            case 3 -> load(scanner);

            case 4 -> inMenu = false;

            default ->
                System.out.println("Invalid option.");
        }
    }
}

    public void createTask(Scanner scanner) {

        System.out.println("\n===== CREATE TASK =====");

        System.out.println("1. Daily Task");
        System.out.println("2. Weekly Task");
        System.out.println("3. Calendar Task");
        System.out.println("4. Back");

        System.out.print("Choose: ");

        int choice = Main.getIntInput(scanner);

        if (choice == 4) {
            return;
        }

        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Description: ");
        String desc = scanner.nextLine();

        System.out.print("Priority: ");
        int priority = Main.getIntInput(scanner);

        System.out.print("Due Hour (0-23): ");
        int due = Main.getIntInput(scanner);

        switch (choice) {

            case 1 -> {

                TaskDaily daily =
                        new TaskDaily(
                                title,
                                desc,
                                priority,
                                due
                        );

                addTask(daily);

                System.out.println(
                        "Daily task added."
                );
            }

            case 2 -> {

                System.out.print(
                        "Day (MONDAY etc): "
                );

                DayOfWeek day =
                        getDayInput(scanner);

                TaskWeekly weekly =
                        new TaskWeekly(
                                title,
                                desc,
                                priority,
                                due,
                                day
                        );

                addTask(weekly);

                System.out.println(
                        "Weekly task added."
                );
            }

            case 3 -> {

                LocalDate date =
                        getValidDate(scanner);

                CalendarTask calendar =
                        new CalendarTask(
                                title,
                                desc,
                                priority,
                                due,
                                date
                        );

                addTask(calendar);

                System.out.println(
                        "Calendar task added."
                );
            }

            default ->
                    System.out.println(
                            "Invalid option."
                    );
        }
    }

    public LocalDate getValidDate(
        Scanner scanner) {

        while (true) {

            try {

                System.out.print(
                        "Date (YYYY-MM-DD): "
                );

                return LocalDate.parse(
                        scanner.nextLine()
                );

            }

            catch (Exception e) {

                System.out.println(
                        "Invalid date format."
                );
            }
        }
    }

    public static DayOfWeek getDayInput(Scanner scanner) {

        while (true) {

            try {

                String input =
                        scanner.nextLine()
                                .trim()
                                .toUpperCase();

                return DayOfWeek.valueOf(input);

            } catch (IllegalArgumentException e) {

                System.out.print(
                        "Invalid day. Try MONDAY, TUESDAY, etc: "
                );
            }
        }
    }
}
