package aaa.aaa.entity;

import aaa.aaa.level.Level;

/**
 * Created by Kaleb on 9/4/2017.
 */

public class Star extends EntityBase {
    private boolean unlocked = false;
    private boolean collected;
    private Star previous;
    private Star next;
    private int numInSet;

    public Star(double x, double y, double xVelocity, double yVelocity, float size, Level level, int numInSet, Star previous, Star next) {
        super(x, y, 0.5f, xVelocity, yVelocity, size, level);
        collected = false;
        this.numInSet = numInSet;
        this.previous = previous;
        this.next = next;
    }

    public boolean getUnlocked() {
        return unlocked;
    }

    public boolean setUnlocked() {
        return unlocked = !unlocked;
    }

    public boolean isCollected() {
        return collected;
    }

    public boolean toggleCollected() {
        return (collected = !collected);
    }

    public Star setPrevious(Star previous) {
        return this.previous = previous;
    }

    public Star getPrevious() {
        return previous;
    }

    public Star setNext(Star next) {
        return this.next = next;
    }

    public Star getNext() {
        return next;
    }

    public void update() {
//        if(getDistanceTo(getLevel().mainPlayer) < )
    }
}
