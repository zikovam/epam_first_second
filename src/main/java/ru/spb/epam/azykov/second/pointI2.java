package ru.spb.epam.azykov.second;

import ru.spb.epam.common.second.I2DPoint;

import java.awt.geom.Point2D;

/**
 * Created by zikovam on 12.05.16.
 */
public class pointI2 extends Point2D implements I2DPoint {
    private double x;
    private double y;

    public pointI2(double x, double y){
        this.x = x;
        this.y = y;
    }

    @Override
    public double getX () {
        return x;
    }

    @Override
    public double getY () {
        return y;
    }

    @Override
    public void setLocation (double x, double y) {
    }
}
