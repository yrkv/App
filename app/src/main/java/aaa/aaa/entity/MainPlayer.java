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


    public MainPlayer(double x, double y, float dir, double xVelocity, double yVelocity, float size, Level level) {
        super(x, y, dir, xVelocity, yVelocity, size, level);
        getLevel().yOffset = (int) (getY() * getLevel().getZoom());
        getLevel().xOffset = (int) (getX() * getLevel().getZoom());
        getLevel().mainPlayer = this;

        setImageView(R.drawable.player);
    }

    @Override
    public void update() {
        for(Puller puller : getLevel().getPullers()) {
            if(puller.getGravity()) {
                changeXVelocity(puller);
                changeYVelocity(puller);
            }
            if(getDistanceTo(puller) < (puller.getSize() + getSize() / 2)) {
                if (puller.isEarth())
                    getLevel().Win();
                else
                    puller.crash();
            }
        }

        float dir = (float) Math.atan(getYVelocity()/getXVelocity());

        if(getXVelocity() < 0) dir = (float) (dir + Math.PI);

        setDir((float) (dir * 180/Math.PI) + 90);

        getLevel().yOffset = (int) (getY() * getLevel().getZoom());
        getLevel().xOffset = (int) (getX() * getLevel().getZoom());

        move();
    }

}
