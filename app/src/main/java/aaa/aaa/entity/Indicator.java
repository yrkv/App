package aaa.aaa.entity;

import android.view.MotionEvent;
import android.view.View;

import aaa.aaa.entity.puller.Puller;
import aaa.aaa.level.Level;

/**
 * Created by USER on 7/22/2017.
 */

public class Indicator extends EntityBase {
    private Puller parent;

    public Indicator(Puller parent) {
        super(-100000, -100000, 0.5f, 0, 0, 40, parent.getLevel());
        this.parent = parent;
    }

    public void update() {
        if ((parent.getGravity() || parent.isEarth()) && !(parent.isOnScreen())) {
            double dir = getLevel().mainPlayer.getDirectionTo(parent);
            setX(getLevel().mainPlayer.getX() + Math.cos(dir) * 500);
            setY(getLevel().mainPlayer.getY() + Math.sin(dir) * 500);
            setDir((float) (getLevel().mainPlayer.getDirectionTo(parent) * 180 / Math.PI + 90));
            display = true;
        } else {
            display = false;
        }
    }

    @Override
    protected boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == 0) {
            if (getParent().getGravity())
                getParent().toggleGravity();
        }
        return true;
    }

    public Puller getParent() {
        return parent;
    }
}
