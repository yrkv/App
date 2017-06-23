package aaa.aaa.entity;

import aaa.aaa.level.Level;

/**
 * Created by Kaleb on 6/22/2017.
 */

public class EntityBase {
    private double x;
    private double y;
    private double xVelocity;
    private double yVelocity;
    private Level level;

    public EntityBase(double x, double y, double xVelocity, double yVelocity, Level level) {
        this.x = x;
        this.y = y;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
        this.level = level;
    }

    public double getX() {
        return x;
    }

    protected double setX(double x) {
        this.x = x;
        return this.x;
    }

    public double getY() {
        return y;
    }

    protected double setY(double y) {
        this.y = y;
        return this.y;
    }

    public double getXVelocity() {
        return xVelocity;
    }

    protected double setXVelocity(double xVelocity) {
        this.xVelocity = xVelocity;
        return this.xVelocity;
    }

    public double getYVelocity() {
        return yVelocity;
    }

    protected double setYVelocity(double yVelocity) {
        this.yVelocity = yVelocity;
        return this.yVelocity;
    }

    public Level getLevel() {
        return level;
    }

    public Level setLevel(Level level) {
        this.level = level;
        return level;
    }

    protected double getDistanceTo(EntityBase other) {
        double otherX = other.getX();
        double otherY = other.getY();

        return Math.sqrt((otherX - getX()) * (otherX - getX()) + (otherY - getY()) * (otherY - getY()));
    }

    protected double getDirectionTo(EntityBase other) {
        double otherX = other.getX();
        double otherY = other.getY();

        double xdiff = otherX - getX();
        double ydiff = otherY - getY();

        double dir = Math.atan(ydiff/xdiff);
    }

    public void update() {}
}
