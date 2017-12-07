package aaa.aaa.entity;

import aaa.aaa.R;
import aaa.aaa.level.Level;

/**
 * Created by Kaleb on 8/16/2017.
 */

public class Wormhole extends EntityBase {

    private Wormhole pair;
    private boolean paired = false;

    public Wormhole(double x, double y, double xVelocity, double yVelocity, float size, Level level) {
        super(x,y,0.5f,xVelocity,yVelocity,size,level,true);
        setImageView(R.drawable.blackhole);
    }

    public boolean isPaired() {
        return paired;
    }

    //Pairs this one to that and that one to this.
    public void mutualPair(Wormhole other) {
        pair = other;
        other.pairTo(this);
        paired = true;
    }

    public void pairTo(Wormhole other) {
        pair = other;
        paired = true;
    }

    public void removePair() {
        if(isPaired()) {
            pair = null;
            paired = false;
        }
    }

    @Override
    public void previewUpdate() {

    }

    @Override
    public void update() {
        MainPlayer player = getLevel().mainPlayer;
//        if (player.wormholeCollision(this)) {
//            double xDiff = getX() - player.getX();
//            double yDiff = getY() - player.getY();
//
//            player.setX(pair.getX() + xDiff);
//            player.setY(pair.getY() + yDiff);
//        }

        if(getDistanceTo(player) < (getSize() + player.getSize())) {
            double inputDirection = getDirectionTo(player);
            double outputDirection = inputDirection - (Math.PI);

            player.setX(pair.getX()+Math.cos(outputDirection)*(pair.getSize()+player.getSize()));
            player.setY(pair.getY()+Math.sin(outputDirection)*(pair.getSize()+player.getSize()));
        }
    }
}
