
public class Task implements Comparable<Task> {

    protected String title;
    protected String description;
    protected int priority;
    protected int dueTime;
    protected boolean completed;

    public Task(String title, String description, int priority, int dueTime) {
        this.title = title;
        this.description = description;
        setPriority(priority);;
        this.completed = false;
        setDueTime(dueTime);
    }

    public void markComplete() {

    if (completed) {

        System.out.println("Task already completed.");
        return;
    }

    completed = true;

    System.out.println("Task marked complete.");
}

    public void markIncomplete() {
        completed = false;
    }

    public void setDueTime(int dueTime) {

        if (dueTime < 0) {
            System.out.println("Invalid time, automatically setting to nearest valid time: 0");
            this.dueTime = 0;
        } else if (dueTime > 23){
            System.out.println("Invalid time, automatically setting to nearest valid time: 23");
            this.dueTime = 23;
        } else {
            this.dueTime = dueTime;
        }
        
    }

    public void setPriority(int priority){
        if (priority < 0){
            this.priority = 0;
        } else if (priority > 5) {
            this.priority = 5;
        } else {
            this.priority = priority;
        }
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
