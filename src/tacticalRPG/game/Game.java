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

   private Grid grid;
   private ArrayList<Actor> actors;
   private Actor player;
   
   private String saveName;
	
   public Game (String saveName) {
	   // Here we will grab and initiate any information necessary when a 
	   // new game is created

       grid = new Grid();
      actors = new ArrayList<Actor>();

	   this.saveName = saveName;
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
   public void setSaveName (String name) { this.saveName = name; }
}
