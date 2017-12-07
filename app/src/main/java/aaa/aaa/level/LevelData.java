package aaa.aaa.level;

import android.content.Context;
import android.content.Entity;
import android.content.res.Resources;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

import aaa.aaa.MainActivity;
import aaa.aaa.R;
import aaa.aaa.entity.DustStorm;
import aaa.aaa.entity.EntityBase;
import aaa.aaa.entity.MainPlayer;
import aaa.aaa.entity.Star;
import aaa.aaa.entity.StarData;
import aaa.aaa.entity.Wormhole;
import aaa.aaa.entity.puller.BlackHole;
import aaa.aaa.entity.puller.Earth;
import aaa.aaa.entity.puller.Planet;
import aaa.aaa.entity.puller.Puller;

/**
 * Created by Kaleb on 6/22/2017.
 */

//TODO: umm wtf? I got it to win and then lose like 10ms later... it counted it as completed. (level 17)
//TODO: also.. level 17 hard af? is it supposed to be?

public class LevelData {
    final Level level;

    public LevelData(RelativeLayout layout, Context context, MainActivity mainActivity, int selectedLevel) {

        int path = -1;

        if (selectedLevel > 0 && mainActivity.completed[selectedLevel-1] && selectedLevel <= StarData.STAR_CONTAINER.length) {
            boolean[] starsCollected = mainActivity.completedStarPaths[selectedLevel - 1];
            int starPathsCount = StarData.STAR_CONTAINER[selectedLevel - 1].length;
            if (starPathsCount > 0) {
                int star = (int) (Math.random() * 10000) % starPathsCount;

                for (int i = star; i < star + starPathsCount; i++) {
                    if (!starsCollected[i % starPathsCount]) {
                        path = i % starPathsCount;
                    }
                }
            }
        }

        level = new Level(layout, context, mainActivity, selectedLevel, path);

//        getLevelData(selectedLevel);
        genLevel("level_" + Integer.toString(selectedLevel));
    }

    public LevelData(RelativeLayout layout, Context context, MainActivity mainActivity, String selectedLevel) {
        int path = -1;

        level = new Level(layout, context, mainActivity, selectedLevel, path);

//        getLevelData(selectedLevel);
        genLevel("level_" + selectedLevel);
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
            case 8: genLevel8(); //TODO: remove these frickin 2dos
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
            case 15: genLevel15(); // TODO: bad level
                break;
            case 16: genLevel16(); // TODO: bad level
                break;
            case 17: genLevel17(); // TODO: fix
                break;
            case 18: genLevel18();
                break;
            case 19: genLevel19();
                break;
            case 20: genLevel20();
                break;
            case 21: genlevel21();
                break;
            case 22: genLevel22();
                break;
            case 23: genLevel23();
                break;
            case -1: startScreen();
                break;
            default: startScreen();
        }
    }

    //This is used by the genLevel(String name) method in order
    //to deal with broken data files that might be handed to it.
    private void crash(String message) {
        Toast.makeText(level.getContext(), message, Toast.LENGTH_LONG).show();
    }

    //This method is used by the genLevel(String name) method to avoid repetition of code.
    private double[] parseDoubles(String[] datas) {
        double[] parsedDatas = new double[datas.length];
        for(int i = 0; i < datas.length; i++) {
            parsedDatas[i] = Double.parseDouble(datas[i]);
        }
        return parsedDatas;
    }

    //This method is used for compartmentalization of genLevel(String name) method.
    private void pairStuff(ArrayList<Wormhole> wormholeList, ArrayList<Integer> pairTos) {
        for(int i = 0; i < wormholeList.size(); i++) {
            if(pairTos.get(i) == -1) continue;
            wormholeList.get(i).pairTo(wormholeList.get(pairTos.get(i)));
        }
    }

    //This method is used by genLevel(String name) in order to add pullers
    //between random shit >.<
    private void dealWithThings(ArrayList<EntityBase> things, ArrayList<String> pullerIndex) {
        System.out.println("\"things\" size: " + things.size());
        for(int i = 0; i < things.size(); i++) {
            String indices = pullerIndex.get(i);
            System.out.println("Index data: " + indices);
            String[] data = indices.split(",");
            System.out.println("Data index 0: " + data[0]);
            if(data.length == 1 && data[0].length() == 2 && data[0].substring(0,2).equals("-1")) continue;
            if(things.get(i) instanceof Puller) {
                for (int j = 0; j < data.length; j++) {
                    ((Puller) things.get(i)).addPuller((Puller) things.get(Integer.parseInt(data[j])));
                }
            }
        }
    }

    private void genLevel(String name) {
        String levelFile = "";

        try {
            System.out.println("Beginning to open asset " + name + "...");
            Resources resources = level.getContext().getResources();
            int resID = resources.getIdentifier(name, "raw", "aaa.aaa");
            if(resID == 0) {
                System.out.println("Error 404: File Not Found.");
                return;
            }
            System.out.println("Asset " + name + " with ID " + resID);
            InputStream input = resources.openRawResource(resID);
            System.out.println("Finished opening asset " + name + "!");
            System.out.println("\nbegin string assembly...\n");
            levelFile = "";
            while(input.available() > 0){
                char letter = (char) input.read();
                levelFile += letter;
            }
            System.out.println("\nfinished string assembly!\n\"" + levelFile + "\"");
        }
        catch(IOException e) {
            e.printStackTrace();
        }

        //~~~~~~~~~~~~~~~~~~~~
        //BEGIN LEVEL ASSEMBLY
        //~~~~~~~~~~~~~~~~~~~~
        System.out.println("Beginning level assembly...");


        ArrayList<EntityBase> things = new ArrayList<EntityBase>();
        ArrayList<String> pullerIndex = new ArrayList<String>();

        String[] lines =  levelFile.split("\n");
        for(int z = 0; z < lines.length; z++) {
            System.out.println("line " + z + ": " + lines[z]);
        }
        int i = 0;
        System.out.println("lines.length: " + lines.length);
        if((1 >= lines.length)) return;
        while((i < lines.length)&&!(lines[i].substring(0,6).equals("Player"))){i++;}
        i++;
        System.out.println("Building players...");
        while((i < lines.length)&&!lines[i].substring(0,5).equals("Earth")) {
            String data = lines[i];
            String[] datas = data.split(" ");
            System.out.println("data line: " + data);
            for(int z = 0; z < datas.length; z++) {
                System.out.println("data " + z + ": " + datas[z]);
            }
            double[] parsedDatas = parseDoubles(datas);
            if(datas.length == 4) {
                things.add(new MainPlayer(parsedDatas[0], parsedDatas[1], parsedDatas[2], parsedDatas[3], level));
                System.out.println("Main player added to \"things\"");
                pullerIndex.add("-1");
            } else {
                System.out.println("ERROR: INCORRECT_ARGS");
                System.out.println("length of \"datas\": " + datas.length);
                crash("Given incorrect args for Player");
            }
            i++;
        }
        System.out.println("Done building players!");
        System.out.println("Building Earths...");
        i++;
        while((i < lines.length)&&!lines[i].substring(0,6).equals("Planet")) {
            String data = lines[i];
            String[] datas = data.split(" ");
            System.out.println("data line: " + data);
            for(int z = 0; z < datas.length; z++) {
                System.out.println("data " + z + ": " + datas[z]);
            }
            if(datas.length == 6) {
                double[] parsedDatas = parseDoubles(datas);
                Earth dick = new Earth(parsedDatas[0], parsedDatas[1], parsedDatas[2], parsedDatas[3], (float)parsedDatas[4], level);
                things.add(dick);
                System.out.println("Earth added to \"things\"");
                pullerIndex.add("-1");
                if(parsedDatas[5]==1) dick.toggleGravity();
            } else if(datas.length == 7) {
                //What this ugly ass hunk does is it puts the data into modified data with one change:
                //It removes the "addPuller" data (because of double parsing issues associated with
                //trying to parse 3,4 (for example) as a double.
                String[] modifiedDatas = new String[datas.length-1];
                for(int m = 0; m < modifiedDatas.length-1; m++) {
                    modifiedDatas[m] = datas[m];
                }
                modifiedDatas[modifiedDatas.length-1] = datas[datas.length-1];

                double[] parsedDatas = parseDoubles(modifiedDatas);

                Earth dick = new Earth(parsedDatas[0], parsedDatas[1], parsedDatas[2], parsedDatas[3], (float)parsedDatas[4], level);
                things.add(dick);
                System.out.println("Earth added to \"things\"");
                pullerIndex.add(datas[5]);
                if(parsedDatas[5]==1) dick.toggleGravity();
            } else {
                System.out.println("ERROR: INCORRECT_ARGS");
                System.out.println("length of \"datas\": " + datas.length);
                crash("Given incorrect args for Earth");
            }
            i++;
        }
        System.out.println("Done building Earths!");
        System.out.println("Building planets...");
        i++;
        while((i < lines.length)&&!lines[i].substring(0,10).equals("Black Hole")) {
            String data = lines[i];
            String[] datas = data.split(" ");
            System.out.println("data line: " + data);
            for(int z = 0; z < datas.length; z++) {
                System.out.println("data " + z + ": " + datas[z]);
            }
            if(datas.length == 6) {
                double[] parsedDatas = parseDoubles(datas);
                Planet dick = new Planet(parsedDatas[0], parsedDatas[1], 0.5f, parsedDatas[2], parsedDatas[3], (float)parsedDatas[4], level);
                if(parsedDatas[5]==1) dick.toggleGravity();
                pullerIndex.add("-1");
                things.add(dick);
                System.out.println("Planet added to \"things\"");
            } else if(datas.length == 7) {
                String[] modifiedDatas = new String[datas.length-1];
                for(int m = 0; m < modifiedDatas.length-1; m++) {
                    modifiedDatas[m] = datas[m];
                }
                modifiedDatas[modifiedDatas.length-1] = datas[datas.length-1];

                double[] parsedDatas = parseDoubles(modifiedDatas);

                Planet dick = new Planet(parsedDatas[0], parsedDatas[1], 0.5f, parsedDatas[2], parsedDatas[3], (float)parsedDatas[4], level);
                pullerIndex.add(datas[5]);
                if(parsedDatas[5]==1) dick.toggleGravity();
                things.add(dick);
                System.out.println("Planet added to \"things\"");
            } else {
                System.out.println("ERROR: INCORRECT_ARGS");
                System.out.println("length of \"datas\": " + datas.length);
                crash("Given incorrect args for Planet");
            }
            i++;
        }
        System.out.println("Done building planets!");
        System.out.println("Building black holes...");
        i++;
        while((i < lines.length)&&!lines[i].substring(0,9).equals("Worm Hole")) {
            String data = lines[i];
            String[] datas = data.split(" ");
            System.out.println("data line: " + data);
            for(int z = 0; z < datas.length; z++) {
                System.out.println("data " + z + ": " + datas[z]);
            }
            if(datas.length == 5) {
                double[] parsedDatas = parseDoubles(datas);
                things.add(new BlackHole(parsedDatas[0], parsedDatas[1], 0.5f, parsedDatas[2], parsedDatas[3], (float)parsedDatas[4], level));
                System.out.println("Black Hole added to \"things\"");
                pullerIndex.add("-1");
            } else if(datas.length == 6) {
                String[] modifiedDatas = new String[datas.length-1];
                for(int m = 0; m < modifiedDatas.length-1; m++) {
                    modifiedDatas[m] = datas[m];
                }
                modifiedDatas[modifiedDatas.length-1] = datas[datas.length-1];

                double[] parsedDatas = parseDoubles(modifiedDatas);

                things.add(new BlackHole(parsedDatas[0], parsedDatas[1], 0.5f, parsedDatas[2], parsedDatas[3], (float)parsedDatas[4], level));
                System.out.println("Black Hole added to \"things\"");
                pullerIndex.add(datas[5]);
            } else {
                System.out.println("ERROR: INCORRECT_ARGS");
                System.out.println("length of \"datas\": " + datas.length);
                crash("Given incorrect args for Black Hole");
            }
            i++;
        }
        System.out.println("Done building black holes!");
        System.out.println("Building wormholes...");
        i++;
        ArrayList<Integer> pairTos = new ArrayList<Integer>();
        ArrayList<Wormhole> wormholeList = new ArrayList<Wormhole>();
        while((i < lines.length)&&!lines[i].substring(0,10).equals("Dust Storm")) {
            String data = lines[i];
            String[] datas = data.split(" ");
            System.out.println("data line: " + data);
            for(int z = 0; z < datas.length; z++) {
                System.out.println("data " + z + ": " + datas[z]);
            }
            if(datas.length == 6) {
                double[] parsedDatas = parseDoubles(datas);
                Wormhole shitter = new Wormhole(parsedDatas[0], parsedDatas[1], parsedDatas[2], parsedDatas[3], (float)parsedDatas[4], level);
                wormholeList.add(shitter);
                things.add(shitter);
                System.out.println("Wormhole added to \"things\"");
                pullerIndex.add("-1");
                pairTos.add((int)parsedDatas[5]);
            } else if(datas.length == 7) {
                String[] modifiedDatas = new String[datas.length-1];
                for(int m = 0; m < modifiedDatas.length-1; m++) {
                    modifiedDatas[m] = datas[m];
                }
                modifiedDatas[modifiedDatas.length-1] = datas[datas.length-1];

                double[] parsedDatas = parseDoubles(modifiedDatas);

                Wormhole shitter = new Wormhole(parsedDatas[0], parsedDatas[1], parsedDatas[2], parsedDatas[3], (float)parsedDatas[4], level);
                wormholeList.add(shitter);
                things.add(shitter);
                System.out.println("Wormhole added to \"things\"");
                pullerIndex.add(datas[5]);
                pairTos.add((int)parsedDatas[5]);
            } else {
                System.out.println("ERROR: INCORRECT_ARGS");
                System.out.println("length of \"datas\": " + datas.length);
                crash("Given incorrect args for Wormhole");
            }
            i++;
        }
        pairStuff(wormholeList, pairTos);
        System.out.println("Done building wormholes!");
        System.out.println("Building dust storms...");
        i++;
        while(i < lines.length) {
            String data = lines[i];
            String[] datas = data.split(" ");
            System.out.println("data line: " + data);
            for(int z = 0; z < datas.length; z++) {
                System.out.println("data " + z + ": " + datas[z]);
            }
            if(datas.length == 5) {
                double[] parsedDatas = parseDoubles(datas);
                things.add(new DustStorm(parsedDatas[0], parsedDatas[1], parsedDatas[2], parsedDatas[3], (float)parsedDatas[4], level));
                System.out.println("Dust Storm added to \"things\"");
                pullerIndex.add("-1");
            } else if(datas.length == 6) {
                String[] modifiedDatas = new String[datas.length-1];
                for(int m = 0; m < modifiedDatas.length-1; m++) {
                    modifiedDatas[m] = datas[m];
                }
                modifiedDatas[modifiedDatas.length-1] = datas[datas.length-1];

                double[] parsedDatas = parseDoubles(modifiedDatas);

                pullerIndex.add(datas[5]);
                things.add(new DustStorm(parsedDatas[0], parsedDatas[1], parsedDatas[2], parsedDatas[3], (float)parsedDatas[4], level));
                System.out.println("Dust Storm added to \"things\"");
            } else {
                System.out.println("ERROR: INCORRECT_ARGS");
                System.out.println("length of \"datas\": " + datas.length);
                crash("Given incorrect args for Dust Storm");
            }
            i++;
        }
        System.out.println("Done building dust storms!");

        System.out.println("Dealing with \"things\"...");
        dealWithThings(things,pullerIndex);
        System.out.println("\"things\" dealt with!");


        System.out.println("Level assembly finished!");
        //~~~~~~~~~~~~~~~~~~~~
        //END LEVEL ASSEMBLY
        //~~~~~~~~~~~~~~~~~~~~

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
        new Planet(519.615242271, -300, 0.5f, 0, 0, 30, level, R.drawable.planet6);
        new Planet(519.615242271,  300, 0.5f, 0, 0, 30, level, R.drawable.planet6);
        new Planet(0,  600, 0.5f, 0, 0, 20, level, R.drawable.planet6);
        new Planet(-519.615242271,  300, 0.5f, 0, 0, 30, level, R.drawable.planet6);
        new Planet(-519.615242271, -300, 0.5f, 0, 0, 30, level, R.drawable.planet6);
    }
	//
  	//end
  	// THE BASICS
  	//start
  	// BLACK HOLES
	//
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
    //
    //End
    // BLACK HOLES
    //Start
    // DYNAMIC PLANETS
    private void genLevel15() {
        Earth earth = new Earth(0, 200, 0.5f, 0, 0, 75, level);
        earth.toggleGravity();
        new MainPlayer(0, 0, 6, 0, level);

        new Planet(0, 400, 0.5f, -6, 0, 50, level, R.drawable.planet3).addPuller(earth);
    }
    private void genLevel16() {
        Planet planet = new Planet(0, 300, 0.5f, 0, 0, 75, level, R.drawable.planet3);
        planet.toggleGravity();
        new MainPlayer(0, 0, 4, 0, level);

        new Earth(0, 600, 0.5f, -4, 0, 50, level).addPuller(planet);
    }
    private void genLevel17() {
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
    private void genLevel18() {
        new MainPlayer(0, 0, 10, 0, level);

        new Planet(0, 400, 0.5f, 0, 0, 200, level).toggleGravity();
        new Planet(0,-400, 0.5f, 0, 0, 200, level).toggleGravity();

        new Earth(0, -750, 0, 0, 50, level);
    }
    private void genLevel19() {
        new MainPlayer(0, 0, 2.5, 2.5, level);

        new Planet(400, 800, 0.5f, 0, 0, 50, level).toggleGravity();

        new Planet(800, 400, 0.5f, 0, 0, 100, level);

        new Planet(1500, 500, 0.5f, 0, 0, 100, level);
        new Planet(1500,-500, 0.5f, 0, 0, 100, level);

        new Earth(400, 2000, 0.5f, 0, 0, 50, level);
    }
  	private void genLevel20() {
    		 
    }
    private void genlevel21() {

    }
    private void genLevel22() {
        new DustStorm(0,-700,0,0,200,level);

        new Earth(-400,0,0,0,65,level);

        new MainPlayer(0,0,0,-4,level);
    }
    private void genLevel23() {
        new DustStorm(0,0,0,0,170,level);

        new Planet(-400,0,0,0,0,80,level,R.drawable.planet5).toggleGravity();
        new Planet(0,-400,0,0,0,80,level,R.drawable.planet5).toggleGravity();
        new Planet(400,0,0,0,0,80,level,R.drawable.planet5).toggleGravity();
        new Planet(0,400,0,0,0,80,level,R.drawable.planet5).toggleGravity();

        new Earth(-1300,-1300,0,0,75,level);

        new MainPlayer(0,0,0,0,level);
    }
    private void startScreen() {
        Earth earth = new Earth(0, 200, 0.5f, 0, 0, 75, level);
        earth.toggleGravity();
        new MainPlayer(0, 0, 6.12372, 0, level);

    }
}
