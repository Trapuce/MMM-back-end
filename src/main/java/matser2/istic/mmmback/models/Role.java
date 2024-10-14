package matser2.istic.mmmback.models;
public enum Role {
    CHEF_DE_CHANTIER("Chef de chantier"),
    RESPONSABLE_DU_CHANTIER("Responsable du chantier"),
    EQUIPIER_SIMPLE("Équipier simple");

    private final String libelle;

    Role(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelle() {
        return this.libelle;
    }
}

