import static org.junit.Assert.*;
import org.junit.Test;


public class PositionTest {

	@Test
	public void testPosition1() throws Exception {
		// Initialise testing objects.
		Position position = new Position(0, 0);
		// Confirm successful construction.
		assertNotNull(position);
	}

	@Test (expected=Exception.class)
	public void testPosition2() throws Exception {
		// Confirm that construction with a negative X throws an exception.
		Position position = new Position(-1, 0);
	}
	
	@Test (expected=Exception.class)
	public void testPosition3() throws Exception {
		// Confirm that construction with a negative Y throws an exception.
		Position position = new Position(0, -1);
	}
	
	@Test
	public void testSetPositon() throws Exception {
		// Initialise testing objects.
		Position position = new Position(0, 0);
		// Set the position to (2, 3).
		position.setPosition(2, 3);
		// Confirm the correct X and Y are returned.
		assertEquals(position.getX(), 2);
		assertEquals(position.getY(), 3);
	}

	@Test
	public void testSetX() throws Exception {
		// Initialise testing objects.
		Position position = new Position(0, 0);
		// Set the X position to 5.
		position.setX(5);
		// Confirm the correct X is returned.
		assertEquals(position.getX(), 5);
	}

	@Test
	public void testSetY() throws Exception {
		// Initialise testing objects.
		Position position = new Position(0, 0);
		// Set the Y position to 5.
		position.setY(5);
		// Confirm the correct Y is returned.
		assertEquals(position.getY(), 5);
	}

	@Test
	public void testGetX() throws Exception {
		// Initialise testing objects.
		Position position = new Position(5, 0);
		// Confirm the correct X is returned.
		assertEquals(position.getX(), 5);
	}

	@Test
	public void testGetY() throws Exception {
		// Initialise testing objects.
		Position position = new Position(0, 5);
		// Confirm the correct Y is returned.
		assertEquals(position.getY(), 5);
	}

}
