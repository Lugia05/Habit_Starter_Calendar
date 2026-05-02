
import java.time.DayOfWeek;

public class TaskWeekly extends Task {

    private DayOfWeek day;

    public TaskWeekly(String title, String description,
            int priority, int dueTime,
            DayOfWeek day) {

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
//Mon - 0 Tue - 1 Wed - 2 Thu - 3 Fri - 4 Sat - 5 Sun - 6
