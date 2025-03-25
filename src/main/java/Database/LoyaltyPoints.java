package Database;

import java.sql.*;

public class LoyaltyPoints {

    static Singleton singleton = Singleton.getInstance();
    static Connection connection = singleton.getConnection();


    public int getLoyaltyPoints(String username){
        int loyaltyPoint = 0;

        String sql = "SELECT loyalty_points from users where username = ?";
        try (PreparedStatement query = connection.prepareStatement(sql)){
            query.setString(1, username);
            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                loyaltyPoint = resultSet.getInt("loyalty_points");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return loyaltyPoint;
    }

    public void updateLoyaltyPoint(String username , int points){
        String sql = "UPDATE Users SET loyalty_points  = loyalty_points + ? WHERE username = ?";
        try(PreparedStatement query = connection.prepareStatement(sql)){
            query.setInt(1, points);
            query.setString(2, username);

            query.executeUpdate();
            System.out.println("Loyalty points updated for user: "+ username);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateLoyaltyPointMinus(String username , int points){
        String sql = "UPDATE Users SET loyalty_points  = loyalty_points - ? WHERE username = ?";
        try(PreparedStatement query = connection.prepareStatement(sql)){
            query.setInt(1, points);
            query.setString(2, username);

            query.executeUpdate();
            System.out.println("Loyalty points updated for user: "+ username);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
