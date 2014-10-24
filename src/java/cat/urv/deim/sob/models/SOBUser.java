package cat.urv.deim.sob.models;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SOBUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;

    public SOBUser() {
        super();
    }

    public SOBUser(Long ID, String username, String email, String password) {
        this.id = ID;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return fixNull(username);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return fixNull(email);
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return fixNull(password);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMessage() {
        return toString();
    }

    @Override
    public String toString() {

        return "\nID:\t\t" + getId() + "\n"
                + "username:\t" + getUsername() + "\n"
                + "email:\t\t" + getEmail() + "\n"
                + "password:\t" + getPassword() + "\n";
    }

    public boolean isValid() {
        return !(username == null || email == null || password == null);
    }

    private String fixNull(String in) {
        return (in == null) ? "" : in;
    }

}
