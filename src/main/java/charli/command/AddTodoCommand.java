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
            if (description.isEmpty()) {
                throw new CharliException("Bop description can't be empty! Use: bop [song name]");
            } else {
                tasks.add(new Todo(description));
                System.out.println("    YAS! Added this bop to your rotation:");
                System.out.println("      " + tasks.get(tasks.size() - 1).toString());
                System.out.println("    Now you have " + tasks.size() + " tracks in your rotation!");
            }
        } catch (Exception e) {
            throw new CharliException("Use: bop [song name]");
        }
        return null;
    }

    public boolean isExit() {
        return false;
    }
}
