package plant;

public class Magnetshroom extends Tanaman{
    public Magnetshroom() {
<<<<<<< HEAD:Tanaman/Magnetshroom.java
        super("Magnetshroom", 100, 100, 0, 10, 0, 20);
=======
        super("Magnetshroom", 100, 100, 0, 10, -1, 20, false);
>>>>>>> 359b7a517f538a8384c0f4d6760c88bcc68dede2:plant/Magnetshroom.java
    }

    public void removeItem(Zombie zombie){
        if (!zombie.is_item_removedZombie) {
            zombie.is_item_removedZombie = true;
        }   
    }
}