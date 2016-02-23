package tacticalRPG.game;

import javafx.scene.image.Image;
import tacticalRPG.Main;

/**
 * Created by David on 2/19/16.
 */
public class Actor {
    private int positionX;
    private int positionY;

    private Image icon;

    public Actor() {
        positionX = 5;
        positionY = 5;

        icon = new Image(Main.class.getResourceAsStream("mikes_head.png"));
        //System.out.println(icon.getWidth() +" " + icon.getHeight() + " ");
    }

    public Image getImage(){
        return icon;
    }

    public Actor(int x, int y) {
        positionX = x;
        positionY = y;
    }

    public void move(int dir){
        switch(dir){
            case 1:
                positionY = positionY - 1;
                break;
            case 2:
                positionX = positionX + 1;
                break;
            case 3:
                positionY = positionY + 1;
                break;
            case 4:
                positionX = positionX - 1;
                break;
        }
    }
    public int getPositionX(){return positionX;}
    public int getPositionY(){return positionY;}

}
