package charli;

import charli.command.Command;
import charli.exception.CharliException;
import charli.util.Parser;
import charli.util.Storage;
import charli.util.TaskList;
import charli.util.Ui;

public class Charli {

    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    public Charli() {
        ui = new Ui();
        storage = new Storage("./data/charli.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }

    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (CharliException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Charli().run();
    }

}


