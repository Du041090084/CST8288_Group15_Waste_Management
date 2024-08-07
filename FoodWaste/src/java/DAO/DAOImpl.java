/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author ryan2
 */
public class DAOImpl {
    
    public DAOImpl(){
        
    }
    
    private String query = null;
    
    public boolean SQL_Login_Validate(String Username, String Password) throws SQLException{
        query = String.format("SELECT * FROM User WHERE (Username = '%1$s' OR Email = '%1$s') AND Password = '%2$s'", Username, Password);
        int rowCount = 0;
        try{
            DBConnection.makeStatement(query);
            DBConnection.execute();
            ResultSet resultSet = DBConnection.getResultSet();
            while (resultSet.next()) {
                rowCount++;
            }
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        if(rowCount != 0){
            return true;
        }
        else{
            return false;
        }
    }
    public void SQL_Insert_New_User (String Username, String Email, String Password, String Customer_Type, double Lat, double Lon) throws SQLException {
        query = String.format("INSERT INTO User (Username, Email, Password, Customer_Type, Lat, Lon) VALUES (%s, %s, %s, %s, %n, %n)",Username, Email, Password, Customer_Type, Lat, Lon);
        try{
            DBConnection.makeStatement(query);
            PreparedStatement statement = DBConnection.getStatement();
            statement.executeUpdate();
            query = null;
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
    public void SQL_Insert_Entry(String Username, String ItemName, int ItemCount, Date ExpDate, String UnitPrice) throws SQLException{
        String OfferType;
        DateFormat DFormat = DateFormat.getDateInstance();
        String Expiry = DFormat.format(ExpDate);
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 7);
        if (ExpDate.before(c.getTime())){
            OfferType = "Surplus";
        }
        else{
            OfferType = "Standard";
        }
        query = String.format("INSERT INTO Entry (Poster, ItemName, ItemCount, ExpDate, OfferType, UnitPrice) VALUES (%s, %s, %n, %s, %s, %s)", Username, ItemName, ItemCount, Expiry, OfferType, UnitPrice);
        DBConnection.makeStatement(query);
        PreparedStatement statement = DBConnection.getStatement();
        statement.executeUpdate();
        query = null;
    }
    public void SQL_Update_Entry(int ID, String OfferType, String UnitPrice) throws SQLException{
        query = String.format("UPDATE Entry SET OfferType = %s, UnitPrice = %s WHERE id = %n", OfferType, UnitPrice, ID);
        DBConnection.makeStatement(query);
        PreparedStatement statement = DBConnection.getStatement();
        statement.executeUpdate();
        query = null;
    }
    public void SQL_Delete_Entry(int ID) throws SQLException{
        query = String.format("DELETE FROM Entry WHERE id = %n", ID);
        DBConnection.makeStatement(query);
        PreparedStatement statement = DBConnection.getStatement();
        statement.executeUpdate();
        query = null;
    }
    public void SQL_Claim(int ID, int ItemCount) throws SQLException{
        ItemCount--;
        query = String.format("UPDATE Entry SET ItemCount = %n WHERE id = %n", ItemCount, ID);
        DBConnection.makeStatement(query);
        PreparedStatement statement = DBConnection.getStatement();
        statement.executeUpdate();
        query = null;
    }
}
