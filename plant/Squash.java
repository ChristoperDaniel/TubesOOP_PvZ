package plant;
public class Squash extends Tanaman{
    public Squash() {
        super("Squash", 50, 100, 5000, 0, 1, 20,false);
    }

    @Override
    public void attackPlant(Zombie zombie){
        zombie.setHealthZombie(zombie.getHealthZombie()-this.attack_damageTanaman);
    }

}