package aaa.aaa.entity.puller;

import aaa.aaa.entity.EntityBase;
import aaa.aaa.level.Level;

/**
 * Created by Kaleb on 6/22/2017.
 */

public class Puller extends EntityBase {

    //CONFIG
    private final double PULLCOEFF = 100;
    //END CONFIG

    private boolean gravity = false;

    public Puller(double x, double y, double xVelocity, double yVelocity, Level level) {
        super(x, y, xVelocity, yVelocity, level);
    }

    protected boolean setGravity(boolean gravity) {
        this.gravity = gravity;
        return this.gravity;
    }

    public boolean getGravity() {
        return gravity;
    }

    public double getPULLCOEFF() {
        return PULLCOEFF;
    }
}
