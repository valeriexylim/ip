import java.util.ArrayList;
import java.util.List;

public class TaskList {
    public List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void remove(Task task) {
        tasks.remove(task);
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void showRotation() {
        if(tasks.isEmpty())
        {
            System.out.println("    YUCK No songs yet!!\n");
        } else {
            System.out.println("    INCREDIBLE MIX INCOMING!!! (" + tasks.size() + " tracks)\n");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("      " + (i + 1) + ". " + tasks.get(i).toString() + "\n");
            }
        }
    }
}
