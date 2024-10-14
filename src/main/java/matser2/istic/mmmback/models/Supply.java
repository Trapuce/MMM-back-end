package matser2.istic.mmmback.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;


@Data
@Entity
@DiscriminatorValue("SUPPLY")
public class Supply extends Resources {
    private int quantity;
    private String unit;


}