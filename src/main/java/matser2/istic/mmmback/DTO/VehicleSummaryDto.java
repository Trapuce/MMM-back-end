package matser2.istic.mmmback.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class VehicleSummaryDto extends ResourcesSimpleDto {

    @NotEmpty(message = "License plate is required.")
    @Size(min = 5, max = 15, message = "License plate must be between 5 and 15 characters.")
    private String licensePlate;

    @NotEmpty(message = "Model is required.")
    @Size(max = 50, message = "Model must not exceed 50 characters.")
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
