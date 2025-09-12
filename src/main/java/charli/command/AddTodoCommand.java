package charli.command;

import charli.exception.CharliException;
import charli.task.Todo;
import charli.util.Storage;
import charli.util.TaskList;
import charli.util.Ui;
/**
 * Command to add a new todo task to the taslist.
 * Handles the 'drop' command for adding simple tasks without deadlines.
 */
public class AddTodoCommand implements Command {
    public String fullCommand;

    public AddTodoCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) throws CharliException {
        try {
            String description = fullCommand.substring(4).trim();
            tasks.add(new Todo(description));
            StringBuilder message = new StringBuilder();
            message.append("HOT! Added this bop to your rotation:")
                    .append(tasks.get(tasks.size() - 1).toString())
                    .append("\n")
                    .append("Now you have ").append(tasks.size()).append(" tracks in your rotation!");
            return message.toString();

        } catch (Exception e) {
            throw new CharliException("Use: bop [song name]");
        }
    }

    public boolean isExit() {
        return false;
    }
}
