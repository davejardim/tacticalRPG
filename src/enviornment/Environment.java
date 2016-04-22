package enviornment;

import java.util.ArrayList;
import java.util.List;
import application.model.tile.UnitTile;
import javafx.scene.image.Image;

public class Environment {

	private int yCord, xCord;
	private Image image;
	private EnvironmentType type;

	public Environment(int x, int y, EnvironmentType type) {
		this.xCord = x;
		this.yCord = y;
		this.image = type.getSprite();
		this.type = type;
	}
	
	public int getXCord() {
		return this.xCord;
	}

	public int getYCord() {
		return this.yCord;
	}
	
	public EnvironmentType getType() {
		return this.type;
	}

	
}
