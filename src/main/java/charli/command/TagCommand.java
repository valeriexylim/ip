// TagCommand.java
package charli.command;

import charli.exception.CharliException;
import charli.util.Storage;
import charli.util.TaskList;
import charli.util.Ui;

public class TagCommand implements Command {
    private static final String USAGE = "Use: tag [task number] [#tag]";
    private final String fullCommand;

    public TagCommand(String fullCommand) { this.fullCommand = fullCommand; }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws CharliException {
        String[] parts = fullCommand.split(" ", 3);
        if (parts.length < 3) throw new CharliException(USAGE);

        int index;

        // Check input track num is an integer
        try {
            index = Integer.parseInt(parts[1]) - 1;
        } catch (NumberFormatException e) {
            throw new CharliException(USAGE);
        }

        // Check track num exists
        boolean isValidIndex = index >= 0 && index < tasks.size();
        if (!isValidIndex) throw new CharliException("Track number doesn't exist!");

        // Add tag to task
        String tag = parts[2].trim();
        tasks.get(index).addTag(tag);


        return "Nice! Tagged track " + (index + 1) + " with " + tag + "\n" + tasks.get(index).toString();
    }

    @Override public boolean isExit() { return false; }
}

