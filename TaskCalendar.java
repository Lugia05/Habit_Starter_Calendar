
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class TaskCalendar {

    private List<Task> allTasks;
    private Map<DayOfWeek, List<TaskWeekly>> weeklyTasks;
    private Queue<Task> todayQueue;
    private Stack<Task> completedStack;

    public TaskCalendar() {
        allTasks = new ArrayList<>();
        weeklyTasks = new HashMap<>();
        todayQueue = new PriorityQueue<>();
        completedStack = new Stack<>();
    }

    public void addTask(Task task) {
        allTasks.add(task);
    }

    public void completeTask(Task task) {
        task.markComplete();
        completedStack.push(task);
    }

    public void undoLastComplete() {
        if (!completedStack.isEmpty()) {
            Task task = completedStack.pop();
            task.completed = false;
        }
    }
}
