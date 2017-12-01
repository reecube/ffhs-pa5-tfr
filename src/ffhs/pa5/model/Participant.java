package ffhs.pa5.model;

/**
 * TODO
 *
 * @author Murat Tokmak
 * @author Barbara Fauth
 * @author Yves Riedener
 * @version 1.0
 */
public class Participant extends ViewObservable {

    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private String role;
    private String notes;

    public Participant(String firstname, String lastname, String email, String phone, String role, String notes) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.notes = notes;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;

        updateView();
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;

        updateView();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;

        updateView();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;

        updateView();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;

        updateView();
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;

        updateView();
    }
}
