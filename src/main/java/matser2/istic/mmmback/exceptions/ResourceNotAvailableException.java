package matser2.istic.mmmback.exceptions;

public class ResourceNotAvailableException extends RuntimeException {
    public ResourceNotAvailableException(String message) {
        super(message);
    }

    public ResourceNotAvailableException(Long resourceId, Long worksiteId) {
        super("Resource with ID " + resourceId + " is not available for worksite " + worksiteId);
    }
}
