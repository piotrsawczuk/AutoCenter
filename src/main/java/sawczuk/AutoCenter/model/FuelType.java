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
@Table(name = "fuel_type")
public class FuelType implements Serializable {
    public enum FuelTypeEnum {
        Petrol(1),
        Diesel(2),
        LPG(3);

        private final int value;
        private static Map map = new HashMap<>();

        FuelTypeEnum(int value) {
            this.value = value;
        }

        static {
            for (FuelTypeEnum fuelType : FuelTypeEnum.values()) {
                map.put(fuelType.value, fuelType);
            }
        }

        public static FuelTypeEnum valueOf(int fuelType) {
            return (FuelTypeEnum) map.get(fuelType);
        }

        public int getValue() {
            return value;
        }
    }


    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "fuel_type_id_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "fuel_type_id_gen", sequenceName = "fuel_type_seq", initialValue = 1, allocationSize = 1)
    private Long id;
    @Column(name = "value")
    private int value;
    @Column(name = "fuel_type")
    private String fuelType;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fuelType")
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

    public void setValue(FuelTypeEnum fuelType) {
        this.value = fuelType.getValue();
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelTypeEnum fuelType) {
        this.fuelType = fuelType.name();
    }

    public List<FuelEconomy> getFuelEconomies() {
        return fuelEconomies;
    }

    public void setFuelEconomies(List<FuelEconomy> fuelEconomies) {
        this.fuelEconomies = fuelEconomies;
    }
}