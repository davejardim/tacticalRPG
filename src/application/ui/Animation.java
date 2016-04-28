package application.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

import application.Main;
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
		//Comparator evaluates distances to within one tenth of a square
		Comparator<int[]> comp = new Comparator<int[]>() {

			@Override
			public int compare(int[] node1, int[] node2) {
				int score1 = node1[0] + node1[1];
				int score2 = node2[0] + node2[1];
				return score1 - score2;
			}
		};
		PriorityQueue<int[]> Q = new PriorityQueue<>(comp);
		//Nodes have form {exact cost, heuristic cost, x, y}
		Q.add(new int[] {0, heuristicDist(fromX, fromY, toX, toY) , fromX , fromY} );

		//Populate queue from possible movements
		for (int i = 0; i < Main.LEVEL_WIDTH ; i++)
		{
			for (int j = 0; j < Main.LEVEL_HEIGHT ; j++)
			{
				if (possibleNodes[i][j] && (i != fromX || j != fromY))
				{
					Q.add(new int[] {1000, heuristicDist(i,j,toX,toY), i, j});
				}
			}
		}

		HashMap<int[], int[]> cameFrom = new HashMap<>();

		while(!Q.isEmpty())
		{
			int[] current = Q.remove();
			if ((int)current[2] == toX && (int)current[3] == toY)
				return reconstruct(current, cameFrom);
			ArrayList<int[]> nodeNeighbours = new ArrayList<>();
			for (int[] element : Q)
			{
				//Top Neighbour
				if (element[1] == current[1] && element[2] == current[2] + 1)
				{
					if (element[0] > current[0] + 1) {
						cameFrom.put(new int[] {element[2] , element[3]} , new int[] {current[2] , current[3]});
						element[0] = current[0] + 1;
						nodeNeighbours.add(element);
					}
				}
				//Right Neighbour
				else if (element[1] == current[1] + 1 && element[2] == current[2])
				{
					if (element[0] > current[0] +1) {
						cameFrom.put(new int[] {element[2] , element[3]} , new int[] {current[2] , current[3]});
						element[0] = current[0] + 1;
						nodeNeighbours.add(element);

					}
				}
				//Bottom Neighbour
				else if (element[1] == current[1] && element[2] == current[2] - 1)
				{
					if (element[0] > current[0] +1) {
						cameFrom.put(new int[] {element[2] , element[3]} , new int[] {current[2] , current[3]});
						element[0] = current[0] + 1;
						nodeNeighbours.add(element);
					}
				}
				//Left Neighbour
				else if (element[1] == current[1] - 1 && element[2] == current[2])
				{
					if (element[0] > current[0] +1) {
						cameFrom.put(new int[] {element[2] , element[3]} , new int[] {current[2] , current[3]});
						element[0] = current[0] + 1;
						nodeNeighbours.add(element);
					}
				}
			}
			//Resort neighbours back into priority queue:
			for (int[] neighbour : nodeNeighbours)
			{
				Q.remove(neighbour);
				Q.add(neighbour);
			}
		}

		return null;
	}

	public static int heuristicDist(int fromX, int fromY, int toX, int toY) {
		//Manhattan distance:
		return (Math.abs(toX - fromX) + Math.abs(toY-fromY));
	}

	public static ArrayList<int[]> reconstruct(int[] current, HashMap<int[], int[]> cameFrom) {
		ArrayList<int[]> path = new ArrayList<>();

		while (current != null) {
			path.add(current);
			current = cameFrom.get(current);
		}
		Collections.reverse(path);
		return path;
	}


	public static void moveUnit(Unit unit, int toX, int toY) {
		int fromX = unit.getXCord();
		int fromY = unit.getYCord();
		SequentialTransition sqTrans = new SequentialTransition();
		ImageView imV = new ImageView(unit.getImage());
		ArrayList<int[]> path = getPathCoordinates(unit, fromX, fromY, toX, toY);
		for (int[] corner : path) {
			int cX = corner[0];
			int cY = corner[1];
			double distanceTravelled = Math.abs(cX + cY-fromX-fromY);
			double travelTime = 80*Math.cbrt(distanceTravelled)+25*Math.sqrt(distanceTravelled)+distanceTravelled;
			TranslateTransition translateTransition = new TranslateTransition(Duration.millis(travelTime), imV);
			translateTransition.setFromX(fromX);
			translateTransition.setFromY(fromY);
			translateTransition.setToX(toX);
			translateTransition.setToY(toY);
			sqTrans.getChildren().add(translateTransition);
			fromX = cX;
			fromY = cY;
		}
		
		sqTrans.play();
	}
}
