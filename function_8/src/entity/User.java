package entity;

public class User {
    //账号
    private String userNumber;
    //密码
    private String userPwd;

    public User() {
    }

    public User(String userNumber, String userPwd) {
        this.userNumber = userNumber;
        this.userPwd = userPwd;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }
}
