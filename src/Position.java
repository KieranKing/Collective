/**
 * This class represents a coordinate in 2D space.
 * @author Kieran King
 */
public class Position {
	
	private int x;
	private int y;
	
	/**
	 * Construct a position object.
	 * @param the x position
	 * @param the y position
	 */
	Position(int x, int y) throws Exception{
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
	 * Set the position.
	 * @param the x position
	 * @param the y position
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
	
	/**
	 * Get the x position.
	 * @return the x position
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Get the y position.
	 * @return the y position
	 */
	public int getY() {
		return y;
	}
}
