package aaa.aaa.entity;

/**
 * Created by Kaleb on 6/22/2017.
 */

public class Planet extends EntityBase {
    private boolean gravity = false;

    public Planet(double x, double y, double velocity) {
        super(x, y, velocity);
    }

    public void setGravity(boolean gravity) {
        this.gravity = gravity;
    }

    public boolean getGravity() {
        return gravity;
    }
}
