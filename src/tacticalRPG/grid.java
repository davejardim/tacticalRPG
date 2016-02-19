package tacticalRPG;

/**
 * Created by David on 2/15/16.
 */
public class Grid {

    public static final int TILESIZE=32;

    int[][] ground = new int[50][50];

    public int[][] getGroundLayer()
    {
        return ground;

    }

    public int getVal(int x, int y)
    {
        return ground[x][y];
    }



}
