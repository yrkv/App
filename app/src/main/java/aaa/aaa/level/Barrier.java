package aaa.aaa.level;

import aaa.aaa.entity.MainPlayer;

/**
 * Created by USER on 10/2/2017.
 */

public class Barrier {
    private double[] xPoints;
    private double[] yPoints;

    public Barrier(String points) {
        String[] split = points.split(" +");

        xPoints = new double[split.length/2];
        yPoints = new double[split.length/2];

        for (int i = 0; i < split.length; i++) {
            double parsed = Double.parseDouble(split[i]);

            int index = i / 2;

            if (i % 2 == 0)
                xPoints[index] = parsed;
            else
                yPoints[index] = parsed;
        }
    }

    private boolean checkCollision(MainPlayer player) {

        // TODO: actually do stuff

        return false;
    }
}
