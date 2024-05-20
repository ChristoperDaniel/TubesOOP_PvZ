package plant;

public class Sunflower extends Tanaman{
    public Sunflower() {
        super("Sunflower", 50, 100, 0, 0, 0, 10, false);
    }
    
    public int generateSun(){
        return 25;
    }
}