package aaa.aaa;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import aaa.aaa.level.LevelData;

public class MainActivity extends AppCompatActivity {

    //CONFIG
    private final double TICKSPEED = 60.0; //in milliseconds
    //END CONFIG
    public boolean playing = false;
    private int selectedLevel = 1;

    private ArrayList<Integer> viewHistory = new ArrayList<>();

    private boolean[] unlocks = {true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true};

    private static final int[][] stages = {
            {0, 10},
            {10, 20},
            {20, 30}
    };

    private int stage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        readData();
        setContentView(R.layout.activity_main);

    }

    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);

        viewHistory.add(layoutResID);
    }

    @Override
    protected void onStop() {
        super.onStop();

//        writeData();
    }

    public void LevelSelect(View v) {
        setContentView(R.layout.stage_select);
        playing = false;
    }

    public void MainMenu(View v) {
        setContentView(R.layout.activity_main);
        playing = false;
    }

    public void GameOver() {
        setContentView(R.layout.gameover);
        playing = false;
    }

    public void Win() {
        if (playing) {
            if (selectedLevel < 30) {
                unlocks[selectedLevel] = true;
                selectedLevel++;
            }
            setContentView(R.layout.win_screen);
            playing = false;
        }
    }

    public void GameLoop(final View view) {
        super.setContentView(R.layout.game_activity);
        final LevelData levelData = new LevelData((RelativeLayout) findViewById(R.id.next_activity), this, this, selectedLevel);

        setContentView(levelData.getLevel().getLayout());
        levelData.getLevel().setBackground((ImageView) findViewById(R.id.background));
        levelData.getLevel().update();

        Runnable myRunnable = new Runnable() {
            double t = System.currentTimeMillis();
            double dt = 1000.0 / TICKSPEED;

            @Override
            public void run() {
                while (playing) {
                    if(System.currentTimeMillis() - t > dt) {
                        t += dt;
                        runOnUiThread(new Runnable() {
                            public void run() {
                                levelData.getLevel().render();
                                levelData.getLevel().update();
                            }
                        });
                    }
                }
            }
        };

        playing = true;
        Thread myThread = new Thread(myRunnable);
        myThread.start();
    }

    public void onBackPressed() {
        playing = false;
        if (viewHistory.size() > 1) {
            viewHistory.remove(viewHistory.size() - 1);
            int lastView = viewHistory.get(viewHistory.size() - 1);
            super.setContentView(lastView);
            if (lastView == R.layout.level_select)
                generateLevelSelectButtons(this.getCurrentFocus());
            if (lastView == R.layout.game_activity) {
                super.setContentView(R.layout.level_select);
                viewHistory.remove(viewHistory.size() - 1);
                generateLevelSelectButtons(this.getCurrentFocus());
            }
        }
    }

    public void callOnBackPressed(View v) {
        onBackPressed();
    }

    public void Restart(View v) {
        playing = false;
        GameLoop(v);
    }

    private void readData() {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        int defaultValue = 1;
        int unlocksNum = sharedPref.getInt(getString(R.string.unlocks_data), defaultValue);

        numToUnlocks(unlocksNum);
    }

    private void writeData() {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putInt(getString(R.string.unlocks_data), unlocksToInt());
        editor.apply();
    }

    private int unlocksToInt() {
        int num = 0;
        for (int i = 0; i < 30; i++) {
            if (unlocks[i])
                num += 1<<i;
        }
        return num;
    }

    private int numToUnlocks(int num) {
        for (int i = 0; i < 30; i++) {
            unlocks[i] = (num & 1) == 1;
            num = num>>1;
        }
        return num;
    }

    public void stage1(View v) {
        stage = 0;
        setContentView(R.layout.level_select);
        generateLevelSelectButtons(v);
    }

    public void stage2(View v) {
        stage = 1;
        setContentView(R.layout.level_select);
        generateLevelSelectButtons(v);
    }

    public void stage3(View v) {
        stage = 2;
        setContentView(R.layout.level_select);
        generateLevelSelectButtons(v);
    }

    private void generateLevelSelectButtons(View v) {
        LinearLayout select = (LinearLayout) findViewById(R.id.levelSelectLayout);

        for (int i = stages[stage][0]; i < stages[stage][1]; i++) {
            Button button = new Button(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams( LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT );
            Resources r = getResources();
            int px = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    20,
                    r.getDisplayMetrics()
            );
            params.setMargins(0, 0, 0, px);
            button.setLayoutParams(params);

            button.setText("" + (i+1));

            final int level = i+1;

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (levelUnlocked(level)) {
                        selectedLevel = level;
                        GameLoop(v);
                    }
                }
            });

            select.addView(button);
        }
    }

    private boolean levelUnlocked(int selectedLevel) {
        return unlocks[selectedLevel-1];
    }

    public void selectLevel1(View v) throws InterruptedException {
        if (levelUnlocked(1)) {
            selectedLevel = 1;
            GameLoop(v);
        }
    }
    public void selectLevel2(View v) throws InterruptedException {
        if (levelUnlocked(2)) {
            selectedLevel = 2;
            GameLoop(v);
        }
    }
    public void selectLevel3(View v) throws InterruptedException {
        if (levelUnlocked(3)) {
            selectedLevel = 3;
            GameLoop(v);
        }
    }
    public void selectLevel4(View v) throws InterruptedException {
        if (levelUnlocked(4)) {
            selectedLevel = 4;
            GameLoop(v);
        }
    }
    public void selectLevel5(View v) throws InterruptedException {
        if (levelUnlocked(5)) {
            selectedLevel = 5;
            GameLoop(v);
        }
    }
    public void selectLevel6(View v) throws InterruptedException {
        if (levelUnlocked(6)) {
            selectedLevel = 6;
            GameLoop(v);
        }
    }
    public void selectLevel7(View v) throws InterruptedException {
        if (levelUnlocked(7)) {
            selectedLevel = 7;
            GameLoop(v);
        }
    }
    public void selectLevel8(View v) throws InterruptedException {
        if (levelUnlocked(8)) {
            selectedLevel = 8;
            GameLoop(v);
        }
    }
    public void selectLevel9(View v) throws InterruptedException {
        if (levelUnlocked(9)) {
            selectedLevel = 9;
            GameLoop(v);
        }
    }
    public void selectLevel10(View v) throws InterruptedException {
        if (levelUnlocked(10)) {
            selectedLevel = 10;
            GameLoop(v);
        }
    }
    public void selectLevel11(View v) throws InterruptedException {
        if (levelUnlocked(11)) {
            selectedLevel = 11;
            GameLoop(v);
        }
    }
    public void selectLevel12(View v) throws InterruptedException {
        if (levelUnlocked(12)) {
            selectedLevel = 12;
            GameLoop(v);
        }
    }
    public void selectLevel13(View v) throws InterruptedException {
        if (levelUnlocked(13)) {
            selectedLevel = 13;
            GameLoop(v);
        }
    }
    public void selectLevel14(View v) throws InterruptedException {
        if (levelUnlocked(14)) {
            selectedLevel = 14;
            GameLoop(v);
        }
    }
    public void selectLevel15(View v) throws InterruptedException {
        if (levelUnlocked(15)) {
            selectedLevel = 15;
            GameLoop(v);
        }
    }
    public void selectLevel16(View v) throws InterruptedException {
        if (levelUnlocked(16)) {
            selectedLevel = 16;
            GameLoop(v);
        }
    }
    public void selectLevel17(View v) throws InterruptedException {
        if (levelUnlocked(17)) {
            selectedLevel = 17;
            GameLoop(v);
        }
    }
    public void selectLevel18(View v) throws InterruptedException {
        if (levelUnlocked(18)) {
            selectedLevel = 18;
            GameLoop(v);
        }
    }
    public void selectLevel19(View v) throws InterruptedException {
        if (levelUnlocked(19)) {
            selectedLevel = 19;
            GameLoop(v);
        }
    }
    public void selectLevel20(View v) throws InterruptedException {
        if (levelUnlocked(20)) {
            selectedLevel = 20;
            GameLoop(v);
        }
    }
    public void selectLevel21(View v) throws InterruptedException {
        if (levelUnlocked(21)) {
            selectedLevel = 21;
            GameLoop(v);
        }
    }
    public void selectLevel22(View v) throws InterruptedException {
        if (levelUnlocked(22)) {
            selectedLevel = 22;
            GameLoop(v);
        }
    }
    public void selectLevel23(View v) throws InterruptedException {
        if (levelUnlocked(23)) {
            selectedLevel = 23;
            GameLoop(v);
        }
    }
    public void selectLevel24(View v) throws InterruptedException {
        if (levelUnlocked(24)) {
            selectedLevel = 24;
            GameLoop(v);
        }
    }
    public void selectLevel25(View v) throws InterruptedException {
        if (levelUnlocked(25)) {
            selectedLevel = 25;
            GameLoop(v);
        }
    }
    public void selectLevel26(View v) throws InterruptedException {
        if (levelUnlocked(26)) {
            selectedLevel = 26;
            GameLoop(v);
        }
    }
    public void selectLevel27(View v) throws InterruptedException {
        if (levelUnlocked(27)) {
            selectedLevel = 27;
            GameLoop(v);
        }
    }
    public void selectLevel28(View v) throws InterruptedException {
        if (levelUnlocked(28)) {
            selectedLevel = 28;
            GameLoop(v);
        }
    }
    public void selectLevel29(View v) throws InterruptedException {
        if (levelUnlocked(29)) {
            selectedLevel = 29;
            GameLoop(v);
        }
    }
    public void selectLevel30(View v) throws InterruptedException {
        if (levelUnlocked(30)) {
            selectedLevel = 30;
            GameLoop(v);
        }
    }

    public void info(View v) {
        setContentView(R.layout.info_activity);
        playing = false;
    }

    public void tutorial(View v) {
        setContentView(R.layout.tutorial);
        playing = false;
    }
}
