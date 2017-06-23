package aaa.aaa.entity;

import android.content.Context;
import android.widget.ImageView;

import aaa.aaa.R;
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
    private ImageView imageView;

    public EntityBase(double x, double y, double xVelocity, double yVelocity, Level level) {
        this.x = x;
        this.y = y;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
        this.level = level;
    }

    protected ImageView getImageView() {
        return imageView;
    }

    protected void setImageView(int resource) {
        imageView = new ImageView(level.getContext());
        imageView.setImageResource(resource);
    }

    public void render(float w, float h, int x, int y) {
        imageView.setScrollX(x);
        imageView.setScrollY(y);
        imageView.setScaleX(w);
        imageView.setScaleY(h);
        level.getLayout().addView(imageView);
    }

    public void render() {
        render(0.25f, 0.25f, (int) x, (int) y);
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

    public boolean canMove() {
        return true;
    }

    public boolean move() {
        x += xVelocity;
        y += yVelocity;

        return canMove();
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

        return dir;
    }

    public void update() {}
}
