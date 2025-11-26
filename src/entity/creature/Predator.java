package entity.creature;

public class Predator extends Creature {
    int attackDamage;

    @Override
    public String getSymbol() {
        return "\uD83D\uDC3A";
    }

    @Override
    public void makeMove() {
        super.makeMove();
    }

}
