package aaa.aaa.entity.puller;

import aaa.aaa.R;
import aaa.aaa.level.Level;

/**
 * Created by Kaleb on 6/22/2017.
 */

public class BlackHole extends Puller {

    public BlackHole(double x, double y, float dir, double xVelocity, double yVelocity, float size, Level level) {
        super(x, y, dir, xVelocity, yVelocity, size, level);
        System.out.println(getGravity());
        setGravity(true);
        System.out.println(getGravity());

        setImageView(R.drawable.blackhole);
    }

    @Override
    public void toggleGravity() {}
}
