package main;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import classes.map.*;
import classes.map.threadmap.GameStatusThread;
import classes.map.threadmap.SpawnZombieThread;
import classes.map.threadmap.UpdateSunThread;
import classes.objects.*;
import classes.player.*;
import zombie.*;

public class Game {
    private boolean statusGame;
    private volatile Deck plantDeck;
    private Inventory inventory;
    private volatile Scanner scanner;
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

    public Game(Map map, Player player, Sun sun, Scanner scanner) {
        statusGame = false;
        map = new Map();
        plantDeck = new Deck();
        inventory = new Inventory();
        sun = new Sun();
        player = new Player(sun);
        this.scanner = scanner; 
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
        statusGame = true;
    }

    public void handleUserInput(Map map, Player player, Sun sun) {
        int row = -1; // Inisialisasi row dengan nilai default yang tidak valid
        int column = -1; // Inisialisasi column dengan nilai default yang tidak valid
        while (statusGame) { // Loop sampai permainan selesai
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
                        if (row < 0 || row > (Map.total_rows-1)) {
                            System.out.println("Baris di luar batas. Silakan masukkan nilai yang valid.");
                            scanner.nextLine();
                            break;
                        }
                        System.out.print("Masukkan kolom untuk menanam tanaman (1-" + (Map.total_columns - 2) + "): ");
                        column = scanner.nextInt();
                        if (column < 1 || column > (Map.total_columns-2)) {
                            System.out.println("Kolom di luar batas. Silakan masukkan nilai yang valid.");
                            scanner.nextLine();
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Input harus berupa angka.");
                        scanner.nextLine();
                        break;
                    }
                    player.menanam(plantDeck, row, column, map);
                    //scanner.nextLine();
                    break;
                case 2:
                    // Menggali Tanaman
                    // Implementasi
                    try {
                        System.out.print("Masukkan baris untuk menggali tanaman (0-" + (Map.total_rows - 1) + "): ");
                        row = scanner.nextInt();
                        if (row < 0 || row >= Map.total_rows) {
                            System.out.println("Baris di luar batas. Silakan masukkan nilai yang valid.");
                            scanner.nextLine();
                            break;
                        }
                        System.out.print("Masukkan kolom untuk menggali tanaman (0-" + (Map.total_columns - 1) + "): ");
                        column = scanner.nextInt();
                        if (column < 1 || column >= Map.total_columns) {
                            System.out.println("Kolom di luar batas. Silakan masukkan nilai yang valid.");
                            scanner.nextLine();
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Input harus berupa angka.");
                        scanner.nextLine();
                        break;
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
                    System.out.println("");
                    break;
                case 5:
                    // Quit Game
                    statusGame = false; // Menghentikan permainan
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

    public void quitGame() {
        statusGame = false;
        System.out.println("Game dihentikan.");
    } 

    public void help() {
        System.out.println("Sebelum Bermain:");
        System.out.println("1. Pilih 'Display Inventory' untuk melihat tanaman yang tersedia.");
        System.out.println("2. Pilih 'Display Deck' untuk melihat tanaman yang ada dalam deck.");
        System.out.println("3. Pilih 'Add Plants' untuk menambahkan tanaman ke dalam deck.");
        System.out.println("4. Pilih 'Remove Plants' untuk menghapus tanaman dari dalam deck.");
        System.out.println("5. Pilih 'Swap Deck Plants' untuk Menukar posisi tanaman dalam deck.");
        System.out.println("6. Pilih 'Swap Inventory Plants' untuk menukar posisi tanaman dalam inventory.");
        System.out.println("7. Pilih 'Swap Inventory Plants' untuk menukar posisi tanaman dalam inventory.");
        System.out.println("8. Pilih 'Enter Game' untuk masuk ke dalam permainan.\n");
        System.out.println("Cara Bermain:");
        System.out.println("1. Pilih 'Menanam Tanaman' untuk menanam tanaman di peta.");
        System.out.println("2. Pilih 'Menggali Tanaman' untuk menggali tanaman dari peta.");
        System.out.println("3. Pilih 'Display Deck' untuk melihat tanaman yang tersedia di deck.");
        System.out.println("4. Pilih 'Display Map' untuk melihat peta permainan.");
        System.out.println("5. Pilih 'Quit Game' untuk keluar dari permainan.");
        System.out.println("6. Pilih 'Help' untuk melihat menu bantuan ini lagi.\n");
    }

    public static void main(String[] args) {

        Scanner scanner = ScannerJava.getScanner();
        while (true){
            Map map = new Map();
            Sun sun = new Sun();
            Player player = new Player(sun);
            Game game = new Game(map,player,sun, scanner);
            game.MulaiGame();
            SpawnZombieThread spawnZombieThread = new SpawnZombieThread(map,game.getStatusGame());
            UpdateSunThread updateSunThread = new UpdateSunThread(sun,game.getStatusGame());
            GameStatusThread gameStatusThread = new GameStatusThread(map, game);
            Thread thread1 = new Thread(spawnZombieThread);
            Thread thread2 = new Thread(updateSunThread);
            Thread thread3 = new Thread(gameStatusThread);
        
            thread1.setDaemon(true);
            thread2.setDaemon(true);

            ExecutorService executor = Executors.newFixedThreadPool(3);
            
            thread1.start();
            thread2.start();
            thread3.start();

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                spawnZombieThread.stop();
                updateSunThread.stop();
                gameStatusThread.stop();
                executor.shutdown();
            }));
            while (game.getStatusGame()){
                game.handleUserInput(map, player, sun);
            }
            if (!game.getStatusGame()){
            System.out.print("Do you want to return to the main game? (yes/no): ");
            String input = scanner.nextLine();
            if (!input.equalsIgnoreCase("yes")) {
                break; // Keluar dari loop utama jika tidak ingin kembali ke game utama
            }
        }
        }
        scanner.close();
        System.exit(0);
    }
}


