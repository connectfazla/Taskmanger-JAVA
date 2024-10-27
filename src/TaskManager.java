import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskManager {
    private ArrayList<Task> tasks;
    private final String TASK_FILE = "data/tasks.txt";

    public TaskManager() {
        tasks = new ArrayList<>();
        loadTasks();
    }

    public void addTask(Scanner scanner) {
        System.out.print("Enter task title: ");
        String title = scanner.nextLine();
        System.out.print("Enter task description: ");
        String description = scanner.nextLine();
        System.out.print("Enter task priority (low, medium, high): ");
        String priority = scanner.nextLine();
        Task newTask = new Task(title, description, priority, "Pending");
        tasks.add(newTask);
        saveTasks();
        System.out.println("Task added.");
    }

    public void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }
        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    public void updateTask(Scanner scanner) {
        System.out.print("Enter task title to update: ");
        String title = scanner.nextLine();
        for (Task task : tasks) {
            if (task.getTitle().equalsIgnoreCase(title)) {
                System.out.print("Enter new status (Pending/In-progress/Completed): ");
                String status = scanner.nextLine();
                task.setStatus(status);
                saveTasks();
                System.out.println("Task updated.");
                return;
            }
        }
        System.out.println("Task not found.");
    }

    public void deleteTask(Scanner scanner) {
        System.out.print("Enter task title to delete: ");
        String title = scanner.nextLine();
        tasks.removeIf(task -> task.getTitle().equalsIgnoreCase(title));
        saveTasks();
        System.out.println("Task deleted.");
    }

    private void saveTasks() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(TASK_FILE))) {
            for (Task task : tasks) {
                writer.println(task.getTitle() + ";" + task.getDescription() + ";" + task.getPriority() + ";" + task.getStatus());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadTasks() {
        try (BufferedReader reader = new BufferedReader(new FileReader(TASK_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 4) {
                    tasks.add(new Task(parts[0], parts[1], parts[2], parts[3]));
                }
            }
        } catch (IOException e) {
            System.out.println("No saved tasks found.");
        }
    }
}
