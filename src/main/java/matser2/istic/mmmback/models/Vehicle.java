package matser2.istic.mmmback.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("VEHICLE")
public class Vehicle extends Resources {
    private String licensePlate;
    private String model;


}
