package de.hsrm.cs.oose13.basics;

/**
 * The Vertex Class represents 2 dimensional vector with x and y coordinates. 
 * @author      Arnold Riemer <arnold.riemer@gmail.com>
 * @version     1.0                
 * @since       2014-01-26          
 */
public class Vertex {
	/**
	 * horizontal coordinate 
	 */
	public double x;
	/**
	 * vertical coordinate 
	 */
	public double y;
	private final static double EPSILON = 0.00001;
	/**
	 * Initializes a newly created Vertex object with x and y set to 0.
	 */	
	public Vertex(){
		this(0,0);
	}
	/**
	 * Constructs a new Vertex with given x and y coordinates
	 * @param x horizontal coordinate
	 * @param y vertical coordinate
	 */
	public Vertex(double x, double y) {
		this.x = x;
		this.y = y;
	}
	/**
	 * Moves this Vertex around the coordinates of given parameter Vertex 
	 *@param v shift this Vertex around v           
	 */
	/**
	 * Moves this Vertex around the coordinates of given parameter Vertex.
	 * <p>
	 * The x and y coordinates of this Vertex object adds the x and y coordinates of given parameter Vertex.
	 * <p>
	 * 
	 * Example<p>
	 * <code><blockquote>
	 * Vertex v1 = new Vertex(2,2);<br>
	 * System.out.println(v1);<br>
	 * v1.moveTo(new Vertex(5,5));<br>
	 * System.out.println(v1);
	 * </blockquote></code>
	 * <p>
	 * This would print 
	 * (2|2)
	 * (7|7)
	 *@param v shift this Vertex around Vertex v           
	 */		
	
	public void move(Vertex v) {
		this.x = this.x + v.x;
		this.y = this.y + v.y;
	}
	/**
	 * Moves this Vertex to the coordinates of given parameter Vertex.
	 * <p>
	 * The x and y coordinates of this Vertex object are set to the x and y coordinates of given parameter Vertex.
	 * <p>
	 * 
	 * Example<p>
	 * <code><blockquote>
	 * Vertex v1 = new Vertex();<br>
	 * System.out.println(v1);<br>
	 * v1.moveTo(new Vertex(5,5));<br>
	 * System.out.println(v1);
	 * </blockquote></code>
	 * <p>
	 * This would print 
	 * (0|0)
	 * (5|5)
	 *@param v shift this Vertex to Vertex v           
	 */	
	public void moveTo(Vertex v) {
		this.x = v.x;
		this.y = v.y;
	}
	/**
	 * Turns this Vertex around. 
	 * <p>
	 * x and y are inverted.
	 *       
	 */	
	public void reverse() {
		this.x = -x;
		this.y = -y;
	}
	/**
	 * Multiple Vertex.<p>
	 * Multiplies this Vertex Object with given integer factor.
	 *@param factor integer to multiple           
	 */	
	public void multiplikate(int factor){
		this.x = x*factor;
		this.y = y*factor;
	}
	
	/**
	 * Erases this Vertex Object.<p>
	 * Sets this Vertex x=0 and y=0        
	 */	
	public void erase(){
		this.x = 0;
		this.y = 0;
	}
	
	/**
	 * Calculate Distance of this Vertex to given Parameter Vertex
	 *@param v calculate distance of this Vertex to given
	 *@return double distance between this Vertex and given Vertex          
	 */	
	public double distance(Vertex v){
		return Math.sqrt(((v.x-this.x)*(v.x-this.x)) + ((v.y-this.y)*(v.y-this.y)));
	}
	
	/**
	 * {@inheritDoc}           
	 */	
	@Override
	public String toString() {
		return "(" + x + "|" + y + ")";
	}
	/**
	 * {@inheritDoc}           
	 */		
	@Override
	public boolean equals(Object o){
		if(o.getClass().equals(this.getClass())){
			Vertex v = (Vertex)o;
			return almostEqual(this.x, v.x) && almostEqual(this.y, v.y);
		}
		return false;	
	}

	private boolean almostEqual(double d1, double d2) {
		return Math.abs(d1-d2)<EPSILON;
	}
	/**
	 * Returns a cardinal Direction as Enumeration
	 * <p>
	 * Return WEST, EAST, NORTH, SOUTH or STOP as Direction
	 * <p>
	 * Each Enumeration has a Vertex v with Values of the Movingspeed in {@link de.hsrm.cs.oose13.PlayLogic#movingspeed movingspeed } 
	 * 	 
	 * @return Direction Enumeration with cardinal direction       
	 */	
	public Direction getDirection(){
		if((x<0)&&(y==0)){
			return Direction.WEST;
		}
		if((x>0)&&(y==0)){
			return Direction.EAST;
		}
		if((x==0)&&(y<0)){
			return Direction.NORTH;
		}
		if((x==0)&&(y>0)){
			return Direction.SOUTH;
		}
		return Direction.STOP;
	}
}// end of class

