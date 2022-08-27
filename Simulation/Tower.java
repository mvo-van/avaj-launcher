package Simulation;

import java.util.HashSet;
import java.util.Set;

public class Tower {
    private Set<Flyable> observers;
    private Set<Flyable> toDelete;
    public Tower(){
        this.observers = new HashSet<Flyable>();
        this.toDelete = new HashSet<Flyable>();
    }

    public void register(Flyable flyable){
        Aircraft aircraft = (Aircraft)flyable;
        this.towerSay(aircraft, "registered to weather tower");
        flyable.registerTower((WeatherTower)this);
        this.observers.add(flyable);
    }

    public void unregister(Flyable flyable){
        Aircraft aircraft = (Aircraft)flyable;
        aircraft.log("landing");
        this.towerSay(aircraft, "unregistered from weather tower");
        this.toDelete.add(flyable);
    }

    protected void conditionsChanged(){
        for(Flyable flyable : observers){
            flyable.updateConditions();
        }
        this.unregisterAll();
    }

    protected void unregisterAll() {
        for ( Flyable flyable : toDelete ){
            this.observers.remove(flyable);
        }
        this.toDelete.clear();
    }

    public void towerSay(Aircraft aircraft, String message){
        Simulation.log("Tower says: %s %s.\n", aircraft.getFullname(), message);
    }
}
