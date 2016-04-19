package application.model.game;

import java.util.ArrayList;
import java.util.Collection;

import application.model.tile.UnitTile;
import application.model.unit.Unit;
import application.model.unit.UnitType;
import application.ui.CharacterSelectionView;
import application.ui.Controller;
import javafx.scene.layout.Pane;

public class CharacterTeam {

		private int teamNumber;
		private ArrayList<UnitTile> selected;
		private UnitType[][] characterGrid;
		private ArrayList<UnitTile> units;
		
		CharacterTeam(int num, CharacterSelection cs) {
			
		teamNumber = num;
		selected = new ArrayList<UnitTile>();
		
		int w = cs.getGridWidth();
		int h = cs.getGridHeight();
		
		characterGrid = new UnitType[w][h];
		
		
		CharacterSelectionView.addTeamSelection(num);
		Pane p;
		if(num == 1)
			p = CharacterSelectionView.team1Pane;
		else 
			p = CharacterSelectionView.team2Pane;
		
		
		for(int i = 0;  i < w; i++){
			for(int j = 0; j < h; j++){
				characterGrid[i][j] = UnitType.PIKACHU;
			}
		}
		
		characterGrid[0][0] =  UnitType.ASSASSIN;
		characterGrid[0][0] = UnitType.ASSASSIN;
		characterGrid[0][0] = UnitType.ASSASSIN;

		UnitTile t = null;
		for(int i = 0;  i < w; i++){
			for(int j = 0; j < h; j++){
				t = new UnitTile(i,j, p);
				t.setUnit(new Unit(i, j, characterGrid[i][j], teamNumber));
			}
		}
		
		
	}
		public void toggleSelection(UnitTile t){
			
			if (selected.contains(t))
			{
				selected.remove(t);
				t.setSelected(false);
				
			}else{
				if(selected.size()< CharacterSelection.NUMBEROFPLAYERS){
					selected.add(t);
					t.setSelected(true);
				}
			}
			
		}
		public boolean isReady() {
			if(selected.size() == CharacterSelection.NUMBEROFPLAYERS)
				return true;
			return false;
		}
		public Collection<UnitTile> getPlayers() {
			return selected;
		}

}
