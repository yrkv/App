package aaa.aaa.entity;

import aaa.aaa.R;
import aaa.aaa.level.Level;

/**
 * Created by Kaleb on 9/15/2017.
 */

public class ArrowHead extends EntityBase {

    private double parentVelocity;
    private float parentDirection;

    public ArrowHead(int parentX, int parentY, double parentXVelocity, double parentYVelocity, Level level) {
        super(parentX,parentY,0,0,0,0,level,false);

        parentVelocity = Math.sqrt(parentXVelocity*parentXVelocity + parentYVelocity*parentYVelocity);

        setImageView(R.drawable.arrowhead);
    }

    public void setParentVelocity(double pxv, double pyv) {
        parentVelocity = Math.sqrt(pxv * pxv + pyv * pyv);
    }

    public double getParentVelocity() {
        return parentVelocity;
    }

    public void setParentDirection(float pdir) {
        parentDirection = pdir;
    }

    public float getParentDirection() {
        return parentDirection;
    }
}
