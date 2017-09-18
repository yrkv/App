package aaa.aaa.entity;

/**
 * Created by USER on 9/13/2017.
 */

public class StarData {
    //This is where all of the Stars are hardcoded in; formatted [Level][StarPath][NumInPath][x/y]
    public static final int[][][][] STAR_CONTAINER = {
            {//level 1
                    {//path 0 - red
                            {-400, -400},
                            {400, 400}
                    },
                    {//path 1 - green
                            {400, -400},
                            {-400, 400}
                    }
            }
    };
}
