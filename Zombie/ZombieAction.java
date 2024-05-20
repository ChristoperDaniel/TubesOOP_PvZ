package zombie;

import classes.map.*;
import plant.Tanaman;
import java.util.List;

//import java.util.concurrent.TimeUnit;

public class ZombieAction implements Runnable {
    private Zombie zombie;
    private Tile tile;
    private Map map;

    public ZombieAction(Zombie zombie, Tile tile, Map map) {
        this.zombie = zombie;
        this.tile = tile;
        this.map = map;
    }

    @Override
    public void run() {
        // Lakukan aksi zombie dalam loop
        while (zombie.getHealthZombie() > 0) {
            // Sinkronisasi untuk menghindari akses bersamaan ke tile
            synchronized (tile) {
                // Cek apakah ada tanaman di tile yang sama dengan posisi zombie
                List<Tanaman> tanaman = tile.getTanaman();
                if (tanaman != null) {
                    // Jika ada tanaman, serang tanaman
                    zombie.attackZombie(tile, map, tanaman.get(tanaman.size()-1));
                } else {
                    // Jika tidak ada tanaman, bergerak
                    zombie.moveZombie(tile, map);
                }
            }

            // Menunggu sebelum melakukan aksi berikutnya
            /*try {
                TimeUnit.SECONDS.sleep(1); // Misalnya menunggu 1 detik antara setiap aksi
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }*/
        }
        // Periksa apakah health zombie kurang dari atau sama dengan 0
        if (zombie.getHealthZombie() <= 0) {
            // Jika iya, hapus zombie dari list zombie pada tile
            tile.removeZombie(zombie);
            // Keluar dari loop untuk menghentikan aksi zombie
        }
    }
}
