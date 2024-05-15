package classes.player;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.List;
import plant.*;
import classes.objects.Deck;

public class Player {
    private String namaLengkap;

    // Constructor
    public Player(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    // mendapatkan nama lengkap
    public String get_namaLengkap() {
        return namaLengkap;
    }

    // mengatur nama lengkap
    public void set_namaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    // menanam tanaman
    public void menanam(Deck deck) {
        // Implementasi logika menanam tanaman
        //soon
            Scanner scanner = new Scanner(System.in);
            while (true) { // loop ini bisa diganti sesuai kondisi permainan Anda
                System.out.println("Tanaman yang tersedia:");
                List<Tanaman> availableTanamans = deck.getAvailableTanamans();
                for (Tanaman tanaman : availableTanamans) {
                    System.out.println(tanaman.getNamaTanaman());
                }
    
                System.out.println("Pilih tanaman untuk ditanam (atau ketik 'exit' untuk keluar):");
                String pilihan = scanner.nextLine();
                if (pilihan.equalsIgnoreCase("exit")) {
                    break;
                }
    
                Tanaman tanaman = deck.getTanamanByName(pilihan);
                if (tanaman != null && !tanaman.isOnCooldown()) {
                    tanaman.startCooldown(scheduler);
                    System.out.println(tanaman.getNamaTanaman() + " telah ditanam. Cooldown dimulai.");
                } else {
                    System.out.println("Tanaman tidak tersedia atau sedang dalam cooldown.");
                }
            }
        }
    }

    // menggali tanaman
    public void menggali() {
        // Implementasi logika menggali tanaman
        //soon
    }

    // menampilkan daftar aksi
    public void displayListActions() {
        System.out.println("Actions:");
        System.out.println("1. Menanam tanaman"); //cth
        System.out.println("2. Menggali tanaman"); //cth
        //soon
    }
}

