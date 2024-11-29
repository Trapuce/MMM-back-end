package matser2.istic.mmmback.DTO;

import jakarta.validation.constraints.NotEmpty;

public class VehicleDto extends ResourcesDto{

    @NotEmpty(message = "License plate is required.")
    private String licensePlate;

    @NotEmpty(message = "Model is required.")
    private String model;

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
