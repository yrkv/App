package aaa.aaa.entity;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import aaa.aaa.entity.puller.Puller;
import aaa.aaa.level.Level;

/**
 * Created by Kaleb on 6/22/2017.
 */

public class EntityBase {
    private double x;
    private double y;
    private float dir;
    private double xVelocity;
    private double yVelocity;
    private double slowCoeff;
    private Level level;
    protected ImageView imageView;
    private boolean tint = false;
    private float size;
    private boolean render = false;
    protected boolean display = true;

    public EntityBase(double x, double y, float dir, double xVelocity, double yVelocity, float size, Level level) {
        this.x = x;
        this.y = y;
        this.dir = dir;
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
                if (isIndicator() && event.getAction() == 0) {
                    if (((Indicator) getThis()).getParent().getGravity())
                    ((Indicator) getThis()).getParent().toggleGravity();
                }
                return true;
            }
        });
        render();
        imageView.setImageResource(resource);
        render = true;
    }

    public boolean isOnScreen() {
        double xPos = x * level.getZoom() - imageView.getWidth() / 2 - level.xOffset + level.getLayout().getWidth() / 2;
        double yPos = y * level.getZoom() - imageView.getHeight() / 2 - level.yOffset + level.getLayout().getHeight() / 2;
        return (xPos > -100 && yPos > -100 && xPos < level.getLayout().getWidth() + 100 && yPos < level.getLayout().getHeight() + 100);
    }

    protected void toggleTint() {
        tint = !tint;
        if (tint)
            imageView.setColorFilter(Color.argb(100, 100, 255, 255));
        else
            imageView.clearColorFilter();
    }

    private void render(float w, float h, float dir, int x, int y) {
        if (display) {
            imageView.setTranslationX(x - imageView.getWidth() / 2 - level.xOffset + level.getLayout().getWidth() / 2);
            imageView.setTranslationY(y - imageView.getHeight() / 2 - level.yOffset + level.getLayout().getHeight() / 2);
        } else {
            imageView.setTranslationX(-10000f);
            imageView.setTranslationY(-10000f);
        }
        imageView.setRotation(dir);
        imageView.setLayoutParams(new RelativeLayout.LayoutParams((int) (w*2), (int) (h*2)));
        if (render && level.getZoom() != (float) Math.PI) {
            level.getLayout().addView(imageView);
            render = false;
        }
    }

    public void setDisplay(boolean display) {
        this.display = display;
    }

    public void render() {
        render(size * level.getZoom(), size * level.getZoom(), dir, (int) (x * level.getZoom()), (int) (y * level.getZoom()));
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

    public float getDir() {
        return dir;
    }

    protected float setDir(float dir) {
        this.dir = dir;
        return this.dir;
    }

    public double getXVelocity() {
        return xVelocity;
    }

    protected double setXVelocity(double xVelocity) {
        this.xVelocity = xVelocity * slowCoeff;
        return this.xVelocity;
    }

    public double getYVelocity() {
        return yVelocity;
    }

    protected double setYVelocity(double yVelocity) {
        this.yVelocity = yVelocity * slowCoeff;
        return this.yVelocity;
    }

    public double getSlowCoeff() {
        return slowCoeff;
    }

    public double setSlowCoeff(double slowCoeff) {
        this.slowCoeff = slowCoeff;
        return slowCoeff;
    }

    public float getSize() {
        return size;
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

    protected double changeXVelocity(Puller puller) {
        double dir = getDirectionTo(puller);
        double r = getDistanceTo(puller);

        double gravForce = puller.getPULLCOEFF() / (r * r);

        return setXVelocity(getXVelocity() + gravForce * Math.cos(dir));
    }

    protected double changeYVelocity(Puller puller) {
        double dir = getDirectionTo(puller);
        double r = getDistanceTo(puller);

        double gravForce = puller.getPULLCOEFF() / (r * r);

        return setYVelocity(getYVelocity() + gravForce * Math.sin(dir));
    }

    public boolean move() {
        if(canMove()) {
            x += xVelocity;
            y += yVelocity;
        }

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

    protected boolean isIndicator() {
        return false;
    }

    public void update() {}
}
