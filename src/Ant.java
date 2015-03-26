/**
 * This class represents a single ant.
 * @author Kieran
 */
/**
 * The Ant class generates an Ant object, which has a unique id, a colour to identify it as a 
 * member of a team and an antbrain.The antclass provides methods needed to manipulate the ant
 * and make descisions withing the game to generate behaviour for the ant, bases on the contents
 * of the cells in a map and the states that the ant is in.
 * @author Ralph
 */
public class Ant {
	
	private Player player;
	private int state;
	private int restingTurns;
	private int direction;
	private boolean hasFood;
	private Cell cell;
	
	/** Kieran
	 * Construct an ant that belongs to a specified player.
	 * @param the player that owns this ant
	 */
	/** Ralph
	* Generates an Ant object with a unique id, a colour and an antbrain.
	* @param id  Unique id of the ant.
	* @param colour  Colour of the ant.
	* @param brain The brain the ant will be using.
	*/
	Ant(Player player) {
		this.player = player;
		this.state = 0;
		this.restingTurns = 0;
		this.direction = 0;
		this.hasFood = false;
		this.cell = null;
	}
	
	/**
	 * Get the player that owns this ant.
	 * @return the player that owns this ant
	 */
	public Player getPlayer() {
		return player;
	}
	
	/** Kieran
	 * Set the brain state.
	 * @param the brain state
	 */
	/** Ralph
	* Sets the state of the ant's brain.
	* @param state The state that the ant's brain should be set to.
	*/
	public void setState(int state) {
		this.state = state;
	}
	
	/** Kieran
	* Gets the state of the ant's brain.
	* @return Returns the state of the the ant's brain.
	*/
	/** Ralph
	 * Get the brain state.
	 * @return the brain state
	 */
	public int getState() {
		return state;
	}
	
	/** Kieran
	 * Set the number of resting turns.
	 * @param number of resting turns
	 */
	/** Ralph
	* Sets the amount of turns the ant has to wait before it can make its move.
	* @param turns The amount of turns the ant has to wait.
	*/
	public void setRestingTurns(int restingTurns) {
		// Note: Update class diagrams to "restingTurns"
		this.restingTurns = restingTurns;
	}
	
	/** Kieran
	 * Get the number of resting turns.
	 * @return number of resting turns
	 */
	/** Ralph
	* Gets the amount of turns the ant has to wait.
	* @return Returns the amount of turns the ant has to wait before making a move.
	*/
	public int getRestingTurns() {
		return restingTurns;
	}
	

	/**  Kieran
	 * Get the direction.
	 * @return the direction (from 0 to 5)
	 */
	/** Ralph
	* Gets the direction in which the ant is facing.
	* @return Returns the direction in which the ant is facing.
	*/
	public int getDirection() {
		return direction;
	}
	
	/** Kieran
	 * Set the direction.
	 * @param the direction (from 0 to 5)
	 */
	/** Ralph
	* Sets the direction in which the ant is facing.
	* @param direction The direction in which the ant is to be set to face.
	*/
	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	/** Kieran
	 * Set the food status.
	 * @param true if this ant is holding food, false otherwise
	 */
	/** Ralph
	* Sets wether an ant has food or not, true is it does, else false.
	* @param hasfood Whether or not an ant has food, true if it does, else false.
	*/
	public void setFood(boolean hasFood) {
		this.hasFood = hasFood;
	}
	
	/** Kieran
	 * Get the food status.
	 * @return true if this ant is holding food, false otherwise
	 */
	/** Ralph
	* Gets wether an ant has food or not, true if it does, else false.
	* @return Returns whether an ant has food or not, true if it does, else false.
	*/
	public boolean getFood() {
		return hasFood;
	}
	
	/** Kieran
	 * Set the cell where this ant is located.
	 * @param the cell where this ant is located
	 */
	/** Ralph
	* Sets the position of the ant.
	* @param position The position the ant is to be set to.
	*/
	public void setCell(Cell cell) {
		this.cell = cell;
	}
	
	/** Kieran
	 * Get the cell where this ant is located.
	 * @return the cell where this ant is located
	 */
	/** Ralph
	* Gets the position of the ant.
	* @return Returns the position of the ant.
	*/
	public Cell getCell() {
		return cell;
	}
	
}
