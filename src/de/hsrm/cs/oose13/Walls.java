package de.hsrm.cs.oose13;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import de.hsrm.cs.oose13.basics.Direction;
import de.hsrm.cs.oose13.basics.GeoImage;
import de.hsrm.cs.oose13.basics.GeometricObject;
import de.hsrm.cs.oose13.basics.ObjectScene;
import de.hsrm.cs.oose13.basics.Vertex;
/**
 * The Walls Class represents an Object with an ArrayList of GeometricObjects.
 * @author      Arnold Riemer <arnold.riemer@gmail.com>
 * @version     1.0                
 * @since       2014-01-26  
 * @see de.hsrm.cs.oose13.basics.Vertex
 * @see de.hsrm.cs.oose13.basics.GeometricObject
 * @see de.hsrm.cs.oose13.basics.GeoImage        
 */
public class Walls implements ObjectScene {
	
	public List<GeometricObject> squares = new ArrayList<GeometricObject>();
	private PlayLogic game;
	
	private int height;
	private int width;

	/**
	 * Constructs a new Walls out of a Stringarray and the {@link PlayLogic Playlogic} Class.<p>
	 * <blockquote>
	 * Example
	 * XXXXXXXXX
	 * XX  XX  X
	 * XX XX  XX
	 * XXXXXXXXX
	 * 
	 * </blockquote>
	 * @param lab Stringarray with Labyrinth
	 * @param game {@link PlayLogic Playlogic}
	 * @see PlayLogic
	 */
	public Walls(String[] lab, PlayLogic game) {
		this.game = game;
		for (int y = 0; y < lab.length; y++) {
			for (int x = 0; x < lab[y].length(); x++) {
				if (lab[y].charAt(x) == 'X') {
					squares.add(new GeoImage(new Vertex((game.border)* x, (game.border) * y), "img/"+setWallImage(lab,x,y) +".png"));
				}
			}
		}
		height = lab.length*game.border;
		width = lab[0].length()*game.border;
	}

	
	/**
	 * Sets needed Image for Walldisplay.
	 * @param lab Stringarray of Labyrinth
	 * @param x Coordinate horizontal
	 * @param y Coordinate vertical
	 * @return String wallname
	 */	
	public String setWallImage(String [] lab, int x, int y){
		boolean above=false, underneath=false, left=false, right=false;
		if(y-1<0){above = false;}
		else{if(lab[y-1].charAt(x) == 'X'){ above = true;}}
		if(y+1>=lab.length) underneath = false;
		else{if(lab[y+1].charAt(x) == 'X'){ underneath = true;}}
		if(x-1<0){left = false;}
		else{if(lab[y].charAt(x-1) == 'X'){ left = true;}}
		if(x+1>=lab[y].length()){right = false;}
		else{if(lab[y].charAt(x+1) == 'X'){ right = true;}}
		if(above&&underneath&&left&&right){return "wall";}
		if(!above&&!underneath&&left&&right){return "wallh";}
		if(above&&underneath&&!left&&!right){return "wallv";}
		if(!above&&underneath&&left&&right){return "walldownt";}
		if(above&&!underneath&&left&&right){return "wallupt";}
		if(above&&underneath&&left&&!right){return "wallleftt";}
		if(above&&underneath&&!left&&right){return "wallrightt";}
		if(above&&!underneath&&left&&!right){return "wallleftup";}
		if(above&&!underneath&&!left&&right){return "wallrightup";}
		if(!above&&underneath&&left&&!right){return "wallleftdown";}
		if(!above&&underneath&&!left&&right){return "wallrightdown";}
		else{if((!above||!underneath)&&(left||right)){return "wallh";}
			 if((above||underneath)&&(!left||!right)){return "wallv";}
			 else return "wall";
		}
	}
	/**
	 * {@inheritDoc}           
	 */		
	@Override
	public void collision() {
		for (GeometricObject wall : squares) {
			if (wall.touches(game.player)) {		
				game.player.moving.reverse();
				game.player.move();
				game.player.moving.reverse();
				game.player.setMovement(Direction.STOP);
			}
		}		
	}
	/**
	 * {@inheritDoc}           
	 */	
	@Override
	public void move() {
		// TODO Auto-generated method stub
		
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
		return width;
	}
	/**
	 * {@inheritDoc}           
	 */	
	@Override
	public int getHeight() {
		return height;
	}
}
