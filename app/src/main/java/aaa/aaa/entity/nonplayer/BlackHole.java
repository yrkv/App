package aaa.aaa.entity.nonplayer;

import aaa.aaa.entity.EntityBase;
import aaa.aaa.level.Level;

/**
 * Created by Kaleb on 6/22/2017.
 */

public class BlackHole extends NonPlayer {

    public BlackHole(double x, double y, double velocity, Level level) {
        super(x, y, velocity, level);
        setGravity(true);
    }

    @Override
    protected boolean setGravity(boolean gravity) {
        return true;
    }
}
