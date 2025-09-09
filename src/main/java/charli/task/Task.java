package charli.task;

/**
 * Abstract base class representing a task in the Charli chatbot.
 * Provides common functionality for all task types including description and completion status.
 */

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with the given description.
     *
     * @param description The description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }


    public String getStatusIcon() {
        return (isDone ? "✓" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Returns the string representation of the task.
     * Format: [Status] Description
     *
     * @return The formatted string representation
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}

