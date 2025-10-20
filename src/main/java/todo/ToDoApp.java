package todo;

import todo.controller.TaskController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"todo", "controller", "service", "repository", "model"})
public class ToDoApp  implements CommandLineRunner {
    private final TaskController taskController;

    public ToDoApp(TaskController taskController) {
        this.taskController = taskController;
    }

    public static void main(String[] args) {
        SpringApplication.run(ToDoApp.class,args);
    }


    @Override
    public void run(String... args) {
        taskController.start();
    }
}
