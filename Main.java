/*
* @Param
* @return
* @Throws
* @Author Erik Cabrera
*/
import java.time.DayOfWeek;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        TaskManager manager = new TaskManager();

        boolean running = true;

        while (running) {

            System.out.println("\n===== TASK MANAGER =====");

            System.out.println("1. Files");
            System.out.println("2. Add Task");
            System.out.println("3. View Tasks");
            System.out.println("4. Complete Task");
            System.out.println("5. Undo Complete");
            System.out.println("6. Sort Tasks");
            System.out.println("7. Delete Task");
            System.out.println("8. Exit");

            System.out.print("Choose: ");

            int choice = getIntInput(scanner);

            switch (choice) {

                case 1 -> {
                    manager.fileMenu(scanner);
                }

                case 2 -> {
                    manager.createTask(scanner);
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

    public static DayOfWeek getDayInput(Scanner scanner) {

        while (true) {

            try {

                String input =
                        scanner.nextLine()
                                .trim()
                                .toUpperCase();

                return DayOfWeek.valueOf(input);

            } catch (IllegalArgumentException e) {

                System.out.print(
                        "Invalid day. Try MONDAY, TUESDAY, etc: "
                );
            }
        }
    }


}

