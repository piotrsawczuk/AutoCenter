package sawczuk.AutoCenter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "driving_type")
public class DrivingType implements Serializable {
    public enum DrivingTypeEnum {
        City(1),
        Highway(2),
        Mixed(3);

        private final int value;
        private static Map map = new HashMap<>();

        DrivingTypeEnum(int value) {
            this.value = value;
        }

        static {
            for (DrivingTypeEnum drivingType : DrivingTypeEnum.values()) {
                map.put(drivingType.value, drivingType);
            }
        }

        public static DrivingTypeEnum valueOf(int fuelEconomyType) {
            return (DrivingTypeEnum) map.get(fuelEconomyType);
        }

        public int getValue() {
            return value;
        }
    }


    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "driving_type_id_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "driving_type_id_gen", sequenceName = "driving_type_seq", initialValue = 1, allocationSize = 1)
    private Long id;
    @Column(name = "value")
    private int value;
    @Column(name = "driving_type")
    private String drivingType;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "drivingType")
    private List<FuelEconomy> fuelEconomies;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(DrivingTypeEnum drivingType) {
        this.value = drivingType.getValue();
    }

    public String getDrivingType() {
        return drivingType;
    }

    public void setDrivingType(DrivingTypeEnum drivingType) {
        this.drivingType = drivingType.name();
    }

    public List<FuelEconomy> getFuelEconomies() {
        return fuelEconomies;
    }

    public void setFuelEconomies(List<FuelEconomy> fuelEconomies) {
        this.fuelEconomies = fuelEconomies;
    }
}
