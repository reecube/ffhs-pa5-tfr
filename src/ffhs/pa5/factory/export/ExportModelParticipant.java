package ffhs.pa5.factory.export;

import ffhs.pa5.model.Participant;

import java.util.ArrayList;

/**
 * Export information concerning participant
 *
 * @author Murat Tokmak
 * @author Barbara Fauth
 * @author Yves Riedener
 * @version 1.0
 */
public class ExportModelParticipant {

    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private String role;
    private String notes;

    /**
     * Export all concerning participant fields
     *
     * @param participant participant
     */
    public ExportModelParticipant(Participant participant) {
        this.firstname = participant.getFirstname();
        this.lastname = participant.getLastname();
        this.email = participant.getEmail();
        this.phone = participant.getPhone();
        this.role = participant.getRole();
        this.notes = participant.getNotes();
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getRole() {
        return role;
    }

    public String getNotes() {
        return notes;
    }
}
