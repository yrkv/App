package aaa.aaa.entity;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import aaa.aaa.R;
import aaa.aaa.entity.puller.Puller;
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
    protected ImageView imageView;
    private boolean tint = false;
    private float size;

    public EntityBase(double x, double y, double xVelocity, double yVelocity, float size, Level level) {
        this.x = x;
        this.y = y;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
        this.level = level;
        this.size = size;

        level.getEntities().add(this);
    }

    protected ImageView getImageView() {
        return imageView;
    }

    protected void setImageView(int resource) {
        imageView = new ImageView(level.getContext());
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (isPuller() && event.getAction() == 0) {
                    ((Puller) getThis()).toggleGravity();
                }
                return true;
            }
        });
        render();
        imageView.setImageResource(resource);
        level.getLayout().addView(imageView);
    }

    protected void toggleTint() {
        tint = !tint;
        if (tint)
            imageView.setColorFilter(Color.argb(100, 100, 255, 255));
        else
            imageView.clearColorFilter();
    }


    public void render(float w, float h, int x, int y) {
        imageView.setTranslationX(x - imageView.getWidth() / 2 - level.xOffset + level.getLayout().getWidth() / 2);
        imageView.setTranslationY(y - imageView.getHeight() / 2 - level.yOffset + level.getLayout().getHeight() / 2);
        imageView.setScaleX(w);
        imageView.setScaleY(h);
    }

    public void render() {
        render(size, size, (int) x, (int) y);
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
        if(xdiff < 0) dir += Math.PI;

        return dir;
    }

    private EntityBase getThis() {
        return this;
    }

    protected boolean isPuller() {
        return false;
    }

    public void update() {}
}
