package tacticalRPG;

import javafx.print.PageLayout;

import java.util.ArrayList;

/**
 * Created by David on 2/15/16.
 */
public class Level {

    Grid ground;
    Actor player;

    public Level()
    {
        ground = new Grid();
        player = new Actor();
    }

    public Grid getGround()
    {
        return ground;
    }

}
