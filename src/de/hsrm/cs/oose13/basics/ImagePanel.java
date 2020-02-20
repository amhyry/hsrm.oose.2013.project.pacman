package de.hsrm.cs.oose13.basics;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
/**
 * The ImagePanel Class represents a Panel with a single Image.
 * @author      Arnold Riemer <arnold.riemer@gmail.com>
 * @version     1.0                
 * @since       2014-01-26          
 */

@SuppressWarnings("serial")
public class ImagePanel extends JPanel {

	private Image img;
	private double width, height;
	private double x,y;
	
	/**
	 * Constructs a ImagePanel out of an Filepath with Dimensions of given Image.
	 * @param path Filepath as String to Imagefile.  
	 */
	public ImagePanel(String path){
		ImageIcon icon = new ImageIcon(path);
		this.img = icon.getImage();
		this.width = icon.getIconWidth();
		this.height = icon.getIconHeight();
		this.x = 0;
		this.y = 0;
	}
	
	/**
	 * Constructs a ImagePanel out of an Filepath scaled by parameters.
	 * @param path Filepath as String to Imagefile.  
	 * @param x Width of Image.
	 * @param y Height of Image.
	 */	
	
	public ImagePanel(String path, int x, int y){
		ImageIcon icon = new ImageIcon(path);
		this.img = icon.getImage();
		this.width = x;
		this.height = y;
		this.x = 0;
		this.y = 0;
	}

	/**
	 * Sets new Imagefile and repaints Panel.
	 * @param path Filepath as String to Imagefile.  
	 */
	public void setNewImg(String path){
		ImageIcon icon = new ImageIcon(path);
		width = icon.getIconWidth();
		height = icon.getIconHeight();
		img = icon.getImage();
		this.repaint();
	}
	
	/**
	 * Sets new Imagefile and repaints Panel and scaled by parameters
	 * @param path Filepath as String to Imagefile.  
	 * @param x Width of Image.
	 * @param y Height of Image.
	 */		
	public void setNewImg(String path, int x,int y){
		ImageIcon icon = new ImageIcon(path);
		width = x;
		height = y;
		img = icon.getImage();
		this.repaint();
	}

	/**
	 *  {@inheritDoc}
	 */		
	@Override
	public Dimension getPreferredSize() {
		return new Dimension((int)this.width, (int)this.height);
	}

	/**
	 *  {@inheritDoc}
	 */			
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//g.drawImage(img, (int)x, (int)y, (int)width, (int)height, null);
		g.drawImage(img, (int)x, (int)y,this.getSize().width,this.getSize().height, this);
	}

	/**
	 *  {@inheritDoc}
	 */			
	@Override
	public boolean equals(Object o){
		if(o instanceof ImagePanel){
			ImagePanel ip = (ImagePanel)o;
			return super.equals(ip) && this.img.equals(ip.img);
		}
		return false;
	}
	
}
