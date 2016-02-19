package tacticalRPG;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * Created by David on 2/15/16.
 */
public class Render {

    private static final Paint BGCOlOR = Color.WHITE; //Background color
    private static final Paint TEXTCOlOR = Color.BLACK; //Background color
    Game game;
    Grid ground;


    public Render(Game g)
    {
        game = g;
        ground = game.getGround();
    }

    public void updateScreen(GraphicsContext gc, double t) {

        //clear the screen
        gc.setFill(BGCOlOR);
        gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());

        //draw ground with basic rectangles
        Grid ground = game.getGround();
        int width = ground.getGroundLayer().length;
        int height = ground.getGroundLayer()[1].length;

        gc.setFill(Color.BLACK);

        for(int x = 0; x < width; x++)
        {
            for(int y = 0; y < height; y++)
            {
                if(ground.getVal(x,y) == 0) {
                    gc.setFill(Color.BLACK);
                    gc.strokeRect(x * ground.TILESIZE, y * ground.TILESIZE, ground.TILESIZE, ground.TILESIZE);
                } else if (ground.getVal(x,y) == 1){
                    gc.setFill(Color.BLUE);
                    gc.fillRect(x * ground.TILESIZE, y * ground.TILESIZE, ground.TILESIZE, ground.TILESIZE);

                }

                //gc.fillText(x+ " " + y ,x,y,2);
            }
        }
        gc.setFill(Color.BLUE);
        //gc.fillText(width + " " + height, 32, 32);

    }

}
