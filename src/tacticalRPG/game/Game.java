package tacticalRPG.game;

import javax.persistence.Entity;
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

   @Id
   private String saveName;
   
   private String userName;
   private Grid grid;
	
   public Game (String saveName, String userName) {
	   // Here we will grab and initiate any information necessary when a 
	   // new game is created

       grid = new Grid();

	   this.saveName = saveName;
	   this.userName = userName;
   }

   public Grid getGrid(){ return grid;}
   public String getSaveName () { return saveName; }
   public void setSaveName (String name) { this.saveName = name; }
   public String getUserName () { return userName; }
   public void setUserName (String name) { this.userName = name; }
}
