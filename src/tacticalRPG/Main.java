package tacticalRPG;

import javafx.animation.AnimationTimer;
import javafx.application.Application;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

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
		root.getChildren().add( canvas );

		GraphicsContext gc = canvas.getGraphicsContext2D();


		//Game init.
		Game game = new Game();
		Render renderer = new Render(game);

		new AnimationTimer()
		{
			public void handle(long currentNanoTime)
			{

				renderer.updateScreen();
			}
		}.start();


		theStage.show();
	}

}