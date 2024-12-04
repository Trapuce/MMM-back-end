package matser2.istic.mmmback.models;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;



@Entity
@DiscriminatorValue("EQUIPMENT")
public class Equipment extends Resources {
    @Column
    @NotBlank(message = "Type cannot be blank")
    @Size(max = 30, message = "Type cannot exceed 30 characters")
    private String type;

    @Column(name = "serial_number",  unique = true)
    @NotBlank(message = "Serial number cannot be blank")
    @Size(max = 50, message = "Serial number cannot exceed 50 characters")
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