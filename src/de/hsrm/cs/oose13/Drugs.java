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
 * The Drugs Class represents an Object with an ArrayList of GeometricObjects.
 * @author      Arnold Riemer <arnold.riemer@gmail.com>
 * @version     1.0                
 * @since       2014-01-26  
 * @see de.hsrm.cs.oose13.basics.Vertex
 * @see de.hsrm.cs.oose13.basics.GeometricObject
 * @see de.hsrm.cs.oose13.basics.GeoImage        
 */
public class Drugs implements ObjectScene {

	public List<GeometricObject> squares = new ArrayList<GeometricObject>();
	private PlayLogic game;
	public Sound chomp;
	/**
	 * Constructs a new Drugs out of a Stringarray and the {@link de.hsrm.cs.oose13.PlayLogic Playlogic} Class.<p>
	 * <blockquote>
	 * Example
	 * CCCCCCCCC
	 * CC  CC  C
	 * CC CC  CC
	 * CCCCCCCCC
	 * 
	 * </blockquote>
	 * @param lab Stringarray with Labyrinth
	 * @param game {@link de.hsrm.cs.oose13.PlayLogic Playlogic}
	 * @see de.hsrm.cs.oose13.PlayLogic 
	 */	
	public Drugs(String[] lab, PlayLogic game) {
		this.game = game;
		chomp = new Sound("sound/pacman_eatfruit.wav");
		this.squares = new ArrayList<GeometricObject>();
		for (int y = 0; y < lab.length; y++) {
			for (int x = 0; x < lab[y].length(); x++) {
				if (lab[y].charAt(x) == 'C') {
					squares.add(new GeoImage(new Vertex((game.border)* x, (game.border) * y), "img/drug.png"));
				}
			}
		}	
	}
	/**
	 * {@inheritDoc}           
	 */	
	@Override
	public void collision(){
		GeometricObject removeDrug = null;
		for (GeometricObject drug:this.squares){
			if(drug.touches(game.player)){
				chomp.start();
				removeDrug = drug;
				game.highondrugs();
				break;
			}	
		}
		if(removeDrug != null){
			this.squares.remove(removeDrug);
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
