package de.hsrm.cs.oose13.basics;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public interface ObjectScene {
	public static List <GeometricObject> squares = new ArrayList<GeometricObject>();
	
	public void collision();
	public void move();
	public void paintAll(Graphics g);
	public int getWidth();
	public int getHeight();
}
