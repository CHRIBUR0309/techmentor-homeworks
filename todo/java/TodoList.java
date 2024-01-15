package todo.java;

import java.time.*;
import java.time.format.*;
import java.util.*;
import java.util.stream.*;

class SharedConstantValues {
    private static final boolean IS_PROCEEDING_BOOLEAN = true;
    private static final int FINISHED_IMPORTANCE = -1;

    boolean getIsProceedingBoolean() {
        return IS_PROCEEDING_BOOLEAN;
    }

    int getFinishedImportance() {
        return FINISHED_IMPORTANCE;
    }
}


/**
 * Todo class has the information of each todo item.
 */
class Todo implements Formattable {
    private String whenRegistered;
    private String title;
    private int importance;
    private boolean isProceeding;
    private String whenLastUpdated;
    private static final RandomTimeSleeper RANDOM_TIME_SLEEPER = new RandomTimeSleeper();
    private static final int SLEEP_TIME_MIN = 10;
    private static final int SLEEP_TIME_MAX = 1000;
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ISO_OFFSET_DATE_TIME;
    private static final SharedConstantValues SHARED_CONSTANT_VALUES = new SharedConstantValues();
    private static final boolean IS_PROCEEDING_BOOLEAN =
            SHARED_CONSTANT_VALUES.getIsProceedingBoolean();
    private static final int FINISHED_IMPORTANCE = SHARED_CONSTANT_VALUES.getFinishedImportance();

    /**
     * Gets the title and the importance of the item and constructs the class.
     *
     * @param title the title of the item
     * @param importance the importance of the item
     */
    Todo(String title, int importance) {
        this.whenRegistered = sleepThenNow();
        this.title = title;
        this.importance = importance;
        this.isProceeding = !IS_PROCEEDING_BOOLEAN;
        this.whenLastUpdated = this.whenRegistered;
    }

    /**
     * Gets the title, the importance, and the datetime when updated last of the item and constructs
     * the class.
     *
     * @param title the title of the item
     * @param importance the importance of the item
     * @param whenLastUpdated the datetime when updated last of the item
     */
    Todo(String title, int importance, String whenLastUpdated) {
        this.whenRegistered = sleepThenNow();
        this.title = title;
        this.importance = importance;
        this.isProceeding = !IS_PROCEEDING_BOOLEAN;
        this.whenLastUpdated = whenLastUpdated;
    }

    /**
     * Gets the title, the importance, and is proceeding or not of the item and constructs the
     * class.
     *
     * @param title the title of the item
     * @param importance the importance of the item
     * @param isProceeding is proceeding or not of the item
     */
    Todo(String title, int importance, boolean isProceeding) {
        this.whenRegistered = sleepThenNow();
        this.title = title;
        this.importance = importance;
        this.isProceeding = isProceeding;
        this.whenLastUpdated = this.whenRegistered;
    }

    /**
     * Gets the title, the importance, is proceeding or not, and the datetime when updated last of
     * the item and constructs the class.
     *
     * @param title the title of the item
     * @param importance the importance of the item
     * @param isProceeding is proceeding or not of the item
     * @param whenLastUpdated the datetime when updated last of the item
     */
    Todo(String title, int importance, boolean isProceeding, String whenLastUpdated) {
        this.whenRegistered = sleepThenNow();
        this.title = title;
        this.importance = importance;
        this.isProceeding = isProceeding;
        this.whenLastUpdated = whenLastUpdated;
    }

    /**
     * Gets the datetime when registered, the title, and the importance of the item and constructs
     * the class.
     *
     * @param whenRegistered the datetime when registered of the item
     * @param title the title of the item
     * @param importance the importance of the item
     */
    Todo(String whenRegistered, String title, int importance) {
        this.whenRegistered = whenRegistered;
        this.title = title;
        this.importance = importance;
        this.isProceeding = !IS_PROCEEDING_BOOLEAN;
        this.whenLastUpdated = this.whenRegistered;
    }

    /**
     * Gets the datetime when registered, the title, the importance, and the datetime when updated
     * last of the item and constructs the class.
     *
     * @param whenRegistered the datetime when registered of the item
     * @param title the title of the item
     * @param importance the importance of the item
     * @param whenLastUpdated the datetime when updated last of the item
     */
    Todo(String whenRegistered, String title, int importance, String whenLastUpdated) {
        this.whenRegistered = whenRegistered;
        this.title = title;
        this.importance = importance;
        this.isProceeding = !IS_PROCEEDING_BOOLEAN;
        this.whenLastUpdated = whenLastUpdated;
    }

    /**
     * Gets the datetime when registered, the title, the importance, and is proceeding or not of the
     * item and constructs the class.
     *
     * @param whenRegistered the datetime when registered of the item
     * @param title the title of the item
     * @param importance the importance of the item
     * @param isProceeding is proceeding or not of the item
     */
    Todo(String whenRegistered, String title, int importance, boolean isProceeding) {
        this.whenRegistered = whenRegistered;
        this.title = title;
        this.importance = importance;
        this.isProceeding = isProceeding;
        this.whenLastUpdated = this.whenRegistered;
    }

    /**
     * Gets the datetime when registered, the title, the importance, is proceeding or not, and the
     * datetime when updated last of the item and constructs the class.
     *
     * @param whenRegistered the datetime when registered of the item
     * @param title the title of the item
     * @param importance the importance of the item
     * @param isProceeding is proceeding or not of the item
     * @param whenLastUpdated the datetime when updated last of the item
     */
    Todo(String whenRegistered, String title, int importance, boolean isProceeding,
            String whenLastUpdated) {
        this.whenRegistered = whenRegistered;
        this.title = title;
        this.importance = importance;
        this.isProceeding = isProceeding;
        this.whenLastUpdated = whenLastUpdated;
    }

    private void sleep() {
        try {
            RANDOM_TIME_SLEEPER.sleep(SLEEP_TIME_MIN, SLEEP_TIME_MAX);
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }

    private String now() {
        return ZonedDateTime.now().format(DATE_TIME_FORMATTER);
    }

    /**
     * Calls {@code sleep()} if it is able, and then, calls {@code now()}.
     * {@code this.whenRegistered} should be the primary key so random time sleeping helps that.
     *
     * @return {@code now()} the datetime when called
     */
    private String sleepThenNow() {
        try {
            sleep();
        } catch (IllegalArgumentException e) {
        }
        return now();
    }

    String getWhenRegistered() {
        return this.whenRegistered;
    }

    void setWhenRegistered(String whenRegistered) {
        this.whenRegistered = whenRegistered;
    }

    String getTitle() {
        return this.title;
    }

    void setTitle(String title) {
        this.title = title;
    }

    int getImportance() {
        return this.importance;
    }

    void setImportance(int importance) {
        this.importance = importance;
    }

    boolean getIsProceeding() {
        return this.isProceeding;
    }

    void setIsProceeding(boolean isProceeding) {
        this.isProceeding = isProceeding;
    }

    void setWhenLastUpdated(String whenLastUpdated) {
        this.whenLastUpdated = whenLastUpdated;
    }

    void updateWhenLastUpdated() {
        this.whenLastUpdated = sleepThenNow();
    }

    @Override
    public String toString() {
        List<String> data =
                Arrays.asList(this.whenRegistered, this.title, String.valueOf(this.importance),
                        String.valueOf(this.isProceeding), this.whenLastUpdated);
        return String.join(",", data);
    }

    @Override
    public void formatTo(Formatter formatter, int flags, int width, int precision) {
        if (this.importance == FINISHED_IMPORTANCE) {
            formatter.format(
                    "%1$s%n    Proceeding level: Finished%n    Registered: %2$s%n    Last updated: %3$s",
                    this.title, this.whenRegistered, this.whenLastUpdated);
        } else if (this.isProceeding == IS_PROCEEDING_BOOLEAN) {
            formatter.format(
                    "%1$s%n    Importance level: %2$2d%n    Proceeding level: Proceeding%n    Registered: %3$s%n    Last updated: %4$s",
                    this.title, this.importance, this.whenRegistered, this.whenLastUpdated);
        } else {
            formatter.format(
                    "%1$s%n    Importance level: %2$2d%n    Proceeding level: Unprocessed%n    Registered: %3$s%n    Last updated: %4$s",
                    this.title, this.importance, this.whenRegistered, this.whenLastUpdated);
        }
    }
}


/**
 * TodoList class is a list of Todo items. It looks like {@code List<Todo>}, but this can sort items
 * automatically.
 */
class TodoList implements Formattable {
    List<Todo> todoList;
    private static final SharedConstantValues SHARED_CONSTANT_VALUES = new SharedConstantValues();
    private static final boolean IS_PROCEEDING_BOOLEAN =
            SHARED_CONSTANT_VALUES.getIsProceedingBoolean();
    private static final int FINISHED_IMPORTANCE = SHARED_CONSTANT_VALUES.getFinishedImportance();

    /**
     * Constructs the class.
     */
    TodoList() {
        this.todoList = new ArrayList<>();
    }

    private void sort() {
        this.todoList = this.todoList.stream()
                .sorted(Comparator.comparing(Todo::getImportance)
                        .thenComparing(Todo::getWhenRegistered).reversed())
                .collect(Collectors.toList());
    }

    /**
     * Gets the title and the importance of the item and adds it to this.
     *
     * @param title the title of the item
     * @param importance the importance of the item
     */
    void addTodoItem(String title, int importance) {
        this.todoList.add(new Todo(title, importance));
        this.sort();
    }

    /**
     * Gets the datetime when registered, the title, the importance, is proceeding or not, and the
     * datetime when updated last of the item and adds it to this.
     *
     * @param whenRegistered the datetime when registered of the item
     * @param title the title of the item
     * @param importance the importance of the item
     * @param isProceeding is proceeding or not of the item
     * @param whenLastUpdated the datetime when updated last of the item
     */
    void addTodoItem(String whenRegistered, String title, int importance, boolean isProceeding,
            String whenLastUpdated) {
        this.todoList
                .add(new Todo(whenRegistered, title, importance, isProceeding, whenLastUpdated));
        this.sort();
    }

    /**
     * Gets the index of the item and removes it from this.
     *
     * @param removedIndex the index of the item
     */
    void removeTodoItem(int removedIndex) {
        this.todoList.remove(removedIndex);
    }

    /**
     * Gets the index of the item and the changed title and changes the title of it.
     *
     * @param changedIndexIndex the index of the item
     * @param changedTitle the changed title of the item
     */
    void changeItemTitle(int changedIndex, String changedTitle) {
        Todo item = this.todoList.get(changedIndex);
        item.setTitle(changedTitle);
        item.updateWhenLastUpdated();
        this.todoList.set(changedIndex, item);
    }

    /**
     * Gets the index of the item and the changed importance and changes the importance of it.
     *
     * @param changedIndexIndex the index of the item
     * @param changedImportance the changed importance of the item
     */
    void changeItemImportance(int changedIndex, int changedImportance) {
        Todo item = this.todoList.get(changedIndex);
        item.setImportance(changedImportance);
        item.updateWhenLastUpdated();
        this.todoList.set(changedIndex, item);
        this.sort();
    }

    /**
     * Gets the index of the item and the changed is proceeding or not of the item and changes the
     * proceeding level of it.
     *
     * @param changedIndexIndex the index of the item
     * @param changedIsProceeding is proceeding or not of the item
     */
    void changeItemProceeding(int changedIndex, boolean changedProcessing) {
        Todo item = this.todoList.get(changedIndex);
        item.setIsProceeding(changedProcessing);
        item.updateWhenLastUpdated();
        this.todoList.set(changedIndex, item);
    }

    /**
     * Gets the index of the item and finishes it.
     *
     * @param changedIndexIndex the index of the item
     */
    void finishItemProcessing(int changedIndex) {
        Todo item = this.todoList.get(changedIndex);
        item.setIsProceeding(IS_PROCEEDING_BOOLEAN);
        item.setImportance(FINISHED_IMPORTANCE);
        item.updateWhenLastUpdated();
        this.todoList.set(changedIndex, item);
        this.sort();
    }

    @Override
    public String toString() {
        return String.join("\n", this.todoList.stream().map(Todo::toString).toList());
    }

    @Override
    public void formatTo(Formatter formatter, int flags, int width, int precision) {
        formatter.format("%s",
                String.join("\n", IntStream.range(0, this.todoList.size()).boxed().map(
                        i -> "\n<Todo item " + i + ">\n" + "%s".formatted(this.todoList.get(i)))
                        .toList()));
    }
}
