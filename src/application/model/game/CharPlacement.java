package application.model.game;

import application.ui.CharPlacementView;
import application.ui.SelectionTile;

public class CharPlacement {
	private CharPlacementView view;
	private CharacterSelection charSelect;
	private SelectionTile selectedTile;
	
	public CharPlacement(CharacterSelection charSelect) {
		selectedTile = null;
		this.charSelect = charSelect;
		view = new CharPlacementView(this);
	}
	
	public CharPlacementView getView() {
		return view;
	}
	
	public CharacterSelection getSelectedChars() {
		return charSelect;
	}
	
	public void onClick(SelectionTile tile) {
		if (selectedTile != null){
			selectedTile.removeHighlight();
		}
		tile.setHighlight();
		selectedTile = tile;
	}
	
	public SelectionTile getSelected() {
		return selectedTile;
	}
}
