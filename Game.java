import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import classes.map.*;
import classes.objects.*;
import classes.player.*;
import plant.*;
import zombie.*;

public class Game {
    private String statusGame;
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
    private ScheduledExecutorService executor;
    private volatile int currentTime;
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
    private int nextSunInterval = getRandomSunInterval(); // Interval acak pertama
    private int maxZombies = 10;

    public Game() {
        statusGame = "Paused";
        player = new Player();
        map = new Map();
        plantDeck = new Deck();
        inventory = new Inventory();
        time = 0;
        isDayTime = true;
        sun = new Sun();
        plantTilesWithThreads = new HashSet<>();
        zombieTilesWithThreads = new HashSet<>();
    }

    public String getStatusGame() {
        return statusGame;
    }

    public void setStatusGame(String statusGame) { //pokonya disini parameter tuh zombienya dah clear semua ato belom gitu
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
            System.out.println("Pilih opsi: ");
            int choice = 0;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka.");
                continue;
            }

            if (choice == 1){
                    isChoosing = false;
            }
            else if (choice == 2){
                    help();
            }
            else if (choice == 3){
                    inventory.displayInventory();
            }
            else if (choice == 4){
                    System.out.println("-----------------");
                    System.out.println("Isi deck tanaman:");
                    for (int i = 0; i < listofAllZombies.size(); i++) {
                        System.out.println((i + 1) + ". " + listofAllZombies.get(i).getNamaZombie());
                    }
                    System.out.println("-----------------");
            }
            else if (choice == 5){
                    System.exit(0);
            }
            else{
                    System.out.println("Pilihan tidak valid.");
            }
        }
        scanner.close();
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

        while (isChoosing && (plantDeck.getSize() != plantDeck.getMaxDeckSize())) {
            displayMenuStart();
            System.out.println("Pilih opsi: ");
            int choice = 0;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka.");
                continue;
            }

            if (choice == 1){
                    inventory.displayInventory();
            }
            else if (choice == 2){
                    plantDeck.displayDeckPlants();
            }
            else if (choice == 3){
                    System.out.print("Masukkan nomor tanaman dari inventory yang ingin Anda tambahkan ke deck: ");
                    index = Integer.parseInt(scanner.nextLine())-1;
                    plantDeck.addPlants(inventory.removePlantsInventory(index));
            }
            else if (choice == 4){
                    System.out.print("Masukkan nomor tanaman dari deck yang ingin Anda hapus: ");   
                    index = Integer.parseInt(scanner.nextLine())-1; 
                    inventory.addPlantsInventory(plantDeck.removePlants(index));
            }
            else if (choice == 5){
                    System.out.print("Masukkan nomor tanaman yang ingin Anda tukar posisinya di deck: ");
                    pos1 = Integer.parseInt(scanner.nextLine()); 
                    System.out.print("Masukkan nomor tanaman di deck yang ingin Anda tukar posisinya dengan: ");
                    pos2 = Integer.parseInt(scanner.nextLine()); 
                    plantDeck.swapPlants(pos1-1,pos2-1);
            }
            else if (choice == 6){
                    System.out.print("Masukkan nomor tanaman yang ingin Anda tukar posisinya di inventory: ");
                    pos1 = Integer.parseInt(scanner.nextLine()); 
                    System.out.print("Masukkan nomor tanaman di inventory yang ingin Anda tukar posisinya dengan: ");
                    pos2 = Integer.parseInt(scanner.nextLine()); 
                    plantDeck.swapPlants(pos1-1,pos2-1);
            }
            else if (choice == 7){
                    isChoosing = false;
                    if (plantDeck.getSize() != plantDeck.getMaxDeckSize()){
                        System.out.println("Isi deck belum full. Silahkan melengkapi deck terlebih dahulu sebelum memasuki game");
                    }
            }
            else{
                System.out.println("Pilihan tidak valid.");
            }
        }
        scanner.close();
        enterGame();
    }

    public void enterGame() {
        executor = Executors.newScheduledThreadPool(7); // 5 threads, tambahkan satu thread untuk update currentTime dan input pengguna

        executor.scheduleAtFixedRate(this::updateSun, 0, 1, TimeUnit.SECONDS);
        executor.scheduleAtFixedRate(this::gameLoop, 0, 1, TimeUnit.SECONDS);
        executor.scheduleAtFixedRate(this::changeTimeOfDay, 0, 1, TimeUnit.SECONDS);
        executor.scheduleAtFixedRate(this::spawnZombies, 0, 1, TimeUnit.SECONDS); // Spawn zombie setiap 10 detik
        executor.scheduleAtFixedRate(this::updateCurrentTime, 0, 1, TimeUnit.SECONDS); // Memperbarui currentTime
        executor.scheduleAtFixedRate(this::checkWinOrLose, 0, 1, TimeUnit.SECONDS); 
        executor.submit(this::handleUserInput); // Memulai thread untuk menangani input pengguna

        try {
            while (!executor.awaitTermination(1, TimeUnit.SECONDS)) {
                // Looping until executor is properly terminated
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    
        gameBaru();
    }

    private int getRandomSunInterval() {
        return random.nextInt(6) + 5; // Menghasilkan interval acak antara 5-10 detik
    }

    private void updateSun() {
        if ((!gameOver || !executor.isShutdown()) && isDayTime && currentTime % nextSunInterval == 0 && currentTime <= 100) {
            sun.addSun(); // Menambahkan 25 Sun
            System.out.println("Sun has fallen from the sky! You've gained 25 sun. Current Time: " + currentTime);
            nextSunInterval = getRandomSunInterval(); // Mengatur ulang interval acak berikutnya
        }
    }

    private void updateCurrentTime() {
        if (!gameOver || !executor.isShutdown()){
            currentTime++;
            if (currentTime > 200) {
                stopGame();
            }
        }
    }
    private void spawnZombies() {
        if (!gameOver || !executor.isShutdown()){
            if (map.getTotalZombies() < maxZombies) {
                map.placeZombie(listofAllZombies);
            }
        }
    }

    private void gameLoop() {
        if (!gameOver) {
            perang();
            /*map.removeDeadEntities();
            if (currentTime >= 160 && currentTime <= 200) {
            if (map.isZombieAtColumn(0)) {
                System.out.println("Game Over! Zombies have breached your defenses!");
                stopGame();
            }
            if (map.allZombiesDefeated()) {
                System.out.println("Congratulations! You've defeated all zombies!");
                stopGame();
            } else {
                System.out.println("Game Over! Zombies have breached your defenses!");
                stopGame();
            }
            }*/
        }
    }

    private void handleUserInput() {
        Scanner scanner = new Scanner(System.in);
        boolean salah = false;
        int row = -1; // Inisialisasi row dengan nilai default yang tidak valid
        int column = -1; // Inisialisasi column dengan nilai default yang tidak valid
        while (!gameOver || !executor.isShutdown()) { // Loop sampai permainan selesai
            displayMenuEnter();
            int choice = scanner.nextInt();
            if (choice == 1){
                    // Menanam Tanaman
                    // Implementasi
                    try {
                        System.out.print("Masukkan baris untuk menanam tanaman (0-" + (Map.total_rows - 1) + "): ");
                        row = Integer.parseInt(scanner.nextLine());
                        if (row < 0 || row >= Map.total_rows) {
                            System.out.println("Baris di luar batas. Silakan masukkan nilai yang valid.");
                            salah = true;
                        }
                        System.out.print("Masukkan kolom untuk menanam tanaman (0-" + (Map.total_columns - 1) + "): ");
                        column = Integer.parseInt(scanner.nextLine());
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
            }
            else if (choice == 2){
                    // Menggali Tanaman
                    // Implementasi
                    try {
                        System.out.print("Masukkan baris untuk menggali tanaman (0-" + (Map.total_rows - 1) + "): ");
                        row = Integer.parseInt(scanner.nextLine());
                        if (row < 0 || row >= Map.total_rows) {
                            System.out.println("Baris di luar batas. Silakan masukkan nilai yang valid.");
                            salah = true;
                        }
                        System.out.print("Masukkan kolom untuk menggali tanaman (0-" + (Map.total_columns - 1) + "): ");
                        column = Integer.parseInt(scanner.nextLine());
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
            }
            else if (choice == 3){
                    // Display Deck
                    plantDeck.displayDeckPlants();
            }
            else if (choice == 4){
                    // Display Map
                    map.displayMap();
            }
            else if (choice == 5){
                    // Quit Game
                    stopGame(); // Menghentikan permainan
                    break;
            }
            else if (choice == 6){
                    // Help
                    help();
            }
            else{
                    System.out.println("Invalid choice!");
            }
        }
        scanner.close();
    }

    private void gameBaru() {
        Game newGame = new Game();
        newGame.MulaiGame();
    }

    private void stopGame() {
        if (executor != null && !executor.isShutdown()) {
            executor.shutdownNow();
        }
        gameOver = true; // Ensure gameOver is set to true
        System.out.println("Game has been stopped.");
    }

    private void perang() {
        while (!gameOver) {
            for (int row = 0; row < Map.total_rows; row++) {
                for (int col = 0; col < Map.total_columns; col++) {
                    Tile tile = map.getTile(row, col);

                    // Jalankan aksi untuk setiap tanaman di tile
                    if (!plantTilesWithThreads.contains(tile)) {
                        for (Tanaman plant : tile.getTanaman()) {
                            PlantAction plantAction = new PlantAction(plant, tile);
                            Thread plantThread = new Thread(plantAction);
                            plantThread.start();
                        }
                        plantTilesWithThreads.add(tile);
                    }

                    // Jalankan aksi untuk setiap zombie di tile
                    if (!zombieTilesWithThreads.contains(tile)) {
                        for (Zombie zombie : tile.getZombies()) {
                            ZombieAction zombieAction = new ZombieAction(zombie, tile);
                            Thread zombieThread = new Thread(zombieAction);
                            zombieThread.start();
                        }
                        zombieTilesWithThreads.add(tile);
                    }
                }
            }
        }
    }

    public void changeTimeOfDay() {
        if (!gameOver || !executor.isShutdown()){
            if (time > 100){
                isDayTime = !isDayTime;
            }
        }
    }

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
    }

    public void quitGame() {
        statusGame = "Paused";
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

    public void implementFlag() {
        if (time == 100){
            map.Flag();
        }
    }

    public static void main(String[] args) {
        System.out.println("Selamat datang di ");
        Game game = new Game();
        game.displayMenuUtama();
    }

}


