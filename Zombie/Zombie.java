package zombie;

public abstract class Zombie {
    protected String namaZombieString;
    protected int healthZombie;
    protected int attack_damageZombie;
    protected int attack_speedZombie;
    protected int speedZombie;
    protected int rangeZombie;
    protected boolean is_item_removedZombie;
    protected boolean is_ability_used;
    protected boolean is_get_slowedZombie;

    public Zombie(String namaZombieString, int healthZombie, int attack_damageZombie, int attack_speedZombie, int speedZombie, int rangeZombie) {
        this.namaZombieString = namaZombieString;
        this.healthZombie = healthZombie;
        this.attack_damageZombie = attack_damageZombie;
        this.attack_speedZombie = attack_speedZombie;
        this.speedZombie = speedZombie;
        this.rangeZombie = rangeZombie;
        this.is_item_removedZombie = false;
        this.is_ability_used = false;
        this.is_get_slowedZombie = false;
    }
    public String getNamaZombie() {
        return namaZombieString;
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
    public abstract void attackZombie();
}