package todo.java;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static TodoList tempTodoList = new TodoList();
    static MyFileReaderWriter fileRW = new MyFileReaderWriter();

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

    static Importance inputImportance() {
        int importance;
        String errorMessage = "Error: Importance level must be between 1 and 10. Try again.";
        while (true) {
            System.out.print("Importance level (min 1, max 10): ");
            try {
                importance = Integer.parseInt(scanner.nextLine());
                if (importance >= 1 && importance <= 10) {
                    return Importance.intToImportance(importance);
                }
                System.err.println(errorMessage);
            } catch (NumberFormatException e) {
                System.err.println(errorMessage);
            }
        }
    }

    static void addTodoItem() {
        String title = inputTitle();
        Importance importance = inputImportance();
        tempTodoList.addTodoItem(title, importance);
        System.out.println("%s".formatted(tempTodoList));
    }

    static int inputIndex(String message) {
        int index;
        int size = tempTodoList.todoList.size();
        String errorMessage = "Error: The number must be between 0 and " + (size - 1) + ". Try again.";
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
        Importance importance = inputImportance();
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
                IsProceeding changedProceeding = IsProceeding.booleanToIsProceeding(proceeding.equals(proceedingStr));
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

    static Path normalizeFilePath(String filePathStr) {
        return Path.of(filePathStr).toAbsolutePath().normalize();
    }

    static void fileToTodoList(Path filePath) {
        List<String> lines;
        try {
            lines = fileRW.myFileReader(filePath).stream().skip(1).toList();
            System.out.println("Loading succeeded.");
        } catch (UncheckedIOException e) {
            throw e;
        }
        List<String> itemInformation;
        String whenRegistered;
        String title;
        Importance importance;
        IsProceeding isProceeding;
        String whenLastUpdated;
        tempTodoList.todoList.clear();
        for (String line : lines) {
            itemInformation = new ArrayList<>(Arrays.asList(line.split(",")));
            whenRegistered = itemInformation.get(0);
            title = itemInformation.get(1);
            importance = Importance.intToImportance(Integer.parseInt(itemInformation.get(2)));
            isProceeding = IsProceeding.booleanToIsProceeding(Boolean.parseBoolean(itemInformation.get(3)));
            whenLastUpdated = itemInformation.get(4);
            tempTodoList.addTodoItem(whenRegistered, title, importance, isProceeding, whenLastUpdated);
            itemInformation.clear();
        }
        System.out.println("%s".formatted(tempTodoList));
    }

    static void todoListToFile(Path filePath) {
        List<String> lines = new ArrayList<>();
        lines.add("whenRegistered,title,importance,is proceeding,last updated");
        tempTodoList.todoList.stream().forEachOrdered(i -> lines.add(i.toString()));
        try {
            fileRW.myFileWriter(filePath, lines);
            System.out.println("Writing succeeded.");
        } catch (UncheckedIOException e) {
            throw e;
        }
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
                    L: Load a list file
                    S. Save the list and quit
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
            } else if (command.equals("L")) {
                if (!isEmpty) {
                    String yesNo;
                    while (true) {
                        System.out.print(
                                "The todo list on your RAM would be deleted. Are you OK? (Yes/No): ");
                        yesNo = scanner.nextLine().toLowerCase();
                        if (yesNo.equals("yes")) {
                            break;
                        } else if (yesNo.equals("no")) {
                            System.out.println("No changing.");
                            continue label_top;
                        } else {
                            System.err
                                    .println("Error: The answer must be 'Yes' or 'No'. Try again.");
                        }
                    }
                }
                System.out.print("The file path you want: ");
                Path filePath = normalizeFilePath(scanner.nextLine());
                if (Files.notExists(filePath)) {
                    System.err.println("Error: The file does not exist.");
                    continue;
                }
                try {
                    fileToTodoList(filePath);
                    continue;
                } catch (UncheckedIOException e) {
                    System.err.println(e);
                    continue;
                }
            } else if (command.equals("S")) {
                System.out.print("The file path you want: ");
                Path filePath = normalizeFilePath(scanner.nextLine());
                try {
                    todoListToFile(filePath);
                    System.exit(0);
                } catch (UncheckedIOException e) {
                    System.err.println(e);
                }
            } else {
                System.err.println(
                        "Error: The command must be '1', '2', '3', '4', '5', '6', 'L', or 'S'. Try again.");
            }
        }
    }
}
