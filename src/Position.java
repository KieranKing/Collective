/**
 * This class represents a coordinate in 2D space.
 * @author Kieran King
 */
/** 
 * The Position class creates Position object with an x,y-coordinate pair.
 * @author Ralph
 */
public class Position {
	
	private int x;
	private int y;
	
	/** Kieran
	 * Construct a position object.
	 * @param the x position
	 * @param the y position
	 */
	/** Ralph
	* Generates a position object with an x and a y coordinate.
	* @param x X-Coordinate of the position.
	* @param y Y-Coordinate of the position.
	*/
	Position(int x, int y) throws Exception {
		if (x<0) {
            throw new Exception("Position X must not be negative!");
        } else {
        	this.x = x;
        }
		if (y<0) {
        	throw new Exception("Position Y must not be negative!");
        } else {
        	this.y = y;
        }
	}
	
	/** Kieran
	 * Set the position.
	 * @param the x position
	 * @param the y position
	 */
	/** Ralph
	* Sets the x and y coordinates of the position to the given values. 
	* @param x X-Coordinate to be set.
	* @param y Y-Coordinate to be set.
	*/
	public void setPosition(int x, int y) throws Exception {
		if (x<0) {
            throw new Exception("Position X must not be negative!");
        } else {
        	this.x = x;
        }
		if (y<0) {
        	throw new Exception("Position Y must not be negative!");
        } else {
        	this.y = y;
        }
	}
	
	/**
	 * Set the x position.
	 * @param the x position
	 */
	public void setX(int x) throws Exception {
		if (x<0) {
        	throw new Exception("Position X must not be negative!");
        } else {
        	this.x = x;
        }
	}
	
	/** Set the y position.
	 * @param the y position
	 * @param y
	 */
	public void setY(int y) throws Exception {
		if (y<0) {
        	throw new Exception("Position Y must not be negative!");
        } else {
        	this.y = y;
        }
	}
	
	/** Kieran
	 * Get the x position.
	 * @return the x position
	 */
	/** Ralph
	* Gets the X-Coordinate of the position.
	* @return Returns the Position's X-Coordinate.
	*/
	public int getX() {
		return x;
	}
	
	/** Kieran
	 * Get the y position.
	 * @return the y position
	 */
	/** Ralph
	* Gets the Y-Coordinate of the position.
	* @return Returns the position's Y-Coordinate.
	*/
	public int getY() {
		return y;
	}
}
