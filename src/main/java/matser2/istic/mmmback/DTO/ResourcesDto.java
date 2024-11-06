package matser2.istic.mmmback.DTO;

import java.util.List;

public  class ResourcesDto {
    private Long id;
    private String name;
    private CompanyGetDto company;
    private List<WorksiteGetDto> worksites;

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

    public CompanyGetDto getCompany() {
        return company;
    }

    public void setCompany(CompanyGetDto company) {
        this.company = company;
    }

    public List<WorksiteGetDto> getWorksites() {
        return worksites;
    }

    public void setWorksites(List<WorksiteGetDto> worksites) {
        this.worksites = worksites;
    }
}
