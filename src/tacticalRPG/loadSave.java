package tacticalRPG;

import tacticalRPG.game.Game;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by David on 2/19/16.
 */
public class loadSave {
    /**
     * Create a new Game object and save it
     *
     * @return Return the game object so that it can be set to current game
     */
    public static Game startNewGame(String saveName) {
        Game newGame = new Game(saveName);
        saveGame(newGame);

        return newGame;
    }

    /**
     * Save game object
     *
     * @param g The game object that is going to be saved
     */
    public static void saveGame(Game g) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("db/games.odb");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            try {
                em.merge(g);
            } catch (Exception e) {
                System.out.println(e);
            }
        } finally {
            em.getTransaction().commit();
        }
        em.close();
        emf.close();
    }
}

