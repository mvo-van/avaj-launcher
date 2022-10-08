package simulation.exception;

public class IdAlreadyUseException extends Exception {
    public IdAlreadyUseException(String name, String line) {
        super(String.format("Id \"%s\" aleady use, error at line %s", name, line));
    }
}
