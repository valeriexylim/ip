public class Parser {
    public static Command parse(String fullCommand) throws CharliException {
        if (fullCommand.equals("rotation")) {
            return new ListCommand();
        } else if (fullCommand.startsWith("played ")) {
            return new MarkCommand(fullCommand,true);
        } else if (fullCommand.startsWith("unplayed ")) {
            return new MarkCommand(fullCommand, false);
        } else if (fullCommand.startsWith("bop ")) {  //Changed from "todo"
            return new AddTodoCommand(fullCommand);
        } else if (fullCommand.startsWith("drop ")) {  //Changed from "deadline"
            return new AddDeadlineCommand(fullCommand);
        } else if (fullCommand.startsWith("show ")) {  //Changed from "event"
            return new AddEventCommand(fullCommand);
        } else if (fullCommand.startsWith("delete ")) {
            return new DeleteCommand(fullCommand);
        } else if (fullCommand.equals("bye")) {
            return new ExitCommand();
        } else {
            throw new CharliException("Use: bop [song], drop [song] /by [date], or show [event] /from [time] /to [time]");
        }
    }
}
