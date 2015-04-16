/**
 * The world class generates a World object, which is made up
 * of a set of cells a set of players and a set of ants.
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
     * Constructs a world loaded from file.
     *
     * @param worldPath The path to the world file to load.
     * @param players The players playing in the world.
     */
    public World(Player[] players, String worldPath) throws IOException, Exception {
        this.players = players;
        antCount = new int[players.length];
        foodCount = new int[players.length];
        ants = new Ant[130 * players.length];
        loadWorld(worldPath);
    }

    /**
     * Randomly generates world.
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
     * Loads a world from file.
     *
     * @param worldPath The path of the world file to be loaded.
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

        //WORLD PARSING
        System.out.println("Checking world syntax...");

        //read in each cell as string then space
        FileReader fchecker = new FileReader(worldPath);
        BufferedReader textchecker = new BufferedReader(fchecker);

        try {
            int test1 = Integer.parseInt(textchecker.readLine());
            int test2 = Integer.parseInt(textchecker.readLine());
        } catch (Exception e) {
            throw new Exception("Size integers invalid.");
        }

        //build char array
        char[][] testmap = new char[SIZE_X][SIZE_Y];
        for (int y = 0; y < SIZE_Y; y++) {
            char[] temprow = textchecker.readLine().toCharArray();
            if (temprow.length > SIZE_X * 2) {
                throw new Exception("A row is too long: " + (y + 1));
            }
            if (temprow.length < SIZE_X * 2) {
                throw new Exception("A row is too short: " + (y + 1));
            }
            for (int x = 0; x < SIZE_X; x++) {
                testmap[x][y] = temprow[x * 2];
            }
        }

        //check no other symbols
        for (int y = 0; y < SIZE_Y; y++) {
            for (int x = 0; x < SIZE_X; x++) {
                if ((testmap[x][y] != '.') && (testmap[x][y] != '#') && (testmap[x][y] != '+') && (testmap[x][y] != '-') && (testmap[x][y] != '5')) {
                    throw new Exception("Unrecognised symbol at location: " + (x + 1) + "," + (y + 1));
                }
            }
        }

        //check the walls
        for (int x = 0; x < SIZE_X; x++) {
            if (testmap[x][0] != '#') {
                throw new Exception("Top wall error at column: " + (x + 1));
            }
            if (testmap[x][SIZE_Y - 1] != '#') {
                throw new Exception("Bottom wall error at column: " + (x + 1));
            }
        }
        for (int y = 0; y < SIZE_Y; y++) {
            if (testmap[0][y] != '#') {
                throw new Exception("Left wall error at row: " + (y + 1));
            }
            if (testmap[SIZE_X - 1][y] != '#') {
                throw new Exception("Right wall error at row: " + (y + 1));
            }
        }

        //finding the rocks
        int rockcount = 0;
        for (int y = 1; y < SIZE_Y - 1; y++) {
            for (int x = 1; x < SIZE_X - 1; x++) {
                if (testmap[x][y] == '#') {
                    if (y % 2 == 0) {
                        //even row
                        if (((testmap[x - 1][y] == '.') || (x == 1)) && ((testmap[x + 1][y] == '.') || (x == SIZE_X - 2)) && ((testmap[x][y - 1] == '.') || (y == 1)) && ((testmap[x][y + 1] == '.') || (y == SIZE_Y - 2)) && ((testmap[x - 1][y - 1] == '.') || ((y == 1) && (x == 1))) && (((testmap[x - 1][y + 1] == '.') || ((y == SIZE_Y - 2) && (x == 1))))) {
                            rockcount++;
                        } else {
                            throw new Exception("Rock found in incorrect place: " + x + "," + y);
                        }
                    } else {
                        //odd row
                        if (((testmap[x - 1][y] == '.') || (x == 1)) && ((testmap[x + 1][y] == '.') || (x == SIZE_X - 2)) && ((testmap[x][y - 1] == '.') || (y == 1)) && ((testmap[x][y + 1] == '.') || (y == SIZE_Y - 2)) && ((testmap[x + 1][y - 1] == '.') || ((y == 1) && (x == SIZE_X - 2))) && (((testmap[x + 1][y + 1] == '.') || ((y == SIZE_Y - 2) && (x == SIZE_X - 2))))) {
                            rockcount++;
                        } else {
                            throw new Exception("Rock found in incorrect place: " + x + "," + y);
                        }
                    }
                }
            }
        }

        if (rockcount < 14) {
            throw new Exception("Not enough rocks in world. 14 Required.");
        }

        if (rockcount > 14) {
            throw new Exception("Too many rocks in world. 14 Required.");
        }

        //foodblobs
        int foodblobcount = 0;

        //skipping in blob cells already considered
        boolean[][] skipfood = new boolean[SIZE_X][SIZE_Y];
        for (int y = 0; y < SIZE_Y; y++) {
            for (int x = 0; x < SIZE_X; x++) {
                skipfood[x][y] = false;
            }
        }

        for (int y = 0; y < SIZE_Y; y++) {
            for (int x = 0; x < SIZE_X; x++) {
                if (testmap[x][y] == '5') {
                    if (!skipfood[x][y]) {
                        if (y % 2 == 0) {
                            //even blobs
                            if (((y + 2) < SIZE_Y - 2)) {
                                //right slant
                                if (testmap[x][y + 2] == '.') {
                                    if (((x + 6) < SIZE_X - 2) && ((y + 5) < SIZE_Y - 2) && ((x - 1) > 0)) {
                                        for (int y1 = y; y1 < y + 2; y1++) {
                                            for (int x1 = x; x1 < x + 5; x1++) {
                                                if (testmap[x1][y1] != '5') {
                                                    throw new Exception("Incorrectly formatted foodblob at: " + (x1 + 1) + "," + (y1 + 1));
                                                }
                                                skipfood[x1][y1] = true;
                                            }
                                        }

                                        for (int y2 = y + 2; y2 < y + 4; y2++) {
                                            for (int x2 = x + 1; x2 < x + 6; x2++) {

                                                if (testmap[x2][y2] != '5') {
                                                    throw new Exception("Incorrectly formatted foodblob at: " + (x2 + 1) + "," + (y2 + 1));
                                                }
                                                skipfood[x2][y2] = true;
                                            }
                                        }

                                        for (int x3 = x + 2; x3 < x + 7; x3++) {

                                            if (testmap[x3][y + 4] != '5') {
                                                throw new Exception("Incorrectly formatted foodblob at: " + (x3 + 1) + "," + (y + 5));
                                            }
                                            skipfood[x3][y + 4] = true;
                                        }

                                        //adjacency
                                        if (((x + 7) < SIZE_X - 2) && ((y + 5) < SIZE_Y - 2) && ((x - 1) > 0) && ((y - 1) > 0)) {

                                            for (int x4 = x - 1; x4 < x + 6; x4++) {
                                                if (testmap[x4][y - 1] != '.' && testmap[x4][y - 1] != '#') {
                                                    throw new Exception("Incorrect foodblob adjacency at: " + (x4 + 1) + "," + y);
                                                }
                                            }

                                            for (int x5 = x + 1; x5 < x + 7; x5++) {
                                                if (testmap[x5][y + 5] != '.' && testmap[x5][y + 5] != '#') {
                                                    throw new Exception("Incorrect foodblob adjacency at: " + (x5 + 1) + "," + (y + 6));
                                                }
                                            }

                                            for (int y4 = y; y4 < y + 3; y4++) {
                                                if (testmap[x - 1][y4] != '.' && testmap[x - 1][y4] != '#') {
                                                    throw new Exception("Incorrect foodblob adjacency at: " + x + "," + (y4 + 1));
                                                }
                                            }

                                            for (int y5 = y + 2; y5 < y + 5; y5++) {
                                                if (testmap[x][y5] != '.' && testmap[x][y5] != '#') {
                                                    throw new Exception("Incorrect foodblob adjacency at: " + (x + 1) + "," + (y5 + 1));
                                                }
                                            }

                                            if (testmap[x + 1][y + 4] != '.' && testmap[x + 1][y + 4] != '#') {
                                                throw new Exception("Incorrect foodblob adjacency at: " + (x + 2) + "," + (y + 5));
                                            }

                                            for (int y6 = y; y6 < y + 2; y6++) {
                                                if (testmap[x + 5][y6] != '.' && testmap[x + 5][y6] != '#') {
                                                    throw new Exception("Incorrect foodblob adjacency at: " + (x + 6) + "," + (y6 + 1));
                                                }
                                            }

                                            for (int y7 = y + 1; y7 < y + 4; y7++) {
                                                if (testmap[x + 6][y7] != '.' && testmap[x + 6][y7] != '#') {
                                                    throw new Exception("Incorrect foodblob adjacency at: " + (x + 7) + "," + (y7 + 1));
                                                }
                                            }

                                            for (int y8 = y + 3; y8 < y + 5; y8++) {
                                                if (testmap[x + 7][y8] != '.' && testmap[x + 7][y8] != '#') {
                                                    throw new Exception("Incorrect foodblob adjacency at: " + (x + 8) + "," + (y8 + 1));
                                                }
                                            }

                                            foodblobcount++;

                                        } else {
                                            throw new Exception("Incorrectly formatted foodblob at: " + (x + 1) + "," + (y + 1));
                                        }

                                    } else {
                                        throw new Exception("Incorrectly formatted foodblob at: " + (x + 1) + "," + (y + 1));
                                    }

                                } //left slant
                                else if (testmap[x][y + 2] == '5') {
                                    if (((x + 5) < SIZE_X - 2) && ((y + 5) < SIZE_Y - 2) && ((x - 3) > 0)) {

                                        for (int y1 = y + 1; y1 < y + 3; y1++) {
                                            for (int x1 = x - 1; x1 < x + 4; x1++) {

                                                if (testmap[x1][y1] != '5') {
                                                    throw new Exception("Incorrectly formatted foodblob at: " + (x1 + 1) + "," + (y1 + 1));
                                                }
                                                skipfood[x1][y1] = true;
                                            }
                                        }

                                        for (int y2 = y + 3; y2 < y + 5; y2++) {
                                            for (int x2 = x - 2; x2 < x + 3; x2++) {

                                                if (testmap[x2][y2] != '5') {
                                                    throw new Exception("Incorrectly formatted foodblob at: " + (x2 + 1) + "," + (y2 + 1));
                                                }
                                                skipfood[x2][y2] = true;
                                            }
                                        }

                                        for (int x3 = x; x3 < x + 5; x3++) {

                                            if (testmap[x3][y] != '5') {
                                                throw new Exception("Incorrectly formatted foodblob at: " + (x3 + 1) + "," + (y + 1));
                                            }
                                            skipfood[x3][y] = true;
                                        }

                                        //adjacency
                                        if (((x + 7) < SIZE_X - 2) && ((y + 5) < SIZE_Y - 2) && ((x - 1) > 0) && ((y - 1) > 0)) {

                                            for (int x4 = x - 1; x4 < x + 6; x4++) {
                                                if (testmap[x4][y - 1] != '.' && testmap[x4][y - 1] != '#') {
                                                    throw new Exception("Incorrect foodblob adjacency at: " + (x4 + 1) + "," + y);
                                                }
                                            }

                                            for (int x5 = x - 3; x5 < x + 4; x5++) {
                                                if (testmap[x5][y + 5] != '.' && testmap[x5][y + 5] != '#') {
                                                    throw new Exception("Incorrect foodblob adjacency at: " + (x5 + 1) + "," + (y + 6));
                                                }
                                            }

                                            for (int y4 = y; y4 < y + 3; y4++) {
                                                if (testmap[x - 2][y4] != '.' && testmap[x - 2][y4] != '#') {
                                                    throw new Exception("Incorrect foodblob adjacency at: " + (x - 1) + "," + (y4 + 1));
                                                }
                                            }

                                            for (int y5 = y + 2; y5 < y + 5; y5++) {
                                                if (testmap[x - 3][y5] != '.' && testmap[x - 3][y5] != '#') {
                                                    throw new Exception("Incorrect foodblob adjacency at: " + (x - 2) + "," + (y5 + 1));
                                                }
                                            }

                                            if (testmap[x - 1][y] != '.' && testmap[x - 1][y] != '#') {
                                                throw new Exception("Incorrect foodblob adjacency at: " + (x) + "," + (y + 1));
                                            }

                                            for (int y6 = y; y6 < y + 2; y6++) {
                                                if (testmap[x + 5][y6] != '.' && testmap[x + 5][y6] != '#') {
                                                    throw new Exception("Incorrect foodblob adjacency at: " + (x + 6) + "," + (y6 + 1));
                                                }
                                            }

                                            for (int y7 = y + 1; y7 < y + 4; y7++) {
                                                if (testmap[x + 4][y7] != '.' && testmap[x + 4][y7] != '#') {
                                                    throw new Exception("Incorrect foodblob adjacency at: " + (x + 5) + "," + (y7 + 1));
                                                }
                                            }

                                            for (int y8 = y + 3; y8 < y + 5; y8++) {
                                                if (testmap[x + 3][y8] != '.' && testmap[x + 3][y8] != '#') {
                                                    throw new Exception("Incorrect foodblob adjacency at: " + (x + 4) + "," + (y8 + 1));
                                                }
                                            }

                                            foodblobcount++;

                                        } else {
                                            throw new Exception("Incorrectly formatted foodblob at: " + (x + 1) + "," + (y + 1));
                                        }

                                    } else {
                                        throw new Exception("Incorrectly formatted foodblob at: " + (x + 1) + "," + (y + 1));
                                    }

                                } else {
                                    throw new Exception("Incorrectly formatted foodblob at: " + (x + 1) + "," + (y + 1));
                                }
                            } else {
                                throw new Exception("Incorrectly formatted foodblob at: " + (x + 1) + "," + (y + 1));
                            }
                        } else {
                            //odd blobs
                            if (((y + 2) < SIZE_Y - 2)) {
                                //right slant
                                if (testmap[x][y + 2] == '.') {
                                    if (((x + 6) < SIZE_X - 2) && ((y + 5) < SIZE_Y - 2) && ((x - 1) > 0)) {

                                        for (int y1 = y + 1; y1 < y + 3; y1++) {
                                            for (int x1 = x + 1; x1 < x + 6; x1++) {

                                                if (testmap[x1][y1] != '5') {
                                                    throw new Exception("Incorrectly formatted foodblob at: " + (x1 + 1) + "," + (y1 + 1));
                                                }
                                                skipfood[x1][y1] = true;
                                            }
                                        }

                                        for (int y2 = y + 3; y2 < y + 5; y2++) {
                                            for (int x2 = x + 2; x2 < x + 7; x2++) {
                                                if (testmap[x2][y2] != '5') {
                                                    throw new Exception("Incorrectly formatted foodblob at: " + (x2 + 1) + "," + (y2 + 1));
                                                }
                                                skipfood[x2][y2] = true;
                                            }
                                        }

                                        for (int x3 = x; x3 < x + 5; x3++) {

                                            if (testmap[x3][y] != '5') {
                                                throw new Exception("Incorrectly formatted foodblob at: " + (x3 + 1) + "," + (y + 1));
                                            }
                                            skipfood[x3][y] = true;
                                        }

                                        //adjacency
                                        if (((x + 7) < SIZE_X - 2) && ((y + 5) < SIZE_Y - 2) && ((x - 1) > 0) && ((y - 1) > 0)) {

                                            for (int x4 = x - 1; x4 < x + 6; x4++) {
                                                if (testmap[x4][y - 1] != '.' && testmap[x4][y - 1] != '#') {
                                                    throw new Exception("Incorrect foodblob adjacency at: " + (x4 + 1) + "," + y);
                                                }
                                            }

                                            for (int x5 = x + 1; x5 < x + 7; x5++) {
                                                if (testmap[x5][y + 5] != '.' && testmap[x5][y + 5] != '#') {
                                                    throw new Exception("Incorrect foodblob adjacency at: " + (x5 + 1) + "," + (y + 6));
                                                }
                                            }

                                            for (int y4 = y; y4 < y + 2; y4++) {
                                                if (testmap[x - 1][y4] != '.' && testmap[x - 1][y4] != '#') {
                                                    throw new Exception("Incorrect foodblob adjacency at: " + x + "," + (y4 + 1));
                                                }
                                            }

                                            for (int y5 = y + 1; y5 < y + 4; y5++) {
                                                if (testmap[x][y5] != '.' && testmap[x][y5] != '#') {
                                                    throw new Exception("Incorrect foodblob adjacency at: " + (x + 1) + "," + (y5 + 1));
                                                }
                                            }

                                            if (testmap[x + 5][y] != '.' && testmap[x + 5][y] != '#') {
                                                throw new Exception("Incorrect foodblob adjacency at: " + (x + 6) + "," + (y + 1));
                                            }

                                            for (int y6 = y; y6 < y + 3; y6++) {
                                                if (testmap[x + 6][y6] != '.' && testmap[x + 6][y6] != '#') {
                                                    throw new Exception("Incorrect foodblob adjacency at: " + (x + 7) + "," + (y6 + 1));
                                                }
                                            }

                                            for (int y7 = y + 2; y7 < y + 5; y7++) {
                                                if (testmap[x + 7][y7] != '.' && testmap[x + 7][y7] != '#') {
                                                    throw new Exception("Incorrect foodblob adjacency at: " + (x + 8) + "," + (y7 + 1));
                                                }
                                            }

                                            for (int y8 = y + 3; y8 < y + 5; y8++) {
                                                if (testmap[x + 1][y8] != '.' && testmap[x + 1][y8] != '#') {
                                                    throw new Exception("Incorrect foodblob adjacency at: " + (x + 2) + "," + (y8 + 1));
                                                }
                                            }

                                            foodblobcount++;

                                        } else {
                                            throw new Exception("Incorrectly formatted foodblob at: " + (x + 1) + "," + (y + 1));
                                        }

                                    } else {
                                        throw new Exception("Incorrectly formatted foodblob at: " + (x + 1) + "," + (y + 1));
                                    }

                                } //left slant
                                else if (testmap[x][y + 2] == '5') {
                                    if (((x + 5) < SIZE_X - 2) && ((y + 5) < SIZE_Y - 2) && ((x - 3) > 0)) {

                                        for (int y1 = y; y1 < y + 2; y1++) {
                                            for (int x1 = x; x1 < x + 5; x1++) {

                                                if (testmap[x1][y1] != '5') {
                                                    throw new Exception("Incorrectly formatted foodblob at: " + (x1 + 1) + "," + (y1 + 1));
                                                }
                                                skipfood[x1][y1] = true;
                                            }
                                        }

                                        for (int y2 = y + 2; y2 < y + 4; y2++) {
                                            for (int x2 = x - 1; x2 < x + 4; x2++) {

                                                if (testmap[x2][y2] != '5') {
                                                    throw new Exception("Incorrectly formatted foodblob at: " + (x2 + 1) + "," + (y2 + 1));
                                                }
                                                skipfood[x2][y2] = true;
                                            }
                                        }

                                        for (int x3 = x - 2; x3 < x + 3; x3++) {

                                            if (testmap[x3][y + 4] != '5') {
                                                throw new Exception("Incorrectly formatted foodblob at: " + (x3 + 1) + "," + (y + 5));
                                            }
                                            skipfood[x3][y + 4] = true;
                                        }

                                        //adjacency
                                        if (((x + 7) < SIZE_X - 2) && ((y + 5) < SIZE_Y - 2) && ((x - 1) > 0) && ((y - 1) > 0)) {

                                            for (int x4 = x - 1; x4 < x + 6; x4++) {
                                                if (testmap[x4][y - 1] != '.' && testmap[x4][y - 1] != '#') {
                                                    throw new Exception("Incorrect foodblob adjacency at: " + (x4 + 1) + "," + y);
                                                }
                                            }

                                            for (int x5 = x - 3; x5 < x + 4; x5++) {
                                                if (testmap[x5][y + 5] != '.' && testmap[x5][y + 5] != '#') {
                                                    throw new Exception("Incorrect foodblob adjacency at: " + (x5 + 1) + "," + (y + 6));
                                                }
                                            }

                                            for (int y4 = y; y4 < y + 2; y4++) {
                                                if (testmap[x - 1][y4] != '.' && testmap[x - 1][y4] != '#') {
                                                    throw new Exception("Incorrect foodblob adjacency at: " + (x) + "," + (y4 + 1));
                                                }
                                            }

                                            for (int y5 = y + 1; y5 < y + 4; y5++) {
                                                if (testmap[x - 2][y5] != '.' && testmap[x - 2][y5] != '#') {
                                                    throw new Exception("Incorrect foodblob adjacency at: " + (x - 1) + "," + (y5 + 1));
                                                }
                                            }

                                            if (testmap[x + 3][y + 4] != '.' && testmap[x + 3][y + 4] != '#') {
                                                throw new Exception("Incorrect foodblob adjacency at: " + (x + 4) + "," + (y + 5));
                                            }

                                            for (int y6 = y + 3; y6 < y + 5; y6++) {
                                                if (testmap[x - 3][y6] != '.' && testmap[x - 3][y6] != '#') {
                                                    throw new Exception("Incorrect foodblob adjacency at: " + (x - 2) + "," + (y6 + 1));
                                                }
                                            }

                                            for (int y7 = y; y7 < y + 3; y7++) {
                                                if (testmap[x + 5][y7] != '.' && testmap[x + 5][y7] != '#') {
                                                    throw new Exception("Incorrect foodblob adjacency at: " + (x + 6) + "," + (y7 + 1));
                                                }
                                            }

                                            for (int y8 = y + 2; y8 < y + 5; y8++) {
                                                if (testmap[x + 4][y8] != '.' && testmap[x + 4][y8] != '#') {
                                                    throw new Exception("Incorrect foodblob adjacency at: " + (x + 5) + "," + (y8 + 1));
                                                }
                                            }

                                            foodblobcount++;

                                        } else {
                                            throw new Exception("Incorrectly formatted foodblob at: " + (x + 1) + "," + (y + 1));
                                        }

                                    } else {
                                        throw new Exception("Incorrectly formatted foodblob at: " + (x + 1) + "," + (y + 1));
                                    }

                                } else {
                                    throw new Exception("Incorrectly formatted foodblob at: " + (x + 1) + "," + (y + 1));
                                }
                            } else {
                                throw new Exception("Incorrectly formatted foodblob at: " + (x + 1) + "," + (y + 1));
                            }
                        }
                    }
                }
            }
        }

        if (foodblobcount < 11) {
            throw new Exception("Not enough foodblobs in world. 11 Required.");
        }

        if (foodblobcount > 11) {
            throw new Exception("Too many foodblobs in world. 11 Required.");
        }

        //anthills
        int hillcount = 0;

        //skipping in blob cells already considered
        boolean[][] skiphill = new boolean[SIZE_X][SIZE_Y];
        for (int y = 0; y < SIZE_Y; y++) {
            for (int x = 0; x < SIZE_X; x++) {
                skiphill[x][y] = false;
            }
        }

        for (int y = 0; y < SIZE_Y; y++) {
            for (int x = 0; x < SIZE_X; x++) {
                if (testmap[x][y] == '+' || testmap[x][y] == '-') {
                    //desired character, + or -
                    char hillchar = testmap[x][y];
                    if (!skiphill[x][y]) {
                        if (y % 2 == 0) {
                            //even hill
                            //domain
                            if (((x - 4) > 0) && ((y - 1) > 0) && ((x + 10) < SIZE_X - 2) && ((y + 13) < SIZE_Y - 2)) {
                                //checking the hill cells

                                for (int y1 = y; y1 < y + 13; y1++) {
                                    for (int x1 = x; x1 < x + 7; x1++) {
                                        if (testmap[x1][y1] != hillchar) {
                                            throw new Exception("Incorrectly formatted anthill at: " + (x1 + 1) + "," + (y1 + 1));
                                        }
                                        skiphill[x1][y1] = true;
                                    }
                                }

                                for (int y2 = y + 5; y2 < y + 8; y2++) {
                                    if (testmap[x - 3][y2] != hillchar) {
                                        throw new Exception("Incorrectly formatted anthill at: " + (x - 2) + "," + (y2 + 1));
                                    }
                                    skiphill[x - 3][y2] = true;
                                }

                                for (int y3 = y + 3; y3 < y + 10; y3++) {
                                    if (testmap[x - 2][y3] != hillchar) {
                                        throw new Exception("Incorrectly formatted anthill at: " + (x - 1) + "," + (y3 + 1));
                                    }
                                    skiphill[x - 2][y3] = true;
                                }

                                for (int y4 = y + 1; y4 < y + 12; y4++) {
                                    if (testmap[x - 1][y4] != hillchar) {
                                        throw new Exception("Incorrectly formatted anthill at: " + (x) + "," + (y4 + 1));
                                    }
                                    skiphill[x - 1][y4] = true;
                                }

                                for (int y5 = y + 2; y5 < y + 11; y5++) {
                                    if (testmap[x + 7][y5] != hillchar) {
                                        throw new Exception("Incorrectly formatted anthill at: " + (x + 8) + "," + (y5 + 1));
                                    }
                                    skiphill[x + 7][y5] = true;
                                }

                                for (int y6 = y + 4; y6 < y + 9; y6++) {
                                    if (testmap[x + 8][y6] != hillchar) {
                                        throw new Exception("Incorrectly formatted anthill at: " + (x + 9) + "," + (y6 + 1));
                                    }
                                    skiphill[x + 8][y6] = true;
                                }

                                if (testmap[x + 9][y + 6] != hillchar) {
                                    throw new Exception("Incorrectly formatted anthill at: " + (x + 10) + "," + (y + 7));
                                }
                                skiphill[x + 9][y + 6] = true;

                                //checking adjacency
                                for (int x2 = x - 1; x2 < x + 8; x2++) {
                                    if (testmap[x2][y - 1] != '.' && testmap[x2][y - 1] != '#') {
                                        throw new Exception("Incorrect anthill adjacency at: " + (x2 + 1) + "," + y);
                                    }
                                }

                                for (int x3 = x - 1; x3 < x + 8; x3++) {
                                    if (testmap[x3][y + 13] != '.' && testmap[x3][y + 13] != '#') {
                                        throw new Exception("Incorrect anthill adjacency at: " + (x3 + 1) + "," + (y + 14));
                                    }
                                }

                                for (int y7 = y + 4; y7 < y + 9; y7++) {
                                    if (testmap[x - 4][y7] != '.' && testmap[x - 4][y7] != '#') {
                                        throw new Exception("Incorrect anthill adjacency at: " + (x - 3) + "," + (y7 + 1));
                                    }
                                }

                                for (int y8 = y + 2; y8 < y + 5; y8++) {
                                    if (testmap[x - 3][y8] != '.' && testmap[x - 3][y8] != '#') {
                                        throw new Exception("Incorrect anthill adjacency at: " + (x - 2) + "," + (y8 + 1));
                                    }
                                }

                                for (int y9 = y + 8; y9 < y + 11; y9++) {
                                    if (testmap[x - 3][y9] != '.' && testmap[x - 3][y9] != '#') {
                                        throw new Exception("Incorrect anthill adjacency at: " + (x - 2) + "," + (y9 + 1));
                                    }
                                }

                                for (int y10 = y; y10 < y + 3; y10++) {
                                    if (testmap[x - 2][y10] != '.' && testmap[x - 2][y10] != '#') {
                                        throw new Exception("Incorrect anthill adjacency at: " + (x - 1) + "," + (y10 + 1));
                                    }
                                }

                                for (int y11 = y + 10; y11 < y + 13; y11++) {
                                    if (testmap[x - 2][y11] != '.' && testmap[x - 2][y11] != '#') {
                                        throw new Exception("Incorrect anthill adjacency at: " + (x - 1) + "," + (y11 + 1));
                                    }
                                }

                                if (testmap[x - 1][y] != '.' && testmap[x - 1][y] != '#') {
                                    throw new Exception("Incorrect anthill adjacency at: " + (x) + "," + (y + 1));
                                }

                                if (testmap[x - 1][y + 12] != '.' && testmap[x - 1][y + 12] != '#') {
                                    throw new Exception("Incorrect anthill adjacency at: " + (x) + "," + (y + 13));
                                }

                                for (int y12 = y; y12 < y + 2; y12++) {
                                    if (testmap[x + 7][y12] != '.' && testmap[x + 7][y12] != '#') {
                                        throw new Exception("Incorrect anthill adjacency at: " + (x + 8) + "," + (y12 + 1));
                                    }
                                }

                                for (int y13 = y + 11; y13 < y + 13; y13++) {
                                    if (testmap[x + 7][y13] != '.' && testmap[x + 7][y13] != '#') {
                                        throw new Exception("Incorrect anthill adjacency at: " + (x + 8) + "," + (y13 + 1));
                                    }
                                }

                                for (int y14 = y + 1; y14 < y + 4; y14++) {
                                    if (testmap[x + 8][y14] != '.' && testmap[x + 8][y14] != '#') {
                                        throw new Exception("Incorrect anthill adjacency at: " + (x + 9) + "," + (y14 + 1));
                                    }
                                }

                                for (int y15 = y + 9; y15 < y + 12; y15++) {
                                    if (testmap[x + 8][y15] != '.' && testmap[x + 8][y15] != '#') {
                                        throw new Exception("Incorrect anthill adjacency at: " + (x + 9) + "," + (y15 + 1));
                                    }
                                }

                                for (int y16 = y + 3; y16 < y + 6; y16++) {
                                    if (testmap[x + 9][y16] != '.' && testmap[x + 9][y16] != '#') {
                                        throw new Exception("Incorrect anthill adjacency at: " + (x + 10) + "," + (y16 + 1));
                                    }
                                }

                                for (int y17 = y + 7; y17 < y + 10; y17++) {
                                    if (testmap[x + 9][y17] != '.' && testmap[x + 9][y17] != '#') {
                                        throw new Exception("Incorrect anthill adjacency at: " + (x + 10) + "," + (y17 + 1));
                                    }
                                }

                                for (int y18 = y + 5; y18 < y + 8; y18++) {
                                    if (testmap[x + 10][y18] != '.' && testmap[x + 10][y18] != '#') {
                                        throw new Exception("Incorrect anthill adjacency at: " + (x + 11) + "," + (y18 + 1));
                                    }
                                }

                            } else {
                                throw new Exception("Incorrectly formed anthill at: " + (x + 1) + "," + (y + 1));
                            }

                            //incrementing hillcount
                            hillcount++;

                        } else {
                            //odd hill
                            //domain
                            if (((x - 4) > 0) && ((y - 1) > 0) && ((x + 10) < SIZE_X - 2) && ((y + 13) < SIZE_Y - 2)) {
                                //checking the hill cells

                                for (int y1 = y; y1 < y + 13; y1++) {
                                    for (int x1 = x; x1 < x + 7; x1++) {
                                        if (testmap[x1][y1] != hillchar) {
                                            throw new Exception("Incorrectly formatted anthill at: " + (x1 + 1) + "," + (y1 + 1));
                                        }
                                        skiphill[x1][y1] = true;
                                    }
                                }

                                for (int y2 = y + 5; y2 < y + 8; y2++) {
                                    if (testmap[x + 9][y2] != hillchar) {
                                        throw new Exception("Incorrectly formatted anthill at: " + (x + 10) + "," + (y2 + 1));
                                    }
                                    skiphill[x + 9][y2] = true;
                                }

                                for (int y3 = y + 3; y3 < y + 10; y3++) {
                                    if (testmap[x + 8][y3] != hillchar) {
                                        throw new Exception("Incorrectly formatted anthill at: " + (x + 9) + "," + (y3 + 1));
                                    }
                                    skiphill[x + 8][y3] = true;
                                }

                                for (int y4 = y + 1; y4 < y + 12; y4++) {
                                    if (testmap[x + 7][y4] != hillchar) {
                                        throw new Exception("Incorrectly formatted anthill at: " + (x + 8) + "," + (y4 + 1));
                                    }
                                    skiphill[x + 7][y4] = true;
                                }

                                for (int y5 = y + 2; y5 < y + 11; y5++) {
                                    if (testmap[x - 1][y5] != hillchar) {
                                        throw new Exception("Incorrectly formatted anthill at: " + (x) + "," + (y5 + 1));
                                    }
                                    skiphill[x - 1][y5] = true;
                                }

                                for (int y6 = y + 4; y6 < y + 9; y6++) {
                                    if (testmap[x - 2][y6] != hillchar) {
                                        throw new Exception("Incorrectly formatted anthill at: " + (x - 1) + "," + (y6 + 1));
                                    }
                                    skiphill[x - 2][y6] = true;
                                }

                                if (testmap[x - 3][y + 6] != hillchar) {
                                    throw new Exception("Incorrectly formatted anthill at: " + (x - 2) + "," + (y + 7));
                                }
                                skiphill[x - 3][y + 6] = true;

                                //checking adjacency
                                for (int x2 = x - 1; x2 < x + 8; x2++) {
                                    if (testmap[x2][y - 1] != '.' && testmap[x2][y - 1] != '#') {
                                        throw new Exception("Incorrect anthill adjacency at: " + (x2 + 1) + "," + y);
                                    }
                                }

                                for (int x3 = x - 1; x3 < x + 8; x3++) {
                                    if (testmap[x3][y + 13] != '.' && testmap[x3][y + 13] != '#') {
                                        throw new Exception("Incorrect anthill adjacency at: " + (x3 + 1) + "," + (y + 14));
                                    }
                                }

                                for (int y7 = y + 4; y7 < y + 9; y7++) {
                                    if (testmap[x + 10][y7] != '.' && testmap[x + 10][y7] != '#') {
                                        throw new Exception("Incorrect anthill adjacency at: " + (x + 11) + "," + (y7 + 1));
                                    }
                                }

                                for (int y8 = y + 2; y8 < y + 5; y8++) {
                                    if (testmap[x + 9][y8] != '.' && testmap[x + 9][y8] != '#') {
                                        throw new Exception("Incorrect anthill adjacency at: " + (x + 10) + "," + (y8 + 1));
                                    }
                                }

                                for (int y9 = y + 8; y9 < y + 11; y9++) {
                                    if (testmap[x + 9][y9] != '.' && testmap[x + 9][y9] != '#') {
                                        throw new Exception("Incorrect anthill adjacency at: " + (x + 10) + "," + (y9 + 1));
                                    }
                                }

                                for (int y10 = y; y10 < y + 3; y10++) {
                                    if (testmap[x + 8][y10] != '.' && testmap[x + 8][y10] != '#') {
                                        throw new Exception("Incorrect anthill adjacency at: " + (x + 9) + "," + (y10 + 1));
                                    }
                                }

                                for (int y11 = y + 10; y11 < y + 13; y11++) {
                                    if (testmap[x + 8][y11] != '.' && testmap[x + 8][y11] != '#') {
                                        throw new Exception("Incorrect anthill adjacency at: " + (x + 9) + "," + (y11 + 1));
                                    }
                                }

                                if (testmap[x + 7][y] != '.' && testmap[x + 7][y] != '#') {
                                    throw new Exception("Incorrect anthill adjacency at: " + (x + 8) + "," + (y + 1));
                                }

                                if (testmap[x + 7][y + 12] != '.' && testmap[x + 7][y + 12] != '#') {
                                    throw new Exception("Incorrect anthill adjacency at: " + (x + 8) + "," + (y + 13));
                                }

                                for (int y12 = y; y12 < y + 2; y12++) {
                                    if (testmap[x - 1][y12] != '.' && testmap[x - 1][y12] != '#') {
                                        throw new Exception("Incorrect anthill adjacency at: " + (x) + "," + (y12 + 1));
                                    }
                                }

                                for (int y13 = y + 11; y13 < y + 13; y13++) {
                                    if (testmap[x - 1][y13] != '.' && testmap[x - 1][y13] != '#') {
                                        throw new Exception("Incorrect anthill adjacency at: " + (x) + "," + (y13 + 1));
                                    }
                                }

                                for (int y14 = y + 1; y14 < y + 4; y14++) {
                                    if (testmap[x - 2][y14] != '.' && testmap[x - 2][y14] != '#') {
                                        throw new Exception("Incorrect anthill adjacency at: " + (x - 1) + "," + (y14 + 1));
                                    }
                                }

                                for (int y15 = y + 9; y15 < y + 12; y15++) {
                                    if (testmap[x - 2][y15] != '.' && testmap[x - 2][y15] != '#') {
                                        throw new Exception("Incorrect anthill adjacency at: " + (x - 1) + "," + (y15 + 1));
                                    }
                                }

                                for (int y16 = y + 3; y16 < y + 6; y16++) {
                                    if (testmap[x - 3][y16] != '.' && testmap[x - 3][y16] != '#') {
                                        throw new Exception("Incorrect anthill adjacency at: " + (x - 2) + "," + (y16 + 1));
                                    }
                                }

                                for (int y17 = y + 7; y17 < y + 10; y17++) {
                                    if (testmap[x - 3][y17] != '.' && testmap[x - 3][y17] != '#') {
                                        throw new Exception("Incorrect anthill adjacency at: " + (x - 2) + "," + (y17 + 1));
                                    }
                                }

                                for (int y18 = y + 5; y18 < y + 8; y18++) {
                                    if (testmap[x - 4][y18] != '.' && testmap[x - 4][y18] != '#') {
                                        throw new Exception("Incorrect anthill adjacency at: " + (x - 3) + "," + (y18 + 1));
                                    }
                                }

                            } else {
                                throw new Exception("Incorrectly formed anthill at: " + (x + 1) + "," + (y + 1));
                            }

                            //incrementing hillcount
                            hillcount++;
                        }
                    }
                }
            }
        }

        if (hillcount < 2) {
            throw new Exception("Not enough anthills in world. 2 Required.");
        }

        if (hillcount > 2) {
            throw new Exception("Too many anthills in world. 2 Required.");
        }

        //complete message
        System.out.println("All tests passed.");

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
