package Database;

import java.sql.*;

import Database.Singleton;

public class DatabaseController {

    static Singleton singleton = Singleton.getInstance();
    static Connection connection = singleton.getConnection();


    public void insertUser(String name, String username, String password){
        String sql = "INSERT INTO Users (Name, username, password) VALUES (?, ?, ?)";
        try (Connection conn = Singleton.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, username);
            pstmt.setString(3, password);
            pstmt.executeUpdate();
            System.out.println("User inserted successfully.");
        } catch (SQLException e) {
            System.out.println("Error inserting user: " + e.getMessage());
        }
    }
}
