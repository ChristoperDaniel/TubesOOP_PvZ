public class Tanaman extends Aquatic{
    private String namaTanaman;
    private int costTanaman;
    private int healthTanaman;
    private int attack_damageTanaman;
    private int attack_speedTanaman;
    private int rangeTanaman;
    private int cooldownTanaman;
    private zombie zombieTerdepan;
    
    // konstruktor
    public Tanaman(String namaTanaman, int costTanaman, int healthTanaman, int attack_damageTanaman, int attack_speedTanaman, int rangeTanaman, int cooldownTanaman, zombie zombieTerdepan){
        this.namaTanaman = namaTanaman;
        this.costTanaman = costTanaman;
        this.healthTanaman = healthTanaman;
        this.attack_damageTanaman = attack_damageTanaman;
        this.attack_speedTanaman = attack_speedTanaman;
        this.rangeTanaman = rangeTanaman;
        this.cooldownTanaman = cooldownTanaman;
        this.zombieTerdepan = zombieTerdepan;
    }
    
    // method
    public String getNamaTanaman(){
        return namaTanaman;
    }
    public int getCostTanaman(){
        return costTanaman;
    }
    public int getHealthTanaman(){
        return healthTanaman;
    }
    public void setHealthTanaman(int healthTanaman){
        this.healthTanaman = healthTanaman;
    }
    public int getAttackDamageTanaman(){
        return attack_damageTanaman;
    }
    public int getAttackSpeedTanaman(){
        return attack_speedTanaman;
    }
    public int getRangeTanaman(){
        return rangeTanaman;
    }
    public int getCooldownTanaman(){
        return cooldownTanaman;
    }
}