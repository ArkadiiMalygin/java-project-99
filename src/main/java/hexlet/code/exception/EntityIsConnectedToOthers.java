package hexlet.code.exception;

public class EntityIsConnectedToOthers extends RuntimeException {
    public EntityIsConnectedToOthers(String message) {
        super(message);
    }
}
