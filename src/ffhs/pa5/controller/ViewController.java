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
     * @param agendaItem agendaItem
     */
    boolean addAgendaItem(AgendaItem agendaItem);

    /**
     * Edit the exist agenda item
     *
     * @param agendaItem agendaItem
     */
    boolean editAgendaItem(AgendaItem agendaItem);

    /**
     * Remove the exist agenda item
     *
     * @param agendaItem agendaItem
     */
    boolean removeAgendaItem(AgendaItem agendaItem);

    /**
     * Moving the agenda item up or down
     *
     * @param agendaItem agendaItem
     * @param moveUp     moveUp
     * @return agendaItem, moveUp
     */
    boolean moveAgendaItem(AgendaItem agendaItem, boolean moveUp);

    /**
     * Add a participant
     *
     * @param participant participant
     */
    boolean addParticipant(Participant participant);

    /**
     * Edit the exist participant
     *
     * @param participant participant
     */
    boolean editParticipant(Participant participant);

    /**
     * Remove the exist participant
     *
     * @param participant participant
     */
    boolean removeParticipant(Participant participant);

    /**
     * Close the meeting
     */
    void closeMeeting();

    /**
     * Change the state
     *
     * @param newState newState
     */
    void changeState(State newState);

    /**
     * Get the actual state
     *
     * @return The actual state
     */
    State getState();

    /**
     * Change the exist meeting title with a new
     *
     * @param newValue newValue
     */
    void changeMeetingTitle(String newValue);

    /**
     * Change the meeting date
     *
     * @param newValue newValue
     */
    void changeMeetingDate(LocalDate newValue);

    /**
     * Change meeting location
     *
     * @param newValue newValue
     */
    void changeMeetingLocation(String newValue);

    /**
     * Open a new exist file from browser
     *
     * @param path path
     * @return path
     */
    FileStorageFactoryResult openFile(String path);

    /**
     * Save the SPV-file
     *
     * @param path path
     * @return path
     */
    FileStorageFactoryResult saveFile(String path);

    /**
     * Get the last save path
     *
     * @return getLastSavePath
     */
    String getLastSavePath();

    /**
     * Change the next meeting date
     *
     * @param newValue newValue
     */
    void changeMeetingNextMeeting(LocalDate newValue);

    /**
     * Export SPV-informations to TXT / PDF
     *
     * @param handler handler
     * @param path    path
     * @return handler, path
     */
    boolean export(ExportOutputHandler handler, String path);
}
