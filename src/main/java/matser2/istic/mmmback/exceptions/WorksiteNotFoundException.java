package matser2.istic.mmmback.exceptions;

public class WorksiteNotFoundException extends RuntimeException {
    public WorksiteNotFoundException(String message) {
        super(message);
    }

    public WorksiteNotFoundException(Long id) {
        super("Worksite not found with ID: " + id);
    }
}
