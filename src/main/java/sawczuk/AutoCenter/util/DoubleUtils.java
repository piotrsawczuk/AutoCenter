package sawczuk.AutoCenter.util;

import sawczuk.AutoCenter.model.DrivingType;
import sawczuk.AutoCenter.model.FuelEconomy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class DoubleUtils {

    private DoubleUtils() {
    }

    public static Double truncateDouble(Double number) {
        if (number != null) {
            Double toBeTruncated = number;
            return BigDecimal.valueOf(toBeTruncated)
                    .setScale(2, RoundingMode.HALF_UP)
                    .doubleValue();
        }
        return null;
    }

    public static Double calculateFillUpCost(Double pricePerLitre, Double fuelAmountFilled) {
        if (pricePerLitre != null && fuelAmountFilled != null)
            return truncateDouble(fuelAmountFilled * pricePerLitre);
        else
            return null;
    }

    public static Double calculateFuelEconomy(Double distanceDriven, Double fuelAmountFilled) {
        if (distanceDriven != null && fuelAmountFilled != null)
            return truncateDouble(fuelAmountFilled * 100 / distanceDriven);
        else
            return null;
    }

    public static Double fuelEconomyAverage(List<FuelEconomy> fuelEconomyList) {
        int divide = 0;
        double sum = 0;
        if (fuelEconomyList != null && !fuelEconomyList.isEmpty()) {
            for (FuelEconomy singleEconomy : fuelEconomyList) {
                sum += singleEconomy.getConsumption();
                divide++;
            }
            if (divide <= 0) {
                return null;
            }
            return truncateDouble(sum / divide);
        } else {
            return null;
        }
    }

    public static Double cityFuelEconomyAverage(List<FuelEconomy> fuelEconomyList) {
        int divide = 0;
        double sum = 0;
        if (fuelEconomyList != null && !fuelEconomyList.isEmpty()) {
            for (FuelEconomy singleEconomy : fuelEconomyList) {
                if (singleEconomy.getDrivingType().getValue() == DrivingType.DrivingTypeEnum.City.getValue()) {
                    sum += singleEconomy.getConsumption();
                    divide++;
                }
            }
            if (divide <= 0) {
                return null;
            }
            return truncateDouble(sum / divide);
        } else {
            return null;
        }
    }

    public static Double highwayFuelEconomyAverage(List<FuelEconomy> fuelEconomyList) {
        int divide = 0;
        double sum = 0;
        if (fuelEconomyList != null && !fuelEconomyList.isEmpty()) {
            for (FuelEconomy singleEconomy : fuelEconomyList) {
                if (singleEconomy.getDrivingType().getValue() == DrivingType.DrivingTypeEnum.Highway.getValue()) {
                    sum += singleEconomy.getConsumption();
                    divide++;
                }
            }
            if (divide <= 0) {
                return null;
            }
            return truncateDouble(sum / divide);
        } else {
            return null;
        }
    }

    public static Double mixedFuelEconomyAverage(List<FuelEconomy> fuelEconomyList) {
        int divide = 0;
        double sum = 0;
        if (fuelEconomyList != null && !fuelEconomyList.isEmpty()) {
            for (FuelEconomy singleEconomy : fuelEconomyList) {
                if (singleEconomy.getDrivingType().getValue() == DrivingType.DrivingTypeEnum.Mixed.getValue()) {
                    sum += singleEconomy.getConsumption();
                    divide++;
                }
            }
            if (divide <= 0) {
                return null;
            }
            return truncateDouble(sum / divide);
        } else {
            return null;
        }
    }

}
