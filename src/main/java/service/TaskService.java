package service;

import model.Task;

import java.util.List;

public interface TaskService {
    List<Task> getAllTasks();
    Task getTaskById(Long id);
    Task createTask(Task task);
    Task updateTask(Long id, Task task);
    void deleteTask(Long id);   // попробовать через Task
    List<Task> filterTaskByStatus(Task.TaskStatus status);
    List<Task> sortTaskByDate();
    List<Task> sortTaskByStatus();
}
