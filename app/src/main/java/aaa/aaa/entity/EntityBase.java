package aaa.aaa.entity;

/**
 * Created by Kaleb on 6/22/2017.
 */

public class EntityBase {
    private double x;
    private double y;
    private double velocity;
    private Level level;

    public EntityBase(double x, double y, double velocity, Level level) {
        this.x = x;
        this.y = y;
        this.velocity = velocity;
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

    public double getVelocity() {
        return velocity;
    }

    protected double setVelocity(double velocity) {
        this.velocity = velocity;
        return this.velocity;
    }

    public Level getLevel() {
        return level;
    }

    public Level setLevel(Level level) {
        this.level = level;
        return level;
    }
}
