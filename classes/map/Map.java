package classes.map;
import java.util.Random;

public class Map {
    private class Tile {
        private boolean isAreaSpawn;
        private boolean isAreaRumput;
        private boolean isAreaRumah;
        private boolean isTileAvail;
        private String symbol;


        public Tile(boolean isAreaSpawn, boolean isAreaRumput, boolean isAreaRumah, boolean isTileAvail) {
            this.isAreaSpawn = isAreaSpawn;
            this.isAreaRumput = isAreaRumput;
            this.isAreaRumah = isAreaRumah;
            this.isTileAvail = isTileAvail;
            this.symbol = "_";
        }

        public void addZombie() {
            if (isAreaSpawn) {
                if (isAreaRumput) {
                    this.symbol = "NZ"; // Non-aquatic zombie
                } else {
                    this.symbol = "AZ"; // Aquatic zombie
                }
                this.isTileAvail = false;
            }
        }

        public void addPlant(String plantSymbol) {
            if (isTileAvail) {
                this.symbol = plantSymbol;
                this.isTileAvail = false;
            }
        }

        @Override
        public String toString() {
            return symbol;
        }
    }
    
    public static int rows = 6;
    public static int columns = 11;
    private Tile[][] map;

    public Map() {
        map = new Tile[rows][columns];
        initializeMap();
    }

    private void initializeMap() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                boolean isAreaSpawn = (col == 10);
                boolean isAreaRumput = ((row == 0 || row == 1 || row == 4 || row == 5) && (col >= 1 && col <= 9));
                boolean isAreaRumah = (col == 0);
                boolean isTileAvail = true;

                map[row][col] = new Tile(isAreaSpawn, isAreaRumput, isAreaRumah, isTileAvail);
            }
        }
    }

    public void spawnZombie() {
        Random rand = new Random();
        for (int row = 0; row < rows; row++) {
            if (rand.nextDouble() < 0.3) {
                map[row][columns - 1].addZombie();
            }
        }
    }

    public void addPlant(int row, int col, String plantSymbol) {
        if (row >= 0 && row < rows && col >= 0 && col < columns) {
            map[row][col].addPlant(plantSymbol);
        }
    }

    public void displayMap() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                System.out.print(map[row][col] + " ");
            }
            System.out.println();
        }
    }

    

    public static void main(String[] args) {
        Map gameMap = new Map();
        gameMap.spawnZombie();
        gameMap.addPlant(2, 5, "P"); // Adding Peashooter at (2, 5)
        gameMap.addPlant(4, 3, "S"); // Adding SunFlower at (4, 3)
        gameMap.displayMap();
    }
}
