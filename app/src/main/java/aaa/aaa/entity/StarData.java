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
                    },
                    {
                            {400,200},
                            {400,0},
                            {400,-200}
                    }
            },
            {//2
                    {
                            {200,1100},
                            {300,700},
                            {150,200}
                    },
                    {
                            {-250,250},
                            {-250,-250},
                            {250,-250},
                            {250,250}
                    }
            },
            {//3
                    {
                            {125,1200},
                            {70,500}
                    },
                    {
                            {500,1100},
                            {500,1700},
                            {240,1000},
                            {120,250}
                    }
            },
            {//4
                    {
                            {-50,-1200},
                            {650,-250},
                            {400,-625}
                    },
                    {
                            {400,-1000},
                            {1000,-250},
                            {200,0}
                    },
                    {
                            {400,-1000},
                            {200,-250}
                    }
            },
            {//5
                    {
                            {300,600},
                            {500,-300},
                            {700,-300},
                            {140,0}
                    },
                    {
                            {200,600},
                            {150,0}
                    }
            },
            {//6
                    {
                            {150,-500},
                            {70,-150}
                    },
                    {
                            {200,-500},
                            {0,80},
                            {-50,-590},
                    },
            },
            {//7
                    {
                            {-500,0},
                            {200,-980},
                            {140,-140}
                    },
                    {
                            {-300,0},
                            {200,-780},
                            {-50,-500}
                    }
            },
            {//8
                    {
                            {-400,-1000},
                            {400,-2000},
                            {-400,-3000},
                            {400,-4000},
                            {-400,-5000}
                    },
                    {
                            {-550,-1000},
                            {-900,-2000},
                            {-800,-3000},
                            {-700,-4000},
                            {-400,-5000}
                    }
            },
            {//9

            },
            {//10
                    {
                            {0,-150},
                            {0,250},
                            {250,325},
                            {100,500}
                    }
            },
            {//11

            },
            {//12
                    {
                            {-100,-300},
                            {-1500,-200}
                    }
            },
            {//13
                    {
                            {600,-200},
                            {300,-500},
                            {-50,-300}
                    }
            },
            {//14
                    {
                            {400,-500},
                            {0,-500}
                    },
                    {
                            {400,-500},
                            {-500,0},
                            {0,-300}
                    }
            }
    };
}
