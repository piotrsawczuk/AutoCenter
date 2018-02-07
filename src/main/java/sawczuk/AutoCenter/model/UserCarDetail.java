package sawczuk.AutoCenter.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "user_car_detail")
public class UserCarDetail {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "user_car_detail_id_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "user_car_detail_id_gen", sequenceName = "user_car_detail_seq", initialValue = 1, allocationSize = 1)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_car_id")
    private UserCar userCar;
    @Column(name = "vin")
    private String vin;
    @Column(name = "license_plate_number")
    private String licencePlateNumber;
    @Column(name = "color")
    private String color;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserCar getUserCar() {
        return userCar;
    }

    public void setUserCar(UserCar userCar) {
        this.userCar = userCar;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getLicencePlateNumber() {
        return licencePlateNumber;
    }

    public void setLicencePlateNumber(String licencePlateNumber) {
        this.licencePlateNumber = licencePlateNumber;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
