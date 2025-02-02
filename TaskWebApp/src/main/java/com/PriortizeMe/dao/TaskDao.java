package com.PriortizeMe.dao;

import com.PriortizeMe.model.Task;
import com.PriortizeMe.model.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TaskDao {
    public boolean addTask(Task task) {
        String query = "INSERT INTO task(userId, taskName, priority) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, task.getUserId());
            ps.setString(2, task.getTaskName());
            ps.setString(3, task.getPriority());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println("Error in addTask: " + e.getMessage());
        }
        return false;
    }

    public List<Task> getTasksByUserId(int userId) {
        List<Task> tasks = new ArrayList<>();
        String query = "SELECT * FROM task WHERE userId = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Task task = new Task();
                    task.setId(rs.getInt("id"));
                    task.setUserId(rs.getInt("userId"));
                    task.setTaskName(rs.getString("taskName"));
                    task.setPriority(rs.getString("priority"));
                    tasks.add(task);
                }
            }
        } catch (Exception e) {
            System.err.println("Error in getTasksByUserId: " + e.getMessage());
        }
        return tasks;
    }

    public boolean deleteTask(int taskId) {
        String query = "DELETE FROM task WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            
            ps.setInt(1, taskId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println("Error in deleteTask: " + e.getMessage());
        }
        return false;
    }
}
