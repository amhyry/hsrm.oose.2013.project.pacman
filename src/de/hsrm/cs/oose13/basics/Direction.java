package de.hsrm.cs.oose13.basics;

import java.util.Random;

import de.hsrm.cs.oose13.PlayLogic;

/**
 * Returns a cardinal Direction as Enumeration
 * Return WEST, EAST, NORTH, SOUTH or STOP as Direction
 * 
 * Each Enumeration has a Vertex v with Values of the Movingspeed in {@link PlayLogic#movingspeed movingspeed }
 * @author      Arnold Riemer <arnold.riemer@gmail.com>
 * @version     1.0                
 * @since       2014-01-26 
 * 
 * 
     
 */	
public enum Direction {
	WEST(new Vertex(-PlayLogic.movingspeed,0)),NORTH(new Vertex(0,-PlayLogic.movingspeed)),
	EAST(new Vertex(PlayLogic.movingspeed,0)),SOUTH(new Vertex(0,PlayLogic.movingspeed)),
	STOP(new Vertex(0,0));
	
	/**
	 * 	Vertex of Direction 
	 */	
	public Vertex v;
	
	/**
	 * 	Constructs a Direction .WEST .EAST .SOUTH .NORTH or .STOP 
	 */	
	Direction(Vertex v){
		this.v = v;	
	}
	/**
	 * 	Randomly returns a Direction 
	 * @return Direction Enumeration with cardinal direction  
	 */
	public static Direction random(){
		Direction dir = Direction.STOP;
		Random rand = new Random();
		boolean horizontal =  rand.nextBoolean();
		boolean forward = rand.nextBoolean();
		if((horizontal)&&(forward)) dir = Direction.EAST;
		if((horizontal)&&(!forward)) dir = Direction.WEST;
		if((!horizontal)&&(forward)) dir = Direction.SOUTH;
		if((!horizontal)&&(!forward)) dir = Direction.NORTH;
		return dir;
	}
}
