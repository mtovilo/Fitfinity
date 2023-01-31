package ba.sum.fpmoz.fitfinity.model;

import java.util.Date;

public class UserProfile1 {
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private Date dateOfBirth;

    public UserProfile1() {}

    public UserProfile1(String username, String firstname, String lastname, String email) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getLastname() { return lastname; }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

}
