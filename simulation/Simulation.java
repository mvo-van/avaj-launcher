package simulation;

import java.util.Set;
import java.util.HashSet;
import simulation.exception.IdAlreadyUseException;
import simulation.exception.InstructionException;
import simulation.exception.NotFileException;
import simulation.exception.ScenarioNotFound;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;
import java.util.regex.Pattern;

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

    public void update(int cycle, Vector<Instruction> instructions) {
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
    }

    public static void main(String[] argv) throws InstructionException, IOException {

        if(argv.length == 1){
            try{
                Simulation simulation=new Simulation(argv[0]);
                simulation.execute();

            } catch (ScenarioNotFound | InstructionException | IdAlreadyUseException e){
                writer.close();
                System.out.println(e.toString());
                System.exit(1);
            }
        }else{
            writer.close();
            try{
                throw new NotFileException();
            }catch (NotFileException e){
                System.out.println(e.toString());
                System.exit(1);
            }
            
        }
        writer.close();
    }

    public Simulation(String filename) throws InstructionException, IOException, ScenarioNotFound, IdAlreadyUseException{
        Vector<Instruction> instructions = new Vector<Instruction>();
        Set<String> idAircraft = new HashSet<String>();
        Instruction tmpInstruction;
        Pattern cyclePattern = Pattern.compile("^\\d+$");
        Pattern instructionPattern = Pattern.compile("^((Baloon)|(JetPlane)|(Helicopter)) \\w+ \\d+ \\d+ \\d+$");
        int cycles = 0;
        int lines = 1;
        BufferedReader buffer;
        try{
            buffer = new BufferedReader(new FileReader(filename));
        }catch (FileNotFoundException e){
            throw new ScenarioNotFound();
        }

            String line = buffer.readLine();
            if (cyclePattern.matcher(line).find()){
                cycles = Integer.parseInt(line);
            } else {
                buffer.close();
                throw new InstructionException( String.valueOf(lines) );
            }

            line = buffer.readLine();
            while(line != null){
                ++lines;
                if (instructionPattern.matcher(line).find()){
                    tmpInstruction = new Instruction(line);
                    if (idAircraft.contains(tmpInstruction.name) == true){
                        throw new IdAlreadyUseException(tmpInstruction.name, String.valueOf(lines) );
                    }
                    else{
                        idAircraft.add(tmpInstruction.name);
                        instructions.add(tmpInstruction);
                    }
                }
                else {
                    buffer.close();
                    throw new InstructionException( String.valueOf(lines) );
                }
                line = buffer.readLine();
            }
        buffer.close();
        this.update(cycles, instructions);
    }

    static void log(String format, Object... args){
        writer.printf(format, args);
    }
}
