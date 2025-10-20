package todo.service;

import todo.model.Task;
import java.util.List;

public interface TaskService {
    List<Task> getAllTasks();
    Task getTaskById(Long id);
    Task createTask(Task task);
    Task updateTask(Long id, Task task);
    void deleteTask(Long id);
    List<Task> filterTasksByStatus(Task.Status status); // Должно быть filterTasksByStatus
    List<Task> sortTasksByDueDate(); // Должно быть sortTasksByDueDate
    List<Task> sortTasksByStatus(); // Должно быть sortTasksByStatus
}