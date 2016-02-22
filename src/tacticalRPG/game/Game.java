package tacticalRPG.game;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.ArrayList;

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

   private ArrayList<Actor> actors;
   private Actor player;

   private String saveName;
   private String userName;
   private Grid grid;
	
   public Game (String saveName, String userName) {
	   // Here we will grab and initiate any information necessary when a 
	   // new game is created

       grid = new Grid();
      actors = new ArrayList<Actor>();

	   this.saveName = saveName;
	   this.userName = userName;
   }

   public Grid getGrid(){ return grid;}



   public ArrayList<Actor> getActors(){
      return actors;
   }

   public void addActor(Actor a){
      actors.add(a);
   }

   public void setPlayer(Actor a){
      player = a;
   }

   public Actor getPlayer(){
      return player;
   }
   public String getSaveName () {return saveName; }

   public void setGrid(Grid g) { this.grid = g; } 

   public void setSaveName (String name) { this.saveName = name; }
   public String getUserName () { return userName; }
   public void setUserName (String name) { this.userName = name; }
}
