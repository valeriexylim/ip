public class ExitCommand implements Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.save(tasks.getTasks());
        ui.showGoodbye();
    }
    public boolean isExit() {
        return true;
    }
}
