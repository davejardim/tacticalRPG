package tacticalRPG.db;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import tacticalRPG.game.Game;

/**
 * This class will handle all db interfacing such as saving, loading,
 * and updating user games.
 * @author markbluemer
 *
 */
public class DataManager {
	/**
	 * Create a new Game object and save it
	 * @return Return the game object so that it can be set to current game
	 */
	public Game startNewGame (String saveName) {
		Game newGame = new Game(saveName, "");
		saveGame(newGame);
       
		return newGame;
	}
	
	/**
	 * Save game object 
	 * @param g The game object that is going to be saved
	 */
	public void saveGame (Game g) {
		EntityManagerFactory emf =
				Persistence.createEntityManagerFactory("db/games.odb");
		EntityManager em = emf.createEntityManager();
		
		Game existing = em.find(Game.class, g);
		em.getTransaction().begin();
		
		if (existing != null) {
			
			existing.setUserName(g.getUserName());
			existing.setGrid(g.getGrid());
			
			/*
			Scanner in = new Scanner(System.in);
			System.out.println("Save already exists. Would you like to overwrite? (y/n)");
			String choice = in.next();
			
			if (choice.equals("y")) {
				//em.merge(g);
				// TODO: Find a way to update object without manually setting each field
				// This is current a workaround
				existing.setUserName(g.getUserName());
				existing.setGrid(g.getGrid());
			} else if (!choice.equals("n")){
				System.out.println("Invalid input. Please try again.");
			}
			
			in.close();
			*/
		} else {
			em.persist(g);
		}
		em.getTransaction().commit();
		
		em.close();
		emf.close();
	}
	
	/**
	 * Grab list of all saved games
	 * @return List of all saved Game objects
	 */
	public List<Game> loadGameList () {
		EntityManagerFactory emf =
				Persistence.createEntityManagerFactory("db/games.odb");
		EntityManager em = emf.createEntityManager();
		
		TypedQuery<Game> query = 
				em.createQuery("SELECT g FROM Game g", Game.class);
		List<Game> results = query.getResultList();
		
		em.close();
		emf.close();
		
		return results;
	}
}
