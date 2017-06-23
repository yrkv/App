package aaa.aaa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import aaa.aaa.entity.EntityBase;
import aaa.aaa.entity.puller.Puller;

public class MainActivity extends AppCompatActivity {
    //CONFIG
    private final double TICKSPEED = 50; //in milliseconds
    //END CONFIG
    private boolean playing = true;
    private ArrayList<EntityBase> entities = new ArrayList<>();
    private ArrayList<Puller> pullers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void GameLoop(final View view) throws InterruptedException {
        setContentView(R.layout.next_activity);
        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {
                long lastTick = System.currentTimeMillis();
                while (playing) {
                    if(System.currentTimeMillis() - lastTick > TICKSPEED) {
                        for(EntityBase entity : entities) {
                            entity.update();
                        }
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
