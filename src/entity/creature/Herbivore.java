package entity.creature;

public class Herbivore extends Creature {

    public Herbivore(int hp, int speed) {
        super(hp, speed);
    }

    @Override
    public String getSymbol() {
        return "\uD83D\uDC11";
    }


}

