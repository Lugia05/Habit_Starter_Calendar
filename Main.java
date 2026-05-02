
import java.time.DayOfWeek;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        TaskManager manager = new TaskManager();

        boolean running = true;

        while (running) {

            System.out.println("\n===== TASK MANAGER =====");

            System.out.println("1. Add Daily Task");
            System.out.println("2. Add Weekly Task");
            System.out.println("3. View Tasks");
            System.out.println("4. Complete Task");
            System.out.println("5. Undo Complete");
            System.out.println("6. Sort Tasks");
            System.out.println("7. Delete Task");
            System.out.println("8. Save Tasks");
            System.out.println("9. Exit");

            System.out.print("Choose: ");

            int choice = getIntInput(scanner);

            switch (choice) {

                case 1 -> {

                    System.out.print("Title: ");
                    String title = scanner.nextLine();

                    System.out.print("Description: ");
                    String desc = scanner.nextLine();

                    System.out.print("Priority: ");
                    int priority = getIntInput(scanner);

                    System.out.print("Due Hour (0-23): ");
                    int due = getIntInput(scanner);

                    TaskDaily daily
                            = new TaskDaily(title, desc, priority, due);

                    manager.addTask(daily);
                }

                case 2 -> {

                    System.out.print("Title: ");
                    String title = scanner.nextLine();

                    System.out.print("Description: ");
                    String desc = scanner.nextLine();

                    System.out.print("Priority: ");
                    int priority = getIntInput(scanner);

                    System.out.print("Due Hour (0-23): ");
                    int due = getIntInput(scanner);

                    scanner.nextLine();

                    System.out.print("Day (MONDAY etc): ");

                    DayOfWeek day
                            = DayOfWeek.valueOf(scanner.nextLine().toUpperCase());

                    TaskWeekly weekly
                            = new TaskWeekly(title, desc, priority, due, day);

                    manager.addTask(weekly);
                }

                case 3 ->
                    manager.displayTasks();

                case 4 -> {

                    manager.displayTasks();

                    System.out.print("Task Number: ");

                    int index = getIntInput(scanner);

                    manager.completeTask(index - 1);
                }

                case 5 ->
                    manager.undoComplete();

                case 6 ->
                    manager.sortTasks();

                case 7 -> {
                    manager.displayTasks();

                    System.out.print("Task Number: ");

                    int index = getIntInput(scanner);

                    manager.removeTask(index - 1);
                }

                case 8 ->
                    manager.saveTasks();
                case 9 ->
                    running = false;

                default ->
                    System.out.println("Invalid option.");
            }
        }

        scanner.close();
    }

    public static int getIntInput(Scanner scanner) {

    while (true) {

        try {

            return Integer.parseInt(scanner.nextLine());

        } catch (NumberFormatException e) {

            System.out.print("Invalid input. Enter a number: ");
        }
    }
}
}
