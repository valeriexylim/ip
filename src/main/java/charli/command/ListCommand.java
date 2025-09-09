package charli.command;

import charli.exception.CharliException;
import charli.util.Storage;
import charli.util.TaskList;
import charli.util.Ui;

/**
 * Command to display all tasks in the task list.
 * Handles the 'rotation' command for task listing.
 */
public class ListCommand implements Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.showRotation();
    }

    public boolean isExit() {
        return false;
    }
}
