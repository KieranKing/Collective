/**
 * This class represents a single ant.
 * @author Kieran King
 */

package antproject.src;

/**
 * The Ant class creates an ant object with a unique id and a player.
 * @author Ralph
 */
public class Ant {
	
	private Player player;
	private int state;
	private int restingTurns;
	private int direction;
	private boolean hasFood;
	private Cell cell;
	private int id;
	
	/**
	 * Construct an ant that belongs to a specified player.
	 * @param player The player the ant belongs to.
	 */
	public Ant(Player player) {
            this.player = player;
	}

	/**
	 * Gets the unique id of the ant.
	 * @return Returns the ant's id.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the unique id of the ant.
	 * @param id The unique id of the ant.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the player the ant belongs to.
	 * @return Returns the player the ant belongs to.
	 */
	public Player getPlayer() {
		return player;
	}
	
	/**
	 * Sets the brain state.
	 * @param state The brain state of the ant.
	 */
	public void setState(int state) {
		this.state = state;
	}
	
	/**
	 * Gets the brain state.
	 * @return Returns the brain state of the ant.
	 */
	public int getState() {
		return state;
	}
	
	/**
	 * Sets the number of resting turns.
	 * @param restingTurns The number of resting turns to be waited.
	 */
	public void setRestingTurns(int restingTurns) {
		this.restingTurns = restingTurns;
	}
	
	/**
	 * Gets the number of resting turns to be waited.
	 * @return Returns the number of resting turns to be waited.
	 */
	public int getRestingTurns() {
		return restingTurns;
	}
	

	/** 
	 * Gets the direction the ant is facing in.
	 * @return Returns the direction the ant is facing in (from 0 to 5)
	 */
	public int getDirection() {
		return direction;
	}
	
	/**
	 * Sets the direction.
	 * @param direction The direction the ant is to face (from 0 to 5).
	 */
	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	/**
	 * Sets whether the ant is holding food or not.
	 * @param hasFood True if the ant is holding food, else false.
	 */
	public void setFood(boolean hasFood) {
		this.hasFood = hasFood;
	}
	
	/**
	 * Gets whether the ant is holding food or not.
	 * @return Returns true if the ant is holding food, else false.
	 */
	public boolean getFood() {
		return hasFood;
	}
	
	/**
	 * Sets the cell where the ant is located.
	 * @param cell The cell where the ant is located
	 */
	public void setCell(Cell cell) {
		this.cell = cell;
	}
	
	/** Gets the cell where the ant is located.
	 * @return The cell where the ant is located.
	 */
	public Cell getCell() {
		return cell;
	}
	
}
