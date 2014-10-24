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
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    
    public SOBUser() {
        super();
    }
    
    public SOBUser(Long ID, String firstName, String lastName, String email, String phone) {
        this.id = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }
    
    public Long getID() {
        return id;
    }

    public void setID(Long ID) {
        this.id = ID;
    }
    
    public String getFirstName() {
        return fixNull(this.firstName);
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return fixNull(this.lastName);
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return fixNull(this.email);
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return fixNull(this.phone);
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    private String fixNull(String in) {
        return (in == null) ? "" : in;
    }

    public String getMessage() {

        return "\nFirst Name: " + getFirstName() + "\n"
                + "Last Name:  " + getLastName() + "\n"
                + "Email:      " + getEmail() + "\n"
                + "Phone:      " + getPhone() + "\n";
    }
    
    public boolean isValid () {
        return !(firstName == null || lastName == null || email == null || phone == null);
    }
}
