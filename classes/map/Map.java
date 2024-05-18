package classes.map;

import zombie.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import plant.*;

public class Map {
    public static int total_rows = 5;
    public static int total_columns = 10;

    private Tile[][] tiles;


    // Constructor
    public Map() {
        tiles = new Tile[total_rows][total_columns];
        for (int row = 0; row < total_rows; row++) {
            for (int col = 0; col < total_columns; col++) {
                tiles[row][col] = new Tile("_", row, col);
            }
        }
    }

    // Mendapatkan tile berdasarkan posisi
    public Tile getTile(int row, int col) {
        if (row >= 0 && row < total_rows && col >= 0 && col < total_columns) {
            return tiles[row][col];
        }
        return null;
    }

    
    public List<Tile> getRow(int row) {
        List<Tile> rowTiles = new ArrayList<>();
        if (row >= 0 && row < total_rows) {
            for (int col = 0; col < total_columns; col++) {
                rowTiles.add(tiles[row][col]);
            }
        } else {
            System.out.println("Baris tidak valid.");
        }
        return rowTiles;
    }

    // Method untuk mengecek apakah di suatu tile terdapat zombie
    public boolean isZombieAvail(int row, int col) {
        Tile currentTile = getTile(row, col);
        if (currentTile != null) {
            return !currentTile.getZombies().isEmpty();
        } else {
            System.out.println("Tile tidak tersedia.");
            return false;
        }
    }

    // Method untuk mengecek apakah di suatu tile terdapat tanaman
    public boolean isTanamanAvail(int row, int col) {
        Tile currentTile = getTile(row, col);
        if (currentTile != null) {
            return !currentTile.getTanaman().isEmpty();
        } else {
            System.out.println("Tile tidak tersedia.");
            return false;
        }
    }

    // Metode untuk memeriksa apakah tile adalah air
    private boolean isWaterTile(int row, int col) {
        return (row == 2 || row == 3) && (col >= 1 && col <= 9);
    }

    private boolean isLilypadAvail(int row, int col){
        Tile current_Tile = getTile(row, col);
        if(isWaterTile(row, col)){
            if(current_Tile.getTanaman().get(0).getNamaTanaman() == "Lilypad"){
                return true;
            } 
        }
        return false;
    }

    // Menempatkan tanaman pada tile tertentu
    public void placeTanaman(int row, int col, Tanaman tanaman) {
        Tile current_Tile = getTile(row, col);
        boolean benar = true;
        if (current_Tile != null) {
            if (isWaterTile(row, col)) {
                if(tanaman.getNamaTanaman() == "Lilypad"){
                    if(current_Tile.getTanaman() == null){
                        current_Tile.addTanaman(tanaman);
                    } else {
                        System.out.println("Lilypad sudah terpasang.");
                        benar = false;
                    }
                } else if (tanaman.getNamaTanaman() != "Lilypad"){
                    if (current_Tile.getTanaman() == null && (tanaman.getNamaTanaman() == "Seashroom" || tanaman.getNamaTanaman() == "Tangle Kelp")){
                        current_Tile.addTanaman(tanaman);
                    }
                    else if(current_Tile.getTanaman() == null || !isLilypadAvail(row, col)){
                        System.out.println("Tidak ada LilyPad, letakkan LilyPad terlebih dahulu.");
                        benar = false;
                    } else if (isLilypadAvail(row, col)){
                        current_Tile.addTanaman(tanaman);
                    }
                }
            } else if (!isWaterTile(row, col)){
                if(tanaman.getNamaTanaman() == "Lilypad" && current_Tile.getTanaman() == null){
                    System.out.println("Lilypad hanya bisa terpasang di air");
                    benar = false;
                } else if(tanaman.getNamaTanaman() != "Lilypad"){
                    if(current_Tile.getTanaman() == null){
                        current_Tile.addTanaman(tanaman);
                    } else {
                        System.out.println("Tile sudah ditempati tanaman lain.");
                        benar = false;
                    }
                }
            }
            if (benar){
                //set row col
                tanaman.setRow(row);
                tanaman.setCol(col);

                //set symbol
                switch (tanaman.getNamaTanaman()){
                    case "Sunflower":
                        current_Tile.setDisplayName("SF");
                        break;
                    case "Jalapeno":
                        current_Tile.setDisplayName("JP");
                        break;
                    case "Lilypad":
                        current_Tile.setDisplayName("LP");
                        break;
                    case "Magnetshroom":
                        current_Tile.setDisplayName("MS");
                        break;
                    case "Peashooter":
                        current_Tile.setDisplayName("PS");
                        break;
                    case "Seashroom":
                        current_Tile.setDisplayName("SS");
                        break;
                    case "SnowPea":
                        current_Tile.setDisplayName("SP");
                        break;
                    case "Squash":
                        current_Tile.setDisplayName("SQ");
                        break;
                    case "TangleKelp":
                        current_Tile.setDisplayName("TK");
                        break;
                    case "Wallnut":
                        current_Tile.setDisplayName("WN");
                        break;
                    default:
                        System.out.println("Jenis tanaman tidak dikenali.");
                        return;
                }
                current_Tile.setDisplayName(tanaman.getNamaTanaman());
            }

        } else {
            System.out.println("Tile tidak tersedia.");
        }
    }
    // Menempatkan zombie pada tile 
    public void placeZombie(int row, int col, List<Zombie> listofZombies) {
        double randomValue = Math.random();
        Random random = new Random();
        if (randomValue <= 0.3) {
            int zombieTypeIndex = random.nextInt(listofZombies.size()); // Pilih tipe zombie secara acak
            Zombie zombieType = listofZombies.get(zombieTypeIndex); // Ambil tipe zombie dari list
            int randomRow = random.nextInt(Map.total_rows); // Pilih baris secara acak
            int randomCol = Map.total_columns - 1; // Pilih kolom di sisi kanan map
            tiles[randomRow][randomCol].addZombie(zombieType); // Spawn dengan baris dan kolom yang dipilih secara acak
        }
    }
    /*
        Random rand = new Random();
        for (int row = 0; row < rows; row++) {
            if (rand.nextDouble() < 0.3) {
                map[row][columns - 1].addZombie();
            }
        }
     */

    // Menghapus tanaman dari tile
    public void removeTanaman(int row, int col, Tanaman tanaman) {
        Tile current_Tile = getTile(row, col);
        if (current_Tile != null && current_Tile.getTanaman() != null) {
            current_Tile.removeTanaman(tanaman);
            current_Tile.setDisplayName("_");
        }
    }

    // Menghapus zombie dari tile
    public void removeZombie(int row, int col, Zombie zombie) {
        Tile current_Tile = getTile(row, col);
        if (current_Tile != null) {
            current_Tile.removeZombie(zombie);
        }
    }

    // Menampilkan peta
    public void displayMap() {
        for (int row = 0; row < total_rows; row++) {
            for (int col = 0; col < total_columns; col++) {
                Tile tile = tiles[row][col];
                System.out.print("[" + (tile.getDisplayName()) + "]" + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Map gameMap = new Map();
        gameMap.placeTanaman(2, 2, new Sunflower());
        gameMap.displayMap();

    }
}

/*
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import plant.*;
import zombie.*;

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

        public void addPlantTile(String plantSymbol) {
            if (isTileAvail) {
                this.symbol = plantSymbol;
                this.isTileAvail = false;
            }
        }

        public void removePlant() {
            if (!isTileAvail && !isAreaSpawn) {
                this.symbol = "_";
                this.isTileAvail = true;
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

    private ConcurrentHashMap<Integer, List<Tanaman>> tanamanLanes;
    private ConcurrentHashMap<Integer, List<Zombie>> zombieLanes;

    public Map() {
        map = new Tile[rows][columns];
        initializeMap();
    }

    private void initializeMap() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                boolean isAreaSpawn = (col == 10);
                boolean isAreaRumput = ((row == 0 || row == 1 || row == 4 || row == 5) && (col >= 2 && col <= 10));
                boolean isAreaRumah = (col == 0);
                boolean isTileAvail = true;

                map[row][col] = new Tile(isAreaSpawn, isAreaRumput, isAreaRumah, isTileAvail);
            }
        }
    }

    public Tile getTiles(int row, int column){
        return map[row][column];
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
            map[row][col].addPlantTile(plantSymbol);
        }
    }

    public void removePlant(int row, int col) {
        if (row >= 0 && row < rows && col >= 0 && col < columns) {
            map[row][col].removePlant();
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
        gameMap.removePlant(2, 5); // Removing Peashooter from (2, 5)
        gameMap.removePlant(4, 3); // Removing SunFlower from (4, 3)
        System.out.println("After removing plants:");
        gameMap.displayMap();
    }
}

*/