package classes.map;

import zombie.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import plant.*;

public class Map {
    public static int total_rows = 5;
    public static int total_columns = 10;
    /*
    row 0-5; col 0 = rumah
    row 0-5; col 10 = spawn
    row 0,1,4,5; col 1-9 = rumput
    row 2,3    ; col 1-9 = pool
    */

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

    // Metode untuk memeriksa apakah tile adalah air
    private boolean isRumputTile(int row, int col) {
        return (row == 0 || row == 1 || row == 4 || row == 5) && (col >= 1 && col <= 9);
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
    public void placeZombie(List<Zombie> listofZombies) {
        Tile current_Tile;
        double randomValue = Math.random();
        Random random = new Random();

        if (randomValue <= 0.3) {
            int zombieTypeIndex = random.nextInt(listofZombies.size()); // Pilih tipe zombie secara acak
            Zombie zombieType = listofZombies.get(zombieTypeIndex); // Ambil tipe zombie dari list
            int randomRow = random.nextInt(Map.total_rows); // Pilih baris secara acak
            int randomCol = Map.total_columns; // Pilih kolom di sisi kanan map

            if(isRumputTile(randomRow, randomCol)){
                if(zombieType.getNamaZombie() != "DuckyTubeZombie" || zombieType.getNamaZombie() != "DolphinRiderZombie"){
                    tiles[randomRow][randomCol].addZombie(zombieType);
                }
            } else if (isWaterTile(randomRow, randomCol)){
                if(zombieType.getNamaZombie() == "DuckyTubeZombie" || zombieType.getNamaZombie() == "DolphinRiderZombie"){
                    tiles[randomRow][randomCol].addZombie(zombieType);
                }
            }

            current_Tile = tiles[randomRow][randomCol];

            //set symbol
            switch (zombieType.getNamaZombie()){
                case "NormalZombie":
                    current_Tile.setDisplayName("NMz");
                    break;
                case "ConeheadZombie":
                    current_Tile.setDisplayName("CHz");
                    break;
                case "BucketheadZombie":
                    current_Tile.setDisplayName("BHz");
                    break;
                case "PoleVaultingZombie":
                    current_Tile.setDisplayName("PVz");
                    break;
                case "DuckyTubeZombie":
                    current_Tile.setDisplayName("DTz");
                    break;
                case "DolphinRiderZombie":
                    current_Tile.setDisplayName("DRz");
                    break;
                case "PeashooterZombie":
                    current_Tile.setDisplayName("PSz");
                    break;
                case "ScreendoorZombie":
                    current_Tile.setDisplayName("SDz");
                    break;
                case "JackInTheBoxZombie":
                    current_Tile.setDisplayName("JBz");
                    break;
                case "RugbyZombie":
                    current_Tile.setDisplayName("RGz");
                    break;
                default:
                    System.out.println("Jenis zombie tidak dikenali.");
                    return;
            }
            current_Tile.setDisplayName(zombieType.getNamaZombie());
        }
    }
 

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
            current_Tile.setDisplayName("_");
        }
    }

    public void Flag(List<Zombie> listofZombies){
        Tile current_Tile;
        double randomValue = Math.random();
        Random random = new Random();

        if (randomValue <= 0.5) {
            int zombieTypeIndex = random.nextInt(listofZombies.size()); // Pilih tipe zombie secara acak
            Zombie zombieType = listofZombies.get(zombieTypeIndex); // Ambil tipe zombie dari list
            int randomRow = random.nextInt(Map.total_rows); // Pilih baris secara acak
            int randomCol = Map.total_columns; // Pilih kolom di sisi kanan map

            if(isRumputTile(randomRow, randomCol)){
                if(zombieType.getNamaZombie() != "DuckyTubeZombie" || zombieType.getNamaZombie() != "DolphinRiderZombie"){
                    tiles[randomRow][randomCol].addZombie(zombieType);
                }
            } else if (isWaterTile(randomRow, randomCol)){
                if(zombieType.getNamaZombie() == "DuckyTubeZombie" || zombieType.getNamaZombie() == "DolphinRiderZombie"){
                    tiles[randomRow][randomCol].addZombie(zombieType);
                }
            }

            current_Tile = tiles[randomRow][randomCol];

            //set symbol
            switch (zombieType.getNamaZombie()){
                case "NormalZombie":
                    current_Tile.setDisplayName("NMz");
                    break;
                case "ConeheadZombie":
                    current_Tile.setDisplayName("CHz");
                    break;
                case "BucketheadZombie":
                    current_Tile.setDisplayName("BHz");
                    break;
                case "PoleVaultingZombie":
                    current_Tile.setDisplayName("PVz");
                    break;
                case "DuckyTubeZombie":
                    current_Tile.setDisplayName("DTz");
                    break;
                case "DolphinRiderZombie":
                    current_Tile.setDisplayName("DRz");
                    break;
                case "PeashooterZombie":
                    current_Tile.setDisplayName("PSz");
                    break;
                case "ScreendoorZombie":
                    current_Tile.setDisplayName("SDz");
                    break;
                case "JackInTheBoxZombie":
                    current_Tile.setDisplayName("JBz");
                    break;
                case "RugbyZombie":
                    current_Tile.setDisplayName("RGz");
                    break;
                default:
                    System.out.println("Jenis zombie tidak dikenali.");
                    return;
            }
            current_Tile.setDisplayName(zombieType.getNamaZombie());
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

}
