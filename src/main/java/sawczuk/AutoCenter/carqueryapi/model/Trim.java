package sawczuk.AutoCenter.carqueryapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Trim {
    Long id;
    Long modelId;
    @JsonProperty("model_id")
    Long trimId;
    @JsonProperty("model_trim")
    String name;
    @JsonProperty("model_year")
    Long year;
    @JsonProperty("model_body")
    String body;
    @JsonProperty("model_engine_position")
    String enginePosition;
    @JsonProperty("model_engine_cc")
    Integer engineCc;
    @JsonProperty("model_engine_cyl")
    Integer engineCyl;
    @JsonProperty("model_engine_type")
    String engineType;
    @JsonProperty("model_engine_valves_per_cyl")
    Integer engineValvesPerCyl;
    @JsonProperty("model_engine_power_ps")
    Integer enginePowerPs;
    @JsonProperty("model_engine_power_rpm")
    Integer enginePowerRpm;
    @JsonProperty("model_engine_torque_nm")
    Integer engineTorqueNm;
    @JsonProperty("model_engine_torque_rpm")
    Integer engineTorqueRpm;
    @JsonProperty("model_engine_bore_mm")
    Double engineBoreMm;
    @JsonProperty("model_engine_stroke_mm")
    Double engineStrokeMm;
    @JsonProperty("model_engine_compression")
    String engineCompression;
    @JsonProperty("model_engine_fuel")
    String engineFuel;
    @JsonProperty("model_top_speed_kph")
    Integer topSpeedKph;
    @JsonProperty("model_0_to_100_kph")
    Double nullTo100Kph;
    @JsonProperty("model_drive")
    String drive;
    @JsonProperty("model_transmission_type")
    String transmissionType;
    @JsonProperty("model_seats")
    Integer seats;
    @JsonProperty("model_doors")
    Integer doors;
    @JsonProperty("model_weight_kg")
    Integer weightKg;
    @JsonProperty("model_length_mm")
    Integer lengthMm;
    @JsonProperty("model_width_mm")
    Integer widthMm;
    @JsonProperty("model_height_mm")
    Integer heightMm;
    @JsonProperty("model_wheelbase_mm")
    Integer wheelbaseMm;
    @JsonProperty("model_lkm_hwy")
    Double lPerKmHighway;
    @JsonProperty("model_lkm_mixed")
    Double lPerKmMixed;
    @JsonProperty("model_lkm_city")
    Double lPerKmCity;
    @JsonProperty("model_fuel_cap_l")
    Integer fuelCapL;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public Long getTrimId() {
        return trimId;
    }

    public void setTrimId(Long trimId) {
        this.trimId = trimId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getEnginePosition() {
        return enginePosition;
    }

    public void setEnginePosition(String enginePosition) {
        this.enginePosition = enginePosition;
    }

    public Integer getEngineCc() {
        return engineCc;
    }

    public void setEngineCc(Integer engineCc) {
        this.engineCc = engineCc;
    }

    public Integer getEngineCyl() {
        return engineCyl;
    }

    public void setEngineCyl(Integer engineCyl) {
        this.engineCyl = engineCyl;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public Integer getEngineValvesPerCyl() {
        return engineValvesPerCyl;
    }

    public void setEngineValvesPerCyl(Integer engineValvesPerCyl) {
        this.engineValvesPerCyl = engineValvesPerCyl;
    }

    public Integer getEnginePowerPs() {
        return enginePowerPs;
    }

    public void setEnginePowerPs(Integer enginePowerPs) {
        this.enginePowerPs = enginePowerPs;
    }

    public Integer getEnginePowerRpm() {
        return enginePowerRpm;
    }

    public void setEnginePowerRpm(Integer enginePowerRpm) {
        this.enginePowerRpm = enginePowerRpm;
    }

    public Integer getEngineTorqueNm() {
        return engineTorqueNm;
    }

    public void setEngineTorqueNm(Integer engineTorqueNm) {
        this.engineTorqueNm = engineTorqueNm;
    }

    public Integer getEngineTorqueRpm() {
        return engineTorqueRpm;
    }

    public void setEngineTorqueRpm(Integer engineTorqueRpm) {
        this.engineTorqueRpm = engineTorqueRpm;
    }

    public Double getEngineBoreMm() {
        return engineBoreMm;
    }

    public void setEngineBoreMm(Double engineBoreMm) {
        this.engineBoreMm = engineBoreMm;
    }

    public Double getEngineStrokeMm() {
        return engineStrokeMm;
    }

    public void setEngineStrokeMm(Double engineStrokeMm) {
        this.engineStrokeMm = engineStrokeMm;
    }

    public String getEngineCompression() {
        return engineCompression;
    }

    public void setEngineCompression(String engineCompression) {
        this.engineCompression = engineCompression;
    }

    public String getEngineFuel() {
        return engineFuel;
    }

    public void setEngineFuel(String engineFuel) {
        this.engineFuel = engineFuel;
    }

    public Integer getTopSpeedKph() {
        return topSpeedKph;
    }

    public void setTopSpeedKph(Integer topSpeedKph) {
        this.topSpeedKph = topSpeedKph;
    }

    public Double getNullTo100Kph() {
        return nullTo100Kph;
    }

    public void setNullTo100Kph(Double nullTo100Kph) {
        this.nullTo100Kph = nullTo100Kph;
    }

    public String getDrive() {
        return drive;
    }

    public void setDrive(String drive) {
        this.drive = drive;
    }

    public String getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public Integer getDoors() {
        return doors;
    }

    public void setDoors(Integer doors) {
        this.doors = doors;
    }

    public Integer getWeightKg() {
        return weightKg;
    }

    public void setWeightKg(Integer weightKg) {
        this.weightKg = weightKg;
    }

    public Integer getLengthMm() {
        return lengthMm;
    }

    public void setLengthMm(Integer lengthMm) {
        this.lengthMm = lengthMm;
    }

    public Integer getWidthMm() {
        return widthMm;
    }

    public void setWidthMm(Integer widthMm) {
        this.widthMm = widthMm;
    }

    public Integer getHeightMm() {
        return heightMm;
    }

    public void setHeightMm(Integer heightMm) {
        this.heightMm = heightMm;
    }

    public Integer getWheelbaseMm() {
        return wheelbaseMm;
    }

    public void setWheelbaseMm(Integer wheelbaseMm) {
        this.wheelbaseMm = wheelbaseMm;
    }

    public Double getlPerKmHighway() {
        return lPerKmHighway;
    }

    public void setlPerKmHighway(Double lPerKmHighway) {
        this.lPerKmHighway = lPerKmHighway;
    }

    public Double getlPerKmMixed() {
        return lPerKmMixed;
    }

    public void setlPerKmMixed(Double lPerKmMixed) {
        this.lPerKmMixed = lPerKmMixed;
    }

    public Double getlPerKmCity() {
        return lPerKmCity;
    }

    public void setlPerKmCity(Double lPerKmCity) {
        this.lPerKmCity = lPerKmCity;
    }

    public Integer getFuelCapL() {
        return fuelCapL;
    }

    public void setFuelCapL(Integer fuelCapL) {
        this.fuelCapL = fuelCapL;
    }

    @Override
    public String toString() {
        return "Trim{" +
                "id=" + id +
                ", modelId=" + modelId +
                ", trimId=" + trimId +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", body='" + body + '\'' +
                ", enginePosition='" + enginePosition + '\'' +
                ", engineCc=" + engineCc +
                ", engineCyl=" + engineCyl +
                ", engineType='" + engineType + '\'' +
                ", engineValvesPerCyl=" + engineValvesPerCyl +
                ", enginePowerPs=" + enginePowerPs +
                ", enginePowerRpm=" + enginePowerRpm +
                ", engineTorqueNm=" + engineTorqueNm +
                ", engineTorqueRpm=" + engineTorqueRpm +
                ", engineBoreMm=" + engineBoreMm +
                ", engineStrokeMm=" + engineStrokeMm +
                ", engineCompression='" + engineCompression + '\'' +
                ", engineFuel='" + engineFuel + '\'' +
                ", topSpeedKph=" + topSpeedKph +
                ", nullTo100Kph=" + nullTo100Kph +
                ", drive='" + drive + '\'' +
                ", transmissionType='" + transmissionType + '\'' +
                ", seats=" + seats +
                ", doors=" + doors +
                ", weightKg=" + weightKg +
                ", lengthMm=" + lengthMm +
                ", widthMm=" + widthMm +
                ", heightMm=" + heightMm +
                ", wheelbaseMm=" + wheelbaseMm +
                ", lPerKmHighway=" + lPerKmHighway +
                ", lPerKmMixed=" + lPerKmMixed +
                ", lPerKmCity=" + lPerKmCity +
                ", fuelCapL=" + fuelCapL +
                '}';
    }
}
