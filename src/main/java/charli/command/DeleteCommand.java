package charli.command;

import charli.exception.CharliException;
import charli.task.Task;
import charli.util.Storage;
import charli.util.TaskList;
import charli.util.Ui;

/**
 * Command to delete tasks from the task list.
 * Handles the 'delete' command for task removal.
 */
public class DeleteCommand implements Command {
    private String fullCommand;

    public DeleteCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws CharliException {
        try {
            int songIndex = Integer.parseInt(fullCommand.split(" ")[1]) - 1;
            if (songIndex >= 0 && songIndex < tasks.size()) {
                Task removedSong = tasks.remove(songIndex);
                System.out.println("    Noted. I've removed this track:");
                System.out.println("      " + removedSong.toString());
                System.out.println("    Now you have " + tasks.size() + " tracks in your rotation!");
            } else {
                throw new CharliException("Track number doesn't exist!");
            }
        } catch (NumberFormatException e) {
            throw new CharliException("Use 'delete [number]' format!");
        } catch (IndexOutOfBoundsException e) {
            throw new CharliException("Please specify a track number!");
        }
    }

    public boolean isExit() {
        return false;
    }
}

