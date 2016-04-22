package application.ui;

import java.util.ArrayList;

import application.model.game.Game;
import application.model.unit.Unit;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Animation {

	public static ArrayList<int[]> getPathCoordinates(Unit unit, int fromX, int fromY, int toX, int toY)
	//Using A* algorithm
	{
		boolean[][] possibleNodes = Game.getValidMoves(fromX, fromY, unit.getMaxMove());
		ArrayList<int[]> visitedNodes = new ArrayList<>();
		ArrayList<int[]> needEvaluation = new ArrayList<>();
		needEvaluation.add(new int[] {0 , fromX , toX} );
		
		return null;
	}
		
	
	public static void moveUnit(Unit unit, int toX, int toY)
	{
		int fromX = unit.getXCord();
		int fromY = unit.getYCord();
		SequentialTransition sqTrans = new SequentialTransition();
		ImageView imV = new ImageView(unit.getImage());
		double distanceTravelled = Math.abs(toX + toY-fromX-fromY);
		double travelTime = 80*Math.cbrt(distanceTravelled)+25*Math.sqrt(distanceTravelled)+distanceTravelled;
		TranslateTransition translateTransition = new TranslateTransition(Duration.millis(travelTime), imV);
		translateTransition.setFromX(fromX);
		translateTransition.setFromY(fromY);
		translateTransition.setToX(toX);
		translateTransition.setToY(toY);
	}
}
