package sawczuk.AutoCenter.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "user_car_detail")
@Getter
@Setter
public class CarDetail {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "user_car_detail_id_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "user_car_detail_id_gen", sequenceName = "user_car_detail_seq", initialValue = 1, allocationSize = 1)
    private Long id;
    @Pattern(regexp = "|[a-zA-Z0-9]{17}+", message = "Provided VIN is incorrect. VIN must be 17 characters long and contain only digits and letters")
    @Column(name = "vin")
    private String vin;
    @Pattern(regexp = "|[a-zA-Z0-9 ]+", message = "Provided license plate number is incorrect")
    @Column(name = "licence_plate_number")
    private String licencePlateNumber;
    @Column(name = "color")
    private String color;
    @Column(name = "image_url")
    private String imageUrl;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_car_id")
    private Car car;
}