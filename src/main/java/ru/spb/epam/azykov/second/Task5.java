package ru.spb.epam.azykov.second;

import ru.spb.epam.common.second.ITestableTask5;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Created by zikovam on 08.05.16.
 */
public class Task5 implements ITestableTask5 {
    @Override
    public double calcResistance (List<IMeasurement> measurements) {
        BigDecimal result = new BigDecimal(0);
        BigDecimal topFraction = new BigDecimal(0);
        BigDecimal botFraction = new BigDecimal(0);
        for (IMeasurement measure :
                measurements) {
            topFraction = topFraction.add(BigDecimal.valueOf(measure.getCurrent()* measure.getVoltage()));
            botFraction = botFraction.add(BigDecimal.valueOf(Math.pow(measure.getCurrent(),2)));
        }
        System.out.println(topFraction);
        System.out.println(botFraction);
        result = topFraction.divide(botFraction, 10, RoundingMode.HALF_UP);
        return result.doubleValue();
    }
}
