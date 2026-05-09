/**
Represents a general task in the task manager system.

A task contains a title, description, priority,
due hour, and completion status.

This class implements Comparable in order to
support custom sorting algorithms.

Tasks are sorted by:
Priority
Due time


@author Erik Cabrera
*/


public class Task implements Comparable<Task> {

    protected String title;
    protected String description;
    protected int priority;
    protected int dueTime;
    protected boolean completed;

/**

Constructs a Task object.
@param title the name of the task
@param description details describing the task
@param priority the task priority level
@param dueTime the hour the task is due (0-23)
@throws IllegalArgumentException if dueTime
is outside the range 0-23
@author Erik Cabrera
*/

    public Task(String title, String description, int priority, int dueTime) {
        this.title = title;
        this.description = description;
        setPriority(priority);;
        this.completed = false;
        setDueTime(dueTime);
    }

/**
Marks the task as completed.
If the task is already completed,
no changes are made.
@author Erik Cabrera
*/
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

/**

Returns the title, piorities, duetime and completion status of the task.
@return the getters of the task
@author Erik Cabrera
*/
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

/**
Compares two tasks for sorting purposes.
Tasks are compared by:
Priority
Due time
@param other the task being compared
@return a negative number if this task
should come first, positive if after,
or 0 if equal
@author Erik Cabrera
*/

    @Override
    public int compareTo(Task other) {

        // Priority first
        if (this.priority != other.priority) {
            return this.priority - other.priority;
        }

        // Earlier due time first
        return this.dueTime - other.dueTime;
    }

/**
Returns a formatted string representation
of the task.
@return formatted task information
@author Erik Cabrera
*/

    @Override
    public String toString() {
        return "[" + (completed ? "X" : " ") + "] "
                + title
                + " | Priority: " + priority
                + " | Due: " + dueTime + ":00";
    }
}
