package matser2.istic.mmmback.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;


@Data
@Entity
@DiscriminatorValue("EQUIPMENT")
public class Equipment extends Resources {
    private String type;
    private String serialNumber;


}