package sawczuk.AutoCenter.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import sawczuk.AutoCenter.util.NumberUtils;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "repair")
@Getter
@Setter
public class Repair {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "repair_id_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "repair_id_gen", sequenceName = "repair_seq", initialValue = 1, allocationSize = 1)
    private Long id;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "date", columnDefinition = "DATE")
    private LocalDate date;
    @DecimalMin(value = "0.0", message = "The value must be positive")
    @Column(name = "mileage")
    private Integer mileage;
    @Column(name = "description")
    private String description;
    @DecimalMin(value = "0.0", message = "The value must be positive")
    @Column(name = "cost", columnDefinition = "NUMERIC(8,2)")
    private Double cost;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exploitation_type")
    private ExploitationType exploitationType;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_car_id")
    private Car car;

    public Repair() {
        this.date = LocalDate.now();
    }

    public void setCost(Double cost) {
        this.cost = NumberUtils.truncateDouble(cost);
    }
}
