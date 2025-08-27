import java.util.Scanner;

public class Charli {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] mix = new String[100];
        int count = 0;
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

            if(input.equals("rotation")) {
                if (count == 0) {
                    System.out.println("    YUCK No songs yet!!\n" + logo);
                } else {
                    System.out.println("    INCREDIBLE MIX INCOMING!!!\n");
                    for (int i = 0; i < count; i++) {
                        System.out.println("      " + (i + 1) + ". " + mix[i] + "\n");
                    }
                }
            } else if (input.equals("bye")) {
                System.out.println("    XOXO\n" + logo);
            } else {
                mix[count] = input;
                count++;
                System.out.println("    added: " + input);
            }
        } while (!input.equals("bye"));

        scanner.close();
    }
}


