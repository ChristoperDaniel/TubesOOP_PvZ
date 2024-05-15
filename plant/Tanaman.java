package plant;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import zombie.*;
import interfaces.*;

public class Tanaman extends Aquatic{
    private String namaTanaman;
    private int costTanaman;
    private int healthTanaman;
    private int attack_damageTanaman;
    private int attack_speedTanaman;
    private int rangeTanaman;
    private int cooldownTanaman;
    private boolean isOnCooldown = false;
    
    
    // konstruktor
    public Tanaman(String namaTanaman, int costTanaman, int healthTanaman, int attack_damageTanaman, int attack_speedTanaman, int rangeTanaman, int cooldownTanaman, boolean isaquatic){
        super(isaquatic);
        this.namaTanaman = namaTanaman;
        this.costTanaman = costTanaman;
        this.healthTanaman = healthTanaman;
        this.attack_damageTanaman = attack_damageTanaman;
        this.attack_speedTanaman = attack_speedTanaman;
        this.rangeTanaman = rangeTanaman;
        this.cooldownTanaman = cooldownTanaman;
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
    public boolean isOnCooldown() {
        return isOnCooldown;
    }
    
    public void startCooldown(ScheduledExecutorService scheduler) {
        isOnCooldown = true;
        scheduler.schedule(() -> isOnCooldown = false, cooldownTanaman, TimeUnit.SECONDS);
    }
}