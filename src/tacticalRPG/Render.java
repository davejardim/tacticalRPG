package tacticalRPG;

/**
 * Created by David on 2/15/16.
 */
public class Render {

    Game game;
    Grid ground;


    public Render(Game g)
    {
        game = g;
        ground = game.getGround();
    }

    public void updateScreen()
    {
        updateGound();
    }

    private void updateGround(){

    }
}
