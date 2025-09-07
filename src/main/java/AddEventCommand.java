public class AddEventCommand implements Command {
    private String fullCommand;

    public AddEventCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws CharliException {
        try {
            String[] parts = fullCommand.substring(5).split("/from|/to");
            if (parts.length < 3) {
                throw new CharliException("Use: show [event] /from [start time] /to [end time]");
            } else {
                String description = parts[0].trim();
                String from = parts[1].trim();
                String to = parts[2].trim();
                tasks.add(new Event(description, from, to));
                System.out.println("    ICONIC! Added this show to your schedule:");
                System.out.println("      " + tasks.get(tasks.size()-1).toString());
                System.out.println("    Now you have " + tasks.size() + " tracks in your rotation!");
            }
        } catch (Exception e) {
            throw new CharliException("Use: show [event] /from [start time] /to [end time]");
        }
    }

    public boolean isExit() {
        return false;
    }
}
