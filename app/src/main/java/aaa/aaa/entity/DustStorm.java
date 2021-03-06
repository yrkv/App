package aaa.aaa.entity;
import aaa.aaa.R;
import aaa.aaa.entity.EntityBase;
import aaa.aaa.level.Level;

/**
 * Created by Kaleb on 8/11/2017.
 */

public class DustStorm extends EntityBase{

    public DustStorm(double x, double y, double xVelocity, double yVelocity, float size, Level level) {
        super(x,y,0.5f,xVelocity,yVelocity,size,level,true);
        setImageView(R.drawable.duststorm);
    }

    public DustStorm(int x, int y, float dir, double xVelocity, double yVelocity, float size, Level level) {
        super(x,y,dir,xVelocity,yVelocity,size,level,true);
        setImageView(R.drawable.duststorm);
    }

    @Override
    public void previewUpdate() {

    }

    @Override
    public void update() {
        for(EntityBase entity : getLevel().getEntities()) {
            if(getDistanceTo(entity) < getSize() + entity.getSize()) {
                entity.setSlowCoeff(0.983);
            }
            else entity.setSlowCoeff(1);
        }
    }
}
