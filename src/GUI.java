/**
 * This class produces a visual representation of the game over time.
 * @author Kieran King
 */

import java.util.Random;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.ShapeFill;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Image; 
import org.newdawn.slick.Input; 
import org.newdawn.slick.fills.GradientFill;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Transform;

public class GUI extends BasicGame {
	
	private static AppGameContainer game;
	private Position mapPosition;
	private int tileSize;
	private int mapWidth;
	private int mapHeight;
	private Position upperLeftBoundary;
	private Position lowerRightBoundary;
	private Image gridBuffer;
	
	GUI() {
		super("AntGame");
	}
	public static void main(String[] arguments)	{
        try {
            game = new AppGameContainer(new GUI());
            game.setDisplayMode(1200, 700, false);
            game.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

	@Override
    public void init(GameContainer container) throws SlickException {
		try {
			// Initialise.
			/*private DisplayMode[] displayModes = Display.getAvailableDisplayModes();
			for (int i = 0; i<displayModes.length; i++) {
				System.out.println(displayModes[i].getWidth() + ", " + displayModes[i].getHeight());
			}*/
			mapPosition = new Position(20, 20);
			tileSize = 20;
			mapWidth = 100;
			mapHeight = 100;
			upperLeftBoundary = new Position(0, 0);
			lowerRightBoundary = new Position(0, 0);
			generateGrid();
		} catch (Exception exception) {
			// Catch exceptions.
			exception.printStackTrace();
		}
    }
 
    @Override
    public void update(GameContainer container, int delta) throws SlickException {
    	try {
	    	// Get the input state.
	    	Input input = container.getInput();
	    	// Fullscreen.
	    	if (input.isKeyPressed(Input.KEY_ESCAPE)) {
	    		if (game.isFullscreen()) {
	    			// Turn off fullscreen.
	    			game.setDisplayMode(1000, 700, false);
	    		} else {
	    			// Turn on fullscren.
	    			game.setDisplayMode(1600, 900, true);
	    		}
	    	}
	    	// Map zooming.
	    	if (input.isKeyPressed(Input.KEY_LBRACKET)) {
				if (tileSize > 5) tileSize -= 5;
				generateGrid();
	    	} else if (input.isKeyPressed(Input.KEY_RBRACKET)) {
	    		tileSize += 5;
	    		generateGrid();
	        }
	    	// Map scrolling.
	    	if (input.isKeyDown(Input.KEY_UP) && mapPosition.getY() < upperLeftBoundary.getY()) {
				mapPosition.setY(mapPosition.getY() + 3);
	    	}
	    	if (input.isKeyDown(Input.KEY_DOWN) && mapPosition.getY() > lowerRightBoundary.getY()) {
	    		mapPosition.setY(mapPosition.getY() - 3);
	        }
	    	if (input.isKeyDown(Input.KEY_LEFT) && mapPosition.getX() < upperLeftBoundary.getX()) {
				mapPosition.setX(mapPosition.getX() + 3);
	    	}
	    	if (input.isKeyDown(Input.KEY_RIGHT) && mapPosition.getX() > lowerRightBoundary.getX()) {
	    		mapPosition.setX(mapPosition.getX() - 3);
	        }
    	} catch (Exception exception) {
			// Catch exceptions.
			exception.printStackTrace();
		}
    }
 
    @Override
    public void render(GameContainer container, Graphics graphics) throws SlickException {
    	ShapeFill background = new GradientFill(0, 0, new Color(60, 60, 60), game.getWidth(), game.getHeight(), new Color(10, 10, 10));
    	graphics.fill(new Rectangle(0, 0, game.getWidth(), game.getHeight()), background);
    	graphics.drawImage(gridBuffer, mapPosition.getX(), mapPosition.getY());
    	graphics.setAntiAlias(true);
    	//
    	try {
			Position[] anthills = {new Position(5, 2), new Position(6, 2), new Position(5, 3)};
			
	    	Polygon hexagon = generateHexagon(tileSize);
	    	
	    	graphics.setColor(new Color(200, 0, 0));
	    	
	    	for (int i=0; i<anthills.length; i++) {
	    		hexagon.setCenterX(mapPosition.getX()+tileSize/2+anthills[i].getX()*tileSize);
		    	hexagon.setCenterY(mapPosition.getY()+tileSize/2+anthills[i].getY()*tileSize);
		    	graphics.draw(hexagon);
		    	graphics.fill(hexagon);
	    	}
	    	
			
		} catch (Exception exception) {
			// Catch exceptions.
			exception.printStackTrace();
		}
    	
    	
    }
	
    private void generateGrid() throws SlickException {
    	// Initalise.
    	gridBuffer = new Image(mapWidth*tileSize+tileSize/2, mapHeight*tileSize);
    	Graphics gridGraphics = gridBuffer.getGraphics();		//gridGraphics.copyArea(grid, 0, 0);
    	gridGraphics.setAntiAlias(true);
    	Random randomGenerator = new Random();
    	int random;
    	// Clear the image.
    	gridGraphics.setBackground(new Color(0,0,0,0));
    	gridGraphics.clear();
    	// Generate a hexagon of the appropriate size.
    	Polygon hexagon = generateHexagon(tileSize);
    	// Generate grid.
    	boolean offsetRow = false;
    	for(int x=0; x<mapWidth; x++) {
    		for (int y=0; y<mapHeight; y++) {
    			// Position this hexagon.
    			if (offsetRow) {
    				// Add an offset to the row.
    				hexagon.setCenterX(tileSize+x*tileSize);
    				offsetRow = false;
    			} else {
    				// Do not add an offset to the row.
    				hexagon.setCenterX(tileSize/2+x*tileSize);
    				offsetRow = true;
    			}
    	    	hexagon.setCenterY(tileSize/2+y*tileSize);
    	    	// Draw this hexagon.
    	    	random = randomGenerator.nextInt(100);
    	    	gridGraphics.setColor(new Color(120+random, 120+random, 120+random));
    	    	gridGraphics.draw(hexagon);
    	    	gridGraphics.fill(hexagon);
    		}
    	}
    	try {
    		// Update map boundaries.
    		if (gridBuffer.getWidth()+40 > game.getWidth()) {
    			// When the grid width is larger than the window.
    			upperLeftBoundary.setX(20);
    			lowerRightBoundary.setX(game.getWidth() - gridBuffer.getWidth() - 20);
    		} else {
    			// When the grid width is smaller than the window.
    			upperLeftBoundary.setX(game.getWidth() - gridBuffer.getWidth() - 20);
    			lowerRightBoundary.setX(20);
    		}
    		if (gridBuffer.getHeight()+40 > game.getHeight()) {
    			// When the grid height is larger than the window.
    			upperLeftBoundary.setY(20);
    			lowerRightBoundary.setY(game.getHeight() - gridBuffer.getHeight() - 20);
    		} else {
    			// When the grid height is smaller than the window.
    			upperLeftBoundary.setY(game.getHeight() - gridBuffer.getHeight() - 20);
    			lowerRightBoundary.setY(20);
    		}
        	// Reposition the map if the resize caused the map to move outside the new boundaries.
        	if (mapPosition.getX() < upperLeftBoundary.getX()) mapPosition.setX(upperLeftBoundary.getX());
        	if (mapPosition.getX() > lowerRightBoundary.getX()) mapPosition.setX(lowerRightBoundary.getX());
        	if (mapPosition.getY() < upperLeftBoundary.getY()) mapPosition.setY(upperLeftBoundary.getY());
        	if (mapPosition.getY() > lowerRightBoundary.getY()) mapPosition.setY(lowerRightBoundary.getY());
		} catch (Exception exception) {
			// Catch exceptions.
			exception.printStackTrace();
		}
    	// Flush and return.
    	gridGraphics.flush();
    }
    
    
    private Polygon generateHexagon(int size) {
    	int radius = size/2;    	
    	float a = (float) ((Math.PI * 2)/6);
    	Polygon hexagon = new Polygon();
    	hexagon.addPoint(radius, 0);
    	for(int i = 1; i < 6; ++i) {
    		hexagon.addPoint((float)(radius*Math.cos(a*i)), (float)(radius*Math.sin(a*i)));
    	}
    	return (Polygon) hexagon.transform(Transform.createRotateTransform(100, hexagon.getCenterX(), hexagon.getCenterY()));
    }
    
}
