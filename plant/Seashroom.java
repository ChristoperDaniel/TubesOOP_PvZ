package plant;
public class Seashroom extends Tanaman{
    public Seashroom() {
        super("Seashroom", 0, 100, 20, 5, -1, 10,false);
    }

    @Override
    public void attackPlant(Zombie zombie){
        zombie.setHealthZombie(zombie.getHealthZombie()-this.attack_damageTanaman);
    }
}