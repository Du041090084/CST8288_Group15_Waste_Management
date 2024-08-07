/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
/**
 *
 * @author ryan2
 */
public class DBConnection {
    
    private static Connection connection = null;
    private static PreparedStatement statement;
    private static ResultSet resultSet;
    private static ResultSetMetaData metaData;
    
    private DBConnection(){}
    
    public Connection createConnection(String url, String username, String password) throws SQLException {
        try {
            if (connection != null) {
                System.out.println("Cannot create new connection, one exists already");
            } else {
                connection = DriverManager.getConnection(url, username, password);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
        return connection;
    }
    
    public static void makeStatement(String query) throws SQLException {
    	try {
    	statement = connection.prepareStatement(query);
    	}
    	catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
    /**
     * @throws SQLException 
     */
    public static void execute() throws SQLException {
    	try {
    	resultSet = statement.executeQuery();
    	}
    	catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
    /** 
     * @return 
     */
    public static PreparedStatement getStatement() {
        return DBConnection.statement;
    }
    /**
     * 
     * @return 
     */
    public static ResultSet getResultSet() {
    	return DBConnection.resultSet;
    }

    /**
     *
     * @return
     * @throws SQLException
     */
    public static ResultSetMetaData process() throws SQLException {
    	try {
    	metaData = resultSet.getMetaData();
    	}
    	catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    	return metaData;
    }

    /**
     *
     * @throws SQLException
     */
    public static void Cleanup() throws SQLException{
     try{
         
         connection.close();
     }
     catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
