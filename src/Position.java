/**
 * The Position class creates Position object with an x,y-coordinate pair.
 * 
 */
public class Position {
	
	private int x;
	private int y;

	/**
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
	

	/**
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
	 * Set the x-coordinate of the position.
	 * @param x The x-coordinate of the position.
	 */
	public void setX(int x) throws Exception {
		if (x<0) {
        	throw new Exception("Position X must not be negative!");
        } else {
        	this.x = x;
        }
	}
	
	/** Set the y-coordinate of the position.
	 * @param y The y-coordinate.
	 */
	public void setY(int y) throws Exception {
		if (y<0) {
        	throw new Exception("Position Y must not be negative!");
        } else {
        	this.y = y;
        }
	}
	

	/**
	* Gets the X-Coordinate of the position.
	* @return Returns the position's x-coordinate.
	*/
	public int getX() {
		return x;
	}
	

	/**
	* Gets the Y-Coordinate of the position.
	* @return Returns the position's y-coordinate.
	*/
	public int getY() {
		return y;
	}
}
