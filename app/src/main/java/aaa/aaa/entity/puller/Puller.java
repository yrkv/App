package aaa.aaa.entity.puller;

import android.view.MotionEvent;
import android.view.View;

import aaa.aaa.MainActivity;
import aaa.aaa.entity.EntityBase;
import aaa.aaa.level.Level;

/**
 * Created by Kaleb on 6/22/2017.
 */

public class Puller extends EntityBase {

    //CONFIG
    private final double PULLCOEFF;
    //END CONFIG

    private int num;

    private boolean gravity = false;

    public Puller(double x, double y, float dir, double xVelocity, double yVelocity, float size, Level level) {
        super(x, y, dir, xVelocity, yVelocity, size, level);
        PULLCOEFF = size * 100;
        getLevel().getPullers().add(this);
    }

    protected boolean setGravity(boolean gravity) {
        this.gravity = gravity;
        return this.gravity;
    }

    public void crash() {
        getLevel().GameOver();

    }

    protected boolean isPuller() {
        return true;
    }

    public void toggleGravity() {
        gravity = !gravity;

        toggleTint();
    }

    public boolean getGravity() {
        return gravity;
    }

    public double getPULLCOEFF() {
        return PULLCOEFF;
    }

    public boolean isEarth() {
        return false;
    }
}
