package sawczuk.AutoCenter.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "user_car")
@Getter
@Setter
public class Car {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "user_car_id_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "user_car_id_gen", sequenceName = "user_car_seq", initialValue = 1, allocationSize = 1)
    private Long id;
    @NotNull
    @Column(name = "car_api_id")
    private Long carApiId;
    @NotNull
    @Column(name = "car_name")
    private String carName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "car")
    private CarDetail carDetail;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "car")
    private List<FuelConsumption> fuelEconomies;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "car")
    private List<Repair> repairs;
}
