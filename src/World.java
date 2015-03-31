/**
 * This class represents the world of a single match.
 *
 * @author Ben Jackson, Kieran King
 */
package antproject.src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class World {

    private Cell[][] cells;

    public Cell[][] getCells() {
        return cells;
    }
    private Player[] players;
    private Ant[] ants;

    public Ant[] getAnts() {
        return ants;
    }
    private int[] antCount; // Note: update class diagram to single array
    private int[] foodCount; // Note: update class diagram to single array
    private int round;
    private final int SIZE_X = 150;
    private final int SIZE_Y = 150;

    /**
     * Construct a randomly generated world.
     */
    public World(Player[] players) throws Exception {
        this.players = players;
        antCount = new int[players.length];
        foodCount = new int[players.length];
        ants = new Ant[130 * players.length];
        generateWorld();

    }

    /**
     * Construct a world loaded from file.
     *
     * @param the world file to load
     */
    public World(Player[] players, String worldPath) throws IOException, Exception {
        this.players = players;
        antCount = new int[players.length];
        foodCount = new int[players.length];
        ants = new Ant[130 * players.length];
        loadWorld(worldPath);
    }

    /**
     * Randomly generate world.
     */
    private void generateWorld() throws Exception {
        Random rand = new Random();

        //DEFINING SIZE AND CELLS
        cells = new Cell[SIZE_X][SIZE_Y];
        for (int y = 0; y < SIZE_Y; y++) {
            for (int x = 0; x < SIZE_X; x++) {
                cells[x][y] = new Cell(new Position(x, y));
            }
        }
        //BUILDING PERIMITER WALLS
        //top + bottom wall
        for (int x = 0; x < SIZE_X; x++) {
            cells[x][0].setRocky(true);
            cells[x][SIZE_Y - 1].setRocky(true);
            cells[x][0].setOccupied(true);
            cells[x][SIZE_Y - 1].setOccupied(true);
        }
        //left + right wall
        for (int y = 0; y < SIZE_Y; y++) {
            cells[0][y].setRocky(true);
            cells[SIZE_X - 1][y].setRocky(true);
            cells[0][y].setOccupied(true);
            cells[SIZE_X - 1][y].setOccupied(true);
        }

        /**
         * BUILDING TWO HEXAGONAL ANTHILLS, SIDE LENGTH 7 we build the hexagon
         * based on the even/oddness of its row
         */
        for (int hillcount = 0; hillcount < 2; hillcount++) {
            boolean foundhillpos = false;
            //loop until a good position is found

            while (!foundhillpos) {
                //generate random position
                int randx = rand.nextInt(SIZE_X);
                int randy = rand.nextInt(SIZE_Y);

                //check the domain
                if (((randx + 15) < SIZE_X - 1) && ((randy + 15) < SIZE_Y - 1)) {
                    //check the row odd/even
                    if (randy % 2 == 0) {
                        //build even anthill
                        //check the space is free
                        foundhillpos = true;

                        for (int y = randy + 5; y < randy + 10; y++) {
                            if (cells[randx][y].getOccupied()) {
                                foundhillpos = false;
                            }
                        }

                        for (int y = randy + 3; y < randy + 12; y++) {
                            if (cells[randx + 1][y].getOccupied()) {
                                foundhillpos = false;
                            }
                        }

                        for (int y = randy + 1; y < randy + 14; y++) {
                            if (cells[randx + 2][y].getOccupied()) {
                                foundhillpos = false;
                            }
                        }

                        for (int y = randy; y < randy + 15; y++) {
                            for (int x = randx + 3; x < randy + 12; x++) {
                                if (cells[x][y].getOccupied()) {
                                    foundhillpos = false;
                                }
                            }
                        }

                        for (int y = randy + 2; y < randy + 13; y++) {
                            if (cells[randx + 12][y].getOccupied()) {
                                foundhillpos = false;
                            }
                        }

                        for (int y = randy + 4; y < randy + 11; y++) {
                            if (cells[randx + 13][y].getOccupied()) {
                                foundhillpos = false;
                            }
                        }

                        for (int y = randy + 6; y < randy + 9; y++) {
                            if (cells[randx + 14][y].getOccupied()) {
                                foundhillpos = false;
                            }
                        }

                        //build hill
                        if (foundhillpos) {

                            for (int y = randy + 5; y < randy + 10; y++) {
                                cells[randx][y].setOccupied(true);
                            }

                            for (int y = randy + 3; y < randy + 12; y++) {
                                cells[randx + 1][y].setOccupied(true);
                            }

                            for (int y = randy + 1; y < randy + 14; y++) {
                                cells[randx + 2][y].setOccupied(true);
                            }

                            for (int y = randy; y < randy + 15; y++) {
                                for (int x = randx + 3; x < randx + 12; x++) {
                                    cells[x][y].setOccupied(true);
                                }
                            }

                            for (int y = randy + 2; y < randy + 13; y++) {
                                cells[randx + 12][y].setOccupied(true);
                            }

                            for (int y = randy + 4; y < randy + 11; y++) {
                                cells[randx + 13][y].setOccupied(true);
                            }

                            for (int y = randy + 6; y < randy + 9; y++) {
                                cells[randx + 14][y].setOccupied(true);
                            }

                            for (int y = randy + 6; y < randy + 9; y++) {
                                cells[randx + 1][y].setAnthill(players[hillcount]);
                            }

                            for (int y = randy + 4; y < randy + 11; y++) {
                                cells[randx + 2][y].setAnthill(players[hillcount]);
                            }

                            for (int y = randy + 2; y < randy + 13; y++) {
                                cells[randx + 3][y].setAnthill(players[hillcount]);
                            }

                            for (int y = randy + 1; y < randy + 14; y++) {
                                for (int x = randx + 4; x < randx + 11; x++) {
                                    cells[x][y].setAnthill(players[hillcount]);
                                }
                            }

                            for (int y = randy + 3; y < randy + 12; y++) {
                                cells[randx + 11][y].setAnthill(players[hillcount]);
                            }

                            for (int y = randy + 5; y < randy + 10; y++) {
                                cells[randx + 12][y].setAnthill(players[hillcount]);
                            }

                            for (int y = randy + 7; y < randy + 8; y++) {
                                cells[randx + 13][y].setAnthill(players[hillcount]);
                            }

                        }
                    } else {
                        //build odd anthill
                        //check the space is free
                        foundhillpos = true;

                        for (int y = randy + 6; y < randy + 9; y++) {
                            if (cells[randx][y].getOccupied()) {
                                foundhillpos = false;
                            }
                        }

                        for (int y = randy + 4; y < randy + 11; y++) {
                            if (cells[randx + 1][y].getOccupied()) {
                                foundhillpos = false;
                            }
                        }

                        for (int y = randy + 2; y < randy + 13; y++) {
                            if (cells[randx + 2][y].getOccupied()) {
                                foundhillpos = false;
                            }
                        }

                        for (int y = randy; y < randy + 15; y++) {
                            for (int x = randx + 3; x < randy + 12; x++) {
                                if (cells[x][y].getOccupied()) {
                                    foundhillpos = false;
                                }
                            }
                        }

                        for (int y = randy + 1; y < randy + 14; y++) {
                            if (cells[randx + 12][y].getOccupied()) {
                                foundhillpos = false;
                            }
                        }

                        for (int y = randy + 3; y < randy + 12; y++) {
                            if (cells[randx + 13][y].getOccupied()) {
                                foundhillpos = false;
                            }
                        }

                        for (int y = randy + 5; y < randy + 10; y++) {
                            if (cells[randx + 14][y].getOccupied()) {
                                foundhillpos = false;
                            }
                        }

                        //build hill
                        if (foundhillpos) {

                            for (int y = randy + 6; y < randy + 9; y++) {
                                cells[randx][y].setOccupied(true);
                            }

                            for (int y = randy + 4; y < randy + 11; y++) {
                                cells[randx + 1][y].setOccupied(true);
                            }

                            for (int y = randy + 2; y < randy + 13; y++) {
                                cells[randx + 2][y].setOccupied(true);
                            }

                            for (int y = randy; y < randy + 15; y++) {
                                for (int x = randx + 3; x < randx + 12; x++) {
                                    cells[x][y].setOccupied(true);
                                }
                            }

                            for (int y = randy + 1; y < randy + 14; y++) {
                                cells[randx + 12][y].setOccupied(true);
                            }

                            for (int y = randy + 3; y < randy + 12; y++) {
                                cells[randx + 13][y].setOccupied(true);
                            }

                            for (int y = randy + 5; y < randy + 10; y++) {
                                cells[randx + 14][y].setOccupied(true);
                            }

                            for (int y = randy + 7; y < randy + 8; y++) {
                                cells[randx + 1][y].setAnthill(players[hillcount]);
                            }

                            for (int y = randy + 5; y < randy + 10; y++) {
                                cells[randx + 2][y].setAnthill(players[hillcount]);
                            }

                            for (int y = randy + 3; y < randy + 12; y++) {
                                cells[randx + 3][y].setAnthill(players[hillcount]);
                            }

                            for (int y = randy + 1; y < randy + 14; y++) {
                                for (int x = randx + 4; x < randx + 11; x++) {
                                    cells[x][y].setAnthill(players[hillcount]);
                                }
                            }

                            for (int y = randy + 2; y < randy + 13; y++) {
                                cells[randx + 11][y].setAnthill(players[hillcount]);
                            }

                            for (int y = randy + 4; y < randy + 11; y++) {
                                cells[randx + 12][y].setAnthill(players[hillcount]);
                            }

                            for (int y = randy + 6; y < randy + 9; y++) {
                                cells[randx + 13][y].setAnthill(players[hillcount]);
                            }

                        }

                    }
                }
            }
        }

        /**
         * BUILDING 11 5*5 FOOD BLOBS OF 5 FOOD (these come in four
         * configurations on a hexagonal grid, downleft slant even/odd, and
         * downright slant even/odd we randomly choose left or right slant, then
         * build the blob based on the even/oddness of its row
         */
        for (int count = 0; count < 11; count++) {
            boolean foodposfound = false;
            switch (rand.nextInt(2)) {
                //downright slant
                case 0:
                    while (foodposfound == false) {
                        int randx = rand.nextInt(SIZE_X);
                        int randy = rand.nextInt(SIZE_Y);

                        boolean small = true;
                        boolean bigone = true;
                        boolean bigtwo = true;

                        //check the row -odd has the small section first, even last
                        if (randy % 2 == 0) {
                            //check the domain
                            if (((randx + 9) < SIZE_X - 1) && ((randy + 7) < SIZE_Y - 1)) {
                                //checking each of the three segments of the blob shape
                                for (int smally = randy + 4; smally < randy + 7; smally++) {
                                    for (int smallx = randx + 2; smallx < randx + 9; smallx++) {
                                        if (cells[smallx][smally].getOccupied()) {
                                            small = false;
                                        }
                                    }
                                }

                                for (int bigoney = randy; bigoney < randy + 4; bigoney++) {
                                    for (int bigonex = randx; bigonex < randx + 7; bigonex++) {
                                        if (cells[bigonex][bigoney].getOccupied()) {
                                            bigone = false;
                                        }
                                    }
                                }

                                for (int bigtwoy = randy + 2; bigtwoy < randy + 6; bigtwoy++) {
                                    for (int bigtwox = randx + 1; bigtwox < randx + 8; bigtwox++) {
                                        if (cells[bigtwox][bigtwoy].getOccupied()) {
                                            bigtwo = false;
                                        }
                                    }
                                }

                                if (small && bigone && bigtwo) {
                                    foodposfound = true;
                                    //set foodplace
                                    for (int smally = randy + 4; smally < randy + 7; smally++) {
                                        for (int smallx = randx + 2; smallx < randx + 9; smallx++) {
                                            cells[smallx][smally].setOccupied(true);
                                        }
                                    }

                                    for (int smallx = randx + 3; smallx < randx + 8; smallx++) {
                                        cells[smallx][randy + 5].setFoodCount(5);
                                    }

                                    for (int bigoney = randy; bigoney < randy + 4; bigoney++) {
                                        for (int bigonex = randx; bigonex < randx + 7; bigonex++) {
                                            cells[bigonex][bigoney].setOccupied(true);
                                        }
                                    }

                                    for (int bigoney = randy + 1; bigoney < randy + 3; bigoney++) {
                                        for (int bigonex = randx + 1; bigonex < randx + 6; bigonex++) {
                                            cells[bigonex][bigoney].setFoodCount(5);
                                        }
                                    }

                                    for (int bigtwoy = randy + 2; bigtwoy < randy + 6; bigtwoy++) {
                                        for (int bigtwox = randx + 1; bigtwox < randx + 8; bigtwox++) {
                                            cells[bigtwox][bigtwoy].setOccupied(true);
                                        }
                                    }

                                    for (int bigtwoy = randy + 3; bigtwoy < randy + 5; bigtwoy++) {
                                        for (int bigtwox = randx + 2; bigtwox < randx + 7; bigtwox++) {
                                            cells[bigtwox][bigtwoy].setFoodCount(5);
                                        }
                                    }
                                }
                            }
                        } else {
                            //check the domain
                            if (((randx + 9) < SIZE_X - 1) && ((randy + 7) < SIZE_Y - 1)) {
                                //checking each of the three segments of the blob shape
                                for (int smally = randy; smally < randy + 3; smally++) {
                                    for (int smallx = randx; smallx < randx + 7; smallx++) {
                                        if (cells[smallx][smally].getOccupied()) {
                                            small = false;
                                        }
                                    }
                                }

                                for (int bigoney = randy + 1; bigoney < randy + 5; bigoney++) {
                                    for (int bigonex = randx + 1; bigonex < randx + 8; bigonex++) {
                                        if (cells[bigonex][bigoney].getOccupied()) {
                                            bigone = false;
                                        }
                                    }
                                }

                                for (int bigtwoy = randy + 3; bigtwoy < randy + 7; bigtwoy++) {
                                    for (int bigtwox = randx + 2; bigtwox < randx + 9; bigtwox++) {
                                        if (cells[bigtwox][bigtwoy].getOccupied()) {
                                            bigtwo = false;
                                        }
                                    }
                                }

                                if (small && bigone && bigtwo) {
                                    foodposfound = true;
                                    //set foodplace
                                    for (int smally = randy; smally < randy + 3; smally++) {
                                        for (int smallx = randx; smallx < randx + 7; smallx++) {
                                            cells[smallx][smally].setOccupied(true);
                                        }
                                    }

                                    for (int smallx = randx + 1; smallx < randx + 6; smallx++) {
                                        cells[smallx][randy + 1].setFoodCount(5);
                                    }

                                    for (int bigoney = randy + 1; bigoney < randy + 5; bigoney++) {
                                        for (int bigonex = randx + 1; bigonex < randx + 8; bigonex++) {
                                            cells[bigonex][bigoney].setOccupied(true);
                                        }
                                    }

                                    for (int bigoney = randy + 2; bigoney < randy + 4; bigoney++) {
                                        for (int bigonex = randx + 2; bigonex < randx + 7; bigonex++) {
                                            cells[bigonex][bigoney].setFoodCount(5);
                                        }
                                    }

                                    for (int bigtwoy = randy + 3; bigtwoy < randy + 7; bigtwoy++) {
                                        for (int bigtwox = randx + 2; bigtwox < randx + 9; bigtwox++) {
                                            cells[bigtwox][bigtwoy].setOccupied(true);
                                        }
                                    }

                                    for (int bigtwoy = randy + 4; bigtwoy < randy + 6; bigtwoy++) {
                                        for (int bigtwox = randx + 3; bigtwox < randx + 8; bigtwox++) {
                                            cells[bigtwox][bigtwoy].setFoodCount(5);
                                        }
                                    }
                                }
                            }
                        }

                    }
                    break;
                //downleft slant 
                case 1:
                    while (foodposfound == false) {
                        int randx = rand.nextInt(SIZE_X);
                        int randy = rand.nextInt(SIZE_Y);

                        boolean small = true;
                        boolean bigone = true;
                        boolean bigtwo = true;

                        //check the row -odd has the small section first, even last
                        if (randy % 2 == 0) {
                            //check the domain
                            if (((randx + 9) < SIZE_X - 1) && ((randy + 7) < SIZE_Y - 1)) {
                                //checking each of the three segments of the blob shape
                                for (int smally = randy + 4; smally < randy + 7; smally++) {
                                    for (int smallx = randx; smallx < randx + 7; smallx++) {
                                        if (cells[smallx][smally].getOccupied()) {
                                            small = false;
                                        }
                                    }
                                }

                                for (int bigoney = randy; bigoney < randy + 4; bigoney++) {
                                    for (int bigonex = randx + 2; bigonex < randx + 9; bigonex++) {
                                        if (cells[bigonex][bigoney].getOccupied()) {
                                            bigone = false;
                                        }
                                    }
                                }

                                for (int bigtwoy = randy + 2; bigtwoy < randy + 6; bigtwoy++) {
                                    for (int bigtwox = randx + 1; bigtwox < randx + 8; bigtwox++) {
                                        if (cells[bigtwox][bigtwoy].getOccupied()) {
                                            bigtwo = false;
                                        }
                                    }
                                }

                                if (small && bigone && bigtwo) {
                                    foodposfound = true;
                                    //set foodplace
                                    for (int smally = randy + 4; smally < randy + 7; smally++) {
                                        for (int smallx = randx; smallx < randx + 7; smallx++) {
                                            cells[smallx][smally].setOccupied(true);
                                        }
                                    }

                                    for (int smallx = randx + 1; smallx < randx + 6; smallx++) {
                                        cells[smallx][randy + 5].setFoodCount(5);
                                    }

                                    for (int bigoney = randy; bigoney < randy + 4; bigoney++) {
                                        for (int bigonex = randx + 2; bigonex < randx + 9; bigonex++) {
                                            cells[bigonex][bigoney].setOccupied(true);
                                        }
                                    }

                                    for (int bigoney = randy + 1; bigoney < randy + 3; bigoney++) {
                                        for (int bigonex = randx + 3; bigonex < randx + 8; bigonex++) {
                                            cells[bigonex][bigoney].setFoodCount(5);
                                        }
                                    }

                                    for (int bigtwoy = randy + 2; bigtwoy < randy + 6; bigtwoy++) {
                                        for (int bigtwox = randx + 1; bigtwox < randx + 8; bigtwox++) {
                                            cells[bigtwox][bigtwoy].setOccupied(true);
                                        }
                                    }

                                    for (int bigtwoy = randy + 3; bigtwoy < randy + 5; bigtwoy++) {
                                        for (int bigtwox = randx + 2; bigtwox < randx + 7; bigtwox++) {
                                            cells[bigtwox][bigtwoy].setFoodCount(5);
                                        }
                                    }
                                }
                            }
                        } else {
                            //check the domain
                            if (((randx + 9) < SIZE_X - 1) && ((randy + 7) < SIZE_Y - 1)) {
                                //checking each of the three segments of the blob shape
                                for (int smally = randy; smally < randy + 3; smally++) {
                                    for (int smallx = randx + 2; smallx < randx + 9; smallx++) {
                                        if (cells[smallx][smally].getOccupied()) {
                                            small = false;
                                        }
                                    }
                                }

                                for (int bigoney = randy + 1; bigoney < randy + 5; bigoney++) {
                                    for (int bigonex = randx + 1; bigonex < randx + 8; bigonex++) {
                                        if (cells[bigonex][bigoney].getOccupied()) {
                                            bigone = false;
                                        }
                                    }
                                }

                                for (int bigtwoy = randy + 3; bigtwoy < randy + 7; bigtwoy++) {
                                    for (int bigtwox = randx; bigtwox < randx + 7; bigtwox++) {
                                        if (cells[bigtwox][bigtwoy].getOccupied()) {
                                            bigtwo = false;
                                        }
                                    }
                                }

                                if (small && bigone && bigtwo) {
                                    foodposfound = true;
                                    //set foodplace
                                    for (int smally = randy; smally < randy + 3; smally++) {
                                        for (int smallx = randx + 2; smallx < randx + 9; smallx++) {
                                            cells[smallx][smally].setOccupied(true);
                                        }
                                    }

                                    for (int smallx = randx + 3; smallx < randx + 8; smallx++) {
                                        cells[smallx][randy + 1].setFoodCount(5);
                                    }

                                    for (int bigoney = randy + 1; bigoney < randy + 5; bigoney++) {
                                        for (int bigonex = randx + 1; bigonex < randx + 8; bigonex++) {
                                            cells[bigonex][bigoney].setOccupied(true);
                                        }
                                    }

                                    for (int bigoney = randy + 2; bigoney < randy + 4; bigoney++) {
                                        for (int bigonex = randx + 2; bigonex < randx + 7; bigonex++) {
                                            cells[bigonex][bigoney].setFoodCount(5);
                                        }
                                    }

                                    for (int bigtwoy = randy + 3; bigtwoy < randy + 7; bigtwoy++) {
                                        for (int bigtwox = randx + 0; bigtwox < randx + 7; bigtwox++) {
                                            cells[bigtwox][bigtwoy].setOccupied(true);
                                        }
                                    }

                                    for (int bigtwoy = randy + 4; bigtwoy < randy + 6; bigtwoy++) {
                                        for (int bigtwox = randx + 1; bigtwox < randx + 6; bigtwox++) {
                                            cells[bigtwox][bigtwoy].setFoodCount(5);
                                        }
                                    }
                                }
                            }
                        }

                    }
                    break;
            }
        }

        //BUILDING 14 ROCKS
        for (int count = 0; count < 14; count++) {
            boolean rockposfound = false;
            //finding a suitable position
            while (rockposfound == false) {
                int randx = rand.nextInt(SIZE_X);
                int randy = rand.nextInt(SIZE_Y);
                if (!cells[randx][randy].getOccupied()) {
                    rockposfound = true;
                    cells[randx][randy].setRocky(true);
                    cells[randx][randy].setOccupied(true);
                    //setting occupied boundaries
                    //left
                    if (randx > 0) {
                        cells[randx - 1][randy].setOccupied(true);
                    }

                    //right
                    if (randx < SIZE_X - 1) {
                        cells[randx + 1][randy].setOccupied(true);
                    }

                    //downright
                    if ((randx < SIZE_X - 1) && (randy < SIZE_Y - 1)) {
                        if (randy % 2 == 0) {
                            //even
                            cells[randx][randy + 1].setOccupied(true);
                        } else {
                            //odd
                            cells[randx + 1][randy + 1].setOccupied(true);
                        }
                    }

                    //downleft
                    if ((randx > 0) && (randy < SIZE_Y - 1)) {
                        if (randy % 2 == 0) {
                            //even
                            cells[randx - 1][randy + 1].setOccupied(true);
                        } else {
                            //odd
                            cells[randx][randy + 1].setOccupied(true);
                        }
                    }

                    //upleft
                    if ((randx > 0) && (randy > 0)) {
                        if (randy % 2 == 0) {
                            //even
                            cells[randx - 1][randy - 1].setOccupied(true);
                        } else {
                            //odd
                            cells[randx][randy - 1].setOccupied(true);
                        }
                    }

                    //upright
                    if ((randx < SIZE_X - 1) && (randy > 0)) {
                        if (randy % 2 == 0) {
                            //even
                            cells[randx][randy - 1].setOccupied(true);
                        } else {
                            //odd
                            cells[randx + 1][randy - 1].setOccupied(true);
                        }
                    }
                }
            }
        }

        //INITIALISE ANTS
        for (int y = 0; y < SIZE_Y; y++) {
            for (int x = 0; x < SIZE_X; x++) {
                if (cells[x][y].getAnthill() == players[0]) {
                    Ant notdec = new Ant(players[0]);
                    cells[x][y].setAnt(notdec);
                    notdec.setCell(cells[x][y]);
                    notdec.setDirection(0);
                    notdec.setFood(false);
                    notdec.setRestingTurns(0);
                    notdec.setState(0);
                    notdec.setId(antCount[0] + antCount[1]);
                    antCount[0]++;
                    ants[antCount[0] + antCount[1]] = notdec;
                }
                if (cells[x][y].getAnthill() == players[1]) {
                    Ant notdec = new Ant(players[1]);
                    cells[x][y].setAnt(notdec);
                    notdec.setCell(cells[x][y]);
                    notdec.setDirection(0);
                    notdec.setFood(false);
                    notdec.setRestingTurns(0);
                    notdec.setState(0);
                    notdec.setId(antCount[0] + antCount[1]);
                    antCount[1]++;
                    ants[antCount[0] + antCount[1]] = notdec;
                }
            }
        }
    }

    /**
     * test method to display a world
     */
    public void displayworld() {
        //display the world to check
        for (int y = 0; y < SIZE_Y; y++) {
            for (int x = 0; x < SIZE_X; x++) {

                if (cells[x][y].isRocky()) {
                    System.out.print("#");
                } else if (cells[x][y].getAnthill() != null) {
                    if (cells[x][y].getAnthill().getId() == 0) {
                        System.out.print("+");
                    } else if (cells[x][y].getAnthill().getId() == 1) {
                        System.out.print("-");
                    }
                } else if (cells[x][y].getFoodCount() > 0) {
                    System.out.print(cells[x][y].getFoodCount());
                } else if (cells[x][y].getOccupied()) {
                    System.out.print(".");
                } else {
                    System.out.print(".");
                }

            }
            System.out.println("");
        }

    }

    /**
     * Load a world from file.
     *
     * @param the world file to load
     */
    private void loadWorld(String worldPath) throws IOException, Exception {
        //load world file from path

        //DEFINING SIZE AND CELLS
        cells = new Cell[SIZE_X][SIZE_Y];
        for (int y = 0; y < SIZE_Y; y++) {
            for (int x = 0; x < SIZE_X; x++) {
                cells[x][y] = new Cell(new Position(x, y));
            }
        }

        //read in each cell as string then space
        FileReader fr = new FileReader(worldPath);
        BufferedReader textReader = new BufferedReader(fr);
        textReader.readLine();
        textReader.readLine();
        for (int y = 0; y < SIZE_Y; y++) {
            int xcount = 0;
            for (char c : textReader.readLine().toCharArray()) {
                if (c == '#') {
                    cells[xcount][y].setRocky(true);
                } else if (c == '+') {
                    cells[xcount][y].setAnthill(players[0]);
                } else if (c == '-') {
                    cells[xcount][y].setAnthill(players[1]);
                } else if (c == '1') {
                    cells[xcount][y].setFoodCount(1);
                } else if (c == '2') {
                    cells[xcount][y].setFoodCount(2);
                } else if (c == '3') {
                    cells[xcount][y].setFoodCount(3);
                } else if (c == '4') {
                    cells[xcount][y].setFoodCount(4);
                } else if (c == '5') {
                    cells[xcount][y].setFoodCount(5);
                } else if (c == '6') {
                    cells[xcount][y].setFoodCount(6);
                } else if (c == '7') {
                    cells[xcount][y].setFoodCount(7);
                } else if (c == '8') {
                    cells[xcount][y].setFoodCount(8);
                } else if (c == '9') {
                    cells[xcount][y].setFoodCount(9);
                } else if (c == ' ') {
                    xcount--;
                }

                xcount++;
            }
        }
        textReader.close();

        //INITIALISE ANTS
        for (int y = 0; y < SIZE_Y; y++) {
            for (int x = 0; x < SIZE_X; x++) {
                if (cells[x][y].getAnthill() == players[0]) {
                    Ant notdec = new Ant(players[0]);
                    cells[x][y].setAnt(notdec);
                    notdec.setCell(cells[x][y]);
                    notdec.setDirection(0);
                    notdec.setFood(false);
                    notdec.setRestingTurns(0);
                    notdec.setState(0);
                    notdec.setId(antCount[0] + antCount[1]);
                    antCount[0]++;
                    ants[antCount[0] + antCount[1]] = notdec;
                }
                if (cells[x][y].getAnthill() == players[1]) {
                    Ant notdec = new Ant(players[1]);
                    cells[x][y].setAnt(notdec);
                    notdec.setCell(cells[x][y]);
                    notdec.setDirection(0);
                    notdec.setFood(false);
                    notdec.setRestingTurns(0);
                    notdec.setState(0);
                    notdec.setId(antCount[0] + antCount[1]);
                    antCount[1]++;
                    ants[antCount[0] + antCount[1]] = notdec;
                }
            }

        }
    }

    /**
     * Increment the food count of a specified player.
     *
     * @param player the player whose food count should be incremented
     */
    public void incrementFoodCount(Player player) {
        foodCount[player.getId()]++;
    }

    /**
     * Decrement the ant count of a specified player.
     *
     * @param player whose ant count should be decremented
     */
    public void decrementAntCount(Player player) {
        antCount[player.getId()]--;
    }

    /**
     * Get the rocky state of a cell at a specified location.
     *
     * @param position the location of the cell to check
     * @return true for rocky, false for clear
     */
    public boolean getRocky(Position position) {
        // Note: add this method to the class diagram
        return cells[position.getX()][position.getY()].isRocky();
    }

    /**
     * Set the rocky state of a cell at a specified location.
     *
     * @param position the location of the cell to update
     * @param rocky true for rocky, false for clear
     */
    public void setRocky(Position position, boolean rocky) {
        // Note: update the paramters in the class diagram
        cells[position.getX()][position.getY()].setRocky(rocky);
    }

    /**
     * Check if an ant exists in a cell at a specified location.
     *
     * @param position the location of the cell to check
     * @return true if an ant is at the specified location, false otherwise
     */
    public boolean isAntAt(Position position) {
        return cells[position.getX()][position.getY()].getAnt() != null;
    }

    /**
     * Set an ant to a cell at a specified location.
     *
     * @param ant the ant to relocate
     * @param position the location of the cell to move this ant to
     */
    public void setAntAt(Ant ant, Position position) {
        cells[position.getX()][position.getY()].setAnt(ant);
        ant.setCell(cells[position.getX()][position.getY()]);

    }

    /**
     * Remove an ant from a cell at a specified location.
     *
     * @param position the location of the cell where the ant should be removed
     * @return the ant that has been removed from the specified cell
     */
    public Ant clearAntAt(Position position) {
        // Note: update class diagram from what this method returns
        Ant tempant = cells[position.getX()][position.getY()].getAnt();
        cells[position.getX()][position.getY()].setAnt(null);
        return tempant;
    }

    /**
     * Check if an ant of a specified id is alive.
     *
     * @param id the id of the ant to check
     * @return true if the specified ant is alive, false otherwise
     */
    public boolean antIsAlive(int id) {
        return ants[id] != null;
    }

    /**
     * Kill an ant in the cell at a specific location.
     *
     * @param position the location of the cell that contains the ant to kill
     */
    public void killAntAt(Position position) {
        //drop held food
        if (cells[position.getX()][position.getY()].getAnt().getFood()) {
            cells[position.getX()][position.getY()].setFoodCount(cells[position.getX()][position.getY()].getFoodCount() + 1);
        }

        //kill ant
        ants[cells[position.getX()][position.getY()].getAnt().getId()] = null;
        cells[position.getX()][position.getY()].setAnt(null);

        //drop body food
        cells[position.getX()][position.getY()].setFoodCount(cells[position.getX()][position.getY()].getFoodCount() + 3);

        //make sure food not above 9
        if (cells[position.getX()][position.getY()].getFoodCount() > 9) {
            cells[position.getX()][position.getY()].setFoodCount(9);
        }
    }

    /**
     * Get the number of food particles contained in a cell at a specified
     * location.
     *
     * @param position the location of the cell to check
     * @return the number of food particles contained in the specified cell
     */
    public int getFoodAt(Position position) {
        return cells[position.getX()][position.getY()].getFoodCount();
    }

    /**
     * Set the number of food particles contained in a cell at a specified
     * location.
     *
     * @param position the location of the cell to update
     * @param foodCount the number of food particles to set in the specified
     * cell
     */
    public void setFoodAt(Position position, int foodCount) {
        cells[position.getX()][position.getY()].setFoodCount(foodCount);
    }

    /**
     * Get the player that owns an anthill at a specified location.
     *
     * @param position the location of the cell to check
     * @return the player that owns the anthill at the specified location, or
     * null if this cell is not part of an anthill
     */
    public Player getAnthillAt(Position position) {
        return cells[position.getX()][position.getY()].getAnthill();
    }

    /**
     * Set the player that owns an anthill at a specified location.
     *
     * @param position the location of the cell
     * @param player the player that owns the specified cell as an anthill
     */
    public void setAnthillAt(Position position, Player player) {
        cells[position.getX()][position.getY()].setAnthill(player);
    }

    /**
     * Set the state of a chemical marker in a cell at a specified location.
     *
     * @param player the player
     * @param chemical the chemical (from 0 to 5)
     * @param marked the state of the specified chemical marker
     * @param position the location of the cell to update
     */
    public void setMarkerAt(Player player, int chemical, boolean marked, Position position) {
        cells[position.getX()][position.getY()].setMarker(player, chemical, marked);
    }

    /**
     * Get the state of a chemical marker in a cell at a specified location.
     *
     * @param player
     * @param chemical the specific chemical (from 0 to 5)
     * @param position the location of the cell to check
     * @return the state of the specified chemical marker
     */
    public boolean getMarkerAt(Player player, int chemical, Position position) {
        return cells[position.getX()][position.getY()].getMarker(player, chemical);
    }

    /**
     * Get the number of ants that are adjacent to a specified ant.
     *
     * @param ant the ant to check for adjacent ants
     * @return the number of adjacent ants
     */
    public int countAdjacentAnts(Ant ant) {
        int numadj = 0;
        //right
        if (ant.getCell().getPosition().getX() < SIZE_X - 1) {
            if (cells[ant.getCell().getPosition().getX()][ant.getCell().getPosition().getY()].getAnt() != null) {
                numadj++;
            }
        }
        //downright
        if ((ant.getCell().getPosition().getX() < SIZE_X - 1) && (ant.getCell().getPosition().getY() < SIZE_Y - 1)) {
            if (ant.getCell().getPosition().getY() % 2 == 0) {
                //even
                if (cells[ant.getCell().getPosition().getX()][ant.getCell().getPosition().getY() + 1].getAnt() != null) {
                    numadj++;
                }
            } else {
                //odd
                if (cells[ant.getCell().getPosition().getX() + 1][ant.getCell().getPosition().getY() + 1].getAnt() != null) {
                    numadj++;
                }
            }
        }
        //downleft
        if ((ant.getCell().getPosition().getX() > 0) && (ant.getCell().getPosition().getY() < SIZE_Y - 1)) {
            if (ant.getCell().getPosition().getY() % 2 == 0) {
                //even
                if (cells[ant.getCell().getPosition().getX() - 1][ant.getCell().getPosition().getY() + 1].getAnt() != null) {
                    numadj++;
                }
            } else {
                //odd
                if (cells[ant.getCell().getPosition().getX()][ant.getCell().getPosition().getY() + 1].getAnt() != null) {
                    numadj++;
                }
            }
        }
        //left
        if (ant.getCell().getPosition().getX() > 0) {
            if (cells[ant.getCell().getPosition().getX() - 1][ant.getCell().getPosition().getY()].getAnt() != null) {
                numadj++;
            }
        }
        //upleft
        if ((ant.getCell().getPosition().getX() > 0) && (ant.getCell().getPosition().getY() > 0)) {
            if (ant.getCell().getPosition().getY() % 2 == 0) {
                //even
                if (cells[ant.getCell().getPosition().getX() - 1][ant.getCell().getPosition().getY() - 1].getAnt() != null) {
                    numadj++;
                }
            } else {
                //odd
                if (cells[ant.getCell().getPosition().getX()][ant.getCell().getPosition().getY() - 1].getAnt() != null) {
                    numadj++;
                }
            }
        }
        //uprght
        if ((ant.getCell().getPosition().getX() < SIZE_X - 1) && (ant.getCell().getPosition().getY() > 0)) {
            if (ant.getCell().getPosition().getY() % 2 == 0) {
                //even
                if (cells[ant.getCell().getPosition().getX()][ant.getCell().getPosition().getY() - 1].getAnt() != null) {
                    numadj++;
                }
            } else {
                //odd
                if (cells[ant.getCell().getPosition().getX() + 1][ant.getCell().getPosition().getY() - 1].getAnt() != null) {
                    numadj++;
                }
            }
        }
        return numadj;
    }

    /**
     * Get the adjacent cell in a specified cell.
     *
     * @param cell the cell to check for an adjacent cell
     * @param direction the direction of the adjacent cell
     * @return the adjacent cell, or null if there is not adjacent cell (outside
     * of the world dimensions)
     */
    public Cell getAdjacentCell(Cell cell, int direction) {
        switch (direction) {
            case 0:
                //right
                if (cell.getPosition().getX() < SIZE_X - 1) {
                    return cells[cell.getPosition().getX() + 1][cell.getPosition().getY()];
                }
                break;
            case 1:
                //downright
                if ((cell.getPosition().getX() < SIZE_X - 1) && (cell.getPosition().getY() < SIZE_Y - 1)) {
                    if (cell.getPosition().getY() % 2 == 0) {
                        //even
                        return cells[cell.getPosition().getX()][cell.getPosition().getY() + 1];
                    } else {
                        //odd
                        return cells[cell.getPosition().getX() + 1][cell.getPosition().getY() + 1];
                    }
                }
                break;
            case 2:
                //downleft
                if ((cell.getPosition().getX() > 0) && (cell.getPosition().getY() < SIZE_Y - 1)) {
                    if (cell.getPosition().getY() % 2 == 0) {
                        //even
                        return cells[cell.getPosition().getX() - 1][cell.getPosition().getY() + 1];
                    } else {
                        //odd
                        return cells[cell.getPosition().getX()][cell.getPosition().getY() + 1];
                    }
                }
                break;
            case 3:
                //left
                if (cell.getPosition().getX() > 0) {
                    return cells[cell.getPosition().getX() - 1][cell.getPosition().getY()];
                }
                break;
            case 4:
                //upleft
                if ((cell.getPosition().getX() > 0) && (cell.getPosition().getY() > 0)) {
                    if (cell.getPosition().getY() % 2 == 0) {
                        //even
                        return cells[cell.getPosition().getX() - 1][cell.getPosition().getY() - 1];
                    } else {
                        //odd
                        return cells[cell.getPosition().getX()][cell.getPosition().getY() - 1];
                    }
                }
                break;
            case 5:
                //upright
                if ((cell.getPosition().getX() < SIZE_X - 1) && (cell.getPosition().getY() > 0)) {
                    if (cell.getPosition().getY() % 2 == 0) {
                        //even
                        return cells[cell.getPosition().getX()][cell.getPosition().getY() - 1];
                    } else {
                        //odd
                        return cells[cell.getPosition().getX() + 1][cell.getPosition().getY() - 1];
                    }
                }
                break;
        }
        return null;
    }

    /**
     * Move an ant in the direction that ant is facing.
     *
     * @param ant the ant to move
     */
    public void moveAnt(Ant ant) {
        cells[ant.getCell().getPosition().getX()][ant.getCell().getPosition().getY()].setAnt(null);
        switch (ant.getDirection()) {
            case 0:
                //right
                if (ant.getCell().getPosition().getX() < SIZE_X - 1) {
                    ant.setCell(cells[ant.getCell().getPosition().getX() + 1][ant.getCell().getPosition().getY()]);
                }
                break;
            case 1:
                //downright
                if ((ant.getCell().getPosition().getX() < SIZE_X - 1) && (ant.getCell().getPosition().getY() < SIZE_Y - 1)) {
                    if (ant.getCell().getPosition().getY() % 2 == 0) {
                        //even
                        ant.setCell(cells[ant.getCell().getPosition().getX()][ant.getCell().getPosition().getY() + 1]);
                    } else {
                        //odd
                        ant.setCell(cells[ant.getCell().getPosition().getX() + 1][ant.getCell().getPosition().getY() + 1]);
                    }
                }
                break;
            case 2:
                //downleft
                if ((ant.getCell().getPosition().getX() > 0) && (ant.getCell().getPosition().getY() < SIZE_Y - 1)) {
                    if (ant.getCell().getPosition().getY() % 2 == 0) {
                        //even
                        ant.setCell(cells[ant.getCell().getPosition().getX() - 1][ant.getCell().getPosition().getY() + 1]);
                    } else {
                        //odd
                        ant.setCell(cells[ant.getCell().getPosition().getX()][ant.getCell().getPosition().getY() + 1]);
                    }
                }
                break;
            case 3:
                //left
                if (ant.getCell().getPosition().getX() > 0) {
                    ant.setCell(cells[ant.getCell().getPosition().getX() - 1][ant.getCell().getPosition().getY()]);
                }
                break;
            case 4:
                //upleft
                if ((ant.getCell().getPosition().getX() > 0) && (ant.getCell().getPosition().getY() > 0)) {
                    if (ant.getCell().getPosition().getY() % 2 == 0) {
                        //even
                        ant.setCell(cells[ant.getCell().getPosition().getX() - 1][ant.getCell().getPosition().getY() - 1]);
                    } else {
                        //odd
                        ant.setCell(cells[ant.getCell().getPosition().getX()][ant.getCell().getPosition().getY() - 1]);
                    }
                }
                break;
            case 5:
                //upright
                if ((ant.getCell().getPosition().getX() < SIZE_X - 1) && (ant.getCell().getPosition().getY() > 0)) {
                    if (ant.getCell().getPosition().getY() % 2 == 0) {
                        //even
                        ant.setCell(cells[ant.getCell().getPosition().getX()][ant.getCell().getPosition().getY() - 1]);
                    } else {
                        //odd
                        ant.setCell(cells[ant.getCell().getPosition().getX() + 1][ant.getCell().getPosition().getY() - 1]);
                    }
                }
                break;
            default:
                break;
        }
    }
}
