package de.hsrm.cs.oose13;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.hsrm.cs.oose13.basics.*;
/**
 * The Ghosts Class represents an Object with an ArrayList of GeometricObjects.
 * @author      Arnold Riemer <arnold.riemer@gmail.com>
 * @version     1.0                
 * @since       2014-01-26  
 * @see de.hsrm.cs.oose13.basics.Vertex
 * @see de.hsrm.cs.oose13.basics.GeometricObject
 * @see de.hsrm.cs.oose13.basics.GeoImage        
 */
public class Ghosts implements ObjectScene{

	public List <GeometricObject> squares = new ArrayList<GeometricObject>();
	private PlayLogic game;
	Sound eaten;
	/**
	 * Constructs a new Ghosts out of a Stringarray and the {@link de.hsrm.cs.oose13.PlayLogic Playlogic} Class.<p>
	 * <blockquote>
	 * Example
	 * GGGGGGGGG
	 * GG  GG  G
	 * GG GG  GG
	 * GGGGGGGGG
	 * 
	 * </blockquote>
	 * @param lab Stringarray with Labyrinth
	 * @param game {@link de.hsrm.cs.oose13.PlayLogic Playlogic}
	 * @see de.hsrm.cs.oose13.PlayLogic 
	 */	
	public Ghosts(String[] lab, PlayLogic game){
		this.game = game;
		eaten = new Sound("sound/pacman_eatghost.wav");
		this.squares = new ArrayList<GeometricObject>();
		for (int y = 0; y < lab.length; y++) {
			for (int x = 0; x < lab[y].length(); x++) {
				if (lab[y].charAt(x) == 'G') {
					int random = (int) (Math.random()*3)+1;
					Direction dir = Direction.NORTH;
					GeoImage gi = new GeoImage(new Vertex((game.border)* x+1, (game.border) * y+1),dir.v, "img/"+ random +"ghostleft.png");
					gi.random = random;
					squares.add(gi);
				}
			}
		}		
	}
	/**
	 * Creates a object moving Direction with an AI away from Parameter GeometricObject
	 * @param that GeometricObject Playerobject
	 * @return Vertex Cardinal Direction
	 * @see Ghosts#newGhostMove(GeometricObject)
	 * @see Ghosts#newGhostFlee(GeometricObject)
	 * @see Ghosts#newGhostMove() 
	 * @see PlayLogic#setNewGhostMovement(GeometricObject, String[], int, Vertex)
	 * @see PlayLogic#checkAreaPoint(GeometricObject, String[], char)
	 * @see PlayLogic#leaveStartPosition(GeometricObject)  
	 * @see de.hsrm.cs.oose13.basics.Vertex#distance(Vertex)  
	 */		
	public Vertex newGhostFlee(GeometricObject that) {
		Direction dir = Direction.NORTH;			
		Random rand = new Random();
		boolean choice = rand.nextBoolean(), choice2 = rand.nextBoolean();
		if(game.checkDistance(that)) choice = true; 
		if(game.checkAreaPoint(that, game.lab, 'G')){ return game.leaveStartPosition(that); }
		if((choice)&&(choice2)){
			if(that.moving.getDirection() == Direction.NORTH){dir = Direction.NORTH;}
			if(that.moving.getDirection() == Direction.SOUTH){dir = Direction.SOUTH;}
			if(that.moving.getDirection() == Direction.WEST){dir = Direction.WEST;}
			if(that.moving.getDirection() == Direction.EAST){dir = Direction.EAST;}
		}
		else{
			return newGhostMove();
		}
		
		return dir.v;
	}
	
	/**
	 * Creates a object moving Direction with an AI towards Parameter GeometricObject
	 * @param that GeometricObject Playerobject
	 * @return Vertex Cardinal Direction
	 * @see Ghosts#newGhostMove(GeometricObject)
	 * @see Ghosts#newGhostFlee(GeometricObject)
	 * @see Ghosts#newGhostMove() 
	 * @see PlayLogic#setNewGhostMovement(GeometricObject, String[], int, Vertex)
	 * @see PlayLogic#checkAreaPoint(GeometricObject, String[], char)
	 * @see PlayLogic#leaveStartPosition(GeometricObject)  
	 * @see de.hsrm.cs.oose13.basics.Vertex#distance(Vertex)  
	 */		
	public Vertex newGhostMove(GeometricObject that) {
		Direction dir = Direction.NORTH;			
		Random rand = new Random();
		boolean choice = rand.nextBoolean(), choice2 = rand.nextBoolean();
		
		if(game.checkDistance(that)) choice = true; 
		if(game.checkAreaPoint(that, game.lab, 'G')){ return game.leaveStartPosition(that); }
		if((choice)&&(choice2)){
			if(game.player.isAbove(that)){dir = Direction.NORTH;}
			if(game.player.isUnderneath(that)){dir = Direction.SOUTH;}
			if(game.player.isLeftOf(that)){dir = Direction.WEST;}
			if(game.player.isRightOf(that)){dir = Direction.EAST;}
		}else{
			return newGhostMove();
		}
		return dir.v;
	}
	/**
	 * Creates a random object moving Direction
	 * @return Vertex random Cardinal Direction
	 * @see Ghosts#newGhostMove(GeometricObject)
	 * @see Ghosts#newGhostFlee(GeometricObject)
	 * @see Ghosts#newGhostMove() 
	 * @see PlayLogic#setNewGhostMovement(GeometricObject, String[], int, Vertex)   
	 */		
	public Vertex newGhostMove() {
		return Direction.random().v;
	}
	/**
	 * {@inheritDoc}           
	 */	
	@Override
	public void collision() {
		for (GeometricObject ghost: this.squares){
			for (GeometricObject wall: game.walls.squares){
				if (wall.touches(ghost)){
					ghost.moving.reverse();
					ghost.move();
					ghost.moving.reverse();
					ghost.moving = newGhostMove(ghost);
					if(game.highondrugs){
						ghost.setNewImg("img/ghostondrugs"+ getDirString(ghost) +".png");
					}
					else{
						ghost.setNewImg("img/"+ ghost.random + "ghost" + getDirString(ghost) + ".png");
					}
				}
			}
			if (game.player.touches(ghost)){
				if(game.highondrugs){
					ghostEaten(ghost);
					game.points++;
				}
				else{
					game.dies();
				}
			}	
		}		
	}
	
	private void ghostEaten(GeometricObject ghost) {
		eaten.start();
		ghost.corner.moveTo(game.ghostposition);
	}
	/**
	 * Creating Picturepath for Objects in Ghosts.
	 * @return String movingdirection 
	 */	
	public String getDirString(GeometricObject ghost){
		if(ghost.moving.x < 0){
			return "left";
		}
		else{
			return "right";
		}
	}
	/**
	 * {@inheritDoc}           
	 */	
	@Override
	public void move() {
		for(GeometricObject go : squares){
			go.move();
		}
	}
	/**
	 * {@inheritDoc}           
	 */	
	@Override
	public void paintAll(Graphics g) {
		for(GeometricObject go : squares){
			go.paintMeTo(g);
		}
	}
	/**
	 * {@inheritDoc}           
	 */	
	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * {@inheritDoc}           
	 */	
	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * Sets new Movement for all Objects in List
	 * @see Ghosts#newGhostMove(GeometricObject)
	 * @see Ghosts#newGhostFlee(GeometricObject)
	 * @see Ghosts#newGhostMove() 
	 * @see PlayLogic#setNewGhostMovement(GeometricObject, String[], int, Vertex)   
	 */	
	public void setNewMovement() {
		Vertex futuremoving;
		for(GeometricObject ghost : squares){
			
				if(!game.highondrugs){
					futuremoving = newGhostMove(ghost);
					game.setNewGhostMovement(ghost, game.lab, game.border, futuremoving);
				}else{
					futuremoving = newGhostFlee(ghost);
					game.setNewGhostMovement(ghost, game.lab, game.border, futuremoving);
				}
		
		}
	}
}
