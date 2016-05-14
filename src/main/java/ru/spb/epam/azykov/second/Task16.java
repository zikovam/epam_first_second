package ru.spb.epam.azykov.second;

import ru.spb.epam.common.second.I2DPoint;
import ru.spb.epam.common.second.ITestableTask16;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Created by zikovam on 12.05.16.
 */

public class Task16 implements ITestableTask16 {
    @Override
    public IFileWithPoints analyze (final I2DPoint center, final int radius, final File output) {

        final SortedMap<I2DPoint, Double> result = calculate(center, radius, output);

        return new IFileWithPoints() {
            @Override
            public File getFile () {
                return output;
            }

            @Override
            public SortedMap<I2DPoint, Double> getPoints () {
                return result;
            }
        };
    }

    private SortedMap<I2DPoint, Double> calculate (final I2DPoint center, int radius, File output) {

        Comparator<I2DPoint> comp = new Comparator<I2DPoint>() {
            @Override
            public int compare (I2DPoint o1, I2DPoint o2) {
                Double dist1 = calcDistance(o1.getX(), o1.getY(), center);
                Double dist2 = calcDistance(o2.getX(), o2.getY(), center);
                if (dist1>=dist2){return 1;}
                if (dist1<dist2) {return -1;}
                return 0;
            }
        };

        final SortedMap<I2DPoint, Double> points = new TreeMap<>(comp);

        int xMin = -(int) Math.abs(center.getX() - radius);
        int xMax = (int) Math.abs(center.getX() + radius);
        int yMin = -(int) Math.abs(center.getY() - radius);
        int yMax = (int) Math.abs(center.getY() + radius);

        for (int i = xMin; i <= xMax; i++) {
            for (int j = yMin; j <= yMax; j++) {
                if (Math.pow(i - center.getX(), 2) + Math.pow(j - center.getY(), 2) < Math.pow(radius, 2)) {
                    I2DPoint point = createPoint(i, j);
                    double dist = calcDistance(i,j,center);
                    points.put(point, dist);
                }
            }
        }

        if (output.exists()) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(output))) {
                for (I2DPoint num :
                        points.keySet()) {
                    Double distance = points.get(num);
                    bw.write(num + " " + distance);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return points;
    }

    private I2DPoint createPoint (final double x, final double y) {
        return new pointI2(x,y);
    }

    private Double calcDistance (double x, double y, I2DPoint center) {
        Double resX = Math.pow(center.getX() - x, 2);
        Double resY = Math.pow(center.getY() - y, 2);
        return Math.sqrt(resX + resY);
    }
}


