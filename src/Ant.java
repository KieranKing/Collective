/**
 * This class represents a single ant.
 * @author Kieran King
 */
public class Ant {
	
	private Player player;
	private int state;
	private int restingTurns;
	private int direction;
	private boolean hasFood;
	private Cell cellWithAnt;
	
	/**
	 * Construct an ant that belongs to a specified player.
	 * @param the player that owns this ant
	 */
	Ant(Player player) {
		//Note: Remove parameter from class diagram
	}
	
	/**
	 * Get the player that owns this ant.
	 * @return the player that owns this ant
	 */
	public Player getPlayer() {
		return player;
	}
	
	/**
	 * Set the brain state.
	 * @param the brain state
	 */
	public void setState(int state) {
		
	}
	
	/**
	 * Get the brain state.
	 * @return the brain state
	 */
	public int getState() {
		return state;
	}
	
	/**
	 * Set the number of resting turns.
	 * @param number of resting turns
	 */
	public void setRestingTurns(int restingTurns) {
		// Note: Update class diagrams to "restingTurns"
	}
	
	/**
	 * Get the number of resting turns.
	 * @return number of resting turns
	 */
	public int getRestingTurns() {
		return restingTurns;
	}
	

	/** 
	 * Get the direction.
	 * @return the direction (from 0 to 5)
	 */
	public int getDirection() {
		return direction;
	}
	
	/**
	 * Set the direction.
	 * @param the direction (from 0 to 5)
	 */
	public void setDirection(int direction) {
		
	}
	
	/**
	 * Set the food status.
	 * @param true if this ant is holding food, false otherwise
	 */
	public void setFood(boolean hasFood) {
		this.hasFood = hasFood;
	}
	
	/**
	 * Get the food status.
	 * @return true if this ant is holding food, false otherwise
	 */
	public boolean getFood() {
		return hasFood;
	}
	
	/**
	 * Set the cell where this ant is located.
	 * @param the cell where this ant is located
	 */
	public void setCell(Cell cell) {
		cellWithAnt = cell;
	}
	
	/** Get the cell where this ant is located.
	 * @return the cell where this ant is located
	 */
	public Cell getCell() {
		return cellWithAnt;
	}
	
}
