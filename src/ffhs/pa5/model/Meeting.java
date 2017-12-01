package ffhs.pa5.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * TODO
 *
 * @author Murat Tokmak
 * @author Barbara Fauth
 * @author Yves Riedener
 * @version 1.0
 */
public class Meeting extends ViewObservable {

    private String title;
    private String location;
    private Date date;
    private ArrayList<Participant> participants;
    private State state;
    private Date nextMeeting;
    private ArrayList<AgendaItem> agendaItems;

    public Meeting() {
        this.title = "";
        this.location = "";
        this.date = null;
        this.participants = new ArrayList<>();
        this.state = State.PREPARATION;
        this.nextMeeting = null;
        this.agendaItems = new ArrayList<>();
    }

    public Meeting(String title, String location, Date date, ArrayList<Participant> participants, State state, Date nextMeeting, ArrayList<AgendaItem> agendaItems) {
        this.title = title;
        this.location = location;
        this.date = date;
        this.participants = participants;
        this.state = state;
        this.nextMeeting = nextMeeting;
        this.agendaItems = agendaItems;
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

    public ArrayList<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(ArrayList<Participant> participants) {
        this.participants = participants;

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

    public ArrayList<AgendaItem> getAgendaItems() {
        return agendaItems;
    }

    public void setAgendaItems(ArrayList<AgendaItem> agendaItems) {
        this.agendaItems = agendaItems;

        updateView();
    }
}
