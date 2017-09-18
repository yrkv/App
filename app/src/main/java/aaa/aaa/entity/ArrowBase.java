package aaa.aaa.entity;

import aaa.aaa.R;
import aaa.aaa.level.Level;

/**
 * Created by Kaleb on 9/17/2017.
 */

public class ArrowBase extends EntityBase {
    public ArrowBase(Level level) {
        super(0,0,0,0,0,0,level,false);

        setImageView(R.drawable.arrowline);
    }
}
