package application.model.tile;

import application.ui.environmentTile.EnvironmentTileView;

public class EnvironmentTile {

	private int xCord, yCord;
	private EnvironmentTileView view;
	
	public EnvironmentTile(int x, int y, double tileSize) {
		this.xCord = x;
		this.yCord = y;
		this.view = new EnvironmentTileView(this, tileSize);
	}

	public int getXCord() {
		return this.xCord;
	}
	
	public int getYCord() {
		return this.yCord;
	}
	
	public EnvironmentTileView getView() {
		return this.view;
	}
}
