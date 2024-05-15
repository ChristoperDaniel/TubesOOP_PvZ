package plant;
public class SnowPea extends Tanaman{
    private SlowingEffect slowingEffect;

    public Snowpea() {
        super("Snow Pea", 175, 100, 25, 4, -1, 10,false );
        this.slowingEffect = new SlowingEffect(0.5f, 3000);
    }

    @Override
    public void attackPlant(Zombie zombie, Tiles tiles) {
        // Mengecek apakah zombie berada pada row yang sama dengan tanaman
        if (zombie.getPosition().getRow() == tiles.getRow()) {
            // Menyerang zombie dengan mengurangi health sesuai dengan attack damage tanaman
            zombie.setHealthZombie(zombie.getHealthZombie() - this.getAttackDamageTanaman());
        }
        zombie.applySlowEffect(slowingEffect);
    }
}