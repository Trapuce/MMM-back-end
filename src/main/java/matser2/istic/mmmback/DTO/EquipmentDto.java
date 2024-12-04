package matser2.istic.mmmback.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class EquipmentDto extends ResourcesDto  {


    @NotEmpty(message = "Equipment type is required.")
    @Size(max = 30, message = "Equipment type must not exceed 30 characters.")
    private String type;

    @NotEmpty(message = "Serial number is required.")
    @Size(max = 50, message = "Serial number must not exceed 50 characters.")
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