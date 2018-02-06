package sawczuk.AutoCenter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Column(name = "id")
    @GenericGenerator(
            name = "fuelTypeSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "fuel_type_seq"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @Id
    @GeneratedValue(generator = "fuelTypeSequenceGenerator")
    private Long id;
    @Column(name = "value")
    private int value;
    @Column(name = "fuel_type")
    private String fuelType;

    @JsonIgnore
    @OneToMany(mappedBy = "fuelType")
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