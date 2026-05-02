
public class TaskDaily extends Task {

    public TaskDaily(String title, String description, int priority, int dueTime) {
        super(title, description, priority, dueTime);
    }

    public void resetDaily() {
        completed = false;
    }
}
