package aaa.aaa.entity;

import aaa.aaa.R;
import aaa.aaa.entity.puller.Puller;

/**
 * Created by USER on 7/22/2017.
 */

public class IndicatorEarth extends Indicator {
    public IndicatorEarth(Puller parent) {
        super(parent);
        setImageView(R.drawable.earth);
    }
}
