package tacticalRPG;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Created by David on 2/15/16.
 */
public class Input {
    Stage stage;
    ArrayList<String> inputList = new ArrayList<String>();

    double lastMouseClickx = 0;
    double lastMouseClicky = 0;

    boolean isMouseClicked = false;

    private class Location {
        int x;
        int y;
        Location(int a, int b) {
            x = a;
            y = b;
        }
        int getX() {return x;}
        int getY() {return y;}

    }


    public Input(Stage s) {
        stage =  s;


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

    public ArrayList<String> getCurrentInputList(){
        return inputList;
    }

    private Location getGridLocation()
    {
        return new Location(0,0);
    }


    public boolean isMouseClicked() { return isMouseClicked;}
}
