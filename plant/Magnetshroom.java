package plant;
import zombie.*;

public class Magnetshroom extends Tanaman{
    public Magnetshroom() {
        super("Magnetshroom", 100, 100, 0, 10, -1, 20, false);
    }

    public void removeItem(Zombie zombie){
        if (!zombie.getIsItemRemovedZombie()) {
            zombie.setIsItemRemovedZombie(true);
        }   
    }
}