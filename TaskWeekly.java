
/**
Represents a recurring weekly task.
Weekly tasks occur on a specific
day of the week.
@author Erik Cabrera
*/

import java.time.DayOfWeek;

public class TaskWeekly extends Task {

    private DayOfWeek day;

    public TaskWeekly(String title, String description, int priority, int dueTime, DayOfWeek day) {

        super(title, description, priority, dueTime);
        this.day = day;
    }

    public DayOfWeek getDay() {
        return day;
    }

    @Override
    public String toString() {
        return super.toString() + " | Day: " + day;
    }
}

/**
Constructs a weekly task.
@param title the task title
@param description task details
@param priority task priority
@param dueTime due hour (0-23)
@param day the day of the week
@author Erik Cabrera
*/
