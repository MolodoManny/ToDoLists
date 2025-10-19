package repository;

import model.Task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class TaskRepositoryImp implements TaskRepository{

private final Map<Long, Task> taskMap = new HashMap<>();
private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public List<Task> findAll() {
        return List.of();
    }

    @Override
    public Optional<Task> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Task save(Task task) {
        return null;
    }

    @Override
    public void deletedById(Long id) {

    }

    @Override
    public boolean existById(Long id) {
        return false;
    }

    @Override
    public List<Task> findByStatus(Task.TaskStatus status) {
        return List.of();
    }
}
