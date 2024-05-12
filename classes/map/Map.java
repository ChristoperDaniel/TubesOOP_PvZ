package classes.map;

public class Map {
    private static int rows = 6;
    private static int columns = 11;
    //total ada 66 tiles
    //row 1-6; column 1 = area rumah
    //row 1-6; column 11 = area zombie muncul
    //row 1,2,5,6; column 2-10 = area rumput
    //row 3,4; column 2-10 = area pool
    private char[][] tiles;
    private Zombie zombie;
    private Tanaman plant;
    private boolean isLilypadAvail;
    private boolean isZombieIn;

    @SuppressWarnings("static-access")
    public Map(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.tiles = new char[rows][columns];
    }

    public char getTiles(int rows, int columns) {
        return tiles[rows][columns];
    }

    public void setTiles(int rows, int columns, char value) {
        tiles[rows][columns] = value;
    }

    public boolean isZombieAvail() {
        return zombie != null;
    }

    public boolean isPlantAvail() {
        return plant != null;
    }

    public boolean isSlotAvail(int rows, int columns) {
        return tiles[rows][columns] == '\u0000';
    }

    public String getPlant() {
        return plant.getType();
    }

    public String getZombie() {
        return zombie.getType();
    }

    public void displayMap() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(tiles[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void removeDeadZombie() {
        if (zombie != null && zombie.getHealth() <= 0) {
            zombie = null;
        }
    }

    public void removeDeadPlant() {
        if (plant != null && plant.getHealth() <= 0) {
            plant = null;
        }
    }

    public void removePlant(int rows, int columns) {
        if (tiles[rows][columns] == 'P') {
            plant = null;
            tiles[rows][columns] = '\u0000';
        }
    }

    public boolean isLilypadAvail() {
        return isLilypadAvail;
    }

    public void setLilypadAvail(int rows, int columns) {
        if (tiles[rows][columns] == 'W') {
            isLilypadAvail = true;
        }
    }

    public void implementLilypadAvail(int rows, int columns) {
        if (isLilypadAvail && tiles[rows][columns] == 'W') {
            tiles[rows][columns] = 'L';
        }
    }

    public boolean isZombieIn() {
        return isZombieIn;
    }

    public void setZombieIn(boolean zombieIn) {
        isZombieIn = zombieIn;
    }

    public Zombie spawnZombies() {
        return new Zombie();
    }

    public boolean isZombieClear() {
        return zombie == null;
    }
}
