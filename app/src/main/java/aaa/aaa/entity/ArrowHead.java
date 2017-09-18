package aaa.aaa.entity;

import aaa.aaa.R;
import aaa.aaa.level.Level;

/**
 * Created by Kaleb on 9/15/2017.
 */

public class ArrowHead extends EntityBase {
    public ArrowHead(Level level) {
        super(0,0,0,0,0,0,level,false);

        setImageView(R.drawable.arrowhead);
    }
}
