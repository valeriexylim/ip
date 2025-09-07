package charli.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import charli.task.Task;
import charli.task.Todo;
import charli.task.Deadline;
import charli.task.Event;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
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
                    if (parts.length < 4) {
                        return null; //Invalid deadline format
                    }
                    LocalDateTime by = LocalDateTime.parse(parts[3], DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                    task = new Deadline(description, by);
                    break;
                case "E":
                    //For Event, the 4th and 5th parts are the 'from' and 'to' strings
                    if (parts.length < 5) {
                        return null; //Invalid event format
                    }
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
            //Catch any errors during parsing like NullPointerException, DateTimeParseException, ArrayIndexOutOfBoundsException
            return null;
        }
    }
    /*
    Check if saved ./data/charli.txt exists and if it does,
    load tasks into this instance's tasks list - ensure previous work saved
     */
    public List<Task> load() {
        List<Task> tasks = new ArrayList<>();
        try {
            //Create file object to represent data file
            File file = new File(filePath);

            //If file doesnt exist, dont need to load
            if (!file.exists()) {
                return tasks;
            }

            //Else, need to load, use Scanner to read the file
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = parseSavedTask(line); // Convert the text line back to a Task object
                if (task != null) { //if parser succeeded
                    tasks.add(task);
                }
            }
            scanner.close(); //prevent leaks
        } catch (IOException e) {
            //still need to handle exception although if (!file.exists()) prevents it
            System.out.println("    Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }


    public void save(List<Task> tasks) {
        try {
            //1. Ensure the ./data directory is created if program runs for first time
            File dataDir = new File("./data");
            if (!dataDir.exists()) {
                dataDir.mkdirs(); // This creates the directory and any necessary parent directories
            }

            //2. Create a FileWriter to write to the file
            FileWriter writer = new FileWriter(filePath);

            //3. Loop through all tasks and convert them to a savable string format
            for (Task task : tasks) {
                //Write string to file followed by newline
                writer.write(convertTaskToFileString(task) + System.lineSeparator());
            }

            //4. Close the writer to ensure all data is saved and resources are freed.
            writer.close();

        } catch (IOException e) {
            System.out.println("Something went wrong saving tasks: " + e.getMessage());
        }
    }

    //Helper for saveTasksToFile after program end
    private String convertTaskToFileString(Task task) {
        String typeCode;
        String details;

        if (task instanceof Todo) {
            typeCode = "T";
            details = task.getDescription();
        } else if (task instanceof Deadline) {
            typeCode = "D";
            Deadline d = (Deadline) task;
            details = d.getDescription() + " | " + d.getBy().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
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
