import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;

public class Charli {

    private List<Task> tasks;
    private String filePath = "./data/charli.txt";
    private Scanner scanner;

    public Charli() {
        tasks = new ArrayList<>();
        scanner = new Scanner(System.in);
        loadTasksFromFile(); //load tasks from file when Charli starts
    }
    public static void main(String[] args) {
        new Charli().run();
    }

    public void run() {

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
                showRotation();
            } else if (input.startsWith("played ")) {
                markSong(input,true);
            } else if (input.startsWith("unplayed ")) {
                markSong(input, false);
            } else if (input.startsWith("bop ")) {  //Changed from "todo"
                addTodo(input);
            } else if (input.startsWith("drop ")) {  //Changed from "deadline"
                addDeadline(input);
            } else if (input.startsWith("show ")) {  //Changed from "event"
                addEvent(input);
            } else if (input.startsWith("delete ")) {
                removeSong(input);
            } else if (input.equals("bye")) {
                saveTasksToFile();
                System.out.println("    XOXO\n" + logo);
            } else {
                System.out.println("    ERROR! Use: bop [song], drop [song] /by [date], or show [event] /from [time] /to [time]");
            }
            System.out.println("    ____________________________________________________________");

        } while (!input.equals("bye"));

        scanner.close();
    }


    private void showRotation() {
        if (tasks.isEmpty()) {
            System.out.println("    YUCK No songs yet!!\n");
        } else {
            System.out.println("    INCREDIBLE MIX INCOMING!!! (" + tasks.size() + " tracks)\n");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("      " + (i + 1) + ". " + tasks.get(i).toString() + "\n");
            }
        }
    }

    private void markSong(String input, boolean markAsDone) {
        try {
            int songIndex = Integer.parseInt(input.split(" ")[1]) - 1;
            if (songIndex >= 0 && songIndex < tasks.size()) {
                if (markAsDone) {
                    tasks.get(songIndex).markAsDone();
                    System.out.println("    YAS! I've marked this bop as played:");
                } else {
                    tasks.get(songIndex).markAsNotDone();
                    System.out.println("    OK, marked this track as unplayed:");
                }
                System.out.println("      " + tasks.get(songIndex).toString() + "\n");
            } else {
                System.out.println("    ERROR: Track number doesn't exist!\n");
            }
        } catch (Exception e) {
            System.out.println("    ERROR: Use 'played [number]' or 'unplayed [number]' format!\n");
        }
    }

    private void addTodo(String input) {
        try {
            String description = input.substring(4).trim();
            if (description.isEmpty()) {
                System.out.println("    ERROR! Bop description can't be empty! Use: bop [song name]");
            } else {
                tasks.add(new Todo(description));
                System.out.println("    YAS! Added this bop to your rotation:");
                System.out.println("      " + tasks.get(tasks.size() - 1).toString());
                System.out.println("    Now you have " + tasks.size() + " tracks in your rotation!");
            }
        } catch (Exception e) {
            System.out.println("    ERROR! Use: bop [song name]");
        }
    }

    //Delete method - remove song
    private void removeSong(String input) {
        try {
            int songIndex = Integer.parseInt(input.split(" ")[1]) - 1;
            if (songIndex >= 0 && songIndex < tasks.size()) {
                Task removedSong = tasks.remove(songIndex);
                System.out.println("    Noted. I've removed this track:");
                System.out.println("      " + removedSong.toString());
                System.out.println("    Now you have " + tasks.size() + " tracks in your rotation!");
            } else {
                System.out.println("    ERROR: Track number doesn't exist!");
            }
        } catch (Exception e) {
            System.out.println("    ERROR: Use 'remove [number]' format!");
        }
    }

    private void addDeadline(String input) {
        try {
            String[] parts = input.substring(5).split("/by");
            if (parts.length < 2) {
                System.out.println("    ERROR! Use: drop [song] /by [release date]");
            } else {
                String description = parts[0].trim();
                String by = parts[1].trim();
                tasks.add(new Deadline(description, by));
                System.out.println("    FIRE! Added this upcoming drop:");
                System.out.println("      " + tasks.get(tasks.size() - 1).toString());
                System.out.println("    Now you have " + tasks.size() + " tracks in your rotation!");
            }
        } catch (Exception e) {
            System.out.println("    ERROR! Use: drop [song] /by [release date]");
        }
    }

    private void addEvent(String input) {
        try {
            String[] parts = input.substring(5).split("/from|/to");
            if (parts.length < 3) {
                System.out.println("    ERROR! Use: show [event] /from [start time] /to [end time]");
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
            System.out.println("    ERROR! Use: show [event] /from [start time] /to [end time]");
        }
    }

    //helper for loadTasks
    private Task parseSavedTask(String line) {
        try {
            //Split the line by " | "
            String[] parts = line.split(" \\| ");
            //Skip corrupted lines e.g. due to manual edits/program crash mid saving
            if (parts.length < 3) {
                return null; //Invalid line, skip it
            }

            //Extract tasks details to build tasks - type, isDone, description
            String type = parts[0];
            boolean isDone = parts[1].equals("1"); // "1" means done, "0" means not done
            String description = parts[2];

            Task task;
            //Create the correct type of task based on the code
            switch (type) {
                case "T":
                    task = new Todo(description);
                    break;
                case "D":
                    //For Deadline, the 4th part is the 'by' date string
                    if (parts.length < 4) return null; //Invalid deadline format
                    task = new Deadline(description, parts[3]);
                    break;
                case "E":
                    //For Event, the 4th and 5th parts are the 'from' and 'to' strings
                    if (parts.length < 5) return null; //Invalid event format
                    task = new Event(description, parts[3], parts[4]);
                    break;
                default:
                    return null; //Unknown task type, skip it
            }

            //If the task was marked as done in the file, mark it as done now
            if (isDone) {
                task.markAsDone();
            }
            return task;

        } catch (Exception e) {
            //Catch any errors during parsing and skip this line
            return null;
        }
    }
    /*
    Check if saved ./data/charli.txt exists and if it does,
    load tasks into this instance's tasks list - ensure previous work saved
     */
    private void loadTasksFromFile() {
        try {
            //Create file object to represent data file
            java.io.File file = new java.io.File(filePath);

            //If file doesnt exist, dont need to load
            if (!file.exists()) {
                return; // Nothing to load, just return
            }

            //Else, need to load, use Scanner to read the file
            java.util.Scanner scanner = new java.util.Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = parseSavedTask(line); // Convert the text line back to a Task object
                if (task != null) { //if parser succeeded
                    tasks.add(task);
                }
            }
            scanner.close(); //prevent leaks
        } catch (java.io.FileNotFoundException e) {
            //still need to handle exception although if (!file.exists()) prevents it
            System.out.println("Data file not found. Starting with an empty list.");
        }
    }


    private void saveTasksToFile() {
        try {
            //1. Ensure the ./data directory is created if program runs for first time
            java.io.File dataDir = new java.io.File("./data");
            if (!dataDir.exists()) {
                dataDir.mkdirs(); // This creates the directory and any necessary parent directories
            }

            //2. Create a FileWriter to write to the file
            java.io.FileWriter writer = new java.io.FileWriter(filePath);

            //3. Loop through all tasks and convert them to a savable string format
            for (Task task : tasks) {
                //Write string to file followed by newline
                writer.write(convertTaskToFileString(task) + System.lineSeparator());
            }

            //4. Close the writer to ensure all data is saved and resources are freed.
            writer.close();

        } catch (java.io.IOException e) {
            System.out.println("Something went wrong saving tasks: " + e.getMessage());
        }
    }

    //helper for saveTasksToFile after program end
    private String convertTaskToFileString(Task task) {
        String typeCode;
        String details;

        if (task instanceof Todo) {
            typeCode = "T";
            details = task.getDescription();
        } else if (task instanceof Deadline) {
            typeCode = "D";
            Deadline d = (Deadline) task;
            details = d.getDescription() + " | " + d.getBy();
        } else if (task instanceof Event) {
            typeCode = "E";
            Event e = (Event) task;
            details = e.getDescription() + " | " + e.getFrom() + " | " + e.getTo();
        } else {
            //Fallback due to unknown task type
            typeCode = "?";
            details = task.getDescription();
        }
        //convert isDone to number 1/0
        int isDone = task.isDone() ? 1 : 0; // Use 1 for done, 0 for not done
        return typeCode + " | " + isDone + " | " + details;
    }



}


