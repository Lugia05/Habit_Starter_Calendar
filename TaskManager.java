
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
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
}
