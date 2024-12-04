package matser2.istic.mmmback.exceptions;

public class ResourceValidationException extends RuntimeException {
    public ResourceValidationException(String message) {
        super(message);
    }

    public ResourceValidationException(String field, String errorMessage) {
        super("Validation error for field " + field + ": " + errorMessage);
    }
}
