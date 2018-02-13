package sawczuk.AutoCenter.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "user_car_detail")
public class CarDetail {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "user_car_detail_id_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "user_car_detail_id_gen", sequenceName = "user_car_detail_seq", initialValue = 1, allocationSize = 1)
    private Long id;
    @Column(name = "vin")
    private String vin;
    @Column(name = "license_plate_number")
    private String licencePlateNumber;
    @Column(name = "color")
    private String color;
    @Column(name = "image_url")
    private String imageUrl;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_car_id")
    private Car car;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
