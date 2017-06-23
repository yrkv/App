package aaa.aaa;

import android.content.Entity;
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

public class MainActivity extends AppCompatActivity {
    //CONFIG
    private final double TICKSPEED = 50; //in milliseconds
    //END CONFIG
    private boolean playing = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void GameLoop(final View view) throws InterruptedException {
        setContentView(R.layout.next_activity);
        final Level level = new Level(this);
        level.getEntities().add(new MainPlayer(10, 10, 0, 0, level));
        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {
                long lastTick = System.currentTimeMillis();
                while (playing) {
                    if(System.currentTimeMillis() - lastTick > TICKSPEED) {
                        level.update();
                        level.resetLayout();
                        level.render();
                        setContentView(level.getLayout());
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
