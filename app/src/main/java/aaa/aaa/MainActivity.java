package aaa.aaa;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import aaa.aaa.entity.Star;
import aaa.aaa.entity.StarData;
import aaa.aaa.level.LevelData;

public class MainActivity extends AppCompatActivity {

    //CONFIG
    private final double TICKSPEED = 60.0; //in milliseconds
    private final boolean DEVMODE = false;
    //END CONFIG
    public boolean playing = false;
    public boolean preview = false;
    public boolean pause = false;
    private int selectedLevel = 1;
    private String customSelectedLevel = "1";

    private final String INTENT_PRESET = "Preset";
    private final String INTENT_CUSTOM = "Custom";

    private String currentIntent = INTENT_PRESET;

    public static int width = 0;
    public static int height = 0;

    private ArrayList<Integer> viewHistory = new ArrayList<>();

    private static final int[][] stages = {
            {0, 9},
            {9, 14},
            {14, 21},
            {21,29},
            {29,38},
            {38,46},
            {46,53}
    };


    private boolean[] unlocks = new boolean[stages[stages.length-1][1]];
    public  boolean[] completed = new boolean[stages[stages.length-1][1]];
    private boolean[] shownInfoScreens = new boolean[stages[stages.length-1][1]];

    public ArrayList<String> customStages = new ArrayList<String>();
    private ArrayList<Boolean> customCompleted = new ArrayList<Boolean>();

    //new boolean[#stages][10]
    public boolean[][] completedStarPaths = new boolean[stages[stages.length-1][1]][10];

    private static final String[] CUSTOM_STAGE_NAMES = {
            "Local",
            "Online"
    };

    private static final String[] STAGE_NAMES = {
            "Stage 1",
            "Stage 2",
            "Stage 3",
            "Stage 4",
            "Stage 5",
            "Stage 6",
            "Stage 7"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //THIS IS TEMPORARY FOR TESTING PURPOSES \/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
        customStages.add("wowzer");
        customCompleted.add(true);
        //THIS IS TEMPORARY FOR TESTING PURPOSES /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        readData();
        unlocks[0] = true;
        setContentView(R.layout.activity_main);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;

        selectedLevel = 0;
        gameLoop(INTENT_PRESET);
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

    public void CustomLevels(View v) {
        setContentView(R.layout.updated_level_select);
        createCustomLevelSelect(v);
        playing = false;
    }

    public void LevelSelect(View v) {
        setContentView(R.layout.updated_level_select);
        createLevelSelect(v);
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

    public void preview(View v) {

    }

    public boolean isPause() {
        return pause;
    }

    public void Win() {
        if (playing) {
            completed[selectedLevel-1] = true;
            if (selectedLevel < completed.length) {
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
        gameLoop(currentIntent);
    }

    public void gameLoop(String intent) {
        currentIntent = intent;
        boolean isPreset = intent.equals(INTENT_PRESET);

        if (selectedLevel != 0 && !shownInfoScreens[selectedLevel-1] && levelToInfoScreen(selectedLevel) != -1) {
            shownInfoScreens[selectedLevel-1] = true;
            setContentView(levelToInfoScreen(selectedLevel));
        } else {
            final LevelData levelData;
            if (isPreset && selectedLevel == 0) {
                setContentView(R.layout.activity_main);
                levelData = new LevelData((RelativeLayout) findViewById(R.id.activity_main), this, this, selectedLevel);
            } else {
                setContentView(R.layout.game_activity);
                if(isPreset) {
                    levelData = new LevelData((RelativeLayout) findViewById(R.id.next_activity), this, this, selectedLevel);
                } else {
                    levelData = new LevelData((RelativeLayout) findViewById(R.id.next_activity), this, this, customSelectedLevel);
                }
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
//          	if(preview) {
//              	pause = true;
//            }
            Thread myThread = new Thread(myRunnable);
            myThread.start();
        }
    }

    public void previewLoop(View v, String intent) {
        previewLoop(intent);
    }

    public void previewLoop(String intent) {
        boolean isPreset = intent.equals(INTENT_PRESET);

        final LevelData levelData;
        setContentView(R.layout.game_activity);
        if(isPreset) {
            levelData = new LevelData((RelativeLayout) findViewById(R.id.next_activity), this, this, selectedLevel);
        } else {
            levelData = new LevelData((RelativeLayout) findViewById(R.id.next_activity), this, this, customSelectedLevel);
        }

        levelData.getLevel().setBackground((ImageView) findViewById(R.id.background));
        levelData.getLevel().xOffset = 0;
        levelData.getLevel().yOffset = 0;

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
                              	levelData.getLevel().previewUpdate();
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
        preview = false;
        if (viewHistory.size() > 1) {
            viewHistory.remove(viewHistory.size() - 1);
            int lastView = viewHistory.get(viewHistory.size() - 1);
            super.setContentView(lastView);
            if (lastView == R.layout.updated_level_select)
                createLevelSelect(this.getCurrentFocus());
            if (lastView == R.layout.activity_main) {
                selectedLevel = 0;
                gameLoop(INTENT_PRESET);
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

    public void Hint(View v) {

    }

    private void readData() {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        String defaultValue = "1";
        String unlocksNum = sharedPref.getString(getString(R.string.unlocks_data), defaultValue);
        String completedNum = sharedPref.getString(getString(R.string.completed_data), "0");
        String infoNum = sharedPref.getString(getString(R.string.info_data), "0");
        String starData = sharedPref.getString("com.downToEarth.starData", dblArrToString(completedStarPaths));

        unlocks = stringToArr(unlocksNum);
        completed = stringToArr(completedNum);
        shownInfoScreens = stringToArr(infoNum);
        completedStarPaths = stringToDblArr(starData);
    }

    private void writeData() {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString(getString(R.string.unlocks_data), arrToString(unlocks));
        editor.putString(getString(R.string.completed_data), arrToString(completed));
        editor.putString(getString(R.string.info_data), arrToString(shownInfoScreens));
        editor.putString("com.downToEarth.starData", dblArrToString(completedStarPaths));
        editor.apply();
    }

    private String arrToString(boolean[] arr) {
        String text = "";
        for(int i = 0; i < unlocks.length; i++) {
            text += arr[i] ? "1" : "0";
        }
        return text;
    }

    private boolean[] stringToArr(String text) {
        boolean[] arr = new boolean[unlocks.length];
        for(int i = 0; i < text.length(); i++) {
            arr[i] = (text.substring(i,i+1).equals("1")) ? true : false;
        }
        return arr;
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

    //PLEASE only use this if the size of the second part of the array desired is 10!!
    //This is written soley for the starpathcompletion checker.
    private String dblArrToString(boolean[][] arr) {
        String text = "";

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < 10; j++) {
                text += arr[i][j] ? 1 : 0;
            }
        }

        return text;
    }

    private boolean[][] stringToDblArr(String s) {
        boolean[][] arr = new boolean[stages[stages.length-1][1]][10];

        for (int i = 0; i < arr.length; i++) {
            String sub = s.substring(i * 10, i * 10 + 10);
            for (int j = 0; j < 10; j ++) {
                char b = sub.charAt(j);

                arr[i][j] = (b == '1');
            }
        }

        return arr;
    }

    float x = -1;
    float y = -1;
    int s = 0;
    int cs = 0;
    boolean canScroll = false;
    private static final int SCROLL_MINIMUM = 60;

    public void createCustomLevelSelect(View v) {
        final HorizontalScrollView scrollView = (HorizontalScrollView) findViewById(R.id.horizontalScroll);

        scrollView.post(new Runnable() {
            @Override
            public void run() {
                instantScrollTo(cs);
            }
        });

        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    x = event.getRawX();
                    y = event.getRawY();
                    canScroll = true;
                }
                if (event.getAction() == MotionEvent.ACTION_MOVE && canScroll) {
                    float xDiff = event.getRawX() - x;
                    scrollView.setScrollX((int) (-xDiff) + width * cs);
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    canScroll = false;
                    float xDiff = event.getRawX() - x;

                    if (xDiff > SCROLL_MINIMUM && cs > 0) {
                        cs--;
                    }
                    if (xDiff < -SCROLL_MINIMUM && cs < CUSTOM_STAGE_NAMES.length - 1) {
                        cs++;
                    }
                    scrollTo(cs);
                }
                return true;
            }
        });

        LinearLayout levelSelects = (LinearLayout) findViewById(R.id.levelSelects);

        for (int i = 0; i < CUSTOM_STAGE_NAMES.length; i++) {
            String STAGE_NAME = CUSTOM_STAGE_NAMES[i];
            RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(width, ViewGroup.LayoutParams.MATCH_PARENT);

            RelativeLayout stage = (RelativeLayout) getLayoutInflater().inflate(R.layout.level_select, levelSelects, false);
            stage.setLayoutParams(p);

            stage.findViewById(R.id.levelsLayout).setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return false;
                }
            });

            ((TextView) stage.findViewById(R.id.stageName)).setText(STAGE_NAME);

            levelSelects.addView(stage);

            //Testing shit for now \/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
            LinearLayout layout = (LinearLayout) stage.findViewById(R.id.levelsLayout);

            final View button = getLayoutInflater().inflate(R.layout.level_select_header, layout, false);

            int px = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    40,
                    getResources().getDisplayMetrics()
            );
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, px);
            params.setMargins(px / 4, px / 8, px / 4, 0);
            button.setLayoutParams(params);

            layout.addView(button);
            //Testing shit for now ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
            if(i == 0) {
                for (int j = 0; j < customStages.size(); j++) {
                    System.out.println("Custom stage iteration: " + j);
                    final String level = customStages.get(j);
                    System.out.println("Custom stage name: " + level);

                    createLevelButton((LinearLayout) stage.findViewById(R.id.levelsLayout), scrollView, level, j);
                }
            }
        }
    }

    public void createLevelSelect(View v) {
        final HorizontalScrollView scrollView = (HorizontalScrollView) findViewById(R.id.horizontalScroll);

        scrollView.post(new Runnable() {
            @Override
            public void run() {
                instantScrollTo(s);
            }
        });

        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    x = event.getRawX();
                    y = event.getRawY();
                    canScroll = true;
                }
                if (event.getAction() == MotionEvent.ACTION_MOVE && canScroll) {
                    float xDiff = event.getRawX() - x;
                    scrollView.setScrollX((int) (-xDiff) + width * s);
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    canScroll = false;
                    float xDiff = event.getRawX() - x;

                    if (xDiff > SCROLL_MINIMUM && s > 0) {
                        s--;
                    }
                    if (xDiff < -SCROLL_MINIMUM && s < STAGE_NAMES.length - 1) {
                        s++;
                    }
                    scrollTo(s);
                }
                return true;
            }
        });

        LinearLayout levelSelects = (LinearLayout) findViewById(R.id.levelSelects);

        for (int i = 0; i < STAGE_NAMES.length; i++) {
            String STAGE_NAME = STAGE_NAMES[i];
            RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(width, ViewGroup.LayoutParams.MATCH_PARENT);

            RelativeLayout stage = (RelativeLayout) getLayoutInflater().inflate(R.layout.level_select, levelSelects, false);
            stage.setLayoutParams(p);

            stage.findViewById(R.id.levelsLayout).setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return false;
                }
            });

            ((TextView) stage.findViewById(R.id.stageName)).setText(starsStage(i) + "/" + maxStarsStage(i) + "  " + STAGE_NAME);

            levelSelects.addView(stage);

            //Testing shit for now \/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
            LinearLayout layout = (LinearLayout) stage.findViewById(R.id.levelsLayout);

            final View button = getLayoutInflater().inflate(R.layout.level_select_header, layout, false);

            int px = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    40,
                    getResources().getDisplayMetrics()
            );
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, px);
            params.setMargins(px / 4, px / 8, px / 4, 0);
            button.setLayoutParams(params);

            layout.addView(button);
            //Testing shit for now ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

            for (int j = stages[i][0]; j < stages[i][1]; j++) {
                final int level = j + 1;

                createLevelButton((LinearLayout) stage.findViewById(R.id.levelsLayout), scrollView, level);
            }
        }
    }

    private String totalStars() {
        int a = 0, b = 0;

        for (int i = 0; i < stages.length; i++) {
            a += starsStage(i);
            b += maxStarsStage(i);
        }

        return a + "/" + b;
    }

    private int starsStage(int stage) {
        int c = 0;
        for (int i = stages[stage][0]; i < stages[stage][1]; i++) {
            c += stars(i);
        }
        return c;
    }

    private int maxStarsStage(int stage) {
        int c = 0;
        for (int i = stages[stage][0]; i < stages[stage][1]; i++) {
            c += maxStars(i);
        }
        return c;
    }

    private int stars(int level) {
        int c = 0;
        if (level >= 0)
            for (int i = 0; i < 10; i++)
                if (completedStarPaths[level][i])
                    c++;
        return c;
    }

    private int maxStars(int level) {
        if (level >= 0 && level < StarData.STAR_CONTAINER.length)
            return StarData.STAR_CONTAINER[level].length;
        else
            return 0;
    }

    private void createLevelButton(LinearLayout layout, final HorizontalScrollView scrollView, final String level, int levelIndex) {
        final View button = getLayoutInflater().inflate(R.layout.level_select_button, layout, false);

        int px = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                40,
                getResources().getDisplayMetrics()
        );
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, px);
        params.setMargins(px / 4, px / 8, px / 4, 0);
        button.setLayoutParams(params);

        ((TextView) button.findViewById(R.id.levelText)).setText("" + level); // keep the ""+, it makes it use the right method

        if (customCompleted.get(levelIndex)) {
            button.findViewById(R.id.button_color).setBackgroundColor(0x8888ff88);
        } else {
            button.findViewById(R.id.button_color).setBackgroundColor(0xaaaaaaff);
        }

        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    x = event.getRawX();
                    y = event.getRawY();
                    canScroll = true;
                }
                if (event.getAction() == MotionEvent.ACTION_MOVE && canScroll) {
                    float xDiff = event.getRawX() - x;
                    scrollView.setScrollX((int) (-xDiff) + width * cs);
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    canScroll = false;
                    float xDiff = event.getRawX() - x;
                    float yDiff = event.getRawY() - y;

                    if (xDiff > SCROLL_MINIMUM && cs > 0) {
                        cs--;
                        scrollTo(cs);
                    }
                    else if (xDiff < -SCROLL_MINIMUM && cs < STAGE_NAMES.length - 1) {
                       cs++;
                        scrollTo(cs);
                    } else if (yDiff < 50) {
                        customSelectedLevel = level;
                        gameLoop(INTENT_CUSTOM);
                    } else {
                        scrollTo(cs);
                    }
                }

                return true;
            }
        });

        button.findViewById(R.id.preview).setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    x = event.getRawX();
                    y = event.getRawY();
                    canScroll = true;
                }
                if (event.getAction() == MotionEvent.ACTION_MOVE && canScroll) {
                    float xDiff = event.getRawX() - x;
                    scrollView.setScrollX((int) (-xDiff) + width * cs);
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    canScroll = false;
                    float xDiff = event.getRawX() - x;
                    float yDiff = event.getRawY() - y;
                    System.out.println(yDiff);

                    if (xDiff > SCROLL_MINIMUM && cs > 0) {
                        cs--;
                        scrollTo(cs);
                    }
                    else if (xDiff < -SCROLL_MINIMUM && cs < STAGE_NAMES.length - 1) {
                        cs++;
                        scrollTo(cs);
                    } else if (yDiff < 50) {
                        customSelectedLevel = level;
                        preview = true;
                        previewLoop(v,INTENT_CUSTOM);
                    } else {
                        scrollTo(cs);
                    }
                }

                return true;
            }
        });

        layout.addView(button);
    }

    private void createLevelButton(LinearLayout layout, final HorizontalScrollView scrollView, final int level) {
        final View button = getLayoutInflater().inflate(R.layout.level_select_button, layout, false);

        int px = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                40,
                getResources().getDisplayMetrics()
        );
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, px);
        params.setMargins(px / 4, px / 8, px / 4, 0);
        button.setLayoutParams(params);

        ((TextView)button.findViewById(R.id.levelText)).setText(""+level); // keep the ""+, it makes it use the right method

        if (completed[level-1]) {
            button.findViewById(R.id.button_color).setBackgroundColor(0x8888ff88);
        } else {
            button.findViewById(R.id.button_color).setBackgroundColor(0xaaaaaaff);
        }

        if (!unlocks[level-1]) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                button.findViewById(R.id.button_color).setBackgroundColor(0xaaffaaaa);
            }
        }

        LinearLayout starsLayout = (LinearLayout) button.findViewById(R.id.starsLayout);
        for (int i = 0; i < completedStarPaths[level-1].length; i++) {
            if (completedStarPaths[level-1][i]) {
                ImageView star = new ImageView(this);

                star.setImageResource(R.drawable.star);
                star.setColorFilter(0xff000000 + Star.typeToColor(i));

                LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(px / 2, px / 2);

                star.setLayoutParams(p);

                starsLayout.addView(star);
            }
        }

        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    x = event.getRawX();
                    y = event.getRawY();
                    canScroll = true;
                }
                if (event.getAction() == MotionEvent.ACTION_MOVE && canScroll) {
                    float xDiff = event.getRawX() - x;
                    scrollView.setScrollX((int) (-xDiff) + width * s);
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    canScroll = false;
                    float xDiff = event.getRawX() - x;
                    float yDiff = event.getRawY() - y;

                    if (xDiff > SCROLL_MINIMUM && s > 0) {
                        s--;
                        scrollTo(s);
                    }
                    else if (xDiff < -SCROLL_MINIMUM && s < STAGE_NAMES.length - 1) {
                        s++;
                        scrollTo(s);
                    } else if (levelUnlocked(level) && yDiff < 50) {
                        selectedLevel = level;
                        gameLoop(INTENT_PRESET);
                    } else {
                        scrollTo(s);
                    }
                }

                return true;
            }
        });

        button.findViewById(R.id.preview).setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    x = event.getRawX();
                    y = event.getRawY();
                    canScroll = true;
                }
                if (event.getAction() == MotionEvent.ACTION_MOVE && canScroll) {
                    float xDiff = event.getRawX() - x;
                    scrollView.setScrollX((int) (-xDiff) + width * s);
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    canScroll = false;
                    float xDiff = event.getRawX() - x;
                    float yDiff = event.getRawY() - y;
                    System.out.println(yDiff);

                    if (xDiff > SCROLL_MINIMUM && s > 0) {
                        s--;
                        scrollTo(s);
                    }
                    else if (xDiff < -SCROLL_MINIMUM && s < STAGE_NAMES.length - 1) {
                        s++;
                        scrollTo(s);
                    } else if (levelUnlocked(level) && yDiff < 50) {
                        selectedLevel = level;
                        preview = true;
                        previewLoop(v,INTENT_PRESET);
                    } else {
                        scrollTo(s);
                    }
                }

                return true;
            }
        });

        layout.addView(button);
    }

    private void scrollTo(int stage) {
        HorizontalScrollView scrollView = (HorizontalScrollView) findViewById(R.id.horizontalScroll);

        scrollView.smoothScrollTo(stage * width, 0);
    }

    private void instantScrollTo(int stage) {
        HorizontalScrollView scrollView = (HorizontalScrollView) findViewById(R.id.horizontalScroll);

        scrollView.scrollTo(stage * width, 0);
    }

    private boolean levelUnlocked(int selectedLevel) {

        return DEVMODE || unlocks[selectedLevel-1];
    }

    public void info(View v) {
        setContentView(R.layout.info_activity);
        playing = false;
    }

    public void tutorial(View v) {
        setContentView(R.layout.tutorial);
        playing = false;
    }

    public void levelStatusInfo(View v) {
        setContentView(R.layout.level_status_info);
        playing = false;
    }
}
