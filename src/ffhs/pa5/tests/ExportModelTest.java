package ffhs.pa5.tests;

import ffhs.pa5.factory.export.ExportModelAgendaItem;
import ffhs.pa5.factory.export.ExportModelParticipant;
import ffhs.pa5.model.AgendaItem;
import ffhs.pa5.model.Participant;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the ExportModelTest class.
 *
 * @author Murat Tokmak
 * @author Barbara Fauth
 * @author Yves Riedener
 * @version 1.0
 */
public class ExportModelTest extends TestingBase {

    // General variables
    private ExportModelAgendaItem exportModelAgendaItem;
    private ExportModelParticipant exportModelParticipant;

    /**
     * Test the ExportModelAgendaItem class
     */
    @Test
    public void testExportModelAgendaItem() throws Exception {

        // Config variables
        final String expectedId = "contentABC123öäü";
        final String expectedTitle = "content ABC 123 öäü";
        final String expectedContent = "content ABC 123 öäü";
        final boolean NotDeleted = false;
        final boolean deleted = true;

        // Variable declaration
        AgendaItem agendaItem = new AgendaItem();

        logTestCase("create existing ExportModelAgendaItem");
        agendaItem.setId(expectedId);
        agendaItem.setTitle(expectedTitle);
        agendaItem.setContent(expectedContent);
        agendaItem.setDeleted(NotDeleted);

        exportModelAgendaItem = new ExportModelAgendaItem(agendaItem);
        assertEquals(expectedId, exportModelAgendaItem.getId());
        assertEquals(expectedTitle, exportModelAgendaItem.getTitle());
        assertEquals(expectedContent, exportModelAgendaItem.getContent());

        logTestCase("make sure deleted AgendaItems aren't created als ExportModelAgendaItems");
        //TODO: @Barbara, Wie kann ich das testen?
    }

    /**
     * Test the ExportModelParticipant class
     */
    @Test
    public void testExportModelParticipant() throws Exception {

        // Config variables
        final String expectedFirstname = "content ABC 123 öäü";
        final String expectedLastname = "content ABC 123 öäü";
        final String expectedEmail = "content ABC 123 öäü";
        final String expectedPhone = "content ABC 123 öäü";
        final String expectedRole = "content ABC 123 öäü";
        final String expectedNotes = "content ABC 123 öäü";

        // Variable declaration
        Participant participant = new Participant();

        logTestCase("create existing ExportModelAgendaItem");
        participant.setFirstname(expectedFirstname);
        participant.setLastname(expectedLastname);
        participant.setEmail(expectedEmail);
        participant.setPhone(expectedPhone);
        participant.setRole(expectedRole);
        participant.setNotes(expectedNotes);

        exportModelParticipant = new ExportModelParticipant(participant);
        assertEquals(expectedFirstname, exportModelParticipant.getFirstname());
        assertEquals(expectedLastname, exportModelParticipant.getLastname());
        assertEquals(expectedEmail, exportModelParticipant.getEmail());
        assertEquals(expectedPhone, exportModelParticipant.getPhone());
        assertEquals(expectedRole, exportModelParticipant.getRole());
        assertEquals(expectedNotes, exportModelParticipant.getNotes());
    }

    /**
     * Test the ExportModelTest class
     */
    @Test
    public void testExportModel() throws Exception {

        // Config variables
        final String expectedTitle = "content ABC 123 öäü";
        final String expectedLocation = "content ABC 123 öäü";
        final Date expectedDate = new Date(System.currentTimeMillis());
        final String expectedState = "content ABC 123 öäü";
        final Date expectedNextMeeting = new Date(System.currentTimeMillis());
        final Date expectedCreationDate = new Date(System.currentTimeMillis());
        final Date expectedLastEditionDate = new Date(System.currentTimeMillis());
        final Date expectedExportdate = new Date(System.currentTimeMillis());
        ArrayList<ExportModelParticipant> participants = new ArrayList<>();
        participants.add(exportModelParticipant);
        ArrayList<ExportModelAgendaItem> agendaItems = new ArrayList<>();
        agendaItems.add(exportModelAgendaItem);


        // Variable declaration
        //TODO @Barbara: hier müsste ich irgendwie ein Dataset generieren?!

    }

}
