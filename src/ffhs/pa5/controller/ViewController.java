package ffhs.pa5.controller;

import ffhs.pa5.factory.export.ExportOutputHandler;
import ffhs.pa5.factory.storage.FileStorageFactoryResult;
import ffhs.pa5.model.AgendaItem;
import ffhs.pa5.model.Participant;
import ffhs.pa5.model.type.State;

import java.time.LocalDate;

/**
 * The ViewController contains the possible actions in SPV
 *
 * @author Murat Tokmak
 * @author Barbara Fauth
 * @author Yves Riedener
 * @version 1.0
 */
public interface ViewController {

    /**
     * Add the agenda item
     *
     * @param agendaItem TODO
     */
    boolean addAgendaItem(AgendaItem agendaItem);

    /**
     * Edit the exist agenda item
     *
     * @param agendaItem TODO
     */
    boolean editAgendaItem(AgendaItem agendaItem);

    /**
     * Remove the exist agenda item
     *
     * @param agendaItem TODO
     */
    boolean removeAgendaItem(AgendaItem agendaItem);

    /**
     * Moving the agenda item up or down
     *
     * @param agendaItem TODO
     * @param moveUp     TODO
     * @return TODO
     */
    boolean moveAgendaItem(AgendaItem agendaItem, boolean moveUp);

    /**
     * Add a participant
     *
     * @param participant TODO
     */
    boolean addParticipant(Participant participant);

    /**
     * Edit the exist participant
     *
     * @param participant TODO
     */
    boolean editParticipant(Participant participant);

    /**
     * Remove the exist participant
     *
     * @param participant TODO
     */
    boolean removeParticipant(Participant participant);

    /**
     * Close the meeting
     */
    void closeMeeting();

    /**
     * Change the state
     *
     * @param newState TODO
     * @return TODO
     */
    boolean changeState(State newState);

    /**
     * Change the exist meeting title with a new
     *
     * @param newValue TODO
     */
    void changeMeetingTitle(String newValue);

    /**
     * Change the meeting date
     *
     * @param newValue TODO
     */
    void changeMeetingDate(LocalDate newValue);

    /**
     * Change meeting location
     *
     * @param newValue TODO
     */
    void changeMeetingLocation(String newValue);

    /**
     * Open a new exist file from browser
     *
     * @param path TODO
     * @return TODO
     */
    FileStorageFactoryResult openFile(String path);

    /**
     * Save the SPV-file
     *
     * @param path TODO
     * @return TODO
     */
    FileStorageFactoryResult saveFile(String path);

    /**
     * Get the last save path
     *
     * @return TODO
     */
    String getLastSavePath();

    /**
     * Change the next meeting date
     *
     * @param newValue TODO
     */
    void changeMeetingNextMeeting(LocalDate newValue);

    /**
     * Export SPV-informations to TXT / PDF
     *
     * @param handler TODO
     * @param path    TODO
     * @return TODO
     */
    boolean export(ExportOutputHandler handler, String path);
}
