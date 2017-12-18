package ffhs.pa5.tests;

import ffhs.pa5.factory.export.ExportFactory;
import ffhs.pa5.factory.export.ExportModel;
import ffhs.pa5.factory.export.FileExportOutputHandler;
import ffhs.pa5.factory.export.TextExportOutputHandler;
import ffhs.pa5.model.AgendaItem;
import ffhs.pa5.model.DataFile;
import ffhs.pa5.model.Participant;
import ffhs.pa5.model.type.State;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Tests for the ExportFactoryTest class.
 *
 * @author Murat Tokmak
 * @author Barbara Fauth
 * @author Yves Riedener
 * @version 1.0
 */
public class ExportFactoryTest extends TestingBase {

    private AgendaItem agendaItem = new AgendaItem();
    private Participant participant = new Participant();

    /**
     * Test the ExportFactoryTest class
     */
    @Test
    public void testExportModel() {

        // Config variables AgendaItem
        final String expectedId = "contentABC123öäü";
        final String expectedTitleAgendaItem = "content ABC 123 öäü";
        final String expectedContent = "content ABC 123 öäü";
        final boolean NotDeleted = false;

        // Define AgendaItem
        agendaItem.setId(expectedId);
        agendaItem.setTitle(expectedTitleAgendaItem);
        agendaItem.setContent(expectedContent);
        agendaItem.setDeleted(NotDeleted);

        // Config variables Participant
        final String expectedFirstname = "content ABC 123 öäü";
        final String expectedLastname = "content ABC 123 öäü";
        final String expectedEmail = "content ABC 123 öäü";
        final String expectedPhone = "content ABC 123 öäü";
        final String expectedRole = "content ABC 123 öäü";
        final String expectedNotes = "content ABC 123 öäü";

        // Define Participant
        participant.setFirstname(expectedFirstname);
        participant.setLastname(expectedLastname);
        participant.setEmail(expectedEmail);
        participant.setPhone(expectedPhone);
        participant.setRole(expectedRole);
        participant.setNotes(expectedNotes);

        // Config variables Meeting
        final String expectedTitleMeeting = "content ABC 123 öäü";
        final String expectedLocation = "content ABC 123 öäü";
        final Date expectedDate = new Date(System.currentTimeMillis());
        final State expectedState = State.PREPARATION;
        final Date expectedNextMeeting = new Date(System.currentTimeMillis());
        final Date expectedCreationDate = new Date(System.currentTimeMillis());
        final Date expectedLastEditionDate = new Date(System.currentTimeMillis());

        // Define Meeting
        DataFile dataFile = new DataFile("MeinPasswortäüö23");
        dataFile.getMeeting().setTitle(expectedTitleMeeting);
        dataFile.getMeeting().setLocation(expectedLocation);
        dataFile.getMeeting().setDate(expectedDate);
        dataFile.getMeeting().setState(expectedState);
        dataFile.getMeeting().setNextMeeting(expectedNextMeeting);
        dataFile.getMeeting().addParticipant(participant);
        dataFile.getMeeting().addAgendaItem(agendaItem);

        ExportModel exportModel = new ExportModel(dataFile, expectedCreationDate, expectedLastEditionDate);

        assertEquals(expectedTitleMeeting, exportModel.getTitle());
        assertEquals(expectedLocation, exportModel.getLocation());
        assertEquals(expectedDate, exportModel.getDate());
        assertEquals(expectedState, exportModel.getState());
        assertEquals(expectedNextMeeting, exportModel.getNextMeeting());
        assertEquals(expectedCreationDate, exportModel.getCreationDate());
        assertEquals(participant.getFirstname(), exportModel.getParticipants().get(0).getFirstname());
        assertEquals(participant.getLastname(), exportModel.getParticipants().get(0).getLastname());
        assertEquals(participant.getEmail(), exportModel.getParticipants().get(0).getEmail());
        assertEquals(participant.getPhone(), exportModel.getParticipants().get(0).getPhone());
        assertEquals(participant.getRole(), exportModel.getParticipants().get(0).getRole());
        assertEquals(participant.getNotes(), exportModel.getParticipants().get(0).getNotes());
        assertEquals(agendaItem.getId(), exportModel.getAgendaItems().get(0).getId());
        assertEquals(agendaItem.getTitle(), exportModel.getAgendaItems().get(0).getTitle());
        assertEquals(agendaItem.getContent(), exportModel.getAgendaItems().get(0).getContent());
        assertNotNull(exportModel.getExportDate());

        // Config variables
        FileExportOutputHandler textExportOutputHandler = new TextExportOutputHandler();
        String path = "path";

        // Variable declaration
        final ExportFactory validExportFactory = new ExportFactory(exportModel, textExportOutputHandler);

        logTestCase("Valid export");
        assertTrue(validExportFactory.export(path));
    }
}
