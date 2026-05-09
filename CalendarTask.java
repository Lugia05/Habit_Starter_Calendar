/**
Returns the scheduled day for the task.
@return the day of the week
@author Erik Cabrera
*/

import java.time.LocalDate;

public class CalendarTask extends Task {

    private LocalDate dueDate;

    public CalendarTask(String title,
                        String description,
                        int priority,
                        int dueTime,
                        LocalDate dueDate) {

        super(title, description,
                priority, dueTime);

        this.dueDate = dueDate;
    }

    public LocalDate getDueDate() {

        return dueDate;
    }

    @Override
    public String toString() {

        return super.toString()
                + " | Date: "
                + dueDate;
    }
}

/**
Represents a calendar-based task
with a specific date.
@author Erik Cabrera
*/