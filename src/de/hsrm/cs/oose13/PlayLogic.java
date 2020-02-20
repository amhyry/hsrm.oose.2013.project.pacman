package de.hsrm.cs.oose13;
import java.awt.Graphics;


import javax.swing.JPanel;

import de.hsrm.cs.oose13.basics.*;
import de.hsrm.cs.oose13.util.FileUtil;
/**
 * This Class represents the Logic of the game process
 * @author      Arnold Riemer <arnold.riemer@gmail.com>
 * @version     1.0                
 * @since       2014-01-26          
 */
@SuppressWarnings("serial")
public class PlayLogic extends JPanel {

	public final int border = 32;
	public static int movingspeed = 1;
	String[] lab;
	String laby;
	Walls walls;
	Ghosts ghosts;
	Coins coins;
	Drugs drugs;
	Pacman player;
	Sound death;
	Sound beginn;
	Sound intermission;
	Sound win;
	boolean highondrugs;
	int timeondrugs = 0;
	Vertex startposition;
	Vertex ghostposition;
	int lives = 3;
	int points = 0;
	public int width = 800;
	public int height = 600;
	/**
	 * Constructs a PlayLogic Object out of an TextFile
	 * @param laby String        
	 */
	public PlayLogic(String laby) {
		this.laby = laby;
		lab = FileUtil.readTextLines(laby);
		walls = new Walls(lab, this);
		ghosts = new Ghosts(lab, this);
		coins = new Coins(lab, this);
		drugs = new Drugs(lab, this);
		startposition = getStartposition(lab, 'P');
		player = new Pacman(new Vertex(startposition.x, startposition.y), this);
		ghostposition = getStartposition(lab, 'G');
		height = walls.getHeight();
		width = walls.getWidth();
		death = new Sound("sound/pacman_death.wav");
		beginn = new Sound("sound/pacman_beginning.wav");
		win = new Sound("sound/pacman_win.wav");
		intermission = new Sound("sound/pacman_intermission.wav");
		beginn.start();
	}

	private Vertex getStartposition(String[] lab, char p) {
		for (int y = 0; y < lab.length; y++) {
			for (int x = 0; x < lab[y].length(); x++) {
				if (lab[y].charAt(x) == p) {
					return new Vertex((border) * x + 1, (border) * y + 1);
				}
			}
		}
		return null;
	}

	/**
	 * Moves all Moveable Objects with their own Moving Vertex 
	 *        
	 */
	public void moveAll() {
		ghosts.setNewMovement();
		ghosts.move();
		setNewMovement(player, lab, border, player.futuremoving);
		checkHighondrugs();
		player.pacmanAnimation();
		player.move();
	}
	/**
	 * Paints all Paintable Objects 
	 * @param g Graphics       
	 */
	public void paintAll(Graphics g) {
		super.paintAll(g);
		walls.paintAll(g);
		coins.paintAll(g);
		drugs.paintAll(g);
		ghosts.paintAll(g);
		player.paintMeTo(g);
	}
	/**
	 * Checks if any Objects Collide
	 * <p>Checks Collision between Walls Ghosts and Pacman and any other 
	 * and if so then changes the Moving Vertex or other Logical Game Events
	 *        
	 */
	public void collisions() {
		ghosts.collision();
		walls.collision();
		coins.collision();
		drugs.collision();
	}
	/**
	 * Sets the Movement Vertex of the Player equal to the wished moving Direction if a Movement is Possible
	 * @param obj GeometricObject PlayerObject
	 * @param lab String[] with Labyrinth String
	 * @param squareborder int with the width of the walls in the Labyrinth
	 * @param futuremoving Vertex of the Direction in which Player is wished to change
	 * @see #isValideMove(String[], int, int)
	 *        
	 */
	public void setNewMovement(GeometricObject obj, String[] lab,
			int squareborder, Vertex futuremoving) {
		int x = (int) obj.corner.x;
		int y = (int) obj.corner.y;

		if ((x % squareborder == 1 && y % squareborder == 1)
				|| (obj.moving.getDirection().equals(Direction.STOP))
				|| (futuremoving.getDirection().equals(Direction.WEST) && (obj.moving
						.getDirection().equals(Direction.EAST)))
				|| (futuremoving.getDirection().equals(Direction.EAST) && (obj.moving
						.getDirection().equals(Direction.WEST)))
				|| (futuremoving.getDirection().equals(Direction.NORTH) && (obj.moving
						.getDirection().equals(Direction.SOUTH)))
				|| (futuremoving.getDirection().equals(Direction.SOUTH) && (obj.moving
						.getDirection().equals(Direction.NORTH)))) {
			x = (int) (x / squareborder);
			y = (int) (y / squareborder);
			switch (futuremoving.getDirection()) {
			case NORTH:
				if (isValideMove(lab, x, y - 1))
					obj.moving = Direction.NORTH.v;
				break;
			case SOUTH:
				if (isValideMove(lab, x, y + 1))
					obj.moving = Direction.SOUTH.v;
				break;
			case WEST:
				if (isValideMove(lab, x - 1, y))
					obj.moving = Direction.WEST.v;
				break;
			case EAST:
				if (isValideMove(lab, x + 1, y))
					obj.moving = Direction.EAST.v;
				break;
			default:
				break;
			}
		}
	}
	/**
	 * Sets the Movement Vertex of a Ghost equal to the wished moving Direction if a Movement is Possible
	 * @param obj GeometricObject Ghost
	 * @param lab String[] with Labyrinth String
	 * @param squareborder int with the width of the walls in the Labyrinth
	 * @param futuremoving Vertex of the Direction in which Ghost is wished to change
	 * @see #isValideMove(String[], int, int)
	 *        
	 */
	public void setNewGhostMovement(GeometricObject obj, String[] lab,
			int squareborder, Vertex futuremoving) {
		int x = (int) obj.corner.x;
		int y = (int) obj.corner.y;

		if (checkCornerPoint(obj, lab)
				&& (x % squareborder == 1 && y % squareborder == 1)
				|| (obj.moving.getDirection().equals(Direction.STOP))) {
			x = (int) (x / squareborder);
			y = (int) (y / squareborder);

			switch (futuremoving.getDirection()) {
			case NORTH:
				if (isValideMove(lab, x, y - 1)
						&& isValideMove(lab, x, y - 1, 'G'))
					obj.moving = Direction.NORTH.v;
				break;
			case SOUTH:
				if (isValideMove(lab, x, y + 1)
						&& isValideMove(lab, x, y + 1, 'G'))
					obj.moving = Direction.SOUTH.v;
				break;
			case WEST:
				if (isValideMove(lab, x - 1, y)
						&& isValideMove(lab, x - 1, y, 'G'))
					obj.moving = Direction.WEST.v;
				break;
			case EAST:
				if (isValideMove(lab, x + 1, y)
						&& isValideMove(lab, x + 1, y, 'G'))
					obj.moving = Direction.EAST.v;
				break;
			default:
				break;
			}
		}
	}
	/**
	 * Checks a StringArray if at given Position exits a given char
	 * @param lab String[] with Labyrinth String
	 * @param x at which column
	 * @param y	at which row
	 * @param area char which should return a false if in lab at given Position equals this
	 * @return boolean
	 * @see #isValideMove(String[], int, int)
	 * @see #checkCornerPoint(GeometricObject, String[])
	 *        
	 */
	public boolean isValideMove(String[] lab, int x, int y, char area) {
		if (y < 0 || y > lab.length) {
			return false;
		}
		if (x < 0 || x > lab[y].length()) {
			return false;
		}
		return lab[y].charAt(x) != area;
	}
	/**
	 * Checks a StringArray if at given Position exits a given char
	 * @param lab String[] with Labyrinth String
	 * @param x at which column
	 * @param y	at which row
	 * @return boolean  
	 * @see #isValideMove(String[], int, int)
	 * @see #checkCornerPoint(GeometricObject, String[])
	 *        
	 */
	public boolean isValideMove(String[] lab, int x, int y) {
		if (y < 0 || y > lab.length) {
			return false;
		}
		if (x < 0 || x > lab[y].length()) {
			return false;
		}
		return lab[y].charAt(x) != 'X';
	}
	/**
	 * Checks GameStatus
	 * <p> If Player has no lives left returns 0.
	 * <p> If There are no Coins left returns 1.
	 * <p> If Game is running returns 2.
	 * @return int
	 *        
	 */
	public int checkGame() {
		if (lives <= 0) {
			return 0;
		}
		if (coins.squares.isEmpty()) {
			win.start();
			return 1;
		}
		return 2;
	}
	/**
	 * Starts the Logical Event for the Death of the Player
	 * <p> Moves back all Ghosts to StartPosition ad Moves Player back to StartPosition        
	 */
	public void dies() {
		death.start();
		for (GeometricObject ghost : ghosts.squares) {
			ghost.corner.moveTo(ghostposition);
		}
		try {
			Thread.sleep(1800);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.lives--;
		player.moving = Direction.STOP.v;
		player.toStart();
		player.move();
		beginn.start();
	}
	/**
	 * Checks the Superpower of the Player, and handles the Logical Event 
	 *         
	 */
	public void checkHighondrugs() {
		if (highondrugs) {
			timeondrugs--;
		}
		if (timeondrugs <= 0) {
			for (GeometricObject ghost : ghosts.squares) {
				if (highondrugs) {
					int random = (int) (Math.random() * 3) + 1;
					ghost.setNewImg("img/" + random + "ghostleft.png");
				}
			}
			highondrugs = false;
			intermission.stop();
		}
	}
	/**
	 * Sets the Players Superpowers when PowerFruit was eaten
	 *         
	 */
	public void highondrugs() {
		for (GeometricObject ghost : ghosts.squares) {
			ghost.setNewImg("img/ghostondrugsleft.png");
		}
		if (!highondrugs)
			intermission.loop(1000);
		highondrugs = true;
		timeondrugs = 750;
	}
	/**
	 * Checks Disctance between Player and a given geometricObject(Ghostobject)
	 * @param that GeometricObject GhostObject
	 * @return boolean if player is nearer than 10*wallwidth returns         
	 */
	public boolean checkDistance(GeometricObject that) {
		if ((that.corner.distance(player.corner) - border * 10) <= 0)
			return true;
		else
			return false;

	}
	/**
	 * Checks if Position of Object is a CornerPoint
	 * @param obj GeometricObject GhostObject
	 * @param lab StringArray of the Labyrinth Text
	 * @return boolean if position is a CornerPoint        
	 */
	public boolean checkCornerPoint(GeometricObject obj, String[] lab) {
		int x = (int) obj.corner.x / border;
		int y = (int) obj.corner.y / border;
		if (isValideMove(lab, x, y - 1) && isValideMove(lab, x, y + 1)
				&& !isValideMove(lab, x - 1, y) && !isValideMove(lab, x + 1, y))
			return false;
		if (!isValideMove(lab, x, y - 1) && !isValideMove(lab, x, y + 1)
				&& isValideMove(lab, x - 1, y) && isValideMove(lab, x + 1, y))
			return false;
		return true;
	}
	/**
	 * Checks if Position of Object is the given Parameter char
	 * @param obj GeometricObject GhostObject
	 * @param lab StringArray of the Labyrinth Text
	 * @param area char of the Position of obj in lab
	 * @return boolean if position is a in lab given the char        
	 */
	public boolean checkAreaPoint(GeometricObject obj, String[] lab, char area) {
		int x = (int) obj.corner.x / border;
		int y = (int) obj.corner.y / border;
		if (y < 0 || y > lab.length) {
			return false;
		}
		if (x < 0 || x > lab[y].length()) {
			return false;
		}
		return lab[y].charAt(x) == area;
	}
	/**
	 * Handles the Logical Event for the Ghosts to leave their StartPosition
	 * @param that GeometricObject GhostObject
	 * @return Vertex for leaving         
	 */
	public Vertex leaveStartPosition(GeometricObject that) {
		int x = (int) that.corner.x / border;
		int y = (int) that.corner.y / border;
		if (isValideMove(lab, x, y - 1)) {
			return Direction.NORTH.v;
		}
		if (!isValideMove(lab, x, y - 1) && !isValideMove(lab, x - 1, y)) {
			return Direction.EAST.v;
		}
		if (!isValideMove(lab, x, y - 1) && !isValideMove(lab, x + 1, y)) {
			return Direction.WEST.v;
		}
		return Direction.random().v;
	}

}
