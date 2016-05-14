package ru.spb.epam.azykov.second;

import ru.spb.epam.common.second.I2DPoint;
import ru.spb.epam.common.second.ITestableTask17;

import java.util.*;

/**
 * Created by zikovam on 13.05.16.
 */
public class Task17 implements ITestableTask17 {
    @Override
    public Set<I2DPoint> analyze (Set<ISegment> segments) {

        Set<I2DPoint> preResult = new HashSet<>();

        Iterator<ISegment> line1 = segments.iterator();
        Iterator<ISegment> line2 = segments.iterator();
        if (line2.hasNext()) {line2.next();}

        double x;
        double y;


        while (line1.hasNext()){
            ISegment firstLine = line1.next();
            while (line2.hasNext()){
                ISegment secondLine = line2.next();

                double point2_x2_MINUS_point2_x1 = secondLine.second().getX() - secondLine.first().getX();
                double point1_y1_MINUS_point2_y1 =  firstLine.first() .getY() - secondLine.first().getY();
                double point2_y2_MINUS_point2_y1 = secondLine.second().getY() - secondLine.first().getY();
                double point1_x1_MINUS_point2_x1 =  firstLine.first() .getX() - secondLine.first().getX();
                double point1_x2_MINUS_point1_x1 =  firstLine.second().getX() - firstLine.first().getX();
                double point1_y2_MINUS_point1_y1 =  firstLine.second().getY() - firstLine.first().getY();

                double UaTop = (point2_x2_MINUS_point2_x1 * point1_y1_MINUS_point2_y1) -
                        (point2_y2_MINUS_point2_y1 * point1_x1_MINUS_point2_x1);
                double UaBot = (point2_y2_MINUS_point2_y1 * point1_x2_MINUS_point1_x1) -
                        (point2_x2_MINUS_point2_x1 * point1_y2_MINUS_point1_y1);//if == 0 then lines are parallel

                double UbTop = (point1_x2_MINUS_point1_x1 * point1_y1_MINUS_point2_y1) -
                        (point1_y2_MINUS_point1_y1 * point1_x1_MINUS_point2_x1);
                double UbBot = (point2_y2_MINUS_point2_y1 * point1_x2_MINUS_point1_x1) -
                        (point2_x2_MINUS_point2_x1 * point1_y2_MINUS_point1_y1);//if == 0 then lines are parallel

                if (UaBot == 0d && UbBot == 0d){
                    continue;
                }

                double Ua = UaTop/UaBot;
                double Ub = UbTop/UbBot;

                if (Ua >= 0d && Ua <= 1d && Ub >= 0d && Ub <= 1d){
                    x = firstLine.first().getX() + Ua * point1_x2_MINUS_point1_x1;
                    y = firstLine.first().getY() + Ua * point1_y2_MINUS_point1_y1;
                    preResult.add(new pointI2(x,y));
                }
            }
        }

        double minX = Double.MAX_VALUE;
        Set<I2DPoint> result = new HashSet<>();
        for (I2DPoint point:
                preResult) {
            if (point.getX() < minX) minX = point.getX();
        }

        for (I2DPoint p:
                preResult) {
            if (p.getX() == minX)
                result.add(p);
        }

        return result;
    }
}