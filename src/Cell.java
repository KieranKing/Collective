/**
 * This class represents a single cell.
 * @author Kieran King
 */
public class Cell {

	private Position position;
	private int foodCount;
	private boolean[][] chemicalMarkers;
	private boolean rocky;
	private Ant antOnCell;
	private Player anthill;
	
	/**
	 * Construct this cell.
	 * @param the position of this cell relative to other cells in the world
	 */
	Cell(Position position) {
		this.position = position;
	}
	
	/**
	 * Get the position of this cell.
	 * @return position of this cell
	 */
	public Position getPosition() {
		return position;
	}
	
	/**
	 * Set the number of food particles contained in this cell.
	 * @param number of food particles
	 */
	public void setFoodCount(int food) {
		// Note: Update class diagram method name
	}
	
	/**
	 * Get the number of food particles contained in this cell.
	 * @return number of food particles
	 */
	public int getFoodCount() {
		// Note: Update class diagram method name
		return foodCount;
	}
	
	/**
	 * Set the state of a chemical marker in this cell.
	 * @param the player
	 * @param the chemical (from 0 to 5)
	 * @param the state of the specified chemical marker
	 */
	public void setMarker(Player player, int chemical, boolean marked) {
		chemicalMarkers[player.getId()][chemical] = marked;
	}
	
	/**
	 * Get the state of a chemical marker in this cell.
	 * @param the player
	 * @param the specific chemical (from 0 to 5)
	 * @return the state of the specified chemical marker
	 */
	public boolean getMarker(Player player, int chemical) {
		// Note: Update returned type on class diagram
		// Note: Removed chemical marker from class diagram
		return chemicalMarkers[player.getId()][chemical];
	}
	
	/**
	 * Set the rocky state of this cell.
	 * @param true for rocky, false for clear
	 */
	public void setRocky(boolean rocky) {
		
	}
	
	/**
	 * Get the rocky state of this cell.
	 * @return true for rocky, false for clear
	 */
	public boolean isRocky() {
		return rocky;
	}
	
	/**
	 * Set the ant that is located in this cell.
	 * @param the ant that is located in this cell, or null if an ant is not located in this cell
	 */
	public void setAnt(Ant ant) {
		
	}
	
	/**
	 * Get the ant that is located in this cell.
	 * @return the ant that is located in this cell, or null if an ant is not located in this cell
	 */
	public Ant getAnt() {
		return antOnCell;
	}
	
	/**
	 * Set the anthill status of this cell.
	 * @param the player who owns the anthill located in this cell, or null if this cell is not part of an anthill
	 */
	public void setAnthill(Player player) {
		
	}
	
	/**
	 * Get the anhill status of this cell.
	 * @return the player who owns the anthill located in this cell, or null if this cell is not part of an anthill
	 */
	public Player getAnthill() {
		return anthill;
	}
	
}
