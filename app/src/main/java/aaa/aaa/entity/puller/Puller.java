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

    private static double maxDist = 0;
    private static double vX = 0;
    private static double vY = 0;

    private int num;
    private float x;
    private float y;
    private ArrayList<Puller> attractedTo = new ArrayList<>();
    private ArrayList<Puller> attracts = new ArrayList<>();

    private boolean gravity = false;

    public Puller(double x, double y, float dir, double xVelocity, double yVelocity, float size, Level level) {
        super(x, y, dir, xVelocity, yVelocity, size, level, true);
        PULLCOEFF = size * 100;
        getLevel().getPullers().add(this);
    }

    public Puller(double x, double y, double xVelocity, double yVelocity, float size, Level level) {
        super(x, y, 0.5f, xVelocity, yVelocity, size, level, true);
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

    @Override
    protected boolean onTouch(View v, MotionEvent event) {
        if(!getLevel().mainActivity.preview) {
            if (event.getAction() == 0) {
                toggleGravity();
            }
        }
        return (!getLevel().mainActivity.preview);
    }

    public void toggleGravity() {
        gravity = !gravity;

        toggleTint();
    }

    public void addPuller(Puller puller) {
        attractedTo.add(puller);
        puller.addAttracts(this);
    }

    public ArrayList<Puller> getAttractedTo() {
        return attractedTo;
    }

    public void addAttracts(Puller puller) {
        attracts.add(puller);
    }

    public ArrayList<Puller> getAttracts() {
        return attracts;
    }

    @Override
    public boolean getGravity() {
        return gravity;
    }

    public double getPULLCOEFF() {
        return PULLCOEFF;
    }

    @Override
    public void previewUpdate() {

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
