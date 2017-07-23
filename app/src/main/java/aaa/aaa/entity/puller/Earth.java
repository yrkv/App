package aaa.aaa.entity.puller;

import aaa.aaa.R;
import aaa.aaa.entity.IndicatorEarth;
import aaa.aaa.level.Level;

/**
 * Created by Yegor on 6/24/2017.
 */

public class Earth extends Puller {
    public Earth(double x, double y, float dir, double xVelocity, double yVelocity, float size, Level level) {
        super(x, y, dir, xVelocity, yVelocity, size, level);
        new IndicatorEarth(this);

        setImageView(R.drawable.earth);
    }

    public boolean isEarth() {
        return true;
    }
}
