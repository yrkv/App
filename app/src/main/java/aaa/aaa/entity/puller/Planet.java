package aaa.aaa.entity.puller;

import aaa.aaa.R;
import aaa.aaa.level.Level;

/**
 * Created by Kaleb on 6/22/2017.
 */

public class Planet extends Puller {

    public Planet(double x, double y, double xVelocity, double yVelocity, Level level) {
        super(x, y, xVelocity, yVelocity, level);

        setImageView(R.drawable.planet1);
    }
}
