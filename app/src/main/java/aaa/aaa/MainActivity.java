package aaa.aaa;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import java.util.ArrayList;

import aaa.aaa.level.LevelData;

public class MainActivity extends AppCompatActivity {

    //CONFIG
    private final double TICKSPEED = 60.0; //in milliseconds
    //END CONFIG
    public boolean playing = false;
    public boolean pause = false;
    private int selectedLevel = 1;

    private ArrayList<Integer> viewHistory = new ArrayList<>();

    private boolean[] unlocks = new boolean[19];
    private boolean[] completed = new boolean[19];
    private boolean[] shownInfoScreens = new boolean[19];

    private static final int[][] stages = {
            {0, 9},
            {9, 15},
            {15, 19}
    };

    private int stage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        readData();
        unlocks[0] = true;
        setContentView(R.layout.activity_main);

        selectedLevel = -1;
        gameLoop();
    }

    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);

        viewHistory.add(layoutResID);
    }

    @Override
    protected void onStop() {
        super.onStop();

        writeData();
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

    public void pause() {
        pause = true;
    }
    public void unPause(View v) {
        pause = false;
    }

    public boolean isPause() {
        return pause;
    }

    public void Win() {
        if (playing) {
            completed[selectedLevel-1] = true;
            if (selectedLevel < completed.length) { // TODO fix to # of levels
                unlocks[selectedLevel] = true;
                selectedLevel++;
                if (selectedLevel < completed.length)
                    unlocks[selectedLevel] = true;
            }
            setContentView(R.layout.win_screen);
            playing = false;
        }
    }

    public void GameLoop(final View view) {
        gameLoop();
    }

    public void gameLoop() {
        if (selectedLevel != -1 && !shownInfoScreens[selectedLevel-1] && levelToInfoScreen(selectedLevel) != -1) {
            shownInfoScreens[selectedLevel-1] = true;
            setContentView(levelToInfoScreen(selectedLevel));
        } else {
            final LevelData levelData;
            if (selectedLevel == -1) {
                setContentView(R.layout.activity_main);
                levelData = new LevelData((RelativeLayout) findViewById(R.id.activity_main), this, this, selectedLevel);
            } else {
                setContentView(R.layout.game_activity);
                levelData = new LevelData((RelativeLayout) findViewById(R.id.next_activity), this, this, selectedLevel);
            }

            levelData.getLevel().setBackground((ImageView) findViewById(R.id.background));
            levelData.getLevel().update();

            Runnable myRunnable = new Runnable() {
                double t = System.currentTimeMillis();
                double dt = 1000.0 / TICKSPEED;

                @Override
                public void run() {
                    while (playing) {
                        if (System.currentTimeMillis() - t > dt) {
                            t += dt;
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    levelData.getLevel().render();
                                    if(!pause) {
                                        levelData.getLevel().update();
                                    }
                                    levelData.getLevel().updateGUI();
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
    }

    private int levelToInfoScreen(int level) {
        switch (level) {
            case 1:
                return R.layout.tutorial;
            case 10:
                return R.layout.info_black_holes;
            default:
                return -1;
        }
    }

    public void onBackPressed() {
        pause = false;
        playing = false;
        if (viewHistory.size() > 1) {
            viewHistory.remove(viewHistory.size() - 1);
            int lastView = viewHistory.get(viewHistory.size() - 1);
            super.setContentView(lastView);
            if (lastView == R.layout.level_select)
                generateLevelSelectButtons(this.getCurrentFocus());
            if (lastView == R.layout.activity_main) {
                selectedLevel = -1;
                gameLoop();
            }
            if (lastView == R.layout.game_activity ||
                    lastView == R.layout.gameover ||
                    lastView == R.layout.win_screen ||
                    lastView == R.layout.tutorial ||
                    lastView == R.layout.info_black_holes) {
                onBackPressed();
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
        int completedNum = sharedPref.getInt(getString(R.string.completed_data), 0);
        int infoNum = sharedPref.getInt(getString(R.string.info_data), 0);

        unlocks = numToArr(unlocksNum);
        completed = numToArr(completedNum);
        shownInfoScreens = numToArr(infoNum);
    }

    private void writeData() {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putInt(getString(R.string.unlocks_data), arrToInt(unlocks));
        editor.putInt(getString(R.string.completed_data), arrToInt(completed));
        editor.putInt(getString(R.string.info_data), arrToInt(shownInfoScreens));
        editor.apply();
    }

    private int arrToInt(boolean[] arr) {
        int num = 0;
        for (int i = 0; i < unlocks.length; i++) {
            if (arr[i])
                num += 1<<i;
        }
        return num;
    }

    private boolean[] numToArr(int num) {
        boolean[] out = new boolean[unlocks.length];
        for (int i = 0; i < unlocks.length; i++) {
            out[i] = (num & 1) == 1;
            num = num>>1;
        }
        return out;
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
        final ImageView background = (ImageView) findViewById(R.id.level_bg);

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.level_select);

        Resources r = getResources();
        int px = 2000;

        final RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams( RelativeLayout.LayoutParams.MATCH_PARENT, px );
        background.setLayoutParams(p);

        ScrollView scrollView = (ScrollView) findViewById(R.id.level_select_scroll);

        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                p.setMargins(0, -v.getScrollY(), 0, 0);

                background.requestLayout();

                return false;
            }
        });


        LinearLayout select = (LinearLayout) findViewById(R.id.levelSelectLayout);

        for (int i = stages[stage][0]; i < stages[stage][1]; i++) {
            Button button = new Button(this);

            px = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    20,
                    r.getDisplayMetrics()
            );
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams( LinearLayout.LayoutParams.MATCH_PARENT, px * 2);
            params.setMargins(0, 0, 0, px);
            button.setLayoutParams(params);

            button.setText("" + (i+1));

            if (completed[i]) {
                button.setBackgroundColor(0x8888ff88);
            } else {
                button.setBackgroundColor(0xaaaaaaaa);
            }

            if (!unlocks[i]) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    button.setForeground(getResources().getDrawable(R.drawable.lock, getTheme()));
                }
            }

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

    public void info(View v) {
        setContentView(R.layout.info_activity);
        playing = false;
    }

    public void tutorial(View v) {
        setContentView(R.layout.tutorial);
        playing = false;
    }
}
