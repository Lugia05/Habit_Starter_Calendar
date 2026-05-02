import java.time.LocalDate;

public class TaskSingle extends Task {

    private LocalDate dueDate;

    public TaskSingle(String title, String description, int priority, int dueTime, LocalDate dueDate) {

        super(title, description, priority, dueTime);

        this.dueDate = dueDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    @Override
    public String toString() {

        return super.toString()
                + " | Date: " + dueDate;
    }
}