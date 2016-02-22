package tacticalRPG;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import tacticalRPG.game.Actor;

import tacticalRPG.db.DataManager;

import tacticalRPG.game.Game;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage theStage) {

        //Canvas, Stage, and GraphicsContext Init.
        theStage.setTitle("Timeline Example");

        Group root = new Group();
        Scene theScene = new Scene(root);
        theStage.setScene(theScene);

        Canvas canvas = new Canvas(512, 512);
        root.getChildren().add(canvas);

        GraphicsContext gc = canvas.getGraphicsContext2D();


        //Game init.
        DataManager link = new DataManager();
        Game game = link.startNewGame("test");
        Render renderer = new Render(game, gc);
        renderer.setShowMenu(false);
        Input input = new Input(theStage, game);

        //add a default player
        Actor mike = new Actor();
        game.addActor(mike);
        game.setPlayer(mike);


        //Frame-based animation
        Timeline gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        final long timeStart = System.currentTimeMillis();
        KeyFrame kf = new KeyFrame(
                Duration.seconds(0.017),                // 60 FPS
                new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent ae) {
                        double t = (System.currentTimeMillis() - timeStart) / 1000.0;

                        if(t%2 == 0) {
                            canvas.setHeight(theStage.getHeight());
                            canvas.setWidth(theStage.getWidth());
                        }
                        //input and rendering screen
                        input.checkInput(theScene);
                        renderer.updateScreen(t);

                        //debugging box
                        gc.setFill(Color.AQUAMARINE);
                        gc.fillRect(5, 10, 125, 75);
                        gc.setFill(Color.BLACK);
                        gc.fillText("Debugging:\nTime: " + t + "\n" + "Key Input: " + input.getCurrentInputList().toString(), 10, 25);

                        //gc.fillText(t+ " ", 30, 50);
                    }
                });

        gameLoop.getKeyFrames().add(kf);
        gameLoop.play();

        theStage.show();
    }

}
