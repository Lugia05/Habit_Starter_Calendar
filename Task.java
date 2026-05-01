
public class Task implements Comparable<Task> {

    protected String title;
    protected String description;
    protected boolean completed;
    protected int priority;

    public Task(String title, String description, int priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.completed = false;
    }

    public void markComplete() {
        completed = true;
    }

    public void markUnComplete() {
        completed = false;
    }

    @Override
    public int compareTo(Task other) {
        return this.priority - other.priority; // lower = higher priority
    }
}
