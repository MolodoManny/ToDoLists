package service;

import model.Task;

import java.util.List;

public class TaskServiceImp implements TaskService {
    @Override
    public List<Task> getAllTasks() {
        return List.of();
    }

    @Override
    public Task getTaskById(Long id) {
        return null;
    }

    @Override
    public Task createTask(Task task) {
        return null;
    }

    @Override
    public Task updateTask(Long id, Task task) {
        return null;
    }

    @Override
    public void deleteTask(Long id) {

    }

    @Override
    public List<Task> filterTaskByStatus(Task.TaskStatus status) {
        return List.of();
    }

    @Override
    public List<Task> sortTaskByDate() {
        return List.of();
    }

    @Override
    public List<Task> sortTaskByStatus() {
        return List.of();
    }
}
