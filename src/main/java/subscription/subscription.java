package subscription;

/**
 *
 * @author Yuyang Du
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
