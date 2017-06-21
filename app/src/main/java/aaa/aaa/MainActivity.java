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
        final char[] chars = new char[100000];
        for (int i = 0; i < chars.length; i++) {
            if (Math.random() < 0.9)
                chars[i] = 'a';
            else
                chars[i] = 'A';
        }

        final TextView textView = (TextView) findViewById(R.id.aaaText);
        Runnable myRunnable = new Runnable() {
            int lastLen = 0;
            @Override
            public void run() {
                long time = System.currentTimeMillis();
                while (true) {
                    int diff = (int) (System.currentTimeMillis() - time);
                    final int len = (diff / 10 > 0) ? diff / 10 : 1;
                    if (len > lastLen) {
                        textView.post(new Runnable() {
                            @Override
                            public void run() {
                                textView.setText(chars, 0, len);
                            }});
                        lastLen = len;
                    }
                }
            }
        };

        Thread myThread = new Thread(myRunnable);
        myThread.start();
    }
}
