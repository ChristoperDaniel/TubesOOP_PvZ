package zombie;

import classes.map.*;
import plant.*;

import java.util.concurrent.TimeUnit;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public abstract class Zombie extends Aquatic {
    private ScheduledExecutorService executorService;
    private String namaZombieString;
    private int healthZombie;
    private int attack_damageZombie;
    private int attack_speedZombie;
    private int speedZombie;
    private int rangeZombie;
    private boolean is_item_removedZombie;
    private boolean is_ability_used;
    private boolean is_get_slowedZombie;
    private int row;
    private int col;

    public Zombie(String namaZombieString, int healthZombie, int attack_damageZombie, int attack_speedZombie, int speedZombie, int rangeZombie, boolean isaquatic) {
        super(isaquatic);
        this.namaZombieString = namaZombieString;
        this.healthZombie = healthZombie;
        this.attack_damageZombie = attack_damageZombie;
        this.attack_speedZombie = attack_speedZombie;
        this.speedZombie = speedZombie;
        this.rangeZombie = rangeZombie;
        this.is_item_removedZombie = false;
        this.is_ability_used = false;
        this.is_get_slowedZombie = false;
        this.row = -1;
        this.col = -1;
        executorService = Executors.newSingleThreadScheduledExecutor();
    }

    public String getNamaZombie() {
        return namaZombieString;
    }

    public int getRowZombie(){
        return row;
    }

    public void setRowZombie(int row){
        this.row = row;
    }

    public int getColZombie(){
        return col;
    }

    public void setColZombie(int col){
        this.col = col;
    }

    public int getHealthZombie() {
        return healthZombie;
    }

    public void setHealthZombie(int healthZombie) {
        this.healthZombie = healthZombie;
    }

    public int getAttackDamageZombie() {
        return attack_damageZombie;
    }

    public int getAttackSpeedZombie() {
        return attack_speedZombie;
    }
    
    public void setAttackSpeedZombie(int attack_speedZombie) {
        this.attack_speedZombie = attack_speedZombie;
    }

    public int getSpeedZombie() {
        return speedZombie;
    }

    public void setSpeedZombie(int speedZombie) {
        this.speedZombie = speedZombie;
    }

    public int getRangeZombie() {
        return rangeZombie;
    }

    public boolean getIsItemRemovedZombie() {
        return is_item_removedZombie;
    }

    public void setIsItemRemovedZombie(boolean is_item_removedZombie) {
        this.is_item_removedZombie = is_item_removedZombie;
    }

    public boolean getIsGetSlowedZombie() {
        return is_get_slowedZombie;
    }

    public void SetIsGetSlowedZombie(boolean is_get_slowedZombie) {
        this.is_get_slowedZombie = is_get_slowedZombie;
    }

    public boolean getIsAbilityUsed() {
        return is_ability_used;
    }

    public void setIsAbilityUsed(boolean is_ability_used) {
        this.is_ability_used = is_ability_used;
    }

    public void slowedZombie(boolean is_get_slowedZombie) {
        if (is_get_slowedZombie) {
            setSpeedZombie(getSpeedZombie() + 5000);
            setAttackSpeedZombie(getAttackSpeedZombie() + 500);
        }
    }
    
    public void attackZombie(Tile tile, Map map, Tanaman tanaman) {
        int x = getRowZombie();
        int y = getColZombie();
        Tile xy = map.getTile(x, y);
        int dmg = getAttackDamageZombie();
        int atkspd = getAttackSpeedZombie();
        List<Tanaman> planted = xy.getTanaman();
        if (getRangeZombie() == 1) {
            executorService.scheduleAtFixedRate(() -> {
                for (Tanaman t : planted) {
                    t.setHealthTanaman(t.getHealthTanaman() - dmg);
                    if (t.getHealthTanaman() <= 0) {
                        xy.removeTanaman(t);
                        executorService.shutdown();
                    }
                }
            } , 0, atkspd, TimeUnit.MILLISECONDS);
        }

        else {
            executorService.scheduleAtFixedRate(() -> {
                for (int i = y; i > 0; i--) {
                    Tile xy1 = map.getTile(x, i);
                    List<Tanaman> planted1 = xy1.getTanaman();
                    if (planted1 != null) {
                        for (Tanaman t : planted1) {
                            t.setHealthTanaman(t.getHealthTanaman() - dmg);
                            if (t.getHealthTanaman() <= 0) {
                                xy1.removeTanaman(t);
                                executorService.shutdown();
                            }
                        }
                    }
                }
            } , 0, atkspd, TimeUnit.MILLISECONDS);
        }
    }

    public void moveZombie (Map map) {
        int x = getRowZombie();
        int y = getColZombie();
        int spd = getSpeedZombie();

        executorService.scheduleAtFixedRate(() ->{
            for (int i = y; i > 0; i--) {
                if (!(map.isTanamanAvail(x, i) == true)) {
                    setRowZombie(i);
                }
                else {
                    executorService.shutdown();
                    break;
                }
            }
        } , 0, spd, TimeUnit.MILLISECONDS);
    }
}