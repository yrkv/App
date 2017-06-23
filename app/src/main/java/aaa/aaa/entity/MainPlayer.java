package aaa.aaa.entity;

import java.util.ArrayList;

import aaa.aaa.MainActivity;
import aaa.aaa.entity.puller.Puller;
import aaa.aaa.level.Level;

/**
 * Created by Kaleb on 6/22/2017.
 */

public class MainPlayer extends EntityBase {


    public MainPlayer(double x, double y, double xVelocity, double yVelocity, Level level) {
        super(x, y, xVelocity, yVelocity, level);
    }


    @Override
    public void update() {
        for(Puller puller : MainActivity.getPullers()) {

        }
    }

}
