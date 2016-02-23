package tacticalRPG;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import tacticalRPG.game.Actor;
import tacticalRPG.game.Game;
import tacticalRPG.game.Grid;

import java.util.ArrayList;

/**
 * Created by David on 2/15/16.
 */
public class Render {

    private static final Paint BGCOlOR = Color.WHITE; //Background color
    //private static final Paint TEXTCOlOR = Color.BLACK; //Background color


    private Game game;
    private GraphicsContext gc;
    private Grid ground;
    private ArrayList<Actor> actors;

    boolean showMenu = false;
    private int screenW;
    private int screenH;


    public Render(GraphicsContext grc)
    {
        game = null;
        actors = null;
        gc = grc;
    }
    public Render(Game g, GraphicsContext grc)
    {
        game = g;
        actors = g.getActors();
        gc = grc;
    }

    public void setGame(Game g){
    	game = g;
    }

    public void updateScreen(double t) {



        //clear the screen
        gc.setFill(BGCOlOR);
        gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());

        if(game != null){
        	drawGround();
        	drawActors();
        }

        if(showMenu)
            drawMenu(0.3);

        
    }

    public void drawGround()
    {
        ground = game.getGrid();
        //ground.get
        //draw ground with basic rectangles
        //int width = ground.getGroundLayer().length;
        //int height = ground.getGroundLayer()[1].length;

        gc.setFill(Color.BLACK);

        for (int x = 0; x < ground.getWidth(); x++) {
            for (int y = 0; y < ground.getHeight(); y++) {
                if (ground.getVal(x, y) == 0) {
                    gc.setFill(Color.BLACK);
                    gc.strokeRect(x * Grid.TILESIZE, y * Grid.TILESIZE, Grid.TILESIZE, Grid.TILESIZE);
                } else if (ground.getVal(x, y) == 1) {
                    gc.setFill(Color.BLUE);
                    gc.fillRect(x * Grid.TILESIZE, y * Grid.TILESIZE, Grid.TILESIZE, Grid.TILESIZE);

                }

                //gc.fillText(x+ " " + y ,x,y,2);
            }
        }
        gc.setFill(Color.BLUE);
        //gc.fillText(width + " " + height, 32, 32);
    }

    public void drawMenu(double opacity)
    {
        screenW = (int)gc.getCanvas().getWidth();
        screenH = (int)gc.getCanvas().getHeight();

        gc.setGlobalAlpha(opacity);
        gc.setFill(Color.gray(0.5, 0.5));
        gc.fillRect(0, 0, screenW, screenH);


        gc.setGlobalAlpha(1);
        gc.setFill(Color.TAN);
        gc.fillRect(screenW/2 - 75, screenH/2 - 20, 150, 40);
        gc.fillRect(screenW/2 - 75, 60 + screenH/2 - 20, 150, 40);
        gc.setFill(Color.BROWN);
        gc.fillText("New Game[n]", screenW / 2 - 40, screenH / 2 + 5);

        gc.fillText("Load Game[l]", screenW / 2 - 40, screenH / 2 + 65);
    }

    public void drawActors(){
        int s = Grid.TILESIZE;

        if(actors != null){
        for(Actor a : actors) {
            gc.setFill(Color.RED);
            //gc.fillOval(a.getPositionX() * s, a.getPositionY() * s, s, s);
     
            gc.drawImage(a.getImage(),a.getPositionX()*s,a.getPositionY()*s,s,s);
            //System.out.println("drawing actors?");
        }
        }
    }
    public void setShowMenu(boolean b)
    {
        showMenu = b;
    }

    public boolean isMenuShown() {return showMenu;}


}
