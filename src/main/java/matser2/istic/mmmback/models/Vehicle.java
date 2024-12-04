package matser2.istic.mmmback.models;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@DiscriminatorValue("VEHICLE")
public class Vehicle extends Resources {

    @Column(name = "license_plate",  unique = true)
    @NotBlank(message = "License plate cannot be blank")
    @Size(min = 5, max = 15, message = "License plate must be between 5 and 15 characters")
    private String licensePlate;

    @Column(name = "model")
    @NotBlank(message = "Model cannot be blank")
    @Size(max = 50, message = "Model name cannot exceed 50 characters")
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
