package aaa.aaa.level;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import aaa.aaa.MainActivity;
import aaa.aaa.R;
import aaa.aaa.entity.EntityBase;
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

    public Level(RelativeLayout layout, Context context, MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        this.context = context;
        this.layout = layout;
    }

    public void setBackground(ImageView background) {
        this.background = background;
    }

    public ArrayList<Puller> getPullers() {
        return pullers;
    }

    public ArrayList<EntityBase> getEntities() {
        return entities;
    }

    public void update() {
        for(EntityBase entity : entities) {
            entity.update();
        }
    }

    public void render() {
        for (EntityBase entity : entities) {
            entity.render();
        }

        background.setX((float) (xOffset / -10.0) - 50000);
        background.setY((float) (yOffset / -10.0) - 50000);
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
}
