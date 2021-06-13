package lk.indikapharmacy.entity;

public class Users {

    private String userID;
    private String name;
    private String userName;
    private String password;

    public Users() {
    }

    public Users(String userID, String name, String userName, String password) {
        this.userID = userID;
        this.name = name;
        this.userName = userName;
        this.password = password;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
