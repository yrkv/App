package aaa.aaa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Aaa(final View view) throws InterruptedException {
        setContentView(R.layout.next_activity);
        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    
                }
            }
        };

        Thread myThread = new Thread(myRunnable);
        myThread.start();
    }
}
