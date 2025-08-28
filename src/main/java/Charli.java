import java.util.Scanner;
import java.util.ArrayList;

public class Charli {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> mix = new ArrayList<>();
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
                showRotation(mix);
            } else if (input.startsWith("played ")) {
                markSong(input, mix, true);
            } else if (input.startsWith("unplayed ")) {
                markSong(input, mix, false);
            } else if (input.startsWith("bop ")) {  // Changed from "todo"
                addTodo(input, mix);
            } else if (input.startsWith("drop ")) {  // Changed from "deadline"
                addDeadline(input, mix);
            } else if (input.startsWith("show ")) {  // Changed from "event"
                addEvent(input, mix);
            } else if (input.startsWith("delete ")) {
                removeSong(input, mix);
            } else if (input.equals("bye")) {
                System.out.println("    XOXO\n" + logo);
            } else {
                System.out.println("    ERROR! Use: bop [song], drop [song] /by [date], or show [event] /from [time] /to [time]");
            }

            System.out.println("    ____________________________________________________________");

        } while (!input.equals("bye"));

        scanner.close();
    }


    private static void showRotation(ArrayList<Task> mix) {
        if (mix.isEmpty()) {
            System.out.println("    YUCK No songs yet!!\n");
        } else {
            System.out.println("    INCREDIBLE MIX INCOMING!!! (" + mix.size() + " tracks)\n");
            for (int i = 0; i < mix.size(); i++) {
                System.out.println("      " + (i + 1) + ". " + mix.get(i).toString() + "\n");
            }
        }
    }

    private static void markSong(String input, ArrayList<Task> mix, boolean markAsDone) {
        try {
            int songIndex = Integer.parseInt(input.split(" ")[1]) - 1;
            if (songIndex >= 0 && songIndex < mix.size()) {
                if (markAsDone) {
                    mix.get(songIndex).markAsDone();
                    System.out.println("    YAS! I've marked this bop as played:");
                } else {
                    mix.get(songIndex).markAsNotDone();
                    System.out.println("    OK, marked this track as unplayed:");
                }
                System.out.println("      " + mix.get(songIndex).toString() + "\n");
            } else {
                System.out.println("    ERROR: Track number doesn't exist!\n");
            }
        } catch (Exception e) {
            System.out.println("    ERROR: Use 'played [number]' or 'unplayed [number]' format!\n");
        }
    }

    private static void addTodo(String input, ArrayList<Task> mix) {
        try {
            String description = input.substring(4).trim();
            if (description.isEmpty()) {
                System.out.println("    ERROR! Bop description can't be empty! Use: bop [song name]");
            } else {
                mix.add(new Todo(description));
                System.out.println("    YAS! Added this bop to your rotation:");
                System.out.println("      " + mix.get(mix.size() - 1).toString());
                System.out.println("    Now you have " + mix.size() + " tracks in your rotation!");
            }
        } catch (Exception e) {
            System.out.println("    ERROR! Use: bop [song name]");
        }
    }

    //Delete method - remove song
    private static void removeSong(String input, ArrayList<Task> mix) {
        try {
            int songIndex = Integer.parseInt(input.split(" ")[1]) - 1;
            if (songIndex >= 0 && songIndex < mix.size()) {
                Task removedSong = mix.remove(songIndex);
                System.out.println("    Noted. I've removed this track:");
                System.out.println("      " + removedSong.toString());
                System.out.println("    Now you have " + mix.size() + " tracks in your rotation!");
            } else {
                System.out.println("    ERROR: Track number doesn't exist!");
            }
        } catch (Exception e) {
            System.out.println("    ERROR: Use 'remove [number]' format!");
        }
    }

    private static void addDeadline(String input, ArrayList<Task> mix) {
        try {
            String[] parts = input.substring(5).split("/by");
            if (parts.length < 2) {
                System.out.println("    ERROR! Use: drop [song] /by [release date]");
            } else {
                String description = parts[0].trim();
                String by = parts[1].trim();
                mix.add(new Deadline(description, by));
                System.out.println("    FIRE! Added this upcoming drop:");
                System.out.println("      " + mix.get(mix.size() - 1).toString());
                System.out.println("    Now you have " + mix.size() + " tracks in your rotation!");
            }
        } catch (Exception e) {
            System.out.println("    ERROR! Use: drop [song] /by [release date]");
        }
    }

    private static void addEvent(String input, ArrayList<Task> mix) {
        try {
            String[] parts = input.substring(5).split("/from|/to");
            if (parts.length < 3) {
                System.out.println("    ERROR! Use: show [event] /from [start time] /to [end time]");
            } else {
                String description = parts[0].trim();
                String from = parts[1].trim();
                String to = parts[2].trim();
                mix.add(new Event(description, from, to));
                System.out.println("    ICONIC! Added this show to your schedule:");
                System.out.println("      " + mix.get(mix.size()-1).toString());
                System.out.println("    Now you have " + mix.size() + " tracks in your rotation!");
            }
        } catch (Exception e) {
            System.out.println("    ERROR! Use: show [event] /from [start time] /to [end time]");
        }
    }
}


