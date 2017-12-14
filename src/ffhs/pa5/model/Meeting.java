package ffhs.pa5.model;

import java.util.ArrayList;
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

    private String title = "";
    private String location = "";
    private Date date = null;
    private State state = State.PREPARATION;
    private Date nextMeeting = null;
    private ArrayList<Participant> participants = new ArrayList<>();
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
    public boolean removeAgendaItem(AgendaItem agendaItem) {
        boolean result = agendaItems.remove(agendaItem);

        if (result) {
            updateView();
        }

        return result;
    }
}
