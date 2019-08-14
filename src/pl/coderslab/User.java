package pl.coderslab;

import org.mindrot.jbcrypt.BCrypt;

public class User {

    private int id;
    private String username;
    private String email;
    private String password;
    private UserGroup group;

    public User() {
    }

    public User(String username, String email, String password, UserGroup userGroup) {
        this.username = username;
        this.email = email;
        this.hashPassword(password);
        this.group = userGroup;
    }

    public void hashPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        hashPassword(password);
    }

    public UserGroup getGroup() {
        return group;
    }

    public void setGroup(UserGroup group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", group=" + group +
                '}';
    }

}
