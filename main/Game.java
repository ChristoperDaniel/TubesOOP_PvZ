package main;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import classes.map.*;
import classes.map.threadmap.GameStatusThread;
import classes.map.threadmap.PerangThread;
import classes.map.threadmap.SpawnZombieThread;
import classes.map.threadmap.UpdateSunThread;
import classes.objects.*;
import classes.player.*;
import plant.*;
import zombie.*;

public class Game {
    private boolean statusGame;
    private Player player;
    private volatile Map map;
    private volatile Deck plantDeck;
    private Inventory inventory;
    private volatile int time;
    private volatile Sun sun;
    private volatile boolean isDayTime;
    private volatile boolean gameOver;
    private volatile Set<Tile> plantTilesWithThreads;
    private volatile Set<Tile> zombieTilesWithThreads;
    private volatile ScheduledExecutorService executor;
    private volatile int currentTime;
    private List<Thread> plantThreads = new ArrayList<>();
    private List<Thread> zombieThreads = new ArrayList<>();
    private volatile List<Zombie> listofAllZombies = new ArrayList<>(List.of(
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
    private Random random = new Random(); // Menambahkan Random instance
    private int maxZombies;

    public Game(Map map, Player player, Sun sun) {
        statusGame = false;
        map = new Map();
        plantDeck = new Deck();
        inventory = new Inventory();
        time = 0;
        isDayTime = true;
        sun = new Sun();
        player = new Player(sun);
        plantTilesWithThreads = new HashSet<>();
        zombieTilesWithThreads = new HashSet<>();
        maxZombies = 10;
    }

    public boolean getStatusGame() {
        return statusGame;
    }

    public void setStatusGame(boolean statusGame) { //pokonya disini parameter tuh zombienya dah clear semua ato belom gitu
        this.statusGame = statusGame; //Win or lose
    } 

    public void displayMenuUtama() {
        System.out.println("Menu Utama:");
        System.out.println("1. Start Game ");
        System.out.println("2. Help");
        System.out.println("3. Plants List");
        System.out.println("4. Zombies List");
        System.out.println("5. Exit Game");
    }

    public void displayMenuStart() {
        System.out.println("Menu Opsi:");
        System.out.println("1. Display Inventory ");
        System.out.println("2. Display Deck");
        System.out.println("3. Add Plants");
        System.out.println("4. Remove Plants");
        System.out.println("5. Swap Deck Plants");
        System.out.println("6. Swap Inventory Plants");
        System.out.println("7. Enter Game");
        
    }

    public void displayMenuEnter() {
        System.out.println("Menu Opsi:");
        System.out.println("1. Menanam Tanaman");
        System.out.println("2. Menggali Tanaman");
        System.out.println("3. Display Deck");
        System.out.println("4. Display Map");
        System.out.println("5. Quit Game");
        System.out.println("6. Help");
        
    }

    public void MulaiGame() {
        Scanner scanner = new Scanner(System.in);
        boolean isChoosing = true;

        System.out.println("Selamat datang di Game Michael vs. Lalapan");
        while (isChoosing ) {
            displayMenuUtama();
            System.out.print("Pilih opsi: ");
            int choice = 0;
            try {
                choice = Integer.parseInt(scanner.nextLine());
                System.out.println("");
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka.");
                continue;
            }

            switch (choice) {
                case 1:
                    isChoosing = false;
                    break;
                case 2:
                    help();
                    break;
                case 3:
                    inventory.displayInventory();
                    break;
                case 4:
                    System.out.println("-----------------");
                    System.out.println("Isi deck tanaman:");
                    for (int i = 0; i < listofAllZombies.size(); i++) {
                        System.out.println((i + 1) + ". " + listofAllZombies.get(i).getNamaZombie());
                    }
                    System.out.println("-----------------");
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
                    break;

            }
        }
        startGame();
    }

    public void startGame() {

        // Menampilkan daftar tanaman dari inventory
        Scanner scanner = new Scanner(System.in);
        boolean isChoosing = true;
        int index;
        int pos1; 
        int pos2;
        System.out.println("Sebelum memulai permainan, silahkan mengatur deck yang akan dipakai untuk melindungi rumah dari serangan zombie\n");

        while (isChoosing) {
            displayMenuStart();
            System.out.print("Pilih opsi: ");
            int choice = 0;
            try {
                choice = Integer.parseInt(scanner.nextLine());
                System.out.println("");
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka.");
                continue;
            }

            switch (choice) {
                case 1:
                    inventory.displayInventory();
                    break;
                case 2:
                    plantDeck.displayDeckPlants();
                    break;
                case 3:
                    if (plantDeck.getSize() < plantDeck.getMaxDeckSize()){
                        System.out.print("Masukkan nomor tanaman dari inventory yang ingin Anda tambahkan ke deck: ");
                        index = Integer.parseInt(scanner.nextLine())-1;
                        plantDeck.addPlants(inventory.removePlantsInventory(index));
                    }
                    else{
                        System.out.println("Deck sudah penuh");
                    }
                    break;
                case 4:
                    System.out.print("Masukkan nomor tanaman dari deck yang ingin Anda hapus: ");   
                    index = Integer.parseInt(scanner.nextLine())-1; 
                    inventory.addPlantsInventory(plantDeck.removePlants(index));
                    break;
                case 5:
                    System.out.print("Masukkan nomor tanaman yang ingin Anda tukar posisinya di deck: ");
                    pos1 = Integer.parseInt(scanner.nextLine()); 
                    System.out.print("Masukkan nomor tanaman di deck yang ingin Anda tukar posisinya dengan: ");
                    pos2 = Integer.parseInt(scanner.nextLine()); 
                    plantDeck.swapPlants(pos1-1,pos2-1);
                    break;
                case 6:
                    System.out.print("Masukkan nomor tanaman yang ingin Anda tukar posisinya di inventory: ");
                    pos1 = Integer.parseInt(scanner.nextLine()); 
                    System.out.print("Masukkan nomor tanaman di inventory yang ingin Anda tukar posisinya dengan: ");
                    pos2 = Integer.parseInt(scanner.nextLine()); 
                    plantDeck.swapPlants(pos1-1,pos2-1);
                    break;
                case 7:
                    if (plantDeck.getSize() < plantDeck.getMaxDeckSize()){
                        System.out.println("Isi deck belum full. Silahkan melengkapi deck terlebih dahulu sebelum memasuki game");
                    }
                    else{
                        isChoosing = false;
                    }
                    break;
                default :
                    System.out.println("Pilihan tidak valid.");
                    break;
            }
        }
        enterGame(map);
    }

    public void enterGame(Map map) {

        executor = Executors.newScheduledThreadPool(1); // 5 threads, tambahkan satu thread untuk update currentTime dan input pengguna
        statusGame = true;
    }

    // private void updateCurrentTime() {
    //     if (!gameOver || !executor.isShutdown()){
    //         currentTime++;
    //         if (currentTime > 200) {
    //             stopGame();
    //         }
    //     }
    // }

    public void handleUserInput(Map map, Player player, Sun sun) {
        Scanner scanner = new Scanner(System.in);
        boolean salah = false;
        int row = -1; // Inisialisasi row dengan nilai default yang tidak valid
        int column = -1; // Inisialisasi column dengan nilai default yang tidak valid
        while (!gameOver) { // Loop sampai permainan selesai
            map.displayMap();
            displayMenuEnter(); 

            // Loop untuk memastikan input pilihan yang valid
            System.out.print("Pilih opsi: ");
            int choice = 0;
            try {
                choice = Integer.parseInt(scanner.nextLine());
                System.out.println("");
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka.");
                continue;
            }
            switch (choice) {
                case 1:
                    // Menanam Tanaman
                    // Implementasi
                    try {
                        System.out.print("Masukkan baris untuk menanam tanaman (0-" + (Map.total_rows - 1) + "): ");
                        row = scanner.nextInt();
                        if (row < 0 || row >= Map.total_rows) {
                            System.out.println("Baris di luar batas. Silakan masukkan nilai yang valid.");
                            salah = true;
                        }
                        System.out.print("Masukkan kolom untuk menanam tanaman (1-" + (Map.total_columns - 1) + "): ");
                        column = scanner.nextInt();
                        if (column < 0 || column >= Map.total_columns) {
                            System.out.println("Kolom di luar batas. Silakan masukkan nilai yang valid.");
                            salah = true;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Input harus berupa angka.");
                        salah = true;
                    }
                    if (salah) {
                        continue;
                    }
                    player.menanam(plantDeck, row, column, map);
                    scanner.nextLine();
                    break;
                case 2:
                    // Menggali Tanaman
                    // Implementasi
                    try {
                        System.out.print("Masukkan baris untuk menggali tanaman (0-" + (Map.total_rows - 1) + "): ");
                        row = scanner.nextInt();
                        if (row < 0 || row >= Map.total_rows) {
                            System.out.println("Baris di luar batas. Silakan masukkan nilai yang valid.");
                            salah = true;
                        }
                        System.out.print("Masukkan kolom untuk menggali tanaman (0-" + (Map.total_columns - 1) + "): ");
                        column = scanner.nextInt();
                        if (column < 0 || column >= Map.total_columns) {
                            System.out.println("Kolom di luar batas. Silakan masukkan nilai yang valid.");
                            salah = true;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Input harus berupa angka.");
                        salah = true;
                    }
                    if (salah) {
                        continue;
                    }
                    player.menggali(map,row,column);
                    scanner.nextLine();
                    break;
                case 3:
                    // Display Deck
                    plantDeck.displayDeckPlants();
                    break;
                case 4:
                    // Display Map
                    map.displayMap();
                    break;
                case 5:
                    // Quit Game
                    gameOver = true; // Menghentikan permainan
                    break;
                case 6:
                    // Help
                    help();
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
    }

    /*public void gameBaru() {
        Game newGame = new Game();
        newGame.MulaiGame();
    }

    private void stopGame() {
        if (executor != null && !executor.isShutdown()) {
            executor.shutdownNow();
        }
        gameOver = true; // Ensure gameOver is set to true
        System.out.println("Game has been stopped.");
    }*/

    public void perang(Map map) {
        while (!gameOver) {
            for (int row = 0; row < Map.total_rows; row++) {
                for (int col = 0; col < Map.total_columns; col++) {
                    Tile tile = map.getTile(row, col);

                    // Jalankan aksi untuk setiap tanaman di tile
                    if (!plantTilesWithThreads.contains(tile)) {
                        for (Tanaman plant : tile.getTanaman()) {
                            PlantAction plantAction = new PlantAction(plant,tile,map,sun);
                            Thread plantThread = new Thread(plantAction);
                            plantThread.start();
                            plantThreads.add(plantThread);
                        }
                        plantTilesWithThreads.add(tile);
                    }

                    // Jalankan aksi untuk setiap zombie di tile
                    if (!zombieTilesWithThreads.contains(tile)) {
                        for (Zombie zombie : tile.getZombies()) {
                            ZombieAction zombieAction = new ZombieAction(false,zombie, tile, map);
                            Thread zombieThread = new Thread(zombieAction);
                            zombieThread.start();
                            zombieThreads.add(zombieThread);
                        }
                        zombieTilesWithThreads.add(tile);
                    }
                }
            }
        }
    }
    /*Runnable changeTimeOfDay = new Runnable(){
        @Override
        public void run(){
            if (!gameOver || !executor.isShutdown()){
                if (time > 100){
                    isDayTime = !isDayTime;
                }
            }
        }
    };
    Runnable checkWinOrLose = new Runnable(){
        @Override
        public void run(){
            if (!gameOver || !executor.isShutdown()) {
                // Cek kondisi menang atau kalah
                if (map.getTotalZombies() == 0 && currentTime >= 161) {
                    System.out.println("Anda menang! Semua zombie telah dikalahkan.");
                    stopGame();
                } else if (map.isZombieReachedFirstColumn()) {
                    System.out.println("Anda kalah! Zombie menang");
                    stopGame();
                }
            }
            else{
                System.out.println("Anda kalah! Zombie menang");
            }
        }
    };

    private void checkWinOrLose() {
        if (!gameOver || !executor.isShutdown()) {
            // Cek kondisi menang atau kalah
            if (map.getTotalZombies() == 0 && currentTime >= 161) {
                System.out.println("Anda menang! Semua zombie telah dikalahkan.");
                stopGame();
            } else if (map.isZombieReachedFirstColumn()) {
                System.out.println("Anda kalah! Zombie menang");
                stopGame();
            }
        }
        else{
            System.out.println("Anda kalah! Zombie menang");
        }
    }*/

    public void quitGame() {
        statusGame = false;
        System.out.println("Game dihentikan.");
    } 

    public void help() {
        System.out.println("Tentang Cara Bermain:");
        System.out.println("1. Pilih 'Menanam Tanaman' untuk menanam tanaman di peta.");
        System.out.println("2. Pilih 'Menggali Tanaman' untuk menggali tanaman dari peta.");
        System.out.println("3. Pilih 'Display Deck' untuk melihat tanaman yang tersedia di deck.");
        System.out.println("4. Pilih 'Display Map' untuk melihat peta permainan.");
        System.out.println("5. Pilih 'Quit Game' untuk keluar dari permainan.");
        System.out.println("6. Pilih 'Help' untuk melihat menu bantuan ini lagi.");
    }

    public void start(Map map, Player player, Sun sun, Game game) {
        // Buat thread untuk menjalankan metode perang
        //PerangThread perangThread = new PerangThread(map,game);
        //Thread thread = new Thread(perangThread);
        //1
        //thread.start();

        // Jalankan handleUserInput di thread utama
        handleUserInput(map, player, sun);
    }

    public static void main(String[] args) {
        Map map = new Map();
        Sun sun = new Sun();
        Player player = new Player(sun);
        Game game = new Game(map,player,sun);
        game.MulaiGame();
        SpawnZombieThread spawnZombieThread = new SpawnZombieThread(map,game.getStatusGame());
        UpdateSunThread updateSunThread = new UpdateSunThread(sun,game.getStatusGame());
        GameStatusThread gameStatusThread = new GameStatusThread(map, game);
        //PerangThread perangThread = new PerangThread(map, game);


        ExecutorService executor = Executors.newFixedThreadPool(3);
        
        executor.execute(spawnZombieThread);
        executor.execute(updateSunThread);
        executor.execute(gameStatusThread);

        // Cara untuk menghentikan thread secara aman
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            spawnZombieThread.stop();
            updateSunThread.stop();
            gameStatusThread.stop();
            executor.shutdown();
        }));
        while (game.getStatusGame()){
            game.start(map,player,sun,game);
        }
    }

}


