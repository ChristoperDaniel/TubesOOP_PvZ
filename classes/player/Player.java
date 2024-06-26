package classes.player;
import java.util.List;
import plant.*;
import classes.objects.*;
import main.ScannerJava;
import classes.map.*;

public class Player {
    private volatile Sun sun;
    // Constructor
    public Player(Sun sun) {
        this.sun = sun;
    }
    public boolean canAfford(int cost) {
        return sun.gettotalSun() >= cost;
    }
    // menanam tanaman
    public void menanam(Deck deck, int row, int col, Map map) {
        while (true) { // loop ini bisa diganti sesuai kondisi permainan Anda
            System.out.println("Tanaman yang tersedia:");
            List<Tanaman> availableTanamans = deck.getAvailableTanamans();
            for (Tanaman tanaman : availableTanamans) {
                System.out.println(tanaman.getNamaTanaman() + " (Cost: " + tanaman.getCostTanaman() + " sun)");
            }
            System.out.println("Sun Anda saat ini: " + sun.gettotalSun());
            System.out.println("Pilih tanaman untuk ditanam (atau ketik 'exit' untuk keluar):");
            ScannerJava.getScanner().nextLine();
            String pilihan = ScannerJava.getScanner().nextLine();

            Tanaman tanaman = deck.getTanamanByName(pilihan);
            if (tanaman != null) {
                if (!tanaman.isOnCooldown() && canAfford(tanaman.getCostTanaman())) {
                    sun.reduceSun(tanaman.getCostTanaman());
                    map.placeTanaman(row, col, tanaman, sun);
                } else if (!canAfford(tanaman.getCostTanaman())) {
                    System.out.println("Tidak cukup sun untuk menanam " + tanaman.getNamaTanaman());
                } else {
                    System.out.println("Tanaman tidak tersedia atau sedang dalam cooldown.");
                }
            } else {
                System.out.println("Tanaman tidak ditemukan.");
            }
            break;
        }
    }
    

    // menggali tanaman
    public void menggali(Map map, int row, int col) {
        // Implementasi logika menggali tanaman
        //soon
        Tile tile = map.getTile(row, col);
        if (map.isTanamanAvail(row,col)) {
            Tanaman tanaman = tile.getTanaman().get(tile.getTanaman().size() - 1);
            map.removeTanaman(row, col, tanaman);
            // Mengembalikan sebagian atau seluruh biaya tanaman (g tau jadi ngga nya)
            //sun.addCustomSun(tanaman.getCostTanaman() / 2);
            System.out.println("Tanaman telah berhasil digali dari baris " + row + " dan kolom " + col + ".");
        } else {
            System.out.println("Tidak ada tanaman di lokasi tersebut.");
        }
    }

    // menampilkan daftar aksi
}

