package ru.spb.epam.azykov.second;

import ru.spb.epam.common.second.ITestableTask19;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by zikovam on 13.05.16.
 */
public class Task19 implements ITestableTask19 {


    @Override
    public int getNumberOvertaking(Set<ICar> cars, long lengthLap, int numberLaps) {
        int overtaking = 0;
        Iterator<ICar> it1 = cars.iterator();
        Iterator<ICar> it2;
        ICar car1;
        ICar car2;
        while (it1.hasNext()) {
            car1 = it1.next();
            it2 = cars.iterator();
            while (it2.hasNext()) {
                car2 = it2.next();
                if (car1.equals(car2)) continue;
                if ( (car1.getSpeed() > car2.getSpeed()) && (car1.getStartPosition() > car2.getStartPosition()) ) overtaking++;
                overtaking = overtaking + numberOvertakings(car1, car2, numberLaps) ;
            }
        }
        return overtaking;
    }


    public int numberOvertakings (ICar firstCar, ICar secondCar, int numberLaps) {
        double firstV = firstCar.getSpeed();
        double secondV = secondCar.getSpeed();
        double overtakings = ((firstV - secondV) / firstV) * numberLaps;
        if (overtakings > 0) return (int) overtakings;
        else return 0;
    }

}
