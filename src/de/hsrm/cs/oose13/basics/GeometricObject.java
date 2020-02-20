package de.hsrm.cs.oose13.basics;

import java.awt.Graphics;
/**
 * The GeometricObject Class represents move and paintable Rectangle Object with Coordinates of an Vertex.
 * @author      Arnold Riemer <arnold.riemer@gmail.com>
 * @version     1.0                
 * @since       2014-01-26  
 * @see de.hsrm.cs.oose13.basics.Vertex        
 */
public class GeometricObject implements MoveAndPaintable {

	/**
	 * Cornerpoints x, y as an Vertex 
	 */	
	public Vertex corner;
	/**
	 * Possible Moving in Vertex Direction 
	 */
	public Vertex moving;
	/**
	 * width and height of this Rectangle Object. 
	 */
	double width, height;
	/**
	 * Random integer variable for random settings
	 */
	public int random;
	/**
	 * Constructs a new GeometricObject with given height and width 
	 * @param h height
	 * @param w width
	 */
	public GeometricObject(double h, double w) {
		this(new Vertex(0, 0), new Vertex(0, 0), h, w);
	}// end of go

	/**
	 * Constructs a new GeometricObject with given height and width and coordinates of a Vertex Object.
	 * @param c Cornerpoint of Rectangle Object.
	 * @param h Height
	 * @param w Width
	 */	
	public GeometricObject(Vertex c, double h, double w) {
		this(c, new Vertex(0, 0), h, w);
	}// end of go
	/**
	 * Constructs a new GeometricObject with given height and width and coordinates of a Vertex Object with a moving Vertex
	 * @param c Cornerpoint of Rectangle Object.
	 * @param m Moving in Vertex m Direction.
	 * @param h Height
	 * @param w Width
	 */	
	public GeometricObject(Vertex c, Vertex m, double h, double w) {
		super();
		if (width < 0) {
			corner.x = corner.x + width;
			width = -width;
		}
		if (height < 0) {
			corner.y = corner.y + height;
			height = -height;
		}

		this.corner = c;
		this.moving = m;
		this.width = w;
		this.height = h;
	}// end of go
	/**
	 * Multiplies width and height of this Object.
	 * @return size as a double
	 */	
	public double size() {
		return width * height;
	}
	/**
	 * Compares this GeometricObject with an other GeometricObject
	 * @param that GeometricObject
	 * @return boolean if this is Larger than Parameter than give back true
	 */	
	public boolean isLargerThan(GeometricObject that) {
		return this.size() > that.size();
	}
	/**
	 * Compares this GeometricObject with an other GeometricObject
	 * @param that GeometricObject
	 * @return boolean if this is above than Parameter than give back true
	 */	
	public boolean isAbove(GeometricObject that) {
		return this.corner.y + this.height < that.corner.y;
	}
	/**
	 * Compares this GeometricObject with an other GeometricObject
	 * @param that GeometricObject
	 * @return boolean if this is underneath than Parameter than give back true
	 */	
	public boolean isUnderneath(GeometricObject that) {
		return that.isAbove(this);
	}
	/**
	 * Compares this GeometricObject with an other GeometricObject
	 * @param that GeometricObject
	 * @return boolean if this is left of Parameter than give back true
	 */	
	public boolean isLeftOf(GeometricObject that) {
		return this.corner.x + this.width < that.corner.x;
	}
	/**
	 * Compares this GeometricObject with an other GeometricObject
	 * @param that GeometricObject
	 * @return boolean if this is right of Parameter than give back true
	 */	
	public boolean isRightOf(GeometricObject that) {
		return that.isLeftOf(this);
	}
	/**
	 * Compares this GeometricObject with an other GeometricObject
	 * @param that GeometricObject
	 * @return boolean if this touches Parameter than give back true
	 */	
	public boolean touches(GeometricObject that) {
		return !(this.isAbove(that) || this.isUnderneath(that)
				|| this.isLeftOf(that) || this.isRightOf(that));
	}

	/**
	 * Moves this GeometricObject in Vertex moving, set in Constructor
	 * 
	 */	
	public void move() {
		this.corner.move(moving);
	}
	
	/**
	 * {@inheritDoc}           
	 */	
	@Override
	public String toString() {
		return corner + " | " + moving + " w:" + width + " h:" + height;
	}
	/**
	 * {@inheritDoc}           
	 */	
	@Override
	public boolean equals(Object o) {
		if (o.getClass().equals(this.getClass())) {
			GeometricObject go = (GeometricObject) o;
			return this.corner.equals(go.corner) && this.width == go.width
					&& this.height == go.height;
		}
		return false;
	}
	/**
	 * {@inheritDoc}           
	 */	
	@Override
	public void paintMeTo(Graphics g) {
		// g.create();
		// g.drawRect((int)this.corner.x, (int)this.corner.y, (int)width,
		// (int)height);
		g.fillRect((int) this.corner.x, (int) this.corner.y, (int) width,
				(int) height);
	}
	/**
	 * Testfunktion, if this Console prints ("Falsche Funktion") your {@link GeoImage#setNewImg(String) GeoImage} is not an GeoImage
	 */	
	public void setNewImg(String path) {
		System.out.println("Falsche funktion");
	}
}// end of class