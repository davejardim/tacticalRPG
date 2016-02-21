package tacticalRPG.game;

/**
 * Created by David on 2/15/16.
 */
public class Grid {

    public static final int TILESIZE=32;

    int[][] grid;
    int w;
    int h;

    public Grid() {
        grid = new int[50][50];
        h=50;
        w=50;
    }
    public int[][] getGroundLayer() {
        return grid;

    }

    public int getVal(int x, int y)
    {
        return grid[x][y];
    }

    public int getWidth() {return w;}
    public int getHeight(){return h;}


}
