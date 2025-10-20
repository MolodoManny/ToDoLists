package todo.model;

import java.time.LocalDate;

public class Task {
    private Long id;
    private String title;
    private String description;
    private LocalDate localDate; // Должно быть dueDate
    private Status status = Status.TODO; // Должно быть status

    public enum Status {
        TODO, IN_PROGRESS, DONE
    }

    public Task() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDate getDueDate() { return localDate; } // Должно быть getDueDate()
    public void setDueDate(LocalDate dueDate) { this.localDate = localDate; }

    public Status getStatus() { return status; } // Должно быть getStatus()
    public void setStatus(Status status) { this.status = status; }

    @Override
    public String toString() {
        return String.format("Task{id=%d, title='%s', dueDate=%s, status=%s}", id, title, localDate, status);
    }
}