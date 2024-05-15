package classes.map;

public class Magnetshroom extends Tanaman{
    public Magnetshroom() {
        super("Magnetshroom", 100, 100, 0, 10, -1, 20);
    }

    public void removeItem(Zombie zombie){
        if (!zombie.is_item_removedZombie) {
            zombie.is_item_removedZombie = true;
        }   
    }
}