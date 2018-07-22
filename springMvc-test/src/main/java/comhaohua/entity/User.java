package comhaohua.entity;    /*
 * @author  Administrator
 * @date 2018/7/20
 */

public class User {
    private String username;
    private  String password;
    private String address;
    private String tel;

    public User() {
    }

    public User(String username, String password, String address, String tel) {
        this.username = username;
        this.password = password;
        this.address = address;
        this.tel = tel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", tel='" + tel + '\'' +
                '}';
    }
}
