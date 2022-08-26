package Simulation;

public class Aircraft {
    protected long id;
    protected String name;
    protected Coordinates coordinates;
    protected String type;
    private long idCounter = 0;
    
    protected Aircraft(String name, Coordinates coordinates, String type){
        this(name, coordinates);
        this.type = type;
    }
    protected Aircraft(String name, Coordinates coordinates){
        this.name = name;
        this.coordinates = coordinates;
        this.id = this.nextId();
    }

    public String getType(){
        return this.type;
    }

    public long getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    private long nextId(){
        return this.idCounter++;
    }

    public void log(String message) {
        Simulation.log(String.format("%s: %s\n", this.getFullname(), message));
//        System.out.printf("%s: %s\n", this.getFullname(), message);
    }

    public String getFullname() {
        return String.format("%s#%s(%d)", this.type, this.name, this.id);
    }
}
