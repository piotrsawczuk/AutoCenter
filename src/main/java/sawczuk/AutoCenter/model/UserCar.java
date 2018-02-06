package sawczuk.AutoCenter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user_car")
public class UserCar {
    @Column(name = "id")
    @GenericGenerator(
            name = "userCarSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "user_car_seq"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @Id
    @GeneratedValue(generator = "userCarSequenceGenerator")
    private Long id;
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;
    @Column(name = "car_api_id")
    private Long carApiId;

    @JsonIgnore
    @OneToMany(mappedBy = "userCar")
    private List<FuelEconomy> fuelEconomies;
    @JsonIgnore
    @OneToMany(mappedBy = "userCar")
    private List<Repair> repairs;
    @JsonIgnore
    @OneToOne(fetch=FetchType.LAZY, mappedBy="userCar")
    private UserCarDetail userCarDetail;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getCarApiId() {
        return carApiId;
    }

    public void setCarApiId(Long carApiId) {
        this.carApiId = carApiId;
    }

    public List<FuelEconomy> getFuelEconomies() {
        return fuelEconomies;
    }

    public void setFuelEconomies(List<FuelEconomy> fuelEconomies) {
        this.fuelEconomies = fuelEconomies;
    }

    public List<Repair> getRepairs() {
        return repairs;
    }

    public void setRepairs(List<Repair> repairs) {
        this.repairs = repairs;
    }

    public UserCarDetail getUserCarDetail() {
        return userCarDetail;
    }

    public void setUserCarDetail(UserCarDetail userCarDetail) {
        this.userCarDetail = userCarDetail;
    }
}
