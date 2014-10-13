package cat.urv.deim.sob;

public class User {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;

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
}
