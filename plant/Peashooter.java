package plant;
public class Peashooter extends Tanaman{
    public Peashooter() {
        super("Peashooter", 100, 100, 25, 4, -1, 10);
    }

    @Override
    public void attackPlant(Zombie zombie, Tiles tiles) {
        // Mengecek apakah zombie berada pada row yang sama dengan tanaman
        if (zombie.getPosition().getRow() == tiles.getRow()) {
            // Menyerang zombie dengan mengurangi health sesuai dengan attack damage tanaman
            zombie.setHealthZombie(zombie.getHealthZombie() - this.getAttackDamageTanaman());
        }
    }
}