/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package businesslayer;

import DAO.DAOImpl;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author ryan2
 */
public class BusinessLogic {
    
    private DAOImpl EntryDao = null;
    
    public BusinessLogic(){
        EntryDao = new DAOImpl();
    }
    public boolean Validate(String Username, String Password) throws SQLException{
        return EntryDao.SQL_Login_Validate(Username, Password);
    }
    public void Register(String Username, String Email, String Password, String Customer_Type) throws SQLException{
        EntryDao.SQL_Insert_New_User(Username, Email, Password, Customer_Type, 0, 0);
    }
    //Overload Constructor
    public void Register(String Username, String Email, String Password, String Customer_Type, double lat, double lon) throws SQLException{
        EntryDao.SQL_Insert_New_User(Username, Email, Password, Customer_Type, lat, lon);
    }
    public void AddEntry(String Username, String ItemName, int ItemCount, Date ExpDate, String UnitPrice) throws SQLException{
        EntryDao.SQL_Insert_Entry(Username, ItemName, ItemCount, ExpDate, UnitPrice);
    }
    public void UpdateEntry(int ID, String OfferType, String UnitPrice) throws SQLException{
        EntryDao.SQL_Update_Entry(ID, OfferType, UnitPrice);
    }
    public void DeleteEntry(int ID) throws SQLException{
        EntryDao.SQL_Delete_Entry(ID);
    }
    public void Claim(int ID, int ItemCount) throws SQLException{
        EntryDao.SQL_Claim(ID, ItemCount);
    }
}
