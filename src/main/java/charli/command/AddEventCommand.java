package charli.command;

import charli.exception.CharliException;
import charli.task.Event;
import charli.util.Storage;
import charli.util.TaskList;
import charli.util.Ui;

/**
 * Command to add a new event task with start and end time to tasklist.
 * Handles the 'show' command for adding time-bound events.
 */
public class AddEventCommand implements Command {
    private String fullCommand;

    public AddEventCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) throws CharliException {
        try {
            String[] parts = fullCommand.substring(5).split("/from|/to");
            if (parts.length < 3) {
                throw new CharliException("Use: show [event] /from [start time] /to [end time]");
            } else {
                String description = parts[0].trim();
                String from = parts[1].trim();
                String to = parts[2].trim();
                tasks.add(new Event(description, from, to));

                StringBuilder message = new StringBuilder("    ICONIC! Added this show to your schedule:\n");
                message.append("      " + tasks.get(tasks.size() - 1).toString())
                        .append("\n").append("    Now you have ").append(tasks.size()).append(" tracks in your rotation!");
                return message.toString();
            }
        } catch (Exception e) {
            throw new CharliException("Use: show [event] /from [start time] /to [end time]");
        }
    }

    public boolean isExit() {
        return false;
    }
}
