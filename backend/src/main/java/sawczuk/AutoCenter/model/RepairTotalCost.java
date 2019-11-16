package sawczuk.AutoCenter.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sawczuk.AutoCenter.util.NumberUtils;

@Getter
@Setter
@NoArgsConstructor
public class RepairTotalCost {
    Integer exploitationTypeValue;
    Double totalCost;

    public RepairTotalCost(Integer exploitationTypeValue, Double totalCost) {
        this.exploitationTypeValue = exploitationTypeValue;
        this.totalCost = NumberUtils.truncateDouble(totalCost);
    }

    public Double getTotalCost() {
        return NumberUtils.truncateDouble(totalCost);
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = NumberUtils.truncateDouble(totalCost);
    }
}
