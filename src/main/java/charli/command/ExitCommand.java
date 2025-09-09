package charli.command;

import charli.util.Storage;
import charli.util.TaskList;
import charli.util.Ui;

public class ExitCommand implements Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.save(tasks.getTasks());
        ui.showGoodbye();
    }

    public boolean isExit() {
        return true;
    }
}
