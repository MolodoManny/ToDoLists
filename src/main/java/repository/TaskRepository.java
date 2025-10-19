package repository;

import model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    List<Task> findAll();
    Optional<Task> findById(Long id);
    Task save(Task task);
    void deletedById(Long id);
    boolean existById(Long id);
    List<Task> findByStatus(Task.TaskStatus status);
}
