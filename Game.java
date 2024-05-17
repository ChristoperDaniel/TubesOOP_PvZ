import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
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
    private ScheduledExecutorService executor;
    private volatile int currentTime;
    private volatile List<Zombie> listofAllZombies = new ArrayList<>(List.of(
        new NormalZombie(),
        new BucketheadZombie(),
        new ConeheadZombie(),
        new DolphinRiderZombie(),
        new JackInTheBoxZombie(),
        new PeashooterZombie(),
        new PoleVaultingZombie(),
        new RugbyZombie(),
        new ScreendoorZombie()
        // jangan lupa bikin jadi zombie
    ));

    public Game() {
        statusGame = "Paused";
        player = new Player();
        map = new Map();
        plantDeck = new Deck();
        inventory = new Inventory();
        time = 0;
        isDayTime = true;
        sun = new Sun();
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

            switch (choice) {
                case 1:
                    isChoosing = false;
                    startGame();
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
                    //pokonya nanti ini bakal diisi list zombie
                    break;
                case 5:
                    System.exit(0);
                    break;  
                default:
                    System.out.println("Pilihan tidak valid.");
                    break;
            }
        }
        scanner.close();
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

            switch (choice) {
                case 1:
                    inventory.displayInventory();
                    break;
                case 2:
                    plantDeck.displayDeckPlants();
                    break;
                case 3:
                    System.out.print("Masukkan nomor tanaman dari inventory yang ingin Anda tambahkan ke deck: ");
                    index = Integer.parseInt(scanner.nextLine())-1;
                    plantDeck.addPlants(inventory.removePlantsInventory(index));
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
                    isChoosing = false;
                    enterGame();
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
                    break;
            }
        }
        scanner.close();

    }

    public void enterGame() {

        executor = Executors.newScheduledThreadPool(5); // 5 threads, tambahkan satu thread untuk update currentTime dan input pengguna

        executor.scheduleAtFixedRate(this::updateSun, 0, 1, TimeUnit.SECONDS);
        executor.scheduleAtFixedRate(this::gameLoop, 0, 1, TimeUnit.SECONDS);
        executor.scheduleAtFixedRate(this::changeTimeOfDay, 0, 1, TimeUnit.SECONDS);
        executor.scheduleAtFixedRate(this::updateCurrentTime, 0, 1, TimeUnit.SECONDS); // Memperbarui currentTime
        executor.submit(this::handleUserInput); // Memulai thread untuk menangani input pengguna
    }

    private void updateSun() {
            if (!gameOver && isDayTime && currentTime % 3 == 0 && currentTime <= 100) {
                sun.addSun();;
                System.out.println("Sun has fallen from the sky! You've gained 25 sun. Current Time: " + currentTime);
            }
    }

    private void updateCurrentTime() {
        currentTime++;
        if (currentTime > 200) {
            stopGame();
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
        while (!gameOver) { // Loop sampai permainan selesai
            displayMenuEnter();
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    // Menanam Tanaman
                    // Implementasi
                    player.menanam(plantDeck);
                    break;
                case 2:
                    // Menggali Tanaman
                    // Implementasi
                    player.menggali();
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
                    stopGame(); // Menghentikan permainan
                    inventory = new Inventory();
                    plantDeck = new Deck();
                    MulaiGame();
                    break;
                case 6:
                    // Help
                    help();
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
        scanner.close();
    }

    private void stopGame() {
        gameOver = true;
    }

    private void perang() {
        /*if (!gameOver) {
            for (int lane = 0; lane < Map.HEIGHT; lane++) {
                List<Tanaman> tanamanLane = map.getTanamanLane(lane);
                List<Zombie> zombieLane = map.getZombieLane(lane);

                synchronized (tanamanLane) {
                    for (Tanaman tanaman : tanamanLane) {
                        synchronized (zombieLane) {
                            if (!zombieLane.isEmpty()) {
                                int rand = new Random().nextInt(zombieLane.size());
                                Zombie zombie = zombieLane.get(rand);
                                tanaman.serang(zombie);
                                if (!zombie.masihHidup()) {
                                    System.out.println(zombie.getNama() + " has been defeated!");
                                }
                            }
                        }
                    }
                }

                synchronized (zombieLane) {
                    for (Zombie zombie : zombieLane) {
                        synchronized (tanamanLane) {
                            if (!tanamanLane.isEmpty()) {
                                int rand = new Random().nextInt(tanamanLane.size());
                                Tanaman tanaman = tanamanLane.get(rand);
                                zombie.serang(tanaman);
                                if (!tanaman.masihHidup()) {
                                    System.out.println(tanaman.getNama() + " has been defeated!");
                                }
                            }
                        }
                    }
                }
            }
        }*/
    }

    public void changeTimeOfDay() {
        if (time >= 100){
            isDayTime = !isDayTime;
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


