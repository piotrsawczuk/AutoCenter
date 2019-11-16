package sawczuk.AutoCenter.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "user_detail")
@Getter
@Setter
public class UserDetail {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "user_detail_id_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "user_detail_id_gen", sequenceName = "user_detail_seq", initialValue = 1, allocationSize = 1)
    private Long id;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "surname")
    private String surname;
    @Column(name = "address")
    private String address;
    @Column(name = "city")
    private String city;
    @Pattern(regexp = "([a-zA-Z0-9 -]+)?", message = "Provided zip code is incorrect")
    @Column(name = "zip_code")
    private String zipCode;
    @Pattern(regexp = "([+]?[0-9 -]+)?", message = "Provided phone number is incorrect")
    @Column(name = "phone_number")
    private String phoneNumber;
    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
