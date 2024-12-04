package matser2.istic.mmmback.exceptions;

public class ResourceTypeMismatchException extends RuntimeException {
    public ResourceTypeMismatchException(String message) {
        super(message);
    }

    public ResourceTypeMismatchException(Long id, String expectedType, String actualType) {
        super("Resource with ID " + id + " is of type " + actualType + ", but " + expectedType + " was expected.");
    }
}

