package myTaskManager.dao;

import myTaskManager.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskManagerDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TaskManagerDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Task> getAllTasks() {
        return jdbcTemplate.query("SELECT * FROM Tasks", new BeanPropertyRowMapper<>(Task.class));
    }

    public Task getTaskById(int id) {
        return jdbcTemplate.query("SELECT * FROM Tasks WHERE id=?",
                        new Object[]{id},
                        new BeanPropertyRowMapper<>(Task.class))
                .stream().findAny().orElse(null);
    }

    public void saveTask(Task task) {
        jdbcTemplate.update("INSERT INTO Tasks(title, description, priority, status) VALUES (?, ?, ?, ?)",
                task.getTitle(),
                task.getDescription(),
                task.getPriority(),
                task.isDone());
    }

    public void updateTask(int id, Task updatedTask) {
        jdbcTemplate.update("UPDATE Tasks SET title=?, description=?, priority=?, status=? WHERE id=?",
                updatedTask.getTitle(),
                updatedTask.getDescription(),
                updatedTask.getPriority(),
                updatedTask.isDone(),
                id);
    }

    public void deleteTask(int id) {
        jdbcTemplate.update("DELETE FROM Tasks WHERE id=?", id);
    }

    public List<Task> sortByPriority() {
        return jdbcTemplate.query("SELECT * FROM Tasks ORDER BY priority",
                new BeanPropertyRowMapper<>(Task.class));
    }


    public void deleteAllTasks() {
        jdbcTemplate.update("DELETE FROM Tasks");
    }

    public List<Task> getCompletedTasks() {
        return jdbcTemplate.query("SELECT * FROM Tasks WHERE status=true",
                new BeanPropertyRowMapper<>(Task.class));
    }

    public List<Task>getUncompletedTasks() {
        return jdbcTemplate.query("SELECT * FROM Tasks WHERE status=false",
                new BeanPropertyRowMapper<>(Task.class));
    }
}