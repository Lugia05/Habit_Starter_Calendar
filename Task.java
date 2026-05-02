
public class Task implements Comparable<Task> {

    protected String title;
    protected String description;
    protected int priority;
    protected int dueTime;
    protected boolean completed;

    public Task(String title, String description, int priority, int dueTime) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.completed = false;
        this.dueTime = dueTime;
    }

    public void markComplete() {
        completed = true;
    }

    public void markIncomplete() {
        completed = false;
    }

    public String getTitle() {
        return title;
    }

    public int getPriority() {
        return priority;
    }

    public int getDueTime() {
        return dueTime;
    }

    public boolean isCompleted() {
        return completed;
    }

    @Override
    public int compareTo(Task other) {

        // Priority first
        if (this.priority != other.priority) {
            return this.priority - other.priority;
        }

        // Earlier due time first
        return this.dueTime - other.dueTime;
    }

    @Override
    public String toString() {
        return "[" + (completed ? "X" : " ") + "] "
                + title
                + " | Priority: " + priority
                + " | Due: " + dueTime + ":00";
    }
}
