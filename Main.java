
import java.time.DayOfWeek;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        /*
        //ArrayList that contains all tasks
        List<Task> allTasks = new ArrayList<>();
        //Hashmap that contains all weeklytasks
        Map<DayOfWeek, List<TaskWeekly>> weeklyTasks = new HashMap<>();
        //A priorityQueue focused on whats due Today
        Queue<Task> todayQueue = new PriorityQueue<>();
        //Stack meant to mark completed tasks. Good for an undo button
        Stack<Task> completedTasks = new Stack<>();
        //Defining the Scanner and variables needed
         */
        // Your code goes here
        //System.out.println("Hello, World!");
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
            System.out.println("7. Show Next Task");
            System.out.println("8. Exit");

            System.out.print("Choose: ");

            int choice = scanner.nextInt();

            scanner.nextLine();

            switch (choice) {

                case 1 -> {

                    System.out.print("Title: ");
                    String title = scanner.nextLine();

                    System.out.print("Description: ");
                    String desc = scanner.nextLine();

                    System.out.print("Priority: ");
                    int priority = scanner.nextInt();

                    System.out.print("Due Hour (0-23): ");
                    int due = scanner.nextInt();

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
                    int priority = scanner.nextInt();

                    System.out.print("Due Hour (0-23): ");
                    int due = scanner.nextInt();

                    scanner.nextLine();

                    System.out.print("Day (MONDAY etc): ");

                    DayOfWeek day
                            = DayOfWeek.valueOf(scanner.nextLine().toUpperCase());

                    TaskWeekly weekly
                            = new TaskWeekly(title, desc,
                                    priority, due, day);

                    manager.addTask(weekly);
                }

                case 3 ->
                    manager.displayTasks();

                case 4 -> {

                    manager.displayTasks();

                    System.out.print("Task Number: ");

                    int index = scanner.nextInt();

                    manager.completeTask(index - 1);
                }

                case 5 ->
                    manager.undoComplete();

                case 6 ->
                    manager.sortTasks();

                case 7 ->
                    manager.showNextTask();

                case 8 ->
                    running = false;

                default ->
                    System.out.println("Invalid option.");
            }
        }

        scanner.close();
    }
}
