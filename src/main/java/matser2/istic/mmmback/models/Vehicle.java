package matser2.istic.mmmback.models;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("VEHICLE")
public class Vehicle extends Resources {

    @Column(name = "license_plate")
    private String licensePlate;

    @Column(name = "model")
    private String model;
    @Override
    public void update(Resources updatedResource) {
        if (updatedResource instanceof Vehicle) {
            Vehicle vehicle = (Vehicle) updatedResource;
            this.licensePlate = vehicle.getLicensePlate();
            this.model = vehicle.getModel();
        }
    }
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
