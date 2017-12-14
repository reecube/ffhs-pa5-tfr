package ffhs.pa5.controller;

import ffhs.pa5.model.AgendaItem;
import ffhs.pa5.model.Participant;

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
    void addAgendaItem(AgendaItem agendaItem);

    /**
     * TODO
     *
     * @param agendaItem TODO
     */
    void editAgendaItem(AgendaItem agendaItem);

    /**
     * TODO
     *
     * @param agendaItem TODO
     */
    void removeAgendaItem(AgendaItem agendaItem);

    /**
     * TODO
     *
     * @param agendaItem TODO
     * @param value TODO
     * @return TODO
     */
    boolean moveAgendaItem(AgendaItem agendaItem, int value);

    /**
     * TODO
     *
     * @param participant TODO
     */
    void addParticipant(Participant participant);

    /**
     * TODO
     *
     * @param participant TODO
     */
    void editParticipant(Participant participant);

    /**
     * TODO
     *
     * @param participant TODO
     */
    void removeParticipant(Participant participant);

    /**
     * TODO
     */
    void closeMeeting();
}