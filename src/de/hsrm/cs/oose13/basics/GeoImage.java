package de.hsrm.cs.oose13.basics;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
/**
 * The GeoImage Class represents move and paintable Image Object with Coordinates of an Vertex.
 * @author      Arnold Riemer <arnold.riemer@gmail.com>
 * @version     1.0                
 * @since       2014-01-26  
 * @see de.hsrm.cs.oose13.basics.Vertex  
 * @see de.hsrm.cs.oose13.basics.GeometricObject        
 */
public class GeoImage extends GeometricObject{
	/**
	 * Image
	 */
	Image img;

	/**
	 * Constructs a GeoImage Object with an Imagefilepath.
	 * @param path Stringpath to Imagefile.
	 */
	public GeoImage(String path){
		this(new Vertex(0,0),new Vertex(0,0), path);
	}
	
	/**
	 * Constructs a GeoImage Object with an Imagefilepath and a Cornerpoint.
	 * @param path Stringpath to Imagefile.
	 * @param c Vertex Cornerpoint of Object
	 */
	public GeoImage(Vertex c, String path) {
		this(c,new Vertex(0,0),path);
	}
	/**
	 * Constructs a GeoImage Object with an Imagefilepath and a Cornerpoint and a Moving Vertex.
	 * @param path Stringpath to Imagefile.
	 * @param c Vertex Cornerpoint of Object
	 * @param m Vertex Moving in Direction
	 */	
	public GeoImage(Vertex c,Vertex m, String path) {
		super(c, m, 0, 0);
		ImageIcon icon = new ImageIcon(path);
		img = icon.getImage();
		width = icon.getIconWidth();
		height = icon.getIconHeight();
	}
	
	/**
	 * Sets new Image with an Imagefilepath.
	 * @param path String to Imagefile.
	 */	
	@Override
	public void setNewImg(String path){
		ImageIcon icon = new ImageIcon(path);
		img = icon.getImage();
	}
	/**
	 * {@inheritDoc}           
	 */	
	@Override
	public void paintMeTo(Graphics g) {
		g.drawImage(img, (int)corner.x, (int)corner.y, null);
	}
	/**
	 * {@inheritDoc}           
	 */		
	@Override
	public boolean equals(Object o){
		if(o instanceof GeoImage){
			GeoImage go = (GeoImage)o;
			return super.equals(go) && this.img.equals(go.img);
		}
		return false;
	}
	
}