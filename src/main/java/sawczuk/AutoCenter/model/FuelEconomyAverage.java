package sawczuk.AutoCenter.model;

public class FuelEconomyAverage {
    Integer drivingTypeValue;
    Integer fuelTypeValue;
    Double economyAverage;

    public Integer getDrivingTypeValue() {
        return drivingTypeValue;
    }

    public void setDrivingTypeValue(Integer drivingTypeValue) {
        this.drivingTypeValue = drivingTypeValue;
    }

    public Double getEconomyAverage() {
        return economyAverage;
    }

    public void setEconomyAverage(Double economyAverage) {
        this.economyAverage = economyAverage;
    }

    public Integer getFuelTypeValue() {
        return fuelTypeValue;
    }

    public void setFuelTypeValue(Integer fuelTypeValue) {
        this.fuelTypeValue = fuelTypeValue;
    }

    public FuelEconomyAverage() {
    }

    public FuelEconomyAverage(Integer drivingTypeValue, Integer fuelTypeValue, Double economyAverage) {
        this.drivingTypeValue = drivingTypeValue;
        this.fuelTypeValue = fuelTypeValue;
        this.economyAverage = economyAverage;
    }
}
