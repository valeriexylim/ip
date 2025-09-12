package charli.command;

import charli.exception.CharliException;
import charli.task.Deadline;
import charli.util.Storage;
import charli.util.TaskList;
import charli.util.Ui;

import java.time.format.DateTimeParseException;

/**
 * Command to add a new deadline task to the taslist.
 * Handles the 'drop' command for adding simple tasks without deadlines.
 */
public class AddDeadlineCommand implements Command {
    private String fullCommand;

    public AddDeadlineCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) throws CharliException {
        try {
            String[] parts = fullCommand.substring(5).split("/by");
            if (parts.length < 2) {
                throw new CharliException("Use: drop [song] /by [dd/mm/yyyy HHmm]");
            } else {
                String description = parts[0].trim();
                String by = parts[1].trim();

                tasks.add(new Deadline(description, by));

                StringBuilder message = new StringBuilder();
                message.append("    FIRE! Added this upcoming drop:\n    ")
                        .append(tasks.get(tasks.size() - 1).toString())
                        .append("\n")
                        .append("    Now you have ")
                        .append(tasks.size())
                        .append(" tracks in your rotation!");
                return message.toString();
            }
        } catch (DateTimeParseException e) {
            throw new CharliException("Date format should be: dd/mm/yyyy HHmm (e.g. 2/12/2019 1800)");
        } catch (Exception e) {
            throw new CharliException("Use: drop [song] /by [dd/mm/yyyy HHmm]");
        }
    }

    public boolean isExit() {
        return false;
    }
}
