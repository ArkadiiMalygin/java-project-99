package hexlet.code.app.exception;

public class EntityIsConnectedToOthers extends RuntimeException {
    public EntityIsConnectedToOthers(String message) {
        super(message);
    }
}
