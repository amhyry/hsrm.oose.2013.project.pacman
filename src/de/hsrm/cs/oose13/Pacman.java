package de.hsrm.cs.oose13;
import de.hsrm.cs.oose13.basics.*;

public class Pacman extends GeoImage{

	Vertex futuremoving = new Vertex(0,0);
	Vertex pastmoving;
	Vertex pastcorner;
	int mouth = 1;
	int mouthtime = 10;

	PlayLogic game;
	double startpositionx,startpositiony;
	/**
	 * Constructs a new Pacman with given x and y coordinates of Vertex c
	 * @param c Vertex Cornerpoint
	 */
	Pacman(Vertex c){
		this(c, new Vertex(0,0), null);
	}
	/**
	 * Constructs a new Pacman with given x and y coordinates of Vertex c with Movingdirection
	 * @param c Vertex Cornerpoint
	 * @param m Vertex Moving Direction
	 */
	Pacman(Vertex c, Vertex m){
		this(c, m, null);
	}
	
	/**
	 * Constructs a new Pacman with given x and y coordinates of Vertex c
	 * @param c Vertex Cornerpoint
	 * @param game PlayLogic
	 */
	Pacman(Vertex c, PlayLogic game){
		this(c, new Vertex(0,0), game);
	}
	/**
	 * Constructs a new Pacman with given x and y coordinates of Vertex c with Movingdirection
	 * @param c Vertex Cornerpoint
	 * @param m Vertex Moving Direction
	 * @param game PlayLogic
	 */	
	Pacman(Vertex c, Vertex m, PlayLogic game){
		super(c,m, "img/leftpacman1.png");
		this.game = game;
		startpositionx = c.x;
		startpositiony = c.y;
		
	}
	/**
	 * Sets Moving Vertex in choosen Direction for future change
	 * @param dir Direction
	 */	
	public void setMovement(Direction dir){
		futuremoving = dir.v;
	}
	/**
	 * Sets Object to Startposition
	 */	
	public void toStart(){
		this.corner.moveTo(new Vertex(startpositionx,startpositiony));
	}
	
	private String getDirString() {
		switch(moving.getDirection()){
		case NORTH:
			return "up";
		case SOUTH:
			return "down";
		case WEST:
			return "left";
		case EAST:
			return "right";
		default:
			return "left";
		}
	}
	/**
	 * Creating Picturepath for Object in Player.
	 * setting new Image String with Animation and Movingdirection
	 * @see #getDirString()
	 * @see #setNewImg(String)
	 */		
	public void pacmanAnimation(){
		if(mouthtime>0){ mouthtime--;}
		else{mouthtime = 10; mouth = mouth==1? 2 :1; }
		this.setNewImg("img/"+ getDirString() + "pacman" + mouth + ".png");
	}	
}
