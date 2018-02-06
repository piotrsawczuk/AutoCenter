package sawczuk.AutoCenter.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "user_car_detail")
public class UserCarDetail {
        @Column(name = "id")
        @GenericGenerator(
                name = "userCarDetailSequenceGenerator",
                strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
                parameters = {
                        @org.hibernate.annotations.Parameter(name = "sequence_name", value = "user_car_detail_seq"),
                        @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                        @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
                }
        )
        @Id
        @GeneratedValue(generator = "userCarDetailSequenceGenerator")
        private Long id;
        @OneToOne(fetch= FetchType.LAZY)
        @JoinColumn(name="user_car_id")
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
