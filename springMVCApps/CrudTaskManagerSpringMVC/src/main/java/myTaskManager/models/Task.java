package myTaskManager.models;

import javax.validation.constraints.*;

public class Task {
    private int id;
    @NotEmpty(message = "Title should not be empty")
    @Size(min = 2, max = 100, message = "Title should be between 2 and 100 characters")

    private String title;
    private String description;
    @NotNull
    @Min(1)
    @Max(3)
    private int priority;
    private boolean done;

    public Task() {
    }

    public Task(int id, String title, String description, int priority, boolean done) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.done = done;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
