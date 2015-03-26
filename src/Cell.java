/**
 * This class represents a single cell.
 * @author Kieran
 */
/**
 * The Cell class creates a cell object which is of a certain colour, red if it belongs
 * to the red anthill, black if it belongs to the black anthill or blank if it belongs
 * to no anthill. It can either be rocky or not, contain a certain amount of food, as 
 * well as an ant and chemical markers for the different ant teams.
 * @author Ralph
 */
public class Cell {

	private Position position;
	private int foodCount;
	private boolean[][] chemicalMarkers;
	private boolean rocky;
	private Ant antOnCell;
	private Player anthill;
	
	/** Kieran
	 * Construct this cell.
	 * @param the position of this cell relative to other cells in the world
	 */
	/** Ralph
	* Generates a cell with the given x,y-coordinates.
	* @param x  X-Coordinate of cell.
	* @param y  Y-Coordinate of cell.
	*/
	Cell(Position position) {
		this.position = position;
		this.foodCount = 0;
		// TODO: Initialise chemical marker array
		this.rocky = false;
		this.antOnCell = null;
	}
	
	/** Kieran
	 * Get the position of this cell.
	 * @return position of this cell
	 */
	/** Ralph
	* Gets the cell in the map at the given x,y-coordinate.
	* @return Returns the cells position.
	*/
	public Position getPosition() {
		return position;
	}
	
	/** Kieran
	 * Set the number of food particles contained in this cell.
	 * @param number of food particles
	 */
	/** Ralph
	* Sets the amount of food contained in the cell.
	* @param amount Amount of food to be set.
	*/
	public void setFoodCount(int food) {
		// Note: Update class diagram method name
	}
	
	/** Kieran
	 * Get the number of food particles contained in this cell.
	 * @return number of food particles
	 */
	/** Ralph
	* Gets the amount of food contained in the cell. 
	* @return Returns the amount of food in the cell.
	*/
	public int getFoodCount() {
		// Note: Update class diagram method name
		return foodCount;
	}
	
	/** Kieran
	 * Set the state of a chemical marker in this cell.
	 * @param the player
	 * @param the chemical (from 0 to 5)
	 * @param the state of the specified chemical marker
	 */
	/** Ralph
	* Sets the given chemicalMarker in the cell to either true or false for the given player, depending on the entered value.
	* @param player  The player the marker belongs to.
	* @param chemicalNo  The number of the chemical to be set from 0-6.
	* @param setMarker The boolean value the marker should be set to, true if there, else false.
	*/
	public void setMarker(Player player, int chemical, boolean marked) {
		chemicalMarkers[player.getId()][chemical] = marked;
	}
	
	/** Kieran
	 * Get the state of a chemical marker in this cell.
	 * @param the player
	 * @param the specific chemical (from 0 to 5)
	 * @return the state of the specified chemical marker
	 */
	/** Ralph
	* Gets the given chemical marker for the given player.
	* @param player  The player the chemical marker belongs to.
	* @param chemicalNo The number of the chemical to be retrieved.
	* @return Returns the given chemical marker belnging to a given player.
	*/
	public boolean getMarker(Player player, int chemical) {
		return chemicalMarkers[player.getId()][chemical];
	}
	
	/** Kieran
	 * Set the rocky state of this cell.
	 * @param true for rocky, false for clear
	 */
	/** Ralph
	* Sets wether or not the cell is rocky, true if rocky, else false.
	* @param rocky The boolean value of wether the cell should be rocky or not.
	*/
	public void setRocky(boolean rocky) {
		this.rocky = rocky;
	}
	
	/** Kieran
	 * Get the rocky state of this cell.
	 * @return true for rocky, false for clear
	 */
	/** Ralph
	* Gets wether or not the cell is rocky, true if rocky, else false.
	* @return Returns wether a cell is rocky or not.
	*/
	public boolean isRocky() {
		return rocky;
	}
	
	/** Kieran
	 * Set the ant that is located in this cell.
	 * @param the ant that is located in this cell, or null if an ant is not located in this cell
	 */
	/** Ralph
	* Sets an ant on the cell.
	* @param ant  The ant to be set on the cell.
	*/
	public void setAnt(Ant ant) {
		this.antOnCell = ant;
	}
	
	/** Kieran
	 * Get the ant that is located in this cell.
	 * @return the ant that is located in this cell, or null if an ant is not located in this cell
	 */
	/** Ralph
	* Gets the ant on the cell, if null, no ant is on the cell.
	* @return Returns null if no ant is on the cell, otherwise returns the ant.
	*/
	public Ant getAnt() {
		return antOnCell;
	}
	
	/** Kieran
	 * Set the anthill status of this cell.
	 * @param the player who owns the anthill located in this cell, or null if this cell is not part of an anthill
	 */
	/** Ralph
	* Sets the cell to a given player colour, representing that players anthill.
	* @param colour  Colour of the player to whom the anthill belongs.
	*/
	public void setAnthill(Player player) {
		
	}
	
	/** Kieran
	 * Get the anhill status of this cell.
	 * @return the player who owns the anthill located in this cell, or null if this cell is not part of an anthill
	 */
	/** Ralph
	* Gets the colour of the cell, which represents which team the anthill belongs to.
	* @return Returns the colour of the team the anthill belongs to.
	*/
	public Player getAnthill() {
		return anthill;
	}
	
}
