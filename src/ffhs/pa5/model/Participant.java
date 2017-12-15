package ffhs.pa5.model;

import com.google.gson.annotations.Expose;
import ffhs.pa5.view.ViewObservable;

import java.util.ArrayList;
import java.util.Observer;

/**
 * This class handles the data of the participants of a meeting.
 *
 * @author Murat Tokmak
 * @author Barbara Fauth
 * @author Yves Riedener
 * @version 1.0
 */
public class Participant extends ViewObservable {

    @Expose
    private String firstname = "";

    @Expose
    private String lastname = "";

    @Expose
    private String email = "";

    @Expose
    private String phone = "";

    @Expose
    private String role = "";

    @Expose
    private String notes = "";

    /**
     * {@inheritDoc}
     */
    @Override
    public void addObserverRecursive(Observer observer) {
        addObserver(observer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        ArrayList<String> result = new ArrayList<>();

        if (firstname != null && firstname.length() > 0) {
            result.add(firstname);
        }

        if (lastname != null && lastname.length() > 0) {
            result.add(lastname);
        }

        if (role != null && role.length() > 0) {
            result.add("(" + role + ")");
        }

        if (email != null && email.length() > 0) {
            result.add("<" + email + ">");
        }

        if (phone != null && phone.length() > 0) {
            result.add("[" + phone + "]");
        }

        return String.join(" ", result);
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
