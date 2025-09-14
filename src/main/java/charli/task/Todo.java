package charli.task;

/**
 * Represents a todo task without any date/time attached.
 * Todos are simple tasks that need to be done someday.
 */


public class Todo extends Task {

    /**
     * Constructs a new Todo task with the given description.
     *
     * @param description The description of the todo task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the todo task.
     * Format: [T][Status] Description
     *
     * @return The formatted string representation
     */

    @Override
    public String toSaveString() {
        // T | done(0/1) | description | tagsCsv
        return String.format("T | %d | %s | %s", isDone ? 1 : 0, getDescription(), tagsCsv());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
