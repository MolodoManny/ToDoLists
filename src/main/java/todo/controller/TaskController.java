package todo.controller;


import lombok.RequiredArgsConstructor;
import todo.model.Task;
import org.springframework.stereotype.Component;
import todo.service.TaskService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

@Component

public class TaskController {
    private final TaskService taskService;
    private final Scanner scanner;
    private final DateTimeFormatter dateTimeFormatter;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
        this.scanner = new Scanner(System.in);
        this.dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    }

    public void start() {
        System.out.println("Welcome to TODO list");
        while (true) {
            printMenuTask();
            String command = scanner.nextLine().toLowerCase();
            try {
                switch (command) {
                    case "add" -> addTask();
                    case "delete" -> deleteTask();
                    case "edit" -> editTask();
                    case "filter" -> filterTask();
                    case "sort" -> sortTask();
                    case "list" -> listAllTasks();
                    case "exit" -> {
                        System.out.println("GoodBye!");
                        return;
                    }
                    default -> System.out.println("Unknown command. Please try again");
                }
            } catch (Exception e) {
                System.out.println("Error" + e.getMessage());
            }
        }
    }

    private void printMenuTask() {
        System.out.println("--------Available commands--------");
        System.out.println("add    -Add new task");
        System.out.println("list   -Showing all available tasks");
        System.out.println("delete -Delete task");
        System.out.println("edit   -Edit task");
        System.out.println("filter -Filer task");
        System.out.println("sort   -Sorting task");
        System.out.println("exit   -Close TODO list");
        System.out.println("Enter the command");
    }

    private void addTask() {
        System.out.println("Enter title: ");
        String title = scanner.nextLine();
        System.out.println("Enter description: ");
        String description = scanner.nextLine();
        LocalDate localDate = readDate("Enter the date (yyyy-MM-dd");
        Task task = new Task();
        task.setTitle(title);
        task.setDescription(description);
        task.setDueDate(localDate);
    }

    private void deleteTask() {
        System.out.println("Enter task ID to delete: ");
        Long id = readLong();
        taskService.deleteTask(id);
        System.out.println("Task was deleted");
    }

    private void editTask() {
        System.out.println("Enter task id to edit:");
        Long id = readLong();
        Task editingTask = taskService.getTaskById(id);
        System.out.println("Current task: " + editingTask);
        System.out.println("Enter new title: ");
        String title = scanner.nextLine();
        if (title.isEmpty()) {
            title = editingTask.getTitle();
        }
        System.out.println("Enter new description");
        String description = scanner.nextLine();
        if (description.isEmpty()) {
            description = editingTask.getDescription();
        }
        System.out.println("Enter new date in format (yyyy-MM-dd)");
        String dateInput = scanner.nextLine();
        LocalDate localDate = dateInput.isEmpty() ? editingTask.getDueDate() : parseDate(dateInput);
        System.out.println("Enter new status (TODO,IN_PROGRESS,DONE) press enter to confirm");
        String statusInput = scanner.nextLine();
        Task.Status status = statusInput.isEmpty() ? editingTask.getStatus() : parseStatus(statusInput);
        Task updatedTask = new Task();
        updatedTask.setTitle(title);
        updatedTask.setDescription(description);
        updatedTask.setDueDate(localDate);
        updatedTask.setStatus(status);
        taskService.updateTask(id, updatedTask);
        System.out.println("Task was update");

    }

    private void listAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        if (tasks.isEmpty()) {
            System.out.println("Tasks was not found");
        } else {
            printTask(tasks);
        }
    }

    private void filterTask() {
        System.out.println("Enter status to filter tasks (TODO,IN_PROGRESS,DONE)");
        Task.Status status = parseStatus(scanner.nextLine());

        List<Task> tasks = taskService.filterTasksByStatus(status);
        if (tasks.isEmpty()) {
            System.out.println("Tasks not found to filter" + status);
        } else {
            printTask(tasks);
        }
    }

    private void sortTask() {
        System.out.println("Sort by: ");
        System.out.println("1 - Date");
        System.out.println("2 - Status ");
        String options = scanner.nextLine();

        List<Task> tasks;

        switch (options) {
            case "1" -> tasks = taskService.sortTasksByDueDate();
            case "2" -> tasks = taskService.sortTasksByStatus();
            default -> {
                System.out.println("Invalid options");
                return;
            }
        }
        printTask(tasks);
    }

    private void printTask(List<Task> tasks) {
        System.out.println("----Tasks----");
        for (Task task : tasks) {
            System.out.printf("ID: | Date: | Status: ",
                    task.getId(),
                    task.getTitle(),
                    task.getDescription(),
                    task.getDueDate(),
                    task.getStatus());
        }
        System.out.println();
    }

    private Task.Status parseStatus(String statusString) {
        try {
            return Task.Status.valueOf(statusString.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid status, please enter the correct (TODO,IN_PROGRESS,DONE");
        }
    }


    private Long readLong() {
        while (true) {
            try {
                return Long.parseLong(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid number id");
            }
        }
    }

    private LocalDate readDate(String prompt) {
        while (true) {
            System.out.println(prompt);
            try {
                return LocalDate.parse(scanner.nextLine());
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format (yyyy-MM-dd");
            }
        }
    }

    private LocalDate parseDate(String dateString) {
        return LocalDate.parse(dateString, dateTimeFormatter);
    }
}