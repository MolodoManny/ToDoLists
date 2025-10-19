package model;

import java.time.LocalDate;

public class Task {
    private long id;
    private String title;
    private String descriptions;
    private LocalDate localDate;
    private TaskStatus taskStatus;

    public enum TaskStatus{
        TODO,IN_PROGRESS,DONE
    }
}
