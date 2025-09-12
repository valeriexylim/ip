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

    public String execute(TaskList tasks, Ui ui, Storage storage) throws CharliException {
        try {
            int songIndex = Integer.parseInt(fullCommand.split(" ")[1]) - 1;
            if (songIndex >= 0 && songIndex < tasks.size()) {
                Task removedSong = tasks.remove(songIndex);

                StringBuilder message = new StringBuilder("Got it! I've removed this track:\n");
                message.append(removedSong.toString())
                        .append("\nNow you have ").append(tasks.size()).append(" tracks in your rotation!");
                return message.toString();
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

