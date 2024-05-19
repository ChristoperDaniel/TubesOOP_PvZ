package plant;

import classes.map.*;
import zombie.Zombie;
import java.util.List;

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
        while (plant.getHealthTanaman() > 0) {
            // Sinkronisasi untuk menghindari akses bersamaan ke tile
            synchronized (tile) {
                // Cek apakah ada zombie di tile paling kanan di baris ini
                Tile rightMostTile = map.getTile(row, Map.total_columns - 1);
                List<Zombie> zombies = rightMostTile.getZombies();

                if (zombies != null && !zombies.isEmpty()) {
                    // Jika ada zombie di tile paling kanan, serang zombie tersebut
                    plant.attack(tile, map, zombies.get(0)); // atau zombies.get(zombies.size() - 1) untuk zombie terakhir
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
