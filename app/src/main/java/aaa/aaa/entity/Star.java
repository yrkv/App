package aaa.aaa.entity;

import android.widget.ImageView;

import aaa.aaa.R;
import aaa.aaa.level.Level;

/**
 * Created by Kaleb on 9/4/2017.
 */

public class Star extends EntityBase {
    private boolean unlocked = false;
    private boolean collected;

    public Star(double x, double y, int starType, Level level) {
        super(x, y, 0.5f, 0, 0, 50f, level);
        collected = false;

        setImageView(R.drawable.star);
        imageView.setColorFilter(typeToColor(starType));
    }

    private int typeToColor(int type) {
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
