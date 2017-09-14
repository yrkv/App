package aaa.aaa.level;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import aaa.aaa.MainActivity;
import aaa.aaa.R;
import aaa.aaa.entity.EntityBase;
import aaa.aaa.entity.MainPlayer;
import aaa.aaa.entity.Star;
import aaa.aaa.entity.StarData;
import aaa.aaa.entity.puller.Puller;

/**
 * Created by Kaleb on 6/22/2017.
 */

public class Level {
    private MainActivity mainActivity;
    private ArrayList<EntityBase> entities = new ArrayList<>();
    private ArrayList<Puller> pullers = new ArrayList<>();
    private RelativeLayout layout;
    private Context context;
    public int xOffset = 0;
    public int yOffset = 0;
    private ImageView background;
    private float zoom = (float) Math.PI;
    public MainPlayer mainPlayer;

    // prevents using stars on levels that don't have them.
    // check this whenever you do anything with stars.
    private boolean useStars;

    private Star[] stars;
    private int[][] starData;

    private boolean airlockLeft = false;
    private boolean airlockRight = false;

    private final static double AIRLOCK_POWER = 5.0;

    private boolean canAirlock = false;
    private float x = -1;

    public Level(RelativeLayout layout, Context context, MainActivity mainActivity, int selectedLevel, int starPath) {
        this.mainActivity = mainActivity;
        this.context = context;
        this.layout = layout;

        useStars = selectedLevel > 0 && selectedLevel <= StarData.STAR_CONTAINER.length;

        if (useStars) {
            starData = StarData.STAR_CONTAINER[selectedLevel-1][starPath];

            stars = new Star[starData.length];

            for (int i = 0; i < starData.length; i++) {
                stars[i] = new Star(starData[i][0], starData[i][1], starPath, this);
            }
        }
    }

    public void enableAirlocks() {
        airlockLeft = true;
        airlockRight = true;
    }

    public void enableLeftAirlock() {
        airlockLeft = true;
    }

    public void enableRightAirlock() {
        airlockRight = true;
    }

    //returns the state pause is set to
    public void pause() {
        mainActivity.pause();
    }
    public void unPause() {
        mainActivity.unPause(layout);
    }

    public void setBackground(ImageView background) {
        this.background = background;

        this.background.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    x = event.getX();
                    canAirlock = true;
                }
                if (event.getAction() == MotionEvent.ACTION_MOVE && canAirlock) {
                    float xDiff = event.getX() - x;
                    if (xDiff < -50) {
                        airlockLeft();
                        canAirlock = false;
                    }
                    if (xDiff > 50) {
                        airlockRight();
                        canAirlock = false;
                    }
                }
                return true;
            }
        });
    }

    private void airlockLeft() {
        if (airlockLeft) {
            mainPlayer.setXVelocity(mainPlayer.getXVelocity() +
                    Math.cos((mainPlayer.getDir()+180) * Math.PI / 180) * AIRLOCK_POWER);
            mainPlayer.setYVelocity(mainPlayer.getYVelocity() +
                    Math.sin((mainPlayer.getDir()+180) * Math.PI / 180) * AIRLOCK_POWER);
            airlockLeft = false;
        }
    }

    private void airlockRight() {
        if (airlockRight) {
            mainPlayer.setXVelocity(mainPlayer.getXVelocity() +
                    Math.cos((mainPlayer.getDir()-0) * Math.PI / 180) * AIRLOCK_POWER);
            mainPlayer.setYVelocity(mainPlayer.getYVelocity() +
                    Math.sin((mainPlayer.getDir()-0) * Math.PI / 180) * AIRLOCK_POWER);
            airlockRight = false;
        }
    }

    public ArrayList<Puller> getPullers() {
        return pullers;
    }

    public ArrayList<EntityBase> getEntities() {
        return entities;
    }

    @Nullable
    public Star[] getStars() {
        return stars;
    }

    public void update() {
        for(EntityBase entity : entities) {
            entity.update();
        }
    }

    //TODO: actually set this up lmao
    public void updateGUI() {
//        for(GUI gui : guiComponents) {
//            gui.update();
//        }
    }

    public void render() {
        if (layout.getWidth() != 0) {
            zoom = layout.getWidth() / 1080f;
        }
        for (EntityBase entity : entities) {
            entity.render();
        }

        background.setX((float) (xOffset / -5.0) - 50000);
        background.setY((float) (yOffset / -5.0) - 50000);
    }

    public RelativeLayout getLayout() {
        return layout;
    }

    public void GameOver() {
        mainActivity.GameOver();
    }

    public void Win() {
        mainActivity.Win();
    }

    public Context getContext() {
        return context;
    }

    public float getZoom() {
        return zoom;
    }
}
