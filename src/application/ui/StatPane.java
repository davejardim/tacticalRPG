package application.ui;

import application.model.tile.CharSelectTile;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class StatPane extends GridPane {
	
	private final int TEXT_SIZE = 16;
	
	private Text hp, attack, def, move;
	
	public StatPane(CharSelectTile selected) {
		this.setMinSize(150, 300);
		
		
		hp = new Text();
		attack = new Text();
		def = new Text();
		move = new Text();
		hp.setFont(new Font("Didot Bold", TEXT_SIZE));
		attack.setFont(new Font("Didot Bold", TEXT_SIZE));
		def.setFont(new Font("Didot Bold", TEXT_SIZE));
		move.setFont(new Font("Didot Bold", TEXT_SIZE));
		
		hp.setText("HP: " + selected.getType().hp());
		hp.setY(200);
		attack.setText("Attack: " + selected.getType().attack());
		attack.setY(200+TEXT_SIZE);
		def.setText("Defense: " + selected.getType().def());
		def.setY(200+TEXT_SIZE*2);
		move.setText("Move distance: " + selected.getType().maxMove());
		move.setY(200+TEXT_SIZE*3);
		
		this.setPadding(new Insets(10, 10, 10, 10));
		this.add(hp, 0, 0);
		this.add(attack, 0, 1);
		this.add(def, 0, 2);
		this.add(move, 0, 3);
	}	
	
	public StatPane() {
		
	}
}
