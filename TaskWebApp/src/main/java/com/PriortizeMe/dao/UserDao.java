package com.PriortizeMe.dao;

import com.PriortizeMe.model.User;
import com.PriortizeMe.model.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.mindrot.jbcrypt.BCrypt;

public class UserDao {

    public boolean registerUser(User user) {
        String query = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12)); // Hash password
            
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, hashedPassword);
            
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println("Error in registerUser: " + e.getMessage());
        }
        return false;
    }

    public User loginUser(String email, String password) {
        String query = "SELECT * FROM users WHERE email = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String hashedPassword = rs.getString("password");
                    if (BCrypt.checkpw(password, hashedPassword)) { // Compare hashed password
                        User user = new User();
                        user.setId(rs.getInt("id"));
                        user.setName(rs.getString("name"));
                        user.setEmail(rs.getString("email"));
                        return user;
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error in loginUser: " + e.getMessage());
        }
        return null;
    }
}
