public class Peashooter extends Tanaman{
    public Peashooter() {
        super("Peashooter", 100, 100, 25, 4, -1, 10);
    }

    @Override
    public void attackPlant(Zombie zombie){
        zombie.setHealthZombie(zombie.getHealthZombie()-this.attack_damageTanaman);
    }
}