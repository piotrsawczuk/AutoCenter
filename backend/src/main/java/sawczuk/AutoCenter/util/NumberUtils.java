package sawczuk.AutoCenter.util;

import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;

@NoArgsConstructor
public class NumberUtils {

    public static Double truncateDouble(Double number) {
        if (number != null) {
            return BigDecimal.valueOf(number)
                    .setScale(2, RoundingMode.HALF_UP)
                    .doubleValue();
        }
        return null;
    }

    public static Double truncateEngineCc(Integer engineCc) {
        if (engineCc != null) {
            double toBeTruncated = engineCc.doubleValue();
            return BigDecimal.valueOf(toBeTruncated / 1000)
                    .setScale(1, RoundingMode.HALF_UP)
                    .doubleValue();
        }
        return null;
    }

    public static Integer psToHpConverter(Integer ps) {
        if (ps != null) {
            return BigDecimal.valueOf(ps * 0.98592325737265)
                    .intValue();
        }
        return null;
    }

    public static Double calculateFillUpCost(Double pricePerLitre, Double fuelAmountFilled) {
        if (pricePerLitre != null && pricePerLitre > 0 && fuelAmountFilled != null && fuelAmountFilled > 0)
            return truncateDouble(fuelAmountFilled * pricePerLitre);
        else
            return null;
    }

    public static Double calculateFuelConsumption(Double distanceDriven, Double fuelAmountFilled) {
        if (distanceDriven != null && distanceDriven > 0 && fuelAmountFilled != null && fuelAmountFilled > 0)
            return truncateDouble(fuelAmountFilled * 100 / distanceDriven);
        else
            return null;
    }

}
