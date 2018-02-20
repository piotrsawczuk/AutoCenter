package sawczuk.AutoCenter.util;

import sawczuk.AutoCenter.model.DrivingType;
import sawczuk.AutoCenter.model.FuelEconomy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class NumberUtils {

    private NumberUtils() {
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

    public static Double truncateEngineCc(Integer engineCc) {
        if (engineCc != null) {
            Double toBeTruncated = engineCc.doubleValue();
            return BigDecimal.valueOf(toBeTruncated / 1000)
                    .setScale(1, RoundingMode.HALF_UP)
                    .doubleValue();
        }
        return null;
    }

    public static Integer psToHpConverter(Integer ps) {
        if (ps != null) {
            Integer toBeTruncated = ps;
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

    public static Double calculateFuelEconomy(Double distanceDriven, Double fuelAmountFilled) {
        if (distanceDriven != null && distanceDriven > 0 && fuelAmountFilled != null && fuelAmountFilled > 0)
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
