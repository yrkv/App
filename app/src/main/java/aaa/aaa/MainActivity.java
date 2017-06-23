package aaa.aaa;

import android.content.Entity;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import aaa.aaa.entity.EntityBase;
import aaa.aaa.entity.MainPlayer;
import aaa.aaa.entity.puller.Puller;
import aaa.aaa.level.Level;
import aaa.aaa.level.LevelData;

public class MainActivity extends AppCompatActivity {
    //CONFIG
    private final double TICKSPEED = 10; //in milliseconds
    //END CONFIG
    private boolean playing = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void GameLoop(final View view) throws InterruptedException {
        setContentView(R.layout.next_activity);
        final LevelData levelData = new LevelData((RelativeLayout) findViewById(R.id.next_activity), this);
        levelData.getLevel().getEntities().add(new MainPlayer(10, 10, 1, 1, 0.2f, levelData.getLevel()));


        setContentView(levelData.getLevel().getLayout());

        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {
                long lastTick = System.currentTimeMillis();
                while (playing) {
                    if(System.currentTimeMillis() - lastTick > TICKSPEED) {
                        lastTick = System.currentTimeMillis();
                        runOnUiThread(new Runnable() {
                            public void run() {
                                levelData.getLevel().update();
                                levelData.getLevel().render();
                            }
                        });
                    }
                }
            }
        };

        Thread myThread = new Thread(myRunnable);
        myThread.start();
    }

    public void info(View v) {
        setContentView(R.layout.info_activity);
    }
}
