package aaa.aaa.entity;

import java.util.ArrayList;

import aaa.aaa.MainActivity;
import aaa.aaa.R;
import aaa.aaa.entity.puller.Puller;
import aaa.aaa.level.Level;

/**
 * Created by Kaleb on 6/22/2017.
 */

public class MainPlayer extends EntityBase {


    public MainPlayer(double x, double y, double xVelocity, double yVelocity, float size, Level level) {
        super(x, y, xVelocity, yVelocity, size, level);

        setImageView(R.drawable.player);
    }

    private double changeXVelocity(Puller puller) {
        double dir = getDirectionTo(puller);
        double r = getDistanceTo(puller);

        double gravForce = puller.getPULLCOEFF() / (r * r);

        return setXVelocity(getXVelocity() + gravForce * Math.cos(dir));
    }

    private double changeYVelocity(Puller puller) {
        double dir = getDirectionTo(puller);
        double r = getDistanceTo(puller);

        double gravForce = puller.getPULLCOEFF() / (r * r);

        return setYVelocity(getYVelocity() + gravForce * Math.sin(dir));
    }

    @Override
    public void update() {
        for(Puller puller : getLevel().getPullers()) {
            if(puller.getGravity()) {
                changeXVelocity(puller);
                changeYVelocity(puller);
            }
        }

        move();
    }

}
