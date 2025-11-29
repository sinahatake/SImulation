package entity.creature;

public class Predator extends Creature {
    int attackDamage;

    public Predator(int hp, int speed, int attackDamage) {
        super(hp, speed);
        this.attackDamage = attackDamage;
    }

    @Override
    public String getSymbol() {
        return "\uD83D\uDC3A";
    }


}
