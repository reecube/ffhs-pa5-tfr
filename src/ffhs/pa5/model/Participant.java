package ffhs.pa5.model;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

/**
 * This class handles the data of the participants of a meeting.
 *
 * @author Murat Tokmak
 * @author Barbara Fauth
 * @author Yves Riedener
 * @version 1.0
 */
public class Participant {

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
    public String toString() { //TODO: @Yves: Diese Methode brauche ich in ExportModelParticipant und nicht hier. Gibt es noch eine andere Verwendung?
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
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
