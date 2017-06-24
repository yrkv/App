package aaa.aaa.level;

import android.content.Context;
import android.widget.RelativeLayout;

import aaa.aaa.R;
import aaa.aaa.entity.MainPlayer;
import aaa.aaa.entity.puller.Planet;

/**
 * Created by Kaleb on 6/22/2017.
 */

public class LevelData {
    final Level level;

    public LevelData(RelativeLayout layout, Context context, int selectedLevel) {
        level = new Level(layout, context);
        new MainPlayer(0, 0, 1, 1, 0.2f, level);
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
        }
    }

    private void genLevel1() {
        new Planet(400, 300, 0, 0, 0.3f, level);
        new Planet(100, 800, 0, 0, 0.2f, level);
        new Planet(600, 1000, 0, 0, 0.25f, level);
    }
    private void genLevel2() {
        new Planet(400, 300, 0, 0, 0.2f, level, R.drawable.planet1);
        new Planet(200, 600, 0, 0, 0.25f, level, R.drawable.planet2);
        new Planet(500, 900, 0, 0, 0.2f, level, R.drawable.planet3);
        new Planet(1200, 1200, 0, 0, 0.2f, level, R.drawable.planet4);
        new Planet(1200, 1400, 0, 0, 0.2f, level, R.drawable.planet4);
        new Planet(1200, 1600, 0, 0, 0.2f, level, R.drawable.planet4);
        new Planet(1200, 1800, 0, 0, 0.2f, level, R.drawable.planet4);
        new Planet(1200, 2000, 0, 0, 0.2f, level, R.drawable.planet4);
        new Planet(1400, 2000, 0, 0, 0.2f, level, R.drawable.planet4);
        new Planet(1000, 2000, 0, 0, 0.2f, level, R.drawable.planet4);

    }
    private void genLevel3() {

    }
    private void genLevel4() {

    }
    private void genLevel5() {

    }
    private void genLevel6() {

    }
    private void genLevel7() {

    }
    private void genLevel8() {

    }
    private void genLevel9() {

    }
    private void genLevel10() {}
    private void genLevel11() {}
    private void genLevel12() {}
    private void genLevel13() {}
    private void genLevel14() {}
    private void genLevel15() {}
    private void genLevel16() {}
    private void genLevel17() {}
    private void genLevel18() {}
    private void genLevel19() {}
    private void genLevel20() {}
    private void genLevel21() {}
    private void genLevel22() {}
    private void genLevel23() {}
    private void genLevel24() {}
    private void genLevel25() {}
    private void genLevel26() {}
    private void genLevel27() {}
    private void genLevel28() {}
    private void genLevel29() {}
    private void genLevel30() {}
}
