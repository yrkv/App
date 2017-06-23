package aaa.aaa.level;

/**
 * Created by Kaleb on 6/22/2017.
 */

public enum LevelData {
    empty(-1),
    planet(0x000000),
    player(0x0000ff);

    final int color;

    private LevelData(int color) {
        this.color = color;
    }
}
