package plant;
public class Jalapeno extends Tanaman implements TanamanPenyerang{
    public Jalapeno() {
        super("Jalapeno", 125, 100, 5000, 0, -1, 30, false);
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