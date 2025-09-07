package charli.command;

import charli.exception.CharliException;
import charli.util.Storage;
import charli.util.TaskList;
import charli.util.Ui;

public class MarkCommand implements Command {
    private String fullCommand;
    private boolean markAsDone;

    public MarkCommand(String fullCommand, boolean markAsDone) {
        this.fullCommand = fullCommand;
        this.markAsDone = markAsDone;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws CharliException {
        try {
            int songIndex = Integer.parseInt(fullCommand.split(" ")[1]) - 1;
            if (songIndex >= 0 && songIndex < tasks.size()) {
                if (markAsDone) {
                    tasks.get(songIndex).markAsDone();
                    System.out.println("    YAS! I've marked this bop as played:");
                } else {
                    tasks.get(songIndex).markAsNotDone();
                    System.out.println("    OK, marked this track as unplayed:");
                }
                System.out.println("      " + tasks.get(songIndex).toString() + "\n");
            } else {
                throw new CharliException("Track number doesn't exist!\n");
            }
        } catch (NumberFormatException e) {
            throw new CharliException("Use 'played [number]' or 'unplayed [number]' format!\n");
        } catch (IndexOutOfBoundsException e) {
            throw new CharliException("Please specify a track number!\n");
        }
    }

    public boolean isExit() {
        return false;
    }
}
