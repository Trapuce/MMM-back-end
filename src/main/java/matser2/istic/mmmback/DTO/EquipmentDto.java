package matser2.istic.mmmback.DTO;

import jakarta.validation.constraints.NotEmpty;

public class EquipmentDto extends ResourcesDto  {


    @NotEmpty(message = "Equipment type is required.")
    private String type;

    @NotEmpty(message = "Serial number is required.")
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