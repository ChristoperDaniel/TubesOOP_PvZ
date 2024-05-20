package zombie;

import classes.map.*;
import plant.Tanaman;
import java.util.ArrayList;
import java.util.List;
import interfaces.ZombieWithAbility;

//import java.util.concurrent.TimeUnit;

public class ZombieAction implements Runnable {
    private boolean is_Ability_Used;
    private Zombie zombie;
    private Tile tile;
    private Map map;

    public ZombieAction(boolean is_Ability_Used, Zombie zombie, Tile tile, Map map) {
        this.is_Ability_Used = zombie.getIsAbilityUsed();
        this.zombie = zombie;
        this.tile = tile;
        this.map = map;
    }

    @Override
    public void run() {
        // Lakukan aksi zombie dalam loop
        while (!(zombie.getHealthZombie() <=0)) {
            // Sinkronisasi untuk menghindari akses bersamaan ke tile
            synchronized (tile) {
                // Cek apakah ada tanaman di tile yang sama dengan posisi zombie
                List<ZombieWithAbility> zability = new ArrayList<>();
                zability.add(new DolphinRiderZombie(tile));
                zability.add(new PoleVaultingZombie(tile));
                zability.add(new JackInTheBoxZombie(tile));

                List<Tanaman> tanaman = tile.getTanaman();
                if (tanaman != null && zombie.getIsAbilityUsed() == true) {
                    // Jika ada tanaman, serang tanaman
                    for (ZombieWithAbility zombie : zability) {
                        zombie.useAbility(is_Ability_Used, tile, map, tanaman.get(tanaman.size()-1));
                    }
                }

                else if (tanaman != null && zombie.getIsAbilityUsed() == true) {
                    // Jika ada tanaman, serang tanaman
                    zombie.attackZombie(tile, map, tanaman.get(tanaman.size()-1));
                } 

                else {
                    // Jika tidak ada tanaman, bergerak
                    zombie.moveZombie(map);
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
