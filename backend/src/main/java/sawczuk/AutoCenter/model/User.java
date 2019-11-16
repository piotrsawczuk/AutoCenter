package sawczuk.AutoCenter.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "app_user")
@Getter
@Setter
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "user_id_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "user_id_gen", sequenceName = "app_user_seq", initialValue = 1, allocationSize = 1)
    private Long id;
    @NotNull
    @Size(min = 2, message = "Username must be at least 2 characters long")
    @Column(name = "username")
    private String username;
    @NotNull
    @Size(min = 6, message = "Password must be at least 6 characters long")
    @JsonIgnore
    @Column(name = "password")
    private String password;
    @NotNull
    @Pattern(regexp=".+@.+\\..+", message = "Invalid email address")
    @Column(name = "email")
    private String email;
    @Column(name = "active")
    private Boolean active;
    @JsonBackReference
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;

    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    private List<Car> cars;
    @JsonBackReference
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    private UserDetail userDetail;

    @JsonIgnore
    @Transient
    private boolean roleAdmin;
}