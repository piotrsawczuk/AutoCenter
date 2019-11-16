package sawczuk.AutoCenter.carqueryapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;
import sawczuk.AutoCenter.util.NumberUtils;

@Getter
@Setter
public class Trim {
    @JsonProperty("model_id")
    private Long modelId;
    @JsonProperty("model_make_id")
    private String makeId;
    @JsonProperty("make_display")
    private String make;
    @JsonProperty("model_name")
    private String model;
    @JsonProperty("model_trim")
    private String trim;
    @JsonProperty("make_country")
    private String country;
    @JsonProperty("model_year")
    private Integer year;
    @JsonProperty("model_body")
    private String body;
    @JsonProperty("model_engine_position")
    private String enginePosition;
    @JsonProperty("model_engine_cc")
    private Integer engineCc;
    @JsonProperty("model_engine_cyl")
    private Integer engineCyl;
    @JsonProperty("model_engine_type")
    private String engineType;
    @JsonProperty("model_engine_valves_per_cyl")
    private Integer engineValvesPerCyl;
    @JsonProperty("model_engine_power_ps")
    private Integer enginePowerPs;
    @JsonProperty("model_engine_power_rpm")
    private Integer enginePowerRpm;
    @JsonProperty("model_engine_torque_nm")
    private Integer engineTorqueNm;
    @JsonProperty("model_engine_torque_rpm")
    private Integer engineTorqueRpm;
    @JsonProperty("model_engine_bore_mm")
    private Double engineBoreMm;
    @JsonProperty("model_engine_stroke_mm")
    private Double engineStrokeMm;
    @JsonProperty("model_engine_compression")
    private String engineCompression;
    @JsonProperty("model_engine_fuel")
    private String engineFuel;
    @JsonProperty("model_top_speed_kph")
    private Integer topSpeedKph;
    @JsonProperty("model_0_to_100_kph")
    private Double nullTo100Kph;
    @JsonProperty("model_drive")
    private String drive;
    @JsonProperty("model_transmission_type")
    private String transmissionType;
    @JsonProperty("model_seats")
    private Integer seats;
    @JsonProperty("model_doors")
    private Integer doors;
    @JsonProperty("model_weight_kg")
    private Integer weightKg;
    @JsonProperty("model_length_mm")
    private Integer lengthMm;
    @JsonProperty("model_width_mm")
    private Integer widthMm;
    @JsonProperty("model_height_mm")
    private Integer heightMm;
    @JsonProperty("model_wheelbase_mm")
    private Integer wheelbaseMm;
    @JsonProperty("model_lkm_hwy")
    private Double lPerKmHighway;
    @JsonProperty("model_lkm_mixed")
    private Double lPerKmMixed;
    @JsonProperty("model_lkm_city")
    private Double lPerKmCity;
    @JsonProperty("model_fuel_cap_l")
    private Integer fuelCapL;

    public String getTrim() {
        if (StringUtils.isEmpty(trim)) {
            String trimName = "";
            trimName = trimName.concat(NumberUtils.truncateEngineCc(engineCc).toString() + "L");
            if (body != null)
                trimName = trimName.concat(" " + body);
            if (enginePowerPs != null)
                trimName = trimName.concat(", " + NumberUtils.psToHpConverter(enginePowerPs) + "HP");
            if (transmissionType != null)
                trimName = trimName.concat(", " + transmissionType + " transmission");
            return trimName;
        } else
            return trim;
    }
}