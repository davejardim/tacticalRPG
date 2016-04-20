package application.model.game;

import java.util.ArrayList;

import application.model.tile.CharSelectTile;
import application.model.unit.Unit;
import application.model.unit.UnitType;
import application.ui.CharacterSelectionView;

public class CharacterSelection {

	protected static final int NUMBEROFPLAYERS = 5;
	protected int gridWidth = 5;
	protected int gridHeight = 4;
	
	private CharacterSelectionView view;
	private int currTeam;
	
	private ArrayList<Unit> charTeam1, charTeam2;



	public CharacterSelection(){
		view = new CharacterSelectionView(this);
		currTeam = 1;
		charTeam1 = new ArrayList<Unit>();
		charTeam2 = new ArrayList<Unit>();
	}
	
	public int getGridWidth(){
		return gridWidth;
	}
	
	public int getGridHeight(){
		return gridHeight;
	}	
	
	public CharacterSelectionView getView() {
		return view;
	}

//	public void onClick(int team, UnitTile unitTile) {
//
//		if(team == 1)
//			charTeam1.toggleSelection(unitTile);
//		if(team == 2)
//			charTeam2.toggleSelection(unitTile);
//		
//		if(charTeam1.isReady() && charTeam2.isReady()){
//			//Controller.charSelectionMenu.setVisible(false);
//			Controller.currentGame.startGame(charTeam1.getPlayers(), charTeam2.getPlayers());
//		}
//	}
	
	public void onClick(CharSelectTile selectedUnit) {
		addChar(selectedUnit.getType());
		view.addCharToView(selectedUnit, currTeam);
	}
	
	// Add the chosen character type to the current team
	public void addChar(UnitType type) {
		if (currTeam == 1) {
			charTeam1.add(new Unit(0, 0, type, 1));
		} else {
			charTeam2.add(new Unit(0, 0, type, 2));
		}
	}

}


