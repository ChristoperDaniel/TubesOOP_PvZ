package plant;

import classes.map.*;
import zombie.Zombie;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PlantAction implements Runnable {
    private Tanaman plant;
    private Tile tile;
    private Map map;
    private int row;

    public PlantAction(Tanaman plant, Tile tile, Map map) {
        this.plant = plant;
        this.tile = tile;
        this.map = map;
        this.row = tile.getY();  // Simpan baris untuk memudahkan akses nanti
    }

    @Override
    public void run() {
        private ScheduledExecutorService executorService;
        while (plant.getHealthTanaman() > 0) {
            // Sinkronisasi untuk menghindari akses bersamaan ke tile
            synchronized (tile) {
                if (plant.getRangeTanaman() == -1){
                    executorService.scheduleAtFixedRate(() ->{
                        List<Zombie> kosong = new ArrayList<>();
                        List<Tile> baris = map.getBaris(y);
                        for (Tile tiles : baris) {
                            if (!tiles.getZombies().isEmpty()) {
                                    // Ambil zombie terdepan
                                Zombie zombieTerdepan = tiles.getZombies().get(0);
                                    // Serang setiap attack_speedTanaman detik sekali
                                zombieTerdepan.setHealthZombie(zombieTerdepan.getHe((althZombie() - this.getAttackDamageTanaman());
                                if (zombieTerdepan.getHealthZombie() <= 0) {
                                        tiles.removeZombie(zombieTerdepan);
                                }
                    } , 0, plant.getAttackSpeedTanaman(), TimeUnit.SECONDS);
                }
            }
        }

            // Menunggu sebelum melakukan aksi berikutnya
            try {
                Thread.sleep(1000); // Misalnya menunggu 1 detik antara setiap aksi
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Periksa apakah health tanaman kurang dari atau sama dengan 0
        if (plant.getHealthTanaman() <= 0) {
            // Jika iya, hapus tanaman dari list tanaman pada tile
            synchronized (tile) {
                tile.removeTanaman(plant);
            }
        }
    }
}
