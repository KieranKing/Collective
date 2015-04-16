/**
 * This class represents a single cell.
 *
 * @author Kieran King
 */
package antproject.src;

public class Cell {

    private Position position;
    private int foodCount;
    private boolean[][] chemicalMarkers;
    private boolean rocky;
    private Ant antOnCell;
    private Player anthill;
    private boolean occupied = false;

    /**
     * Constructs a cell with the given position.
     *
     * @param position The position of this cell relative to other cells in the world
     */
    public Cell(Position position) {
        this.position = position;
        chemicalMarkers = new boolean[2][6];
    }

    /**
     * Gets the position of the cell.
     *
     * @return Returns the position of the cell.
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Sets the number of food particles to be contained in the cell.
     *
     * @param number of food particles
     */
    public void setFoodCount(int food) {
        // Note: Update class diagram method name
        foodCount = food;
    }

    /**
     * Get the number of food particles contained in the cell.
     *
     * @return number of food particles
     */
    public int getFoodCount() {
        // Note: Update class diagram method name
        return foodCount;
    }

    /**
     * Set the state of a chemical marker in the cell.
     *
     * @param player The player whose chemical marker is to be set.
     * @param chemical The chemical marker to be set in this cell.
     * @param marked The state of the specified chemical marker.
     */
    public void setMarker(Player player, int chemical, boolean marked) {
        chemicalMarkers[player.getId()][chemical] = marked;
    }

    /**
     * Get the state of a chemical marker in this cell.
     *
     * @param player The player whose chemical marker is to be retrieved.
     * @param chemical The chemical marker to be retrieved.
     * @return Returns the state of the specified chemical marker.
     */
    public boolean getMarker(Player player, int chemical) {
        return chemicalMarkers[player.getId()][chemical];
    }

    /**
     * Sets whether the cell is rocky or not, true if rocky, else false.
     *
     * @param rocky True if the cell is rocky, else false.
     */
    public void setRocky(boolean rocky) {
        this.rocky = rocky;
    }

    /**
     * Gets whether or not the cell is rocky, true if rocky, else false.
     *
     * @return true for rocky, false for clear
     */
    public boolean isRocky() {
        return rocky;
    }

    /**
     * Sets an ant on the cell.
     *
     * @param ant The ant to be set on the cell.
     *
     */
    public void setAnt(Ant ant) {
        antOnCell = ant;
    }

    /**
     * Get the ant on the cell, if null, no ant is located on the cell.
     *
     * @return Returns null if no ant is on the cell, otherwise returns the ant.
     */
    public Ant getAnt() {
        return antOnCell;
    }

    /**
     * Sets the anthill status of this cell.
     *
     * @param player The player who owns the anthill located in this cell, or null if this cell is not part of an anthill
     */
    public void setAnthill(Player player) {
        anthill = player;
    }

    /**
     * Gets the anthill status of this cell.
     *
     * @return Returns the player who owns the anthill located in this cell, or null if this cell is not part of an anthill.
     */
    public Player getAnthill() {
        return anthill;
    }
    
    public void setOccupied(boolean value){
        occupied = value;
    }
    
    public boolean getOccupied(){
        return occupied;
    }
}
