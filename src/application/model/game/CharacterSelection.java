package application.model.game;

import java.util.ArrayList;

import application.Main;
import application.model.tile.UnitTile;
import application.model.unit.Unit;
import application.model.unit.UnitType;
import application.ui.CharacterSelectionView;
import application.ui.Controller;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

public class CharacterSelection {

protected static final int NUMBEROFPLAYERS = 5;
protected int gridWidth = 5;
protected int gridHeight = 4;

private CharacterSelectionView view;

private CharacterTeam charTeam1, charTeam2;



	public CharacterSelection(){
		
		view = new CharacterSelectionView(this);
		charTeam1 = new CharacterTeam(1, this);
		charTeam2 = new CharacterTeam(2, this);
	}
	
	public int getGridWidth(){return gridWidth;}
	public int getGridHeight(){return gridHeight;}
	

	

	

	public void onClick(int team, UnitTile unitTile) {

		if(team == 1)
			charTeam1.toggleSelection(unitTile);
		if(team == 2)
			charTeam2.toggleSelection(unitTile);
		
		if(charTeam1.isReady() && charTeam2.isReady()){
			Controller.charSelectionMenu.setVisible(false);
			Controller.currentGame = new Game();
			Controller.currentGame.startGame(charTeam1.getPlayers(), charTeam2.getPlayers());
		}
	}

}


