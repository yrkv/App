package aaa.aaa.entity.puller;
import aaa.aaa.R;
import aaa.aaa.entity.EntityBase;
import aaa.aaa.level.Level;

/**
 * Created by Kaleb on 8/11/2017.
 */

public class DustStorm extends EntityBase{

    public DustStorm(int x, int y, double xVelocity, double yVelocity, float size, Level level) {
        super(x,y,0.5f,xVelocity,yVelocity,size,level);
        setImageView(R.drawable.planet6);
    }

    public DustStorm(int x, int y, float dir, double xVelocity, double yVelocity, float size, Level level) {
        super(x,y,dir,xVelocity,yVelocity,size,level);
        setImageView(R.drawable.planet6);
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
