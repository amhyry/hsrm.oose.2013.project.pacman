package de.hsrm.cs.oose13;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import de.hsrm.cs.oose13.basics.GeoImage;
import de.hsrm.cs.oose13.basics.GeometricObject;
import de.hsrm.cs.oose13.basics.ObjectScene;
import de.hsrm.cs.oose13.basics.Sound;
import de.hsrm.cs.oose13.basics.Vertex;
/**
 * The Coins Class represents an Object with an ArrayList of GeometricObjects.
 * @author      Arnold Riemer <arnold.riemer@gmail.com>
 * @version     1.0                
 * @since       2014-01-26  
 * @see de.hsrm.cs.oose13.basics.Vertex
 * @see de.hsrm.cs.oose13.basics.GeometricObject
 * @see de.hsrm.cs.oose13.basics.GeoImage        
 */
public class Coins implements ObjectScene {

	private PlayLogic game;
	public List <GeometricObject> squares = new ArrayList<GeometricObject>();
	public Sound chomp;
	
	/**
	 * Constructs a new Coins out of a Stringarray and the {@link de.hsrm.cs.oose13.PlayLogic Playlogic} Class.<p>
	 * <blockquote>
	 * Example
	 * OOOOOOOOO
	 * OO  OO  O
	 * OO OO  OO
	 * OOOOOOOOO
	 * 
	 * </blockquote>
	 * @param lab Stringarray with Labyrinth
	 * @param game {@link de.hsrm.cs.oose13.PlayLogic Playlogic}
	 * @see de.hsrm.cs.oose13.PlayLogic 
	 */	
	public Coins(String[] lab, PlayLogic game){
		this.game = game;
		
		chomp = new Sound("sound/pacman_chomp.wav");
		
		
		for (int y = 0; y < lab.length; y++) {
			for (int x = 0; x < lab[y].length(); x++) {
				if (lab[y].charAt(x) == 'O') {
					squares.add(new GeoImage(new Vertex((game.border)* x, (game.border) * y), "img/coin.png"));
				}
			}
		}
	}
	/**
	 * {@inheritDoc}           
	 */	
	@Override
	public void collision() {
		GeometricObject removeCoin = null;
		for (GeometricObject coin : this.squares) {
			if (game.player.touches(coin)) {
					chomp.start();
					removeCoin = coin;
					game.points++;
					break;
			}
		}
		if (removeCoin != null){
			this.squares.remove(removeCoin);
		}	
		
	}
	/**
	 * {@inheritDoc}           
	 */	
	@Override
	public void move() {
		
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
	
}
