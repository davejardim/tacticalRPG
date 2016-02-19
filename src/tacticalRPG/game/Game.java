package tacticalRPG.game;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * This class will contain all user specific information. This
 * will allow us to save and reload the user's game simply
 * by taking a snapshot of this class.
 * @author Mark Bluemer
 *
 */
@Entity

public class Game implements Serializable {
	private static final long serialVersionUID = 1L;

   @Id @GeneratedValue
   long id;

   private Grid grid;
   
   private String saveName;
	
   public Game (String saveName) {
	   // Here we will grab and initiate any information necessary when a 
	   // new game is created

       grid = new Grid();

	   this.saveName = saveName;
   }

   public Grid getGrid(){ return grid;}


   
   public String getSaveName () {return saveName; }
   public void setSaveName (String name) { this.saveName = name; }
}
