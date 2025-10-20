package service;

import model.Task;
import repository.TaskRepository;

import java.util.Comparator;
import java.util.List;

public class TaskServiceImp implements TaskService {
private final TaskRepository taskRepository;

    public TaskServiceImp(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    @Override
    public List<Task> getAllTasks() {
        return  taskRepository.findAll();
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElseThrow(()-> new RuntimeException("Task not found"+ id));
    }

    @Override
    public Task createTask(Task task) {
        if (task.getTaskStatus()==null){
            task.setTaskStatus(Task.TaskStatus.TODO);
        }
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Long id, Task task) {
        if (!taskRepository.existById(id)){
            throw  new RuntimeException("Task not found with id" + id);
        }
        task.setId(id);
        return  taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long id) {
        if (!taskRepository.existById(id)){
            throw  new RuntimeException("Task not found with id" + id);
        }
        taskRepository.deletedById(id);
    }

    @Override
    public List<Task> filterTaskByStatus(Task.TaskStatus status) {
        return taskRepository.findByStatus(status);
    }

    @Override
    public List<Task> sortTaskByDate() {
        return taskRepository.findAll().stream().sorted(Comparator.comparing(Task::getLocalDate)).toList();
    }

    @Override
    public List<Task> sortTaskByStatus() {
        return taskRepository.findAll().stream().sorted(Comparator.comparing(Task::getTaskStatus)).toList();
    }
}
