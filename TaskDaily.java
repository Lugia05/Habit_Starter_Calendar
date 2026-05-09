
/**
Represents a recurring daily task.
Daily tasks repeat every day.
@author Erik Cabrera
*/

public class TaskDaily extends Task {

    public TaskDaily(String title, String description, int priority, int dueTime) {
        super(title, description, priority, dueTime);
    }

    public void resetDaily() {
        completed = false;
    }
}

/**
Constructs a daily task.
@param title the task title
@param description task details
@param priority task priority
@param dueTime due hour (0-23)
@author Erik Cabrera
*/
