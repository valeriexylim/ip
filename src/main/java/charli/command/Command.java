package charli.command;

import charli.exception.CharliException;
import charli.util.Storage;
import charli.util.TaskList;
import charli.util.Ui;

public interface Command {
    void execute(TaskList tasks, Ui ui, Storage storage) throws CharliException;
    boolean isExit();
}
