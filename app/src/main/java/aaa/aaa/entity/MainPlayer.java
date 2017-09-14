package aaa.aaa.entity;

import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

import aaa.aaa.MainActivity;
import aaa.aaa.R;
import aaa.aaa.entity.puller.Puller;
import aaa.aaa.level.Level;

/**
 * Created by Kaleb on 6/22/2017.
 */

public class MainPlayer extends EntityBase {

    private float x;
    private float y;

    private static final int[][] collision = {
            {0, 0},
            {10, 50},
            {10, -50},
            {-10, 50},
            {-10, -50}
    };

    public MainPlayer(double x, double y, double xVelocity, double yVelocity, Level level) {
        super(x, y, 0.5f, xVelocity, yVelocity, 50, level);
        getLevel().yOffset = (int) (getY() * getLevel().getZoom());
        getLevel().xOffset = (int) (getX() * getLevel().getZoom());
        getLevel().mainPlayer = this;


        setImageView(R.drawable.player);
    }

    @Override
    protected boolean onTouch(View v, MotionEvent event) {
        if(getLevel().mainActivity.preview) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                x = event.getX();
                y = event.getY();
            }
            if (event.getAction() == MotionEvent.ACTION_MOVE) {
                if (event.getX() != x) {
                    getLevel().xOffset -= event.getX() - x;
                    x = event.getX();
                }
                if (event.getY() != y) {
                    getLevel().yOffset -= event.getY() - y;
                    y = event.getY();
                }
            }
        }
        return true;
    }

    @Override
    public void update() {
        for(Puller puller : getLevel().getPullers()) {
            if(puller.getGravity()) {
                changeXVelocity(puller);
                changeYVelocity(puller);
            }
            if(checkCollisionWith(puller)) {
                if (puller.isEarth())
                    getLevel().Win();
                else
                    puller.crash();
            }
        }

        Star[] stars = getLevel().getStars();

        if (stars != null) {
            for (Star star : stars) {
                if (checkCollisionWith(star)) {
                    star.collide();
                }
            }
        }

        float dir = (float) Math.atan(getYVelocity()/getXVelocity());

        if (getXVelocity() < 0) dir = (float) (dir + Math.PI);

        setDir((float) (dir * 180 / Math.PI) + 90);

        getLevel().yOffset = (int) (getY() * getLevel().getZoom());
        getLevel().xOffset = (int) (getX() * getLevel().getZoom());

        move();
    }

    public boolean checkCollisionWith(EntityBase entityBase) {
        // get location of the entity and translate it by the player's location
        double x = entityBase.getX() - getX();
        double y = entityBase.getY() - getY();
        float r  = entityBase.getSize();

        double rot = -(getDir()) * Math.PI / 180;

        // rotation matrix to translate the entity's location by the player's rotation
        double newX = x * Math.cos(rot) - y * Math.sin(rot);
        double newY = x * Math.sin(rot) + y * Math.cos(rot);

        // check if any of the collision points are within the entity's circle
        for (int i = 0; i < collision.length; i++) {
            double dist = Math.sqrt(Math.pow(newX - collision[i][0], 2) + Math.pow(newY - collision[i][1], 2));

            if (dist < r)
                return true;
        }

        return false;
    }

    public boolean wormholeCollision(EntityBase entityBase) {
        // get location of the entity and translate it by the player's location
        double x = entityBase.getX() - getX();
        double y = entityBase.getY() - getY();
        float r  = entityBase.getSize();

        double rot = getDir();

        // rotation matrix to translate the entity's location by the player's rotation
        x =  x * Math.cos(rot) + y * Math.sin(rot);
        y = -x * Math.sin(rot) + y * Math.cos(rot);

        // check if any of the collision points are within the entity's circle
        for (int i = 0; i < collision.length; i++) {
            double dist = Math.sqrt(Math.pow(getX() - x, 2) + Math.pow(getY() - y, 2));

            if (dist > r)
                return false;
        }

        return true;
    }
}
