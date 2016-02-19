package tacticalRPG;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.util.Duration;

public class Main extends Application
{


	public static void main(String[] args)
	{
		launch(args);
	}

	public void start(Stage theStage)
	{
		theStage.setTitle("Timeline Example");

		Group root = new Group();
		Scene theScene = new Scene( root );
		theStage.setScene(theScene);

		Canvas canvas = new Canvas( 512, 512 );
		root.getChildren().add(canvas);

		GraphicsContext gc = canvas.getGraphicsContext2D();


		//Game init.
		Game game = new Game();
		Render renderer = new Render(game);
		Input input = new Input(theStage);

		//Frame-based animation
		Timeline gameLoop = new Timeline();
		gameLoop.setCycleCount( Timeline.INDEFINITE );
		final long timeStart = System.currentTimeMillis();
		KeyFrame kf = new KeyFrame(
				Duration.seconds(0.017),                // 60 FPS
				new EventHandler<ActionEvent>()
				{
					public void handle(ActionEvent ae)
					{
						double t = (System.currentTimeMillis() - timeStart) / 1000.0;

						//input and rendering screen
						input.checkInput(theScene);
						renderer.updateScreen(gc,t);

						//debugging
						gc.setFill(Color.AQUAMARINE);
						gc.fillRect(5, 10, 125, 75);
						gc.setFill(Color.BLACK);
						gc.fillText("Time: "+ t + "\n" + "Key Input: " + input.getCurrentInputList().toString(), 10, 25);

						//gc.fillText(t+ " ", 30, 50);
					}
				});

		gameLoop.getKeyFrames().add( kf );
		gameLoop.play();

		theStage.show();
	}

}