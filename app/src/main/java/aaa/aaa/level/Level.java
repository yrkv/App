package aaa.aaa.level;

import android.content.Context;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import aaa.aaa.entity.EntityBase;
import aaa.aaa.entity.puller.Puller;

/**
 * Created by Kaleb on 6/22/2017.
 */

public class Level {
    private ArrayList<EntityBase> entities = new ArrayList<>();
    private ArrayList<Puller> pullers = new ArrayList<>();
    private RelativeLayout layout;
    private Context context;

    public Level(Context context) {
        this.context = context;
        layout = new RelativeLayout(context);
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
    }

    public void resetLayout() {
        layout = new RelativeLayout(context);
    }

    public RelativeLayout getLayout() {
        return layout;
    }

    public Context getContext() {
        return context;
    }
}
