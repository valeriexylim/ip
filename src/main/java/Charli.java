import java.util.Scanner;

public class Charli {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String logo =
                "         ,--./,-.   \n"
            + "        / #      \\  \n"
            + "       |          | \n"
            + "        \\        /  \n"
            + "         `._,._.'   \n";

        System.out.println("Hi British Vogue, I'm CharliXCX!\nWhat club classics would you like on your summer rotation?\n");
        String input;

        //read input until the input is "bye"
        do {
            //System.out.print("    "); // Indentation for user input
            input = scanner.nextLine();

            System.out.println("    ____________________________________________________________");

            if (!input.equals("bye")) {
                System.out.println("    " + input);
            } else {
                System.out.println("    XOXO\n" + logo);
            }

        } while (!input.equals("bye"));

        scanner.close();
    }
}


