package tacticalRPG;

/**
 * Created by David on 2/15/16.
 */
public class Game {

    Level level;

    public Game() {
        level = new Level();
    }

    public Grid getGround() {
       return level.getGround();
    }


}
