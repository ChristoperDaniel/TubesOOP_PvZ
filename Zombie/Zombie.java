package zombie;

import classes.map.*;
import plant.*;

public class Zombie extends Aquatic {
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

    public Zombie(String namaZombieString, int healthZombie, int attack_damageZombie, int attack_speedZombie, int speedZombie, int rangeZombie, boolean is_item_removedZombie, boolean isaquatic) {
        super(isaquatic);
        this.namaZombieString = namaZombieString;
        this.healthZombie = healthZombie;
        this.attack_damageZombie = attack_damageZombie;
        this.attack_speedZombie = attack_speedZombie;
        this.speedZombie = speedZombie;
        this.rangeZombie = rangeZombie;
        this.is_ability_used = false;
        this.is_get_slowedZombie = false;
        this.row = -1;
        this.col = -1;
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

    public void attackZombie(Tile tile, Map map, Tanaman tanaman) {
        tanaman.setHealthTanaman(tanaman.getHealthTanaman() - getAttackDamageZombie());
        if (tanaman.getHealthTanaman() <= 0) {
            tile.removeTanaman(tanaman);
            tanaman.setColPlant(12);
        }
    }

    public synchronized void moveZombie (Map map) {
            int x = getRowZombie();
            // Menggunakan AtomicInteger untuk memodifikasi nilai di dalam lambda
            int y = getColZombie();
            if (y > 0) {
                map.removeZombie(x,y,this);
                y--;
                setColZombie(y);
                map.getTile(x, y).addZombie(this);
            }
            
        
    }
}