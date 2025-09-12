package charli.util;

import charli.command.AddDeadlineCommand;
import charli.command.AddEventCommand;
import charli.command.AddTodoCommand;
import charli.command.Command;
import charli.command.DeleteCommand;
import charli.command.ExitCommand;
import charli.command.FindCommand;
import charli.command.ListCommand;
import charli.command.MarkCommand;
import charli.exception.CharliException;

public class Parser {

    private static final String HELP_MESSAGE = """
            Please speak my language...
            
            COMMANDS YOU CAN MAKE:
            
            rotation                         
            played <n> | unplayed <n>       
            bop <song>               
            drop <song> /by <dd/mm/yyyy HHmm>     
            show <event> /from <time> /to <time> 
            delete <n>                       
            find <keyword>                   
            bye                              

            EXAMPLES:
            
            bop Apple
            drop "Rich Man" /by 19/2/2025 1800
            show "studio session" /from 2pm /to 3pm
            played 2
            
            Now, you try!
            """;

    public static Command parse(String fullCommand) throws CharliException {

        if (fullCommand.equals("rotation")) {
            return new ListCommand();
        } else if (fullCommand.startsWith("played")) {
            return new MarkCommand(fullCommand,true);
        } else if (fullCommand.startsWith("unplayed")) {
            return new MarkCommand(fullCommand, false);
        } else if (fullCommand.startsWith("bop")) {  // Changed from "todo"
            return new AddTodoCommand(fullCommand);
        } else if (fullCommand.startsWith("drop")) {  // Changed from "deadline"
            return new AddDeadlineCommand(fullCommand);
        } else if (fullCommand.startsWith("show")) {  // Changed from "event"
            return new AddEventCommand(fullCommand);
        } else if (fullCommand.startsWith("delete")) {
            return new DeleteCommand(fullCommand);
        } else if (fullCommand.equals("bye")) {
            return new ExitCommand();
        } else if (fullCommand.startsWith("find")) {
            return new FindCommand(fullCommand);
        } else {
            throw new CharliException(HELP_MESSAGE);
        }
    }
}
