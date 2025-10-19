package exceptions;

public class TaskNotFoundExceptions extends RuntimeException {
    public TaskNotFoundExceptions(String message) {
        super(message);
    }

    public TaskNotFoundExceptions(long id) {
        super("Task with id:" + id + " not founded");
    }
}
