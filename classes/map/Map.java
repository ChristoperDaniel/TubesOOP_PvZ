package classes.map;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.lang.reflect.InvocationTargetException;
//import java.util.*;

//import classes.map.threadmap.GameStatusThread;
//import classes.map.threadmap.SpawnZombieThread;
import plant.*;
import zombie.*;
//import main.*;
import classes.objects.*;
//import classes.player.*;

public class Map {
    public static int total_rows = 6;
    public static int total_columns = 11;
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
                tiles[row][col] = new Tile("___", row, col);
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

    // Metode untuk memeriksa apakah zombie telah mencapai rumah pengguna
    public boolean isZombieReachedFirstColumn() {
        for (int i = 0; i < Map.total_rows; i++) {
            if (isZombieAvail(i,0)) {
                return true; // Ada zombie yang mencapai kolom pertama
            }
        }
        return false; // Tidak ada zombie yang mencapai kolom pertama
    }

    // Metode untuk memeriksa apakah tile adalah air
    private boolean isWaterTile(int row, int col) {
        return (row == 2 || row == 3) && (col > 0 && col < 11);
    }

    // Metode untuk memeriksa apakah tile adalah air
    private boolean isRumputTile(int row, int col) {
        return (row == 0 || row == 1 || row == 4 || row == 5) && (col > 0 && col < 11);
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
    public void placeTanaman(int row, int col, Tanaman tanaman, Sun sun) {
        Tile current_Tile = getTile(row, col);
        boolean benar = true;
        if (current_Tile != null) {
            if(col > 0 && col < 10){
                if (isWaterTile(row, col)) {
                    if(tanaman.getNamaTanaman().equals("Lilypad")){
                        if(current_Tile.getTanaman().isEmpty()){
                            current_Tile.addTanaman(tanaman);
                        } else {
                            System.out.println("Lilypad sudah terpasang.");
                            benar = false;
                        }
                    } else if (!tanaman.getNamaTanaman().equals("Lilypad")) {
                        if (current_Tile.getTanaman().isEmpty() && (tanaman.getNamaTanaman().equals("Seashroom") || tanaman.getNamaTanaman().equals("Tangle Kelp"))){
                            current_Tile.addTanaman(tanaman);
                        } else if ((current_Tile.getTanaman().isEmpty() || !isLilypadAvail(row, col)) && (!(tanaman.getAquatic()))){
                            System.out.println("Tidak ada LilyPad, letakkan LilyPad terlebih dahulu.");
                            benar = false;
                        } else if (isLilypadAvail(row, col) && (!(tanaman.getAquatic()))){
                            tanaman.setIsLilyPadAvail(true);
                            current_Tile.removeTanaman(current_Tile.getTanaman().get(0));
                            current_Tile.addTanaman(tanaman);
                        }
                    }
                }
                else if (!isWaterTile(row, col)){
                    if(tanaman.getAquatic()){
                        System.out.println("Tanaman ini hanya bisa terpasang di air");
                        benar = false;
                    } else if(!tanaman.getAquatic()){
                        if(current_Tile.getTanaman().isEmpty()){
                            current_Tile.addTanaman(tanaman);
                        } else {
                            System.out.println("Tile sudah ditempati tanaman lain.");
                            benar = false;
                        }
                    }
                }
        } else {
            System.out.println("Tile tidak bisa ditanami tanaman.");
            benar = false;
        }
            

            if (benar){
                //set row col
                tanaman.setRowPlant(row);
                tanaman.setColPlant(col);

                //set symbol
                switch (tanaman.getNamaTanaman()){
                    case "Sunflower":
                        current_Tile.setDisplayName("SFt");
                        tanaman.setHealthTanaman(new Sunflower().getHealthTanaman());
                        break;
                    case "Jalapeno":
                        current_Tile.setDisplayName("JPt");
                        tanaman.setHealthTanaman(new Jalapeno().getHealthTanaman());
                        break;
                    case "Lilypad":
                        current_Tile.setDisplayName("LPt");
                        tanaman.setHealthTanaman(new Lilypad().getHealthTanaman());
                        break;
                    case "Magnetshroom":
                        current_Tile.setDisplayName("MSt");
                        tanaman.setHealthTanaman(new Magnetshroom().getHealthTanaman());
                        break;
                    case "Peashooter":
                        current_Tile.setDisplayName("PSt");
                        tanaman.setHealthTanaman(new Peashooter().getHealthTanaman());
                        break;
                    case "Seashroom":
                        current_Tile.setDisplayName("SSt");
                        tanaman.setHealthTanaman(new Seashroom().getHealthTanaman());
                        break;
                    case "Snow Pea":
                        current_Tile.setDisplayName("SPt");
                        tanaman.setHealthTanaman(new SnowPea().getHealthTanaman());
                        break;
                    case "Squash":
                        current_Tile.setDisplayName("SQt");
                        tanaman.setHealthTanaman(new Squash().getHealthTanaman());
                        break;
                    case "TangleKelp":
                        current_Tile.setDisplayName("TKt");
                        tanaman.setHealthTanaman(new TangleKelp().getHealthTanaman());
                        break;
                    case "Wallnut":
                        current_Tile.setDisplayName("WNt");
                        tanaman.setHealthTanaman(new Wallnut().getHealthTanaman());
                        break;
                    default:
                        System.out.println("Jenis tanaman tidak dikenali.");
                        return;
                }
                PlantAction plantAction = new PlantAction(tanaman,this.getTile(row, col),this,sun);
                Thread plantThread = new Thread(plantAction);
                plantThread.start();
                System.out.println(tanaman.getHealthTanaman());
                tanaman.startCooldown();
                System.out.println(tanaman.getNamaTanaman() + " telah ditanam. Cooldown dimulai. Sun tersisa: " + sun.gettotalSun());
            }
        }
    }

    public int getTotalZombies() {
        int count = 0;
        for (int i = 0; i < total_rows; i++) {
            for (int j = 0; j < total_columns; j++) {
                count += tiles[i][j].getZombies().size();
            }
        }
        return count;
    }
    public volatile static List<Zombie> listofZombies = new ArrayList<>(List.of(
        new NormalZombie(null),
        new BucketheadZombie(null),
        new ConeheadZombie(null),
        new DolphinRiderZombie(null),
        new JackInTheBoxZombie(null),
        new PeashooterZombie(null),
        new PoleVaultingZombie(null),
        new RugbyZombie(null),
        new ScreendoorZombie(null),
        new DuckyTubeZombie(null)
        // jangan lupa bikin jadi zombie
    ));

    public volatile static List<Class<? extends Zombie>> listofZombieClasses = new ArrayList<>();

    static {
        // Isi listofZombieClasses dengan referensi class dari setiap instance zombie dalam listofZombies
        for (Zombie zombie : listofZombies) {
            Class<? extends Zombie> zombieClass = zombie.getClass();
            listofZombieClasses.add(zombieClass);
        }
    }

    public void placeZombie(List<Zombie> listofZombies) {
        Tile current_Tile;
        Random random = new Random();
        for(int i = 0; i < total_rows; i++){
            double randomValue = Math.random();
            int randomCol = Map.total_columns - 1; // Pilih kolom di sisi kanan map
            int randomRow = i;
            current_Tile = tiles[randomRow][randomCol];
            if (randomValue <= 0.3){
                int zombieTypeIndex = random.nextInt(listofZombies.size()); // Pilih tipe zombie secara acak
                Zombie zombieType = listofZombies.get(zombieTypeIndex);
                try{
                Class<? extends Zombie> zombieClass = listofZombieClasses.get(zombieTypeIndex); // Ambil tipe zombie dari list
                zombieType = zombieClass.getDeclaredConstructor(Tile.class).newInstance(current_Tile);
                } catch (NoSuchMethodException e) {
                    // Tangani jika tidak ada konstruktor yang sesuai
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    // Tangani jika tidak dapat membuat instance baru
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    // Tangani jika tidak diizinkan mengakses konstruktor
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    // Tangani jika ada kesalahan saat memanggil konstruktor
                    e.printStackTrace();
                }
                //System.out.println(randomRow);
                //System.out.println(randomCol);
                //System.out.println(zombieType.getNamaZombie());
                
                if(isWaterTile(randomRow, randomCol) == true){
                    if(zombieType.getNamaZombie().equals("DuckyTubeZombie")){
                        current_Tile.addZombie(zombieType);
                    } else if (zombieType.getNamaZombie().equals("DolphinRiderZombie")){    
                        current_Tile.addZombie(zombieType);
                    } else {
                        return;
                    }
                } else if(isRumputTile(randomRow, randomCol) == true){        
                    if(!zombieType.getNamaZombie().equals("DuckyTubeZombie") && !zombieType.getNamaZombie().equals("DolphinRiderZombie")){
                        current_Tile.addZombie(zombieType);
                    } else {
                        return;
                    }
                }
                
                zombieType.setRowZombie(randomRow);
                zombieType.setColZombie(randomCol);
    
    
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
                ZombieAction zombieAction = new ZombieAction(zombieType.getIsAbilityUsed(),zombieType,current_Tile,this);
                Thread zombieThread = new Thread(zombieAction);
                zombieThread.start();
            }
        }
        
    }
    
    // Menghapus tanaman dari tile
    public void removeTanaman(int row, int col, Tanaman tanaman) {
        Tile current_Tile = getTile(row, col);
        if (current_Tile != null && current_Tile.getTanaman() != null) {
            current_Tile.removeTanaman(tanaman);
            current_Tile.setDisplayName("___");
        }
    }

    // Menghapus zombie dari tile
    public void removeZombie(int row, int col, Zombie zombie) {
        Tile current_Tile = getTile(row, col);
        if (current_Tile != null) {
            current_Tile.removeZombie(zombie);
            current_Tile.setDisplayName("___");
        }
    }

    // Menampilkan peta
    public void displayMap() {
        final String RESET = "\u001B[0m";
        final String ORANGE = "\u001B[38;5;208m";
        final String RED = "\u001B[31m";
        final String BLUE = "\u001B[34m";
        final String GREEN = "\u001B[32m";
    
        for (int row = 0; row < total_rows; row++) {
            for (int col = 0; col < total_columns; col++) {
                Tile tile = tiles[row][col];
                String color = RESET;
    
                // Determine the color based on the row and column
                if (row >= 0 && row <= 5 && col == 0) {
                    color = ORANGE;
                } else if (row >= 0 && row <= 5 && col == 10) {
                    color = RED;
                } else if ((row == 2 || row == 3) && col >= 1 && col <= 9) {
                    color = BLUE;
                } else if ((row == 0 || row == 1 || row == 4 || row == 5) && col >= 1 && col <= 9) {
                    color = GREEN;
                }
    
                // Print the tile with the colored brackets
                System.out.print(color + "[" + RESET + tile.getDisplayName() + color + "]" + RESET + " ");
            }
            System.out.println();
        }
    }
    
    /*public static void main(String[] args) {
        Scanner scanner = ScannerJava.getScanner();
        Map maps = new Map();
        Sun sun = new Sun();
        Game game = new Game(maps,new Player(sun),sun, scanner);
        SpawnZombieThread zombieSpawner = new SpawnZombieThread(maps,game.getStatusGame());
        GameStatusThread gameStatus = new GameStatusThread(maps, game);
        Thread thread1 = new Thread(zombieSpawner);
        Thread thread2 = new Thread(gameStatus);
        thread1.start();
        thread2.start();
        

        /*System.out.println("Map:");
        Map maps = new Map();
        System.out.println("Menanam lilypad ");
        maps.placeTanaman(2, 5, new Lilypad());
        maps.displayMap();
        System.out.println("Menanam peashooter");
        maps.placeTanaman(2, 5, new Peashooter());
        maps.displayMap();
        System.out.println("Panggil zombie");
        maps.placeZombie(listofZombies);
        maps.displayMap();*/
    //}
}