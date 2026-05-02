
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class TaskManager {

    // LIST
    private List<Task> allTasks;

    // MAP
    private Map<DayOfWeek, List<TaskWeekly>> weeklyMap;

    // QUEUE
    private Queue<Task> taskQueue;

    // STACK
    private Stack<Task> completedStack;

    public TaskManager() {

        allTasks = new ArrayList<>();

        weeklyMap = new HashMap<>();

        taskQueue = new PriorityQueue<>();

        completedStack = new Stack<>();
    }

    // ADD TASK
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

    // DISPLAY TASKS
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

    //Save Tasks
    public void saveTasks() {

        try {

            PrintWriter writer =
                    new PrintWriter(new FileWriter("tasks.txt"));

            for (Task task : allTasks) {

                writer.println(task.toString());
            }

            writer.close();

            System.out.println("Tasks saved.");

        } catch (IOException e) {

            System.out.println("Error saving file.");
        }
    }
}
