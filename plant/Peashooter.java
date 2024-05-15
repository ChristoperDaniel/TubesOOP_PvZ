package plant;
import zombie.*;
import classes.map.*;
public class Peashooter extends Tanaman{
    public Peashooter() {
        super("Peashooter", 100, 100, 25, 4, -1, 10, false);
    }

    @Override
    public void attackPlant(Zombie zombie, int x, int y) {
        // Mengecek apakah zombie berada pada row yang sama dengan tanaman
                if (zombie.get.getRow() == tiles.getRow()) {
            // Menyerang zombie dengan mengurangi health sesuai dengan attack damage tanaman
            zombie.setHealthZombie(zombie.getHealthZombie() - this.getAttackDamageTanaman());
        }
    }
}