import java.util.Scanner;

public class Charli {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] songs = new Task[100];
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
                    System.out.println("    YUCK No songs yet!!\n");
                } else {
                    System.out.println("    INCREDIBLE MIX INCOMING!!!\n");
                    for (int i = 0; i < count; i++) {
                        System.out.println("      " + (i + 1) + ". " + songs[i].toString()+ "\n");
                    }
                }
            } else if (input.equals("bye")) {
                System.out.println("    XOXO\n" + logo);
            } else if (input.startsWith("played ")) {
                //mark song as played
                try {
                    int songIndex = Integer.parseInt(input.substring(7)) - 1;
                    if (songIndex >= 0 && songIndex < count) {
                        songs[songIndex].markAsPlayed();
                        System.out.println("    YAS! I've marked this bop as played:");
                        System.out.println("    " + songs[songIndex].toString() + "\n");
                    } else {
                        System.out.println("    ERROR: Song number doesn't exist!\n");
                    }
                } catch (Exception e) {
                    System.out.println("    ERROR: Use 'played [number]' format!\n");
                }
            } else if (input.startsWith("unplayed ")) {
                try {
                    int songIndex = Integer.parseInt(input.substring(9)) - 1;
                    if (songIndex >= 0 && songIndex < count) {
                        songs[songIndex].markAsUnplayed();
                        System.out.println("    OK, marked this track as unplayed:");
                        System.out.println("      " + songs[songIndex].toString() + "\n");
                    } else {
                        System.out.println("    ERROR: Song number doesn't exist!\n");
                    }
                } catch (Exception e) {
                    System.out.println("    ERROR: Use 'unplayed [number]' format!\n");
                }
            } else {
                songs[count] = new Task(input);
                count++;
                System.out.println("    added: " + input);
            }

        } while (!input.equals("bye"));

        scanner.close();
    }
}


