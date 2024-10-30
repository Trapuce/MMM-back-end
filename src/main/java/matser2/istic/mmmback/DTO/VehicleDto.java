package matser2.istic.mmmback.DTO;

public class VehicleDto extends ResourcesDto{
    private String licensePlate;
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
