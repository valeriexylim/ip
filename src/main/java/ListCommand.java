public class ListCommand implements Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.showRotation();
    }

    public boolean isExit() {
        return false;
    }
}
