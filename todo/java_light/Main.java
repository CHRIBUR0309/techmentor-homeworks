package todo.java;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static TodoList tempTodoList = new TodoList();

    static String inputTitle() {
        String title;
        while (true) {
            System.out.print("Todo item title: ");
            title = scanner.nextLine().replace(",", " ");
            if (!title.isBlank()) {
                return title;
            }
            System.err.println("Error: Title must have at least 1 letter. Try again.");
        }
    }

    static int inputImportance() {
        int importance;
        String errorMessage = "Error: Importance level must be between 1 and 10. Try again.";
        while (true) {
            System.out.print("Importance level (min 1, max 10): ");
            try {
                importance = Integer.parseInt(scanner.nextLine());
                if (importance >= 1 && importance <= 10) {
                    return importance;
                }
                System.err.println(errorMessage);
            } catch (NumberFormatException e) {
                System.err.println(errorMessage);
            }
        }
    }

    static void addTodoItem() {
        String title = inputTitle();
        int importance = inputImportance();
        tempTodoList.addTodoItem(title, importance);
        System.out.println("%s".formatted(tempTodoList));
    }

    static int inputIndex(String message) {
        int index;
        int size = tempTodoList.todoList.size();
        String errorMessage =
                "Error: The number must be between 0 and " + (size - 1) + ". Try again.";
        while (true) {
            System.out.print(message);
            try {
                index = Integer.parseInt(scanner.nextLine());
                if (index >= 0 && index < size) {
                    return index;
                }
                System.err.println(errorMessage);
            } catch (NumberFormatException e) {
                System.err.println(errorMessage);
            }
        }
    }

    static void removeTodoItem() {
        System.out.println("%s".formatted(tempTodoList));
        String message = "The item number you want to remove: ";
        int removedIndex = inputIndex(message);
        tempTodoList.removeTodoItem(removedIndex);
        System.out.println("%s".formatted(tempTodoList));
    }

    static void changeItemTitle() {
        System.out.println("%s".formatted(tempTodoList));
        String message = "The item number you want to change title: ";
        int changedIndex = inputIndex(message);
        String title = inputTitle();
        tempTodoList.changeItemTitle(changedIndex, title);
        System.out.println("%s".formatted(tempTodoList));
    }

    static void changeItemImportance() {
        System.out.println("%s".formatted(tempTodoList));
        String message = "The item number you want to change importance level: ";
        int changedIndex = inputIndex(message);
        int importance = inputImportance();
        tempTodoList.changeItemImportance(changedIndex, importance);
        System.out.println("%s".formatted(tempTodoList));
    }

    static void changeItemProceeding() {
        System.out.println("%s".formatted(tempTodoList));
        String message = "The item number you want to change proceeding (Unprocessed/Proceeding): ";
        int changedIndex = inputIndex(message);
        String proceeding;
        List<String> proceeds = Arrays.asList("unprocessed", "proceeding");
        String proceedingStr = proceeds.get(1);
        while (true) {
            proceeding = scanner.nextLine().toLowerCase();
            if (proceeds.contains(proceeding)) {
                boolean changedProceeding = proceeding.equals(proceedingStr);
                tempTodoList.changeItemProceeding(changedIndex, changedProceeding);
                break;
            }
            System.err.println(
                    "Error: The proceeding must be 'Unprocessed' or 'Proceeding'. Try again.");
        }
        System.out.println("%s".formatted(tempTodoList));
    }

    static void finishItemProcessing() {
        System.out.println("%s".formatted(tempTodoList));
        String message = "The item number you want to get finished: ";
        int changedIndex = inputIndex(message);
        tempTodoList.finishItemProcessing(changedIndex);
        System.out.println("%s".formatted(tempTodoList));
    }

    public static void main(String[] args) {
        label_top: while (true) {
            System.out.print("""
                    Input a command.
                    1. Add a todo item
                    2. Remove the todo item
                    3. Change the item title
                    4. Change the item importance level
                    5. Change the item proceeding
                    6. Get finished the item proceeding
                    """);
            String command = scanner.nextLine().toUpperCase();
            boolean isEmpty = tempTodoList.todoList.isEmpty();
            if (command.equals("1")) {
                addTodoItem();
            } else if (command.equals("2")) {
                if (isEmpty) {
                    System.err.println("Error: The list is empty.");
                    continue;
                }
                removeTodoItem();
            } else if (command.equals("3")) {
                if (isEmpty) {
                    System.err.println("Error: The list is empty.");
                    continue;
                }
                changeItemTitle();
            } else if (command.equals("4")) {
                if (isEmpty) {
                    System.err.println("Error: The list is empty.");
                    continue;
                }
                changeItemImportance();
            } else if (command.equals("5")) {
                if (isEmpty) {
                    System.err.println("Error: The list is empty.");
                    continue;
                }
                changeItemProceeding();
            } else if (command.equals("6")) {
                if (isEmpty) {
                    System.err.println("Error: The list is empty.");
                    continue;
                }
                finishItemProcessing();
            } else {
                System.err.println(
                        "Error: The command must be '1', '2', '3', '4', '5', or '6'. Try again.");
            }
        }
    }
}
