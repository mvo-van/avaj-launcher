package Simulation;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Vector;

public class Simulation {
    private int cycle;
    private Vector<Instruction> instructions;
    private static PrintWriter writer;

    static {
        try {
            writer = new PrintWriter("simulation.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Simulation(int cycle, Vector<Instruction> instructions) {
        this.cycle = cycle;
        this.instructions = instructions;
    }

    public void execute() {
        Tower tower = new WeatherTower();

        for (Instruction instruction : instructions){
            tower.register(AircraftFactory.newAircraft(instruction.type, instruction.name,instruction.longitude, instruction.latitude, instruction.height));
        }
        for(int i=0; i < this.cycle; ++i){
            tower.conditionsChanged();
        }
        writer.close();
    }

    static void log(String format, Object... args){
        writer.printf(format, args);
    }
}
