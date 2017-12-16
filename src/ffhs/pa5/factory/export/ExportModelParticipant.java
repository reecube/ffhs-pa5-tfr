package ffhs.pa5.factory.export;

import ffhs.pa5.model.Participant;

import java.util.ArrayList;

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

    /**
     * TODO
     *
     * @param participant TODO
     */
    ExportModelParticipant(Participant participant) {
        this.firstname = participant.getFirstname();
        this.lastname = participant.getLastname();
        this.email = participant.getEmail();
        this.phone = participant.getPhone();
        this.role = participant.getRole();
        this.notes = participant.getNotes();
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

        if (notes != null && notes.length() > 0) {
            result.add("{" + notes + "}");
        }

        return String.join(" ", result);
    }
}
