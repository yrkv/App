package aaa.aaa.entity;

import android.widget.ImageView;

import aaa.aaa.R;
import aaa.aaa.level.Level;

/**
 * Created by Kaleb on 9/4/2017.
 */

public class Star extends EntityBase {
    private boolean enabled = false;
    private boolean collected = false;

    private static final float COLLISION_SIZE = 200;

    public Star(double x, double y, int starType, Level level) {
        super(x, y, 0.5f, 0, 0, 50f, level, true);
        display = false;

        setImageView(R.drawable.star);
        imageView.setColorFilter(0x88000000 + typeToColor(starType));
    }

    public static int typeToColor(int type) {
        switch (type) {
            case 0: return 0xff0000;
            case 1: return 0x00ff00;
            case 2: return 0x0000ff;
            case 3: return 0xffff00;
            case 4: return 0xff00ff;
            case 5: return 0x00ffff;
        }
        return 31415; // because fuck it - error color
    }

    public float getSize() {
        return COLLISION_SIZE;
    }

    public void collide() {
        if (enabled) {
            enabled = false;
            collected = true;
            display = false;

            getLevel().nextStar();
        }
    }

    public void enable() {
        enabled = true;
        display = true;
    }
}
