/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package subscription;

import database.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Yuyang Du
 */
public class SubscriptionDAOImpl implements SubscriptionDAO{
    public SubscriptionDAOImpl(){};
    
    @Override
    public void create(subscription sub){
        String sql = "SELECT * FROM subscriptions WHERE userEmail = ? AND storeId = ?";
        try (Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, sub.getUserEmail());
            statement.setInt(2, sub.getStoreId());
            ResultSet resultSet = statement.executeQuery();
            int a = 0;
             while (resultSet.next()){
                 a++;
             }
             if(a==0){
                sql = "INSERT INTO subscriptions (userEmail, storeId) VALUES (?,?)";
                PreparedStatement newStatement = connection.prepareStatement(sql);
                newStatement.setString(1, sub.getUserEmail());
                newStatement.setInt(2, sub.getStoreId());
                newStatement.executeUpdate();
             }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    };
    @Override
    public List<subscription> getAllSubscribers(int storeId){
        List<subscription> subs = new ArrayList<>();
        String sql = "SELECT * FROM subscriptions WHERE storeId = ?";
    try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, storeId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    subscription sub = new subscription(resultSet.getString("userEmail"),resultSet.getInt("storeId"));
                    subs.add(sub);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    return subs;
    }
    
    @Override
    public void deleteSubscription(String email, int storeId){
          String sql = "DELETE * FROM subscriptions WHERE userEmail = ? AND storeId = ?";
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            statement.setInt(2, storeId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
    }
}
