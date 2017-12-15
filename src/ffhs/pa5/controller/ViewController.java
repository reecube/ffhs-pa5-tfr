package ffhs.pa5.controller;

import ffhs.pa5.model.AgendaItem;
import ffhs.pa5.model.Participant;
import ffhs.pa5.model.State;

/**
 * TODO
 *
 * @author Murat Tokmak
 * @author Barbara Fauth
 * @author Yves Riedener
 * @version 1.0
 */
public interface ViewController {

    /**
     * TODO
     *
     * @param agendaItem TODO
     */
    boolean addAgendaItem(AgendaItem agendaItem);

    /**
     * TODO
     *
     * @param agendaItem TODO
     */
    boolean editAgendaItem(AgendaItem agendaItem);

    /**
     * TODO
     *
     * @param agendaItem TODO
     */
    boolean removeAgendaItem(AgendaItem agendaItem);

    /**
     * TODO
     *
     * @param agendaItem TODO
     * @param moveUp     TODO
     * @return TODO
     */
    boolean moveAgendaItem(AgendaItem agendaItem, boolean moveUp);

    /**
     * TODO
     *
     * @param participant TODO
     */
    boolean addParticipant(Participant participant);

    /**
     * TODO
     *
     * @param participant TODO
     */
    boolean editParticipant(Participant participant);

    /**
     * TODO
     *
     * @param participant TODO
     */
    boolean removeParticipant(Participant participant);

    /**
     * TODO
     */
    void closeMeeting();

    /**
     * TODO
     *
     * @param newState TODO
     * @return TODO
     */
    boolean changeState(State newState);
}
