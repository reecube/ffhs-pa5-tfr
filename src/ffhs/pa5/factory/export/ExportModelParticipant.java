package ffhs.pa5.factory.export;

import ffhs.pa5.model.Participant;

/**
 * TODO
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

    public ExportModelParticipant(Participant participant) {
        // TODO: Barbara, fülle die Variablen anhand der Parameter
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
