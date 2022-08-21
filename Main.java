import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Vector;
import java.util.regex.*;

import Simulation.Instruction;
import Simulation.Simulation;
import Simulation.Exception.InstructionException;

public class Main{
    public static void main(String[] argv){
        if(argv.length == 1){
            execute(argv[0]);
        }
    }

    private static void execute(String filename) {
        Vector<Instruction> instructions = new Vector<Instruction>();
        Pattern cyclePattern = Pattern.compile("^\\d+$");
        Pattern instructionPattern = Pattern.compile("^((Baloon)|(JetPlane)|(Helicopter)) \\w+ \\d+ \\d+ \\d+$");
        int cycles = 0;
        int lines = 1;

        try{
            BufferedReader buffer = new BufferedReader(new FileReader(filename));
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
                    instructions.add(new Instruction(line));
                }
                else {
                    buffer.close();
                    throw new InstructionException( String.valueOf(lines) );
                }
                line = buffer.readLine();
            }
            buffer.close();
        }catch(Exception e){
            System.out.println(e.toString());
        }
        Simulation simulation = new Simulation(cycles, instructions);

        simulation.execute();
    }
}