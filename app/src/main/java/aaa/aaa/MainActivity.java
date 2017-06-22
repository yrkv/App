package aaa.aaa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private boolean playing = true;

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
                while (playing) {

                }
            }
        };

        Thread myThread = new Thread(myRunnable);
        myThread.start();
    }

    public void Exit() {
        playing = false;
    }
}
