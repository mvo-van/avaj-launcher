package simulation;

public class Instruction {
    public String type;
    public String name;
    public int     longitude;
    public int     latitude;
    public int     height;

    public Instruction(String line){
        String[] items = line.split(" ");
        this.type = items[0];
        this.name = items[1];
        this.longitude = Integer.parseInt(items[2]);
        this.latitude = Integer.parseInt(items[3]);
        this.height = Integer.parseInt(items[4]);
    }

    public String toString() {
        return String.format("Type=%s Name=%s Lon=%d Lat=%d Height=%d",
            this.type,
            this.name,
            this.latitude,
            this.longitude,
            this.height);
    }
}
