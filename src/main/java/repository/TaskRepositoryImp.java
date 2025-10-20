package repository;

import model.Task;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class TaskRepositoryImp implements TaskRepository{

private final Map<Long, Task> taskMap = new HashMap<>();
private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public List<Task> findAll() {
      return new ArrayList<>(taskMap.values());
    }

    @Override
    public Optional<Task> findById(Long id) {
        return Optional.ofNullable(taskMap.get(id));
    }

    @Override
    public Task save(Task task) {
        if (task.getId() == null){
            task.setId(idGenerator.getAndIncrement());
        }
        taskMap.put(task.getId(),task);
        return task;
    }

    @Override
    public void deletedById(Long id) {
        taskMap.remove(id);
    }

    @Override
    public boolean existById(Long id) {
        return taskMap.containsKey(id);
    }

    @Override
    public List<Task> findByStatus(Task.TaskStatus status) {
        return taskMap.values().stream().filter(task -> task.getTaskStatus()== status).toList();
    }
}
