package ffhs.pa5.model;

import com.google.gson.annotations.Expose;
import ffhs.pa5.model.type.State;
import ffhs.pa5.view.ViewObservable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Observer;

/**
 * This class handles the data of a meeting.
 *
 * @author Murat Tokmak
 * @author Barbara Fauth
 * @author Yves Riedener
 * @version 1.0
 */
public class Meeting extends ViewObservable {

    @Expose
    private String title = "";

    @Expose
    private String location = "";

    @Expose
    private Date date = null;

    @Expose
    private State state = State.PREPARATION;

    @Expose
    private Date nextMeeting = null;

    @Expose
    private ArrayList<Participant> participants = new ArrayList<>();

    @Expose
    private ArrayList<AgendaItem> agendaItems = new ArrayList<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public void addObserverRecursive(Observer observer) {
        addObserver(observer);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;

        updateView();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;

        updateView();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;

        updateView();
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;

        updateView();
    }

    public Date getNextMeeting() {
        return nextMeeting;
    }

    public void setNextMeeting(Date nextMeeting) {
        this.nextMeeting = nextMeeting;

        updateView();
    }

    public Participant[] getParticipants() {
        return participants.toArray(new Participant[0]);
    }

    /**
     * TODO
     *
     * @param participant TODO
     * @return TODO
     */
    public boolean addParticipant(Participant participant) {
        boolean result = participants.add(participant);

        if (result) {
            updateView();
        }

        return result;
    }

    /**
     * TODO
     *
     * @param participant TODO
     * @return TODO
     */
    public boolean editParticipant(Participant participant) {
        if (!participants.contains(participant)) {
            return false;
        }

        updateView();

        return true;
    }

    /**
     * TODO
     *
     * @param participant TODO
     * @return TODO
     */
    public boolean removeParticipant(Participant participant) {
        boolean result = participants.remove(participant);

        if (result) {
            updateView();
        }

        return result;
    }

    public AgendaItem[] getAgendaItems() {
        return agendaItems.toArray(new AgendaItem[0]);
    }

    /**
     * TODO
     *
     * @param agendaItem TODO
     * @return TODO
     */
    public boolean addAgendaItem(AgendaItem agendaItem) {
        boolean result = agendaItems.add(agendaItem);

        if (result) {
            updateView();
        }

        return result;
    }

    /**
     * TODO
     *
     * @param agendaItem TODO
     * @return TODO
     */
    public boolean editAgendaItem(AgendaItem agendaItem) {
        if (!agendaItems.contains(agendaItem)) {
            return false;
        }

        updateView();

        return true;
    }

    /**
     * TODO
     *
     * @param agendaItem TODO
     * @return TODO
     */
    public boolean removeAgendaItem(AgendaItem agendaItem) {
        boolean result = agendaItems.remove(agendaItem);

        if (result) {
            updateView();
        }

        return result;
    }

    /**
     * TODO
     *
     * @param agendaItem TODO
     * @param moveUp     TODO
     * @return TODO
     */
    public boolean moveAgendaItem(AgendaItem agendaItem, boolean moveUp) {
        final int currentIndex = agendaItems.indexOf(agendaItem);
        if (currentIndex < 0) {
            return false;
        }

        int newIndex = currentIndex + (moveUp ? -1 : 1);

        if (newIndex < 0) {
            newIndex = agendaItems.size() + newIndex;
        } else {
            newIndex = newIndex % agendaItems.size();
        }

        Collections.swap(agendaItems, currentIndex, newIndex);

        final boolean result = agendaItems.indexOf(agendaItem) == newIndex;

        if (result) {
            updateView();
        }

        return result;
    }
}
