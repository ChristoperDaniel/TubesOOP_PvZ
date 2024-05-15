package plant;
public class SnowPea extends Tanaman{
    private SlowingEffect slowingEffect;

    public Snowpea() {
        super("Snow Pea", 175, 100, 25, 4, -1, 10,false );
        this.slowingEffect = new SlowingEffect(0.5f, 3000);
    }

    public int slowDamage(){
        
    }

    @Override
    public void attackPlant(Zombie zombie){
        zombie.setHealthZombie(zombie.getHealthZombie()-this.attack_damageTanaman);
    }
}