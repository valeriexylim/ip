import java.util.Scanner;
public class Ui {
    private Scanner scanner;
    public Ui() {
        this.scanner = new Scanner(System.in);

    }

    public void showWelcome() {
        String logo =
                "         ,--./,-.   \n" +
                "        / #      \\  \n" +
                "       |          | \n" +
                "        \\        /  \n" +
                "         `._,._.'   \n";
        System.out.println("Hi British Vogue, I'm CharliXCX!\nWhat club classics would you like on your summer rotation?\n");

    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showLine() {
        System.out.println("    ____________________________________________________________");

    }

    public void showError(String message) {
        System.out.println("    ERROR! " + message);
    }

    public void showLoadingError() {
        System.out.pintln(" Data file not found. Starting with an empty list.")
    }

    public void showGoodbye() {
        System.out.println("    XOXO");
        String logo =
                "         ,--./,-.   \n" +
                        "        / #      \\  \n" +
                        "       |          | \n" +
                        "        \\        /  \n" +
                        "         `._,._.'   \n";
        System.out.println(logo);
    }
}
