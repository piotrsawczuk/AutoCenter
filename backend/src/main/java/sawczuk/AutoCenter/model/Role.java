package sawczuk.AutoCenter.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "app_role")
@Getter
@Setter
public class Role implements GrantedAuthority {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "role_id_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "role_id_gen", sequenceName = "app_role_seq", initialValue = 1, allocationSize = 1)
    private Long id;
    @NotNull
    @Column(name = "role_name")
    private String name;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "roles")
    private Set<User> users;

    @Override
    public String getAuthority() {
        return name;
    }
}
