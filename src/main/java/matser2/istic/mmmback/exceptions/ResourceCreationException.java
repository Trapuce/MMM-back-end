package matser2.istic.mmmback.exceptions;

public class ResourceCreationException extends RuntimeException {
    public ResourceCreationException(String message) {
        super(message);
    }

    public ResourceCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
