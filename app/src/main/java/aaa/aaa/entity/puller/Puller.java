package aaa.aaa.entity.puller;

import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

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
    private ArrayList<Puller> attractedTo = new ArrayList<>();

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

    public void addPuller(Puller puller) {
        attractedTo.add(puller);
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

    @Override
    public void update() {
        if (attractedTo.size() > 0) {
            for (Puller puller : attractedTo) {
                changeXVelocity(puller);
                changeYVelocity(puller);
            }

            float dir = (float) Math.atan(getYVelocity() / getXVelocity());

            if (getXVelocity() < 0) dir = (float) (dir + Math.PI);

            setDir((float) (dir * 180 / Math.PI) + 90);

            move();
        }
    }
}
