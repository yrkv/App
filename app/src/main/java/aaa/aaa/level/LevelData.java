package aaa.aaa.level;

import android.content.Context;
import android.widget.RelativeLayout;

import aaa.aaa.MainActivity;
import aaa.aaa.R;
import aaa.aaa.entity.MainPlayer;
import aaa.aaa.entity.puller.BlackHole;
import aaa.aaa.entity.puller.Earth;
import aaa.aaa.entity.puller.Planet;

/**
 * Created by Kaleb on 6/22/2017.
 */

public class LevelData {
    final Level level;

    public LevelData(RelativeLayout layout, Context context, MainActivity mainActivity, int selectedLevel) {
        level = new Level(layout, context, mainActivity);

        getLevelData(selectedLevel);
    }

    public Level getLevel() {
        return level;
    }

    public void getLevelData(int currentLevel) {
        switch(currentLevel) {
            case 1: genLevel1();
                break;
            case 2: genLevel2();
                break;
            case 3: genLevel3();
                break;
            case 4: genLevel4();
                break;
            case 5: genLevel5();
                break;
            case 6: genLevel6();
                break;
            case 7: genLevel7();
                break;
            case 8: genLevel8();
                break;
            case 9: genLevel9();
                break;
            case 10: genLevel10();
                break;
            case 11: genLevel11();
                break;
            case 12: genLevel12();
                break;
            case 13: genLevel13();
                break;
            case 14: genLevel14();
                break;
            case 15: genLevel15();
                break;
            case 16: genLevel16();
                break;
            case 17: genLevel17();
                break;
            case 18: genLevel18();
                break;
            case 19: genLevel19();
                break;
            case 20: genLevel20();
                break;
            case 21: genLevel21();
                break;
            case 22: genLevel22();
                break;
            case 23: genLevel23();
                break;
            case 24: genLevel24();
                break;
            case 25: genLevel25();
                break;
            case 26: genLevel26();
                break;
            case 27: genLevel27();
                break;
            case 28: genLevel28();
                break;
            case 29: genLevel29();
                break;
            case 30: genLevel30();
                break;
            default: startScreen();
        }
    }

    private void genLevel1() {
        new MainPlayer(-300, 1500, 0.5f, 4, -2, 50, level);
        new Earth(0, 0, 0.5f, 0, 0, 80, level);
        new Planet(200, 900, 0.5f, 0, 0, 70, level, R.drawable.planet6);

    }
    private void genLevel2() {
        new MainPlayer(-500, 1700, 0.5f, 5, -3, 50, level);
        new Earth(0, 0, 0.5f, 0, 0, 100, level);
        new Planet(-300, 1000, 0.5f, 0, 0, 60, level, R.drawable.planet5);
        new Planet(200, 900, 0.5f, 0, 0, 50, level, R.drawable.planet6);

    }
    private void genLevel3() {
        new MainPlayer(0, 2300, 0.5f, 0, -6, 50, level);
        new Earth(0, 0, 0.5f, 0, 0, 100, level);
        new Planet(50, 1000, 0.5f, 0, 0, 60, level, R.drawable.planet1);
        new Planet(500, 1400, 0.5f, 0, 0, 100, level, R.drawable.planet4);

    }
    private void genLevel4() {

        new MainPlayer(-1000, -1200, 0.5f, 10, 0, 50, level);
        new Earth(0, -50, 0.5f, 0, 0, 100, level);
        new Planet(-50, -1000, 0.5f, 0, 0, 100, level, R.drawable.planet6);
        new Planet(800, -250, 0.5f, 0, 0, 100, level, R.drawable.planet6);

    }
    private void genLevel5() {
        new MainPlayer(100, 1000, 0.5f, 5, -8, 50, level);
        new Earth(0, 0, 0.5f, 0, 0, 80, level);
        new Planet(100, 600, 0.5f, 0, 0, 90, level, R.drawable.planet6);
        new Planet(600, -300, 0.5f, 0, 0, 50, level, R.drawable.planet6);
    }
    private void genLevel6() {
        new MainPlayer(-800, -400, 0.5f, 8, -3, 50, level);
        new Earth(0, -50, 0.5f, 0, 0, 100, level);
        new Planet(-50, -500, 0.5f, 0, 0, 70, level, R.drawable.planet6);
    }
    private void genLevel7() {
        new MainPlayer(50, 1000, 0.5f, -2, -2, 50, level);
        new Earth(0, 0, 0.5f, 0, 0, 100, level);
        new Planet(200, -900, 0.5f, 0, 0, 50, level, R.drawable.planet6);
    }
    private void genLevel8() {

    }
    private void genLevel9() {

    }
    private void genLevel10() {
        new MainPlayer(0, -345, 0.5f, 4.5, 0, 50, level);
        new Earth(0, 0, 0.5f, 0, 0, 100, level).toggleGravity();
        new Planet(0, -600, 0.5f, 0, 0, 30, level, R.drawable.planet6);
        new Planet(Math.sqrt(3)*300, -300, 0.5f, 0, 0, 30, level, R.drawable.planet6);
        new Planet(Math.sqrt(3)*300,  300, 0.5f, 0, 0, 30, level, R.drawable.planet6);
        new Planet(0,  600, 0.5f, 0, 0, 20, level, R.drawable.planet6);
        new Planet(Math.sqrt(3)*-300,  300, 0.5f, 0, 0, 30, level, R.drawable.planet6);
        new Planet(Math.sqrt(3)*-300, -300, 0.5f, 0, 0, 30, level, R.drawable.planet6);
    }
    private void genLevel11() {
        new MainPlayer(0, 150, 0.5f, 6, 0, 50, level);
        new Earth(600, 0, 0.5f, 0, 0, 75, level);
        new BlackHole(0, 0, 0.5f, 0, 0, 50, level);
    }
    private void genLevel12() {

        new MainPlayer(-100, 600, 0.5f, 6, 0, 50, level);
        new Earth(0, 0, 0.5f, 0, 0, 75, level);
        new BlackHole(300, -300, 0.5f, 0, 0, 40, level);
        new BlackHole(-300, 300, 0.5f, 0, 0, 40, level);
        new BlackHole(-300, -300, 0.5f, 0, 0, 40, level);
        new BlackHole(300, 300, 0.5f, 0, 0, 40, level);
    }
    private void genLevel13() {
    }
    private void genLevel14() {}
    private void genLevel15() {}
    private void genLevel16() {}
    private void genLevel17() {}
    private void genLevel18() {}
    private void genLevel19() {}
    private void genLevel20() {}
    private void genLevel21() {
        Earth earth = new Earth(0, 200, 0.5f, 0, 0, 75, level);
        earth.toggleGravity();
        new MainPlayer(0, 0, 0.5f, 6, 0, 50, level);

        new Planet(0, 400, 0.5f, -6, 0, 50, level, R.drawable.planet3).addPuller(earth);
    }
    private void genLevel22() {
        Planet planet = new Planet(0, 300, 0.5f, 0, 0, 75, level, R.drawable.planet3);
        planet.toggleGravity();
        new MainPlayer(0, 0, 0.5f, 4, 0, 50, level);

        new Earth(0, 600, 0.5f, -4, 0, 50, level).addPuller(planet);
    }
    private void genLevel23() {
        Earth earth = new Earth(0, 0, 0.5f, 0, 0, 75, level);
        earth.toggleGravity();

        new Planet(0, 100, 0.5f, 11, 0, 25, level, R.drawable.planet6).addPuller(earth);
        new Planet(0, -100, 0.5f, -11, 0, 25, level, R.drawable.planet6).addPuller(earth);
        new Planet(421.91, 0, 0.5f, -0.23, 2.6, 25, level, R.drawable.planet6).addPuller(earth);
        new Planet(-421.91, 0, 0.5f, 0.23, -2.6, 25, level, R.drawable.planet6).addPuller(earth);
        // 421.9108971401853, 2.59687808808825, 0.23213718906861144
        double dist = 250;
        new MainPlayer(0, dist, 0.5f, Math.sqrt(7500/dist), 0, 50, level);
    }
    private void genLevel24() {}
    private void genLevel25() {}
    private void genLevel26() {}
    private void genLevel27() {}
    private void genLevel28() {}
    private void genLevel29() {}
    private void genLevel30() {}

    private void startScreen() {
        Earth earth = new Earth(0, 200, 0.5f, 0, 0, 75, level);
        earth.toggleGravity();
        new MainPlayer(0, 0, 0.5f, 6.12372, 0, 50, level);
    }
}
