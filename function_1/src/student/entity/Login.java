package student.entity;

public class Login {
    private int userid;
    private String username;
    private String userpsw;

    public Login() {
    }

    public Login(String username, String userpsw) {
        this.username = username;
        this.userpsw = userpsw;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpsw() {
        return userpsw;
    }

    public void setUserpsw(String userpsw) {
        this.userpsw = userpsw;
    }
}
