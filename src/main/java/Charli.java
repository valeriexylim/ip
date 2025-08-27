import java.util.Scanner;

public class Charli {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] mix = new Task[100];
        int count = 0;
        String logo =
                "         ,--./,-.   \n" +
                "        / #      \\  \n" +
                "       |          | \n" +
                "        \\        /  \n" +
                "         `._,._.'   \n";

        System.out.println("Hi British Vogue, I'm CharliXCX!\nWhat club classics would you like on your summer rotation?\n");
        String input;

        do {
            input = scanner.nextLine();
            System.out.println("    ____________________________________________________________");

            if (input.equals("rotation")) {
                showRotation(mix, count);
            } else if (input.startsWith("played ")) {
                count = markSong(input, mix, count, true);
            } else if (input.startsWith("unplayed ")) {
                count = markSong(input, mix, count, false);
            } else if (input.startsWith("bop ")) {  // Changed from "todo"
                count = addTodo(input, mix, count);
            } else if (input.startsWith("drop ")) {  // Changed from "deadline"
                count = addDeadline(input, mix, count);
            } else if (input.startsWith("show ")) {  // Changed from "event"
                count = addEvent(input, mix, count);
            } else if (input.equals("bye")) {
                System.out.println("    XOXO\n" + logo);
            } else {
                System.out.println("    ERROR! Use: bop [song], drop [song] /by [date], or show [event] /from [time] /to [time]");
            }

            System.out.println("    ____________________________________________________________");

        } while (!input.equals("bye"));

        scanner.close();
    }

    private static void showRotation(Task[] mix, int count) {
        if (count == 0) {
            System.out.println("    YUCK No songs yet!!\n");
        } else {
            System.out.println("    INCREDIBLE MIX INCOMING!!! (" + count + " tracks)\n");
            for (int i = 0; i < count; i++) {
                System.out.println("      " + (i + 1) + ". " + mix[i].toString() + "\n");
            }
        }
    }

    private static int markSong(String input, Task[] mix, int count, boolean markAsDone) {
        try {
            int songIndex = Integer.parseInt(input.split(" ")[1]) - 1;
            if (songIndex >= 0 && songIndex < count) {
                if (markAsDone) {
                    mix[songIndex].markAsDone();
                    System.out.println("    YAS! I've marked this bop as played:");
                } else {
                    mix[songIndex].markAsNotDone();
                    System.out.println("    OK, marked this track as unplayed:");
                }
                System.out.println("      " + mix[songIndex].toString() + "\n");
            } else {
                System.out.println("    ERROR: Track number doesn't exist!\n");
            }
        } catch (Exception e) {
            System.out.println("    ERROR: Use 'played [number]' or 'unplayed [number]' format!\n");
        }
        return count;
    }

    private static int addTodo(String input, Task[] mix, int count) {
        try {
            String description = input.substring(4).trim();
            if (description.isEmpty()) {
                System.out.println("    ERROR! Bop description can't be empty! Use: bop [song name]");
            } else {
                mix[count] = new Todo(description);
                count++;
                System.out.println("    YAS! Added this bop to your rotation:");
                System.out.println("      " + mix[count-1].toString());
                System.out.println("    Now you have " + count + " tracks in your rotation!");
            }
        } catch (Exception e) {
            System.out.println("    ERROR! Use: bop [song name]");
        }
        return count;
    }

    private static int addDeadline(String input, Task[] mix, int count) {
        try {
            String[] parts = input.substring(5).split("/by");
            if (parts.length < 2) {
                System.out.println("    ERROR! Use: drop [song] /by [release date]");
            } else {
                String description = parts[0].trim();
                String by = parts[1].trim();
                mix[count] = new Deadline(description, by);
                count++;
                System.out.println("    FIRE! Added this upcoming drop:");
                System.out.println("      " + mix[count-1].toString());
                System.out.println("    Now you have " + count + " tracks in your rotation!");
            }
        } catch (Exception e) {
            System.out.println("    ERROR! Use: drop [song] /by [release date]");
        }
        return count;
    }

    private static int addEvent(String input, Task[] mix, int count) {
        try {
            String[] parts = input.substring(5).split("/from|/to");
            if (parts.length < 3) {
                System.out.println("    ERROR! Use: show [event] /from [start time] /to [end time]");
            } else {
                String description = parts[0].trim();
                String from = parts[1].trim();
                String to = parts[2].trim();
                mix[count] = new Event(description, from, to);
                count++;
                System.out.println("    ICONIC! Added this show to your schedule:");
                System.out.println("      " + mix[count-1].toString());
                System.out.println("    Now you have " + count + " tracks in your rotation!");
            }
        } catch (Exception e) {
            System.out.println("    ERROR! Use: show [event] /from [start time] /to [end time]");
        }
        return count;
    }
}


