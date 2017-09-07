package aaa.aaa.level;

import android.content.Context;
import android.widget.RelativeLayout;

import aaa.aaa.MainActivity;
import aaa.aaa.R;
import aaa.aaa.entity.MainPlayer;
import aaa.aaa.entity.Star;
import aaa.aaa.entity.Wormhole;
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

    //Dat reference doe
    //This is where all of the Stars are hardcoded in; formatted [Level][StarPath][NumInPath]
    private static final Star[][][] STAR_CONTAINER = {

    };

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
            case 9: genLevel9(); // TODO: delete
                break;
            case 10: genLevel10(); // TODO: fix
                break;
            case 11: genLevel11();// TODO: delete
                break;
            case 12: genLevel12();// TODO: move
                break;
            case 13: genLevel13();// TODO: move
                break;
            case 14: genLevel14(); // TODO: delete
                break;
            case 15: genLevel15();// TODO: delete
                break;
            case 16: genLevel16(); // TODO: bad level
                break;
            case 17: genLevel17(); // TODO: bad level
                break;
            case 18: genLevel18(); // TODO: fix
                break;
            case 19: genLevel19();
                break;
            case -1: startScreen();
        }
    }

    //
    //
    // THE BASICS
    //
    //
    private void genLevel1() {
        new MainPlayer(-300, 1500, 4, -2, level);
        new Earth(0, 0, 0.5f, 0, 0, 80, level);
        new Planet(200, 900, 0.5f, 0, 0, 70, level, R.drawable.planet6);
    }
    private void genLevel2() {
        new MainPlayer(-500, 1700, 5, -3, level);
        new Earth(0, 0, 0.5f, 0, 0, 100, level);
        new Planet(-300, 1000, 0.5f, 0, 0, 60, level, R.drawable.planet5);
        new Planet(200, 900, 0.5f, 0, 0, 50, level, R.drawable.planet6);

    }
    private void genLevel3() {
        new MainPlayer(0, 2300, 0, -6, level);
        new Earth(0, 0, 0.5f, 0, 0, 100, level);
        new Planet(50, 1000, 0.5f, 0, 0, 60, level, R.drawable.planet1);
        new Planet(500, 1400, 0.5f, 0, 0, 100, level, R.drawable.planet4);

    }
    private void genLevel4() {

        new MainPlayer(-1000, -1200, 10, 0, level);
        new Earth(0, -50, 0.5f, 0, 0, 100, level);
        new Planet(-50, -1000, 0.5f, 0, 0, 100, level, R.drawable.planet6);
        new Planet(800, -250, 0.5f, 0, 0, 100, level, R.drawable.planet6);

    }
    private void genLevel5() {
        new MainPlayer(100, 1000, 5, -8, level);
        new Earth(0, 0, 0.5f, 0, 0, 80, level);
        new Planet(100, 600, 0.5f, 0, 0, 90, level, R.drawable.planet6);
        new Planet(600, -300, 0.5f, 0, 0, 50, level, R.drawable.planet6);
    }
    private void genLevel6() {
        new MainPlayer(-800, -400, 8, -3, level);
        new Earth(0, -50, 0.5f, 0, 0, 100, level);
        new Planet(-50, -500, 0.5f, 0, 0, 70, level, R.drawable.planet6);
    }
    private void genLevel7() {
        new MainPlayer(50, 1000, -2, -2, level);
        new Earth(0, 0, 0.5f, 0, 0, 100, level);
        new Planet(200, -900, 0.5f, 0, 0, 50, level, R.drawable.planet6);
    }
    private void genLevel8() {
        new MainPlayer(0,0,-1,-3,level);
        new Planet(0,-1000,0.5f,0,0,70,level);
        new Planet(300,-1000,0.5f,0,0,120,level);
        new Planet(0,-2000,0.5f,0,0,70,level);
        new Planet(-300,-2000,0.5f,0,0,120,level);
        new Planet(0,-3000,0.5f,0,0,70,level);
        new Planet(300,-3000,0.5f,0,0,120,level);
        new Planet(0,-4000,0.5f,0,0,70,level);
        new Planet(-300,-4000,0.5f,0,0,120,level);
        new Planet(0,-5000,0.5f,0,0,70,level);
        new Planet(300,-5000,0.5f,0,0,120,level);
        new Earth(0,-6000,0.5f,0,0,75,level);
    }
    private void genLevel9() {
        new MainPlayer(0, -345, 4.5, 0, level);
        new Earth(0, 0, 0.5f, 0, 0, 100, level).toggleGravity();
        new Planet(0, -600, 0.5f, 0, 0, 30, level, R.drawable.planet6);
        new Planet(Math.sqrt(3)*300, -300, 0.5f, 0, 0, 30, level, R.drawable.planet6);
        new Planet(Math.sqrt(3)*300,  300, 0.5f, 0, 0, 30, level, R.drawable.planet6);
        new Planet(0,  600, 0.5f, 0, 0, 20, level, R.drawable.planet6);
        new Planet(Math.sqrt(3)*-300,  300, 0.5f, 0, 0, 30, level, R.drawable.planet6);
        new Planet(Math.sqrt(3)*-300, -300, 0.5f, 0, 0, 30, level, R.drawable.planet6);
    }





    private void genLevel10() {
        new MainPlayer(0, 150, 6, 0, level);
        new Earth(0, 600, 0.5f, 0, 0, 75, level);
        new BlackHole(0, 0, 0.5f, 0, 0, 50, level);
    }
    private void genLevel11() {

        new MainPlayer(-100, 600, 6, 0, level);
        new Earth(0, 0, 0.5f, 0, 0, 75, level);
        new BlackHole(300, -300, 0.5f, 0, 0, 40, level);
        new BlackHole(-300, 300, 0.5f, 0, 0, 40, level);
        new BlackHole(-300, -300, 0.5f, 0, 0, 40, level);
        new BlackHole(300, 300, 0.5f, 0, 0, 40, level);
    }
    private void genLevel12() {
        Planet p = new Planet(0, 0, 0.5f, 0, 0, 75, level, R.drawable.planet4);
        p.toggleGravity();
        new MainPlayer(0, 300, 5, 0, level);

        BlackHole a = new BlackHole(-1500, 100, 0.5f, 0, 0, 50, level);
        BlackHole b = new BlackHole(-1500, -500, 0.5f, 0, 0, 50, level);
        p.addPuller(a);
        p.addPuller(b);

        new Earth(-2000, -300, 0.5f, 0, 0, 50, level);

    }
    private void genLevel13() {
        new MainPlayer(800, 0, -2, -3, level);
        Earth e = new Earth(0, 0, 0.5f, 0, 0, 100, level);
        // e.toggleGravity();
        new Planet(0, 400, 0.5f,  5, 0, 70, level, R.drawable.planet6).addPuller(e);
        new Planet(0, -400, 0.5f, -5, 0, 70, level, R.drawable.planet6).addPuller(e);
        new Planet(400,  0, 0.5f, 0, -5, 70, level, R.drawable.planet6).addPuller(e);
        new Planet(-400, 0, 0.5f, 0, 5, 70, level, R.drawable.planet6).addPuller(e);
        // new Planet(,  , 0.5f, 0, 0, 30, level, R.drawable.planet6).addPuller(e);
        // new Planet(, , 0.5f, 0, 0, 30, level, R.drawable.planet6).addPuller(e);
    }
    private void genLevel14() {
        new MainPlayer(800, 0, -2, -3, level);
        Earth e = new Earth(0, 0, 0.5f, 0, 0, 100, level);
        // e.toggleGravity();
        new Planet(0, 400, 0.5f,  5, 0, 40, level, R.drawable.planet1).addPuller(e);
        new Planet(0, -400, 0.5f, -5, 0, 40, level, R.drawable.planet2).addPuller(e);
        new Planet(400,  0, 0.5f, 0, -5, 40, level, R.drawable.planet3).addPuller(e);
        new Planet(-400, 0, 0.5f, 0, 5, 40, level, R.drawable.planet4).addPuller(e);
        new Planet(400 * Math.sin(45),  -400 * Math.sin(45), 0.5f, -5 * Math.sin(45), -5 * Math.sin(45), 40, level, R.drawable.planet5).addPuller(e);
        new Planet(-400 * Math.sin(45), -400 * Math.sin(45), 0.5f, 5 * Math.sin(45), 5 * Math.sin(45), 40, level, R.drawable.planet6).addPuller(e);
    }
    private void genLevel15() {
        new MainPlayer(600, 0, 0, Math.sqrt(20), level);
        new Earth(0, 0, 0.5f, 0, 0, 75, level);
        BlackHole a = new BlackHole(0, 250, 0.5f, Math.sqrt(5), 0, 60, level);
        BlackHole b = new BlackHole(0, -250, 0.5f, -Math.sqrt(5), 0, 60, level);
        a.addPuller(b);
        b.addPuller(a);
    }
    //
    //
    // BLACK HOLES
    //
    //
    private void genLevel16() {
        Earth earth = new Earth(0, 200, 0.5f, 0, 0, 75, level);
        earth.toggleGravity();
        new MainPlayer(0, 0, 6, 0, level);

        new Planet(0, 400, 0.5f, -6, 0, 50, level, R.drawable.planet3).addPuller(earth);
    }
    private void genLevel17() {
        Planet planet = new Planet(0, 300, 0.5f, 0, 0, 75, level, R.drawable.planet3);
        planet.toggleGravity();
        new MainPlayer(0, 0, 4, 0, level);

        new Earth(0, 600, 0.5f, -4, 0, 50, level).addPuller(planet);
    }
    private void genLevel18() {
        Earth earth = new Earth(0, 0, 0.5f, 0, 0, 75, level);
        earth.toggleGravity();
        new Planet(0, 100, 0.5f, 11, 0, 25, level, R.drawable.planet6).addPuller(earth);
        new Planet(0, -100, 0.5f, -11, 0, 25, level, R.drawable.planet6).addPuller(earth);
        new Planet(421.91, 0, 0.5f, -0.23, 2.6, 25, level, R.drawable.planet6).addPuller(earth);
        new Planet(-421.91, 0, 0.5f, 0.23, -2.6, 25, level, R.drawable.planet6).addPuller(earth);
        // 421.9108971401853, 2.59687808808825, 0.23213718906861144
        double dist = 250;
        new MainPlayer(0, dist, Math.sqrt(7500/dist), 0, level);
    }

    private void genLevel19() {
        new MainPlayer(0, 0, 10, 0, level);

        new Planet(0, 400, 0.5f, 0, 0, 200, level).toggleGravity();
        new Planet(0,-400, 0.5f, 0, 0, 200, level).toggleGravity();

        new Earth(0, -750, 0, 0, 50, level);
    }

    private void startScreen() {
        Earth earth = new Earth(0, 200, 0.5f, 0, 0, 75, level);
        earth.toggleGravity();
        new MainPlayer(0, 0, 6.12372, 0, level);
    }
}
