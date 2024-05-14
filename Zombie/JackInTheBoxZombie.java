package Zombie;

public class JackInTheBoxZombie extends Zombie implements ZombieWithAbility {
    public JackInTheBoxZombie() {
        super("JackInTheBoxZombie", 300, 5000, 1,  5, 1);
    }
    public void attackZombie(){}
    public void useAbility() {}
}
