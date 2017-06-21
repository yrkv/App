package aaa.aaa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Aaa(View view) throws InterruptedException {
        setContentView(R.layout.next_activity);
        ((TextView) findViewById(R.id.aaaText)).setText(new char[] {'a', 'b', 'c'}, 0, 3);
    }
}
