package plant;
public class Jalapeno extends Tanaman implements TanamanPenyerang{
    public Jalapeno() {
        super("Jalapeno", 125, 100, 5000, 0, -1, 30, false);
    }

    @Override
    public void attackPlant(Zombie zombie){
        zombie.setHealthZombie(zombie.getHealthZombie()-this.attack_damageTanaman);
    }
}