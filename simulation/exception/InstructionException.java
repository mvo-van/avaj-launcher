package simulation.exception;

public class InstructionException extends Exception {
    public InstructionException(String line) {
        super(String.format("Instruction error at line %s", line));
    }
}
