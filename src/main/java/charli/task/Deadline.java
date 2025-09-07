package charli.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDateTime by;

    //define format for input and display
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter DISPLAY_FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy, h:mma");

    //Constructor for user input - strings
    public Deadline(String description, String by) throws DateTimeParseException {
        super(description);
        this.by = LocalDateTime.parse(by, INPUT_FORMATTER);
    }

    //Constructor for internal use - parse previous tasks in storage file
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public LocalDateTime getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DISPLAY_FORMATTER) + ")";
    }
}
