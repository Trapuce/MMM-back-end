package matser2.istic.mmmback.models;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;



@Entity
@DiscriminatorValue("EQUIPMENT")
public class Equipment extends Resources {
    @Column
    private String type;
    @Column(name = "serial_number")
    private String serialNumber;

    @Override
    public void update(Resources updatedResource) {
        if (updatedResource instanceof Equipment) {
            Equipment equipment = (Equipment) updatedResource;
            this.type = equipment.getType();
            this.serialNumber = equipment.getSerialNumber();
        }
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}