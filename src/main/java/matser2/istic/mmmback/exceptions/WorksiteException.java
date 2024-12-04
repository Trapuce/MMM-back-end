package matser2.istic.mmmback.exceptions;

public class WorksiteException extends RuntimeException {
    public WorksiteException(String message) {
        super(message);
    }

    public WorksiteException(String message, Throwable cause) {
        super(message, cause);
    }
}
