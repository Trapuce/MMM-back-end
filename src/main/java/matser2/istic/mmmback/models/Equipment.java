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