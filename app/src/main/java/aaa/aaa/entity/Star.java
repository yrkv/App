package aaa.aaa.entity;

import aaa.aaa.R;
import aaa.aaa.level.Level;

/**
 * Created by Kaleb on 9/4/2017.
 */

public class Star {
    private boolean unlocked = false;
    private boolean collected;

    public Star(double x, double y, float size) {
        collected = false;

        //TODO: This resource is potentially temporary; Also a tint must be added even if it is not kept.
        //TODO: Figure out how to work around the fact that this method is not available.
//        setImageView(R.drawable.star);
    }

    public boolean getUnlocked() {
        return unlocked;
    }

    public boolean setUnlocked(boolean unlocked) {
        return this.unlocked = unlocked;
    }

    public boolean isCollected() {
        return collected;
    }

    public boolean setCollected(boolean collected) {
        return this.collected = collected;
    }
}
