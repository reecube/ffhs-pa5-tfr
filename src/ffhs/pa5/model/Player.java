package ffhs.pa5.model;

import java.util.Observable;

/**
 * the legendary Player class
 *
 * @author Tamara Burri
 * @author Yves Riedener
 * @version 1.0
 */
public class Player extends Observable {
    private String name;
    private int seeds;
    private ComputerStrategy computerStrategy;

    /**
     * the legendary constructor
     */
    public Player() {
        this.name = null;
        this.seeds = 0;
        this.computerStrategy = null;

        // notify the observers for changes
        this.setChanged();
        this.notifyObservers();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

        // notify the observers for changes
        this.setChanged();
        this.notifyObservers();
    }


    public int getSeeds() {
        return seeds;
    }

    public void setSeeds(int seeds) {
        this.seeds = seeds;

        // notify the observers for changes
        this.setChanged();
        this.notifyObservers();
    }

    public ComputerStrategy getComputerStrategy() {
        return computerStrategy;
    }

    public void setComputerStrategy(ComputerStrategy computerStrategy) {
        this.computerStrategy = computerStrategy;

        // notify the observers for changes
        this.setChanged();
        this.notifyObservers();
    }


    @Override
    public String toString() {
        return name;//String.format("%s [%d]", name, hashCode());
    }

}
