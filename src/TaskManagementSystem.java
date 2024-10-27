import java.util.Scanner;

public class TaskManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AuthManager authManager = new AuthManager();
        TaskManager taskManager = new TaskManager();

        System.out.println("Welcome to Task Management System");

        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (authManager.login(username, password)) {
            System.out.println("Login successful!");

            boolean running = true;
            while (running) {
                System.out.println("\nOptions:");
                System.out.println("1. Add Task");
                System.out.println("2. View Tasks");
                System.out.println("3. Update Task");
                System.out.println("4. Delete Task");
                System.out.println("5. Exit");
                System.out.print("Choose an option: ");
                
                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                switch (choice) {
                    case 1:
                        taskManager.addTask(scanner);
                        break;
                    case 2:
                        taskManager.viewTasks();
                        break;
                    case 3:
                        taskManager.updateTask(scanner);
                        break;
                    case 4:
                        taskManager.deleteTask(scanner);
                        break;
                    case 5:
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid option. Try again.");
                }
            }
        } else {
            System.out.println("Login failed.");
        }
        scanner.close();
    }
}
