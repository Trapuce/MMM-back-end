package matser2.istic.mmmback.DTO;

import jakarta.validation.constraints.NotEmpty;
import matser2.istic.mmmback.models.Availability;

import java.util.List;

public abstract class ResourcesDto {
    private Long id;

    @NotEmpty(message = "name is required.")
    private String name;

    private List<WorksiteGetDto> worksites;

    private List<Availability> availabilities ;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public List<WorksiteGetDto> getWorksites() {
        return worksites;
    }

    public void setWorksites(List<WorksiteGetDto> worksites) {
        this.worksites = worksites;
    }

    public List<Availability> getAvailabilities() {
        return availabilities;
    }

    public void setAvailabilities(List<Availability> availabilities) {
        this.availabilities = availabilities;
    }
}
