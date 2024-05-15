package plant;
public class TangleKelp extends Tanaman{
    public TangleKelp() {
        super("Tangle Kelp", 25, 100, 5000, 0, 1, 20, false);
    }

    @Override
    public void attackPlant(Zombie zombie){
        zombie.setHealthZombie(zombie.getHealthZombie()-this.attack_damageTanaman);
    }
}