package charli.command;

import charli.exception.CharliException;
import charli.task.Deadline;
import charli.util.Storage;
import charli.util.TaskList;
import charli.util.Ui;
import java.time.format.DateTimeParseException;



public class AddDeadlineCommand implements Command {
    private String fullCommand;

    public AddDeadlineCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws CharliException {
        try {
            String[] parts = fullCommand.substring(5).split("/by");
            if (parts.length < 2) {
                throw new CharliException("Use: drop [song] /by [dd/mm/yyyy HHmm]");
            } else {
                String description = parts[0].trim();
                String by = parts[1].trim();

                tasks.add(new Deadline(description, by));

                System.out.println("    FIRE! Added this upcoming drop:");
                System.out.println("      " + tasks.get(tasks.size() - 1).toString());
                System.out.println("    Now you have " + tasks.size() + " tracks in your rotation!");
            }
        } catch (DateTimeParseException e) {
            throw new CharliException("Date format should be: dd/mm/yyyy HHmm (e.g. 2/12/2019 1800)");
        } catch (Exception e) {
            throw new CharliException("Use: drop [song] /by [dd/mm/yyyy HHmm]");
        }
    }

    public boolean isExit() {
        return false;
    }
}
