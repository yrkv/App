package aaa.aaa.entity.puller;

import aaa.aaa.R;
import aaa.aaa.level.Level;

/**
 * Created by Kaleb on 6/22/2017.
 */

public class Planet extends Puller {

    public Planet(double x, double y, double xVelocity, double yVelocity, float size, Level level) {
        super(x, y, xVelocity, yVelocity, level);

        setImageView(randomPlanet());
    }

    private int randomPlanet() {
        int num = (int) (Math.random() * 7 + 1);
        switch (num) {
            case 1:return R.drawable.planet1;
            case 2:return R.drawable.planet2;
            case 3:return R.drawable.planet3;
            case 4:return R.drawable.planet4;
            case 5:return R.drawable.planet5;
            case 6:return R.drawable.planet6;
            case 7:return R.drawable.planet7;
        }
        return R.drawable.planet1;
    }
}
