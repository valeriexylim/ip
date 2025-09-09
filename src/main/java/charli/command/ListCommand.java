package charli.command;

import charli.util.Storage;
import charli.util.TaskList;
import charli.util.Ui;

public class ListCommand implements Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.showRotation();
    }

    public boolean isExit() {
        return false;
    }
}
