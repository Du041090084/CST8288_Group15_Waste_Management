/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package subscription;

/**
 *
 * @author ryan2
 */
public class subscription {
    private String userEmail;
    private int storeId;
    public subscription(String email, int Id){
        this.userEmail = email;
        this.storeId = Id;
    }
    public void setUserEmail(String email){
        this.userEmail = email;
    }
    public String getUserEmail(){
        return userEmail;
    }
    public void setStoreId(int Id){
        this.storeId = Id;
    }
    public int getStoreId(){
        return storeId;
    }
}
