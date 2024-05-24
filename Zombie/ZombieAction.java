package zombie;

import classes.map.*;
import plant.Tanaman;
import java.util.ArrayList;
import java.util.List;
import interfaces.ZombieWithAbility;

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
        while (zombie.getHealthZombie() > 0) {
            // Sinkronisasi untuk menghindari akses bersamaan ke tile
                // Cek apakah ada tanaman di tile yang sama dengan posisi zombie
                List<ZombieWithAbility> zability = new ArrayList<>();
                zability.add(new DolphinRiderZombie(tile));
                zability.add(new PoleVaultingZombie(tile));
                zability.add(new JackInTheBoxZombie(tile));

                int x = zombie.getRowZombie();
                int y = zombie.getColZombie();
                Tile tile1 = map.getTile(x, y);
                List<Tanaman> tanamans = tile1.getTanaman();
                if (tanamans != null) {
                    for (Tanaman t : tanamans) {
                        if (t.getHealthTanaman() > 0) {
                            if (zombie.getIsAbilityUsed()) {
                                // Jika ada tanaman, serang tanaman
                                for (ZombieWithAbility zombie : zability) {
                                    zombie.useAbility(is_Ability_Used, tile, map, t);
                                }
                            }
        
                            else if ((!(zombie.getIsAbilityUsed())) && (!(zombie.getIsGetSlowedZombie()))) {
                                // Jika ada tanaman, serang tanaman
                                try {
                                    Thread.sleep(zombie.getAttackSpeedZombie()); // Misalnya menunggu 1 detik antara setiap aksi
                                    zombie.attackZombie(tile, map, t);
                                } catch (InterruptedException e) {
                                    Thread.currentThread().interrupt();
                                }
                            } 
        
                            else if ((!(zombie.getIsAbilityUsed())) && zombie.getIsGetSlowedZombie()){
                                // Jika tidak ada tanaman, bergerak
                                try {
                                    Thread.sleep(zombie.getAttackSpeedZombie() + 500); // Misalnya menunggu 1 detik antara setiap aksi
                                    zombie.attackZombie(tile, map, t);
                                } catch (InterruptedException e) {
                                    Thread.currentThread().interrupt();
                                }
                            }
                        }
                        else {
                            tanamans.remove(t);
                            break;
                        }
                }

                if (tanamans.isEmpty() && (!zombie.getIsAbilityUsed()) && (!zombie.getIsGetSlowedZombie())){
                    // Jika tidak ada tanaman, bergerak
                    try {
                        Thread.sleep(zombie.getSpeedZombie()); // Misalnya menunggu 1 detik antara setiap aksi
                        zombie.moveZombie(map);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }

                else if (tanamans.isEmpty() && (!zombie.getIsAbilityUsed()) && zombie.getIsGetSlowedZombie()){
                    // Jika tidak ada tanaman, bergerak
                    try {
                        Thread.sleep(zombie.getSpeedZombie() + 5000); // Misalnya menunggu 1 detik antara setiap aksi
                        zombie.moveZombie(map);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        // Periksa apakah health zombie kurang dari atau sama dengan 0
        if (zombie.getHealthZombie() <= 0) {
            map.removeZombie(zombie.getRowZombie(),zombie.getColZombie(),zombie);
        }
    }
}
