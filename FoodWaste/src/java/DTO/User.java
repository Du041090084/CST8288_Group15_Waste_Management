/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author ryan2
 */
public class User {
    private String Username;
    private String Email;
    private String Password;
    private String UserType;
    private String[] Preferences;
    private double Lat;
    private double Lon;
    
    public String getUsername(){
        return Username;
    }
    public void setUserName(String in){
        this.Username = in;
    }
    
    public String getEmail(){
        return Email;
    }
    public void setEmail(String in){
        this.Email = in;
    }
    
    public String getPassword(){
        return Password;
    }
    public void setPassword(String in){
        this.Password = in;
    }
    
    public String getUserType(){
        return UserType;
    }
    public void setUserType(String in){
        this.UserType = in;
    }
    
    public String[] getPreferences(){
        return Preferences;
    }
    public void setPreferences(String[] in){
        this.Preferences = in;
    }
    
    public double getLat(){
        return Lat;
    }
    public void setLat(double in){
        this.Lat = in;
    }
    
    public double getLon(){
        return Lon;
    }
    public void setLon(double in){
        this.Lon = in;
    }
}
