import static org.junit.Assert.*;

import org.junit.Test;


public class CellTest {

	@Test
	public void testCell() throws Exception {
		// Initialise testing objects.
		Cell cell = new Cell(new Position(0, 0));
		// Confirm successful construction.
		assertNotNull(cell);
	}

	@Test
	public void testGetPosition() throws Exception {
		// Initialise testing objects.
		Cell cell = new Cell(new Position(5, 6));
		// Confirm the correct position is returned.
		assertEquals(cell.getPosition(), new Position(5, 6));
	}

	@Test
	public void testGetFoodCount() throws Exception {
		// Initialise testing objects.
		Cell cell = new Cell(new Position(0, 0));
		// Set the number of food particles contained in this cell to 23.
		cell.setFoodCount(23);
		// Confirm the correct number of food particles are returned.
		assertEquals(cell.getFoodCount(), 23);
	}
	
	@Test
	public void testSetFoodCount() throws Exception {
		// Initialise testing objects.
		Cell cell = new Cell(new Position(0, 0));
		// Set the number of food particles contained in this cell to 23.
		cell.setFoodCount(23);
	}

	@Test
	public void testGetMarker() throws Exception {
		// Initialise testing objects.
		Cell cell = new Cell(new Position(0, 0));
		Player player = new Player(0, "test", null);
		// Confirm chemical marker "3" for the specified player is unmarked.
		assertFalse(cell.getMarker(player, 3));
		// Set chemical marker "3" for the specified player to be marked.
		cell.setMarker(player, 3, true);
		// Confirm chemical marker "3" for the specified player has changed from unmarked to marked.
		assertTrue(cell.getMarker(player, 3));
	}
	
	@Test
	public void testSetMarker() throws Exception {
		// Initialise testing objects.
		Cell cell = new Cell(new Position(0, 0));
		Player player = new Player(0, "test", null);
		// Set chemical marker "3" for the specified player to be marked.
		cell.setMarker(player, 3, true);
	}

	@Test
	public void testIsRocky() throws Exception {
		// Initialise testing objects.
		Cell cell = new Cell(new Position(0, 0));
		// Confirm this cell is currently clear.
		assertFalse(cell.isRocky());
		// Set this cell to rocky.
		cell.setRocky(true);
		// Confirm this cell has updated from clear to rocky.
		assertTrue(cell.isRocky());
	}

	@Test
	public void testSetRocky() throws Exception {
		// Initialise testing objects.
		Cell cell = new Cell(new Position(0, 0));
		// Set this cell to rocky.
		cell.setRocky(true);
	}

	@Test
	public void testGetAnt() throws Exception {
		// Initialise testing objects.
		Cell cell = new Cell(new Position(0, 0));
		Player player = new Player(0, "test", null);
		Ant ant = new Ant(player);
		// Set the specified ant to be located in this cell.
		cell.setAnt(ant);
		// Confirm the specified ant is located in this cell.
		assertEquals(cell.getAnt(), ant);
	}
	
	@Test
	public void testSetAnt() throws Exception {
		// Initialise testing objects.
		Cell cell = new Cell(new Position(0, 0));
		Player player = new Player(0, "test", null);
		Ant ant = new Ant(player);
		// Set the specified ant to be located in this cell.
		cell.setAnt(ant);
	}

	@Test
	public void testGetAnthill() throws Exception {
		// Initialise testing objects.
		Cell cell = new Cell(new Position(0, 0));
		Player player = new Player(0, "test", null);
		// Set the anthill status of this cell to the specified player.
		cell.setAnthill(player);
		// Confirm the anthill status of this belongs to the specified player.
		assertEquals(cell.getAnthill(), player);
	}
	
	@Test
	public void testSetAnthill() throws Exception {
		// Initialise testing objects.
		Cell cell = new Cell(new Position(0, 0));
		Player player = new Player(0, "test", null);
		// Set the anthill status of this cell to the specified player.
		cell.setAnthill(player);
	}

}
