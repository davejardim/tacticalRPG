package enviornment;

import java.util.ArrayList;
import java.util.List;
import application.model.tile.UnitTile;
import javafx.scene.image.Image;

public class Enviornment {

	private int yCord, xCord;
	private Image image;


	private EnviornmentType type;

	public Enviornment(int x, int y, EnviornmentType type) {
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
	
	public EnviornmentType getType() {
		return this.type;
	}

	
}
