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
        try {
            this.keyword = fullCommand.substring(5).trim();
            if (keyword.isEmpty()) {
                throw new CharliException("You gotta specify a keyword...");
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new CharliException("Use: find [keyword]");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws CharliException {
        TaskList matchingTasks = new TaskList();

        // Search through tasks
        for (int i = 0; i < tasks.size(); i++) {
            String taskDescription = tasks.get(i).getDescription().toLowerCase();

            if (taskDescription.contains(keyword.toLowerCase())) {
                matchingTasks.add(tasks.get(i));
            }
        }

        // Display results
        if (matchingTasks.isEmpty()) {
            throw new CharliException("No tracks found containing: " + keyword);
        } else {
            System.out.println("    Here are the matching tracks in your rotation:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.println("    " + (i + 1) + ". " + matchingTasks.get(i));
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }




}
