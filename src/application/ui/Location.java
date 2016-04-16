package application.ui;

public class Location {
	private int xCord;
	private int yCord;
	public Location(int x, int y){
		setX(x);
		setY(y);
	}
	public int getX() {
		return xCord;
	}
	public void setX(int xCord) {
		this.xCord = xCord;
	}
	public int getY() {
		return yCord;
	}
	public void setY(int yCord) {
		this.yCord = yCord;
	}
	
	public String toString(){
		return "Location: "  + xCord+ ", " + yCord;
	}
	
	public void setCoordinate(int x, int y) {
		if(x >= 0 && x <= Controller.currentGame.xSize && y >= 0 && y<= Controller.currentGame.ySize){
			xCord = x;
			yCord = y;
		//	System.out.println(this);
		}
		
	}
	
}
