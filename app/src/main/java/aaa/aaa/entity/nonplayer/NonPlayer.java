package aaa.aaa.entity.nonplayer;

import aaa.aaa.entity.EntityBase;
import aaa.aaa.level.Level;

/**
 * Created by Kaleb on 6/22/2017.
 */

public class NonPlayer extends EntityBase {
    private boolean gravity = false;

    public NonPlayer(double x, double y, double velocity, Level level) {
        super(x, y, velocity, level);
    }

    protected boolean setGravity(boolean gravity) {
        this.gravity = gravity;
        return this.gravity;
    }

    public boolean getGravity() {
        return gravity;
    }
}
