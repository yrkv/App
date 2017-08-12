package aaa.aaa;

import aaa.aaa.entity.EntityBase;
import aaa.aaa.entity.MainPlayer;
import aaa.aaa.level.Level;

/**
 * Created by Kaleb on 8/12/2017.
 */

public class Airlock {
    int[] airlockCharges = {1,1};
    MainPlayer player;

    public Airlock(MainPlayer player) {
        this.player = player;
    }

    public int getLeftCharges() {
        return airlockCharges[0];
    }

    public int setLeftCharges(int charges) {
        airlockCharges[0] = charges;
        return charges;
    }

    public int getRightCharges() {
        return airlockCharges[1];
    }

    public int setRightCharges(int charges) {
        airlockCharges[1] = charges;
        return charges;
    }

    public int useLeftCharge() {
        float dir = player.getDir();

        return airlockCharges[0];
    }
}
