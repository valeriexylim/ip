package charli.command;

import charli.exception.CharliException;
import charli.util.Storage;
import charli.util.TaskList;
import charli.util.Ui;

/**
 * Represents a command to find tasks containing a specific keyword.
 * Handles the 'find' command for searching task descriptions.
 */
public class FindCommand implements Command {
    private String keyword;

    public FindCommand(String fullCommand) throws CharliException {
        // Trim leading/trailing whitespace
        String trimmed = fullCommand.trim();

        // "find" is 4 chars. If trimmed length == 4, then there's no keyword.
        if (trimmed.length() <= 4) {
            throw new CharliException("You gotta specify a keyword...");
        }

        // Otherwise, cut off the command word and trim again
        this.keyword = trimmed.substring(4).trim();
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws CharliException {
        TaskList matchingTasks = new TaskList();

        // Search through tasks
        for (int i = 0; i < tasks.size(); i++) {
            String taskDescription = tasks.get(i).getDescription().toLowerCase();

            if (taskDescription.contains(keyword.toLowerCase())) {
                matchingTasks.add(tasks.get(i));
            }
        }

        // Display results
        StringBuilder message;
        if (matchingTasks.isEmpty()) {
            throw new CharliException("No tracks found containing: " + keyword);
        } else {
            message = new StringBuilder("Here are the matching tracks in your rotation:\n");
            for (int i = 0; i < matchingTasks.size(); i++) {
                message.append(i + 1).append(". ")
                        .append(matchingTasks.get(i)).append("\n");
            }
        }
        return message.toString();
    }

    @Override
    public boolean isExit() {
        return false;
    }




}
