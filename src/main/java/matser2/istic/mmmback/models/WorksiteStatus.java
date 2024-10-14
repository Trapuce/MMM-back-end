package matser2.istic.mmmback.models;


public enum WorksiteStatus {
    NOT_STARTED("Non réalisé"),
    IN_PROGRESS("En cours"),
    INTERRUPTED("Interrompu"),
    COMPLETED("Terminé");

    private final String label;

    WorksiteStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
