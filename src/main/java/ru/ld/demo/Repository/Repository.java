package ru.ld.demo.Repository;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.ui.Model;
import ru.ld.demo.Models.Task;

import java.util.List;

@org.springframework.stereotype.Repository
public class Repository {

    private final JdbcTemplate jdbc;

    public Repository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Task> findAll() {
        String sql = "SELECT * FROM tasks";

        RowMapper<Task> taskRowMapper = (r, i) -> {
            Task task = new Task();
            task.setId(r.getInt("id"));
            task.setName(r.getString("name"));
            task.setDescription(r.getString("description"));
            task.setStatus(r.getString("status"));
            return task;
        };

        return jdbc.query(sql, taskRowMapper);
    }

    public Task save(Task task) {
        String sql = "INSERT INTO tasks (name, description, status) VALUES (?, ?, ?)";
        jdbc.update(sql, task.getName(), task.getDescription(), "in process");
        return task;
    }

    public void complete(int id) {
        String sql = "UPDATE tasks SET status = 'Ready' WHERE id = ?";
        jdbc.update(sql, id);
    }
}