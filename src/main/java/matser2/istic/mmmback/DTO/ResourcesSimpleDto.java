package matser2.istic.mmmback.DTO;

import jakarta.validation.constraints.NotEmpty;

public abstract class ResourcesSimpleDto {

    private Long id;

    @NotEmpty(message = "name is required.")
    private String name;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
