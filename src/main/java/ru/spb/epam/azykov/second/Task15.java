package ru.spb.epam.azykov.second;

import ru.spb.epam.common.second.I2DPoint;
import ru.spb.epam.common.second.ITestableTask15;

import java.io.File;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by zikovam on 09.05.16.
 */
public class Task15 implements ITestableTask15 {

    @Override
    public IFileWithLines analyze (final Set<I2DPoint> points, final File output) {
        return new IFileWithLines() {
            @Override
            public File getFile () {
                return output;
            } //не реальзован вывод в файл (%

            @Override
            public Set<ILine> getLines () {
                return calc(points);
            }
        };
    }

    public ILine createline (final Set<I2DPoint> points) {
        return new ILine() {
            @Override
            public Set<I2DPoint> getPoints () {
                return points;
            }
        };
    }

    private Set<ILine> calc (Set<I2DPoint> points){
        Set<ILine> result = new HashSet<>();
        Set<I2DPoint> toTheLine;
        Iterator<I2DPoint> firstPoint = points.iterator();

        Iterator<I2DPoint> secondPoint = points.iterator();
            //secondPoint.next();

        Iterator<I2DPoint> checkpoint = points.iterator();
            //checkpoint.next();
            //checkpoint.next();

        while (firstPoint.hasNext()){
            I2DPoint point1 = firstPoint.next();

            while (secondPoint.hasNext()){
                I2DPoint point2 = secondPoint.next();
                toTheLine = new HashSet<>();
                toTheLine.add(point1);
                toTheLine.add(point2);

                while (checkpoint.hasNext()){
                    I2DPoint point3 = checkpoint.next();
                    if (checkPoint(point1,point2,point3)){
                        toTheLine.add(point3);
                    }
                }

                if (toTheLine.size()>2) result.add(createline(toTheLine));
            }
        }
        return result;
    }

    private boolean checkPoint (I2DPoint firstP, I2DPoint secondP, I2DPoint checkP) {
        if (firstP.getX() == secondP.getX() && firstP.getX() == checkP.getX()) return true;
        if (firstP.getY() == secondP.getY() && firstP.getY() == checkP.getY()) return true;
        if( (checkP.getX() - firstP.getX()) / (secondP.getX() - firstP.getX()) ==
                (checkP.getY() - firstP.getY()) / (secondP.getY() - secondP.getX()) ) return true;
        else return false;
    }
}
