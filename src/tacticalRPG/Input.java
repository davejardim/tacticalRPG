package tacticalRPG;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tacticalRPG.db.DataManager;
import tacticalRPG.game.model.Actor;
import tacticalRPG.game.model.Game;

import java.util.ArrayList;

/**
 * Created by David on 2/15/16.
 */
public class Input {
    Stage stage;
    ArrayList<String> inputList = new ArrayList<String>();

    private Game game;
    
    private DataManager link;
    private Render render;

    double lastMouseClickx = 0;
    double lastMouseClicky = 0;

    boolean isMouseClicked = false;


    public Input(Stage s, DataManager l, Render r){
    	stage = s;
    	link = l;
    	render = r;
    }
    public void checkInput(Scene scene) {

        scene.setOnMousePressed(
                new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event) {
                        lastMouseClickx = event.getSceneX();
                        lastMouseClicky = event.getSceneY();
                        isMouseClicked = true;


                    }
                }
        );

        scene.setOnMouseReleased(
                new EventHandler<MouseEvent>() {
                    public void handle(MouseEvent event) {
                        isMouseClicked = false;
                    }
                });

        scene.setOnKeyPressed(
                new EventHandler<KeyEvent>() {
                    public void handle(KeyEvent e) {
                        String code = e.getCode().toString();

                        // only add once... prevent duplicates
                        if (!inputList.contains(code))
                            inputList.add(code);

                        checkKeys();

                    }
                });

        scene.setOnKeyReleased(
                new EventHandler<KeyEvent>() {
                    public void handle(KeyEvent e) {
                        String code = e.getCode().toString();
                        inputList.remove(code);
                    }
                });


    }


    public void checkKeys()
    {
    	if(game != null){
        if (inputList.contains("UP"))
            game.getPlayer().move(1);
        if (inputList.contains("RIGHT"))
            game.getPlayer().move(2);
        if(inputList.contains("DOWN"))
            game.getPlayer().move(3);
        if(inputList.contains("LEFT"))
            game.getPlayer().move(4);
        if(inputList.contains("ESCAPE")){
        	if(render.isMenuShown())
        		render.setShowMenu(false);
        	else 
        		render.setShowMenu(true);
        	}
    	}
    	
    	
    	//load a new game
    	if(inputList.contains("N") && render.isMenuShown())
    	{
    		game = link.startNewGame("test");
    		render.setGame(game);
    		 //add a default player
            Actor mike = new Actor();
            game.addActor(mike);
            game.setPlayer(mike);
            render.setShowMenu(false);
    	}
    }


    public ArrayList<String> getCurrentInputList(){
        return inputList;
    }


    public boolean isMouseClicked() { return isMouseClicked;}
}
