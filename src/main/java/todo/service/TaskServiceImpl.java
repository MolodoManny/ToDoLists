package todo.service;

import todo.model.Task;
import todo.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private  TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found: " + id));
    }

    @Override
    public Task createTask(Task task) {
        if (task.getStatus() == null) {
            task.setStatus(Task.Status.TODO);
        }
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Long id, Task task) {
        if (!taskRepository.existById(id)) {
            throw new RuntimeException("Task not found with id: " + id);
        }
        task.setId(id);
        return taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long id) {
        if (!taskRepository.existById(id)) {
            throw new RuntimeException("Task not found with id: " + id);
        }
        taskRepository.deletedById(id);
    }

    @Override
    public List<Task> filterTasksByStatus(Task.Status status) {
        return taskRepository.findByStatus(status);
    }

    @Override
    public List<Task> sortTasksByDueDate() {
        return taskRepository.findAll().stream()
                .sorted(Comparator.comparing(Task::getDueDate))
                .toList();
    }

    @Override
    public List<Task> sortTasksByStatus() {
        return taskRepository.findAll().stream()
                .sorted(Comparator.comparing(Task::getStatus))
                .toList();
    }
}