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

	
	// Add the chosen character type to the current team and then add to view
	public void addChar(CharSelectTile selectedChar) {
		if (currTeam == 1) {
			charTeam1.add(new Unit(0, 0, selectedChar.getType(), 1));
		} else {
			charTeam2.add(new Unit(0, 0, selectedChar.getType(), 2));
		}
		view.addCharToView(selectedChar, currTeam);
		switchTeams();
	}
	
	// Switch teams or finish choosing
	private void switchTeams() {
		if (!isDone()) {
			currTeam = (currTeam == 1) ? 2 : 1;
			view.switchTeams();
		} else {
			view.switchToDone();
		}
	}
	
	private boolean isDone() {
		return charTeam2.size() == 2;
	}

}


