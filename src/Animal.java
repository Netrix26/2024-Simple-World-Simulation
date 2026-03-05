import java.util.Random;
public abstract class Animal extends Organism {
    private Random random = new Random();

    @Override
    public void action() {
        int moveX, moveY;
        boolean executedCorrectly = false;
        while (executedCorrectly == false) {
            do //loop prevents "moving" to the same cell
            {
                moveX = random.nextInt(3) - 1;
                moveY = random.nextInt(3) - 1;
            } while (!worldIsIn.isMoveCorrect(moveX, moveY, 1));
            executedCorrectly = worldIsIn.moveOrganism(x, y, x + moveX, y + moveY);
            //moveOrganism() returns 0 if the coordinates are outside of the board
        }
    }

    @Override
    public void collision(Organism stationary) {
        if (stationary.isPlant()) {
            if (stationary.isPoisonous()) {
                System.out.println(this.name() + " ate poisonous  " + stationary.name());
                worldIsIn.freeCell(stationary.getX(), stationary.getY());
                worldIsIn.freeCell(x, y);
            } else {
                int tempX = stationary.getX();
                int tempY = stationary.getY();
                if (stationary.isStrenghtening() != 0)
                    System.out.println(this.name() + "gained strength from " + stationary.name());
                increaseStrenght(stationary.isStrenghtening());
                worldIsIn.freeCell(stationary.getX(), stationary.getY());
                worldIsIn.moveOrganism(x, y, tempX, tempY);
            }
        } else if (type == stationary.getType()) {
            if (tourCounter == 0 && stationary.getCooldown() == 0) {
                Organism pointer = replicate();
                int X = 0, Y = 0;
                boolean executedCorrectly = false;
                for (int i = 0; i < Constants.TRIES; i++) {
                    do //loop prevents "breeding" to the same cell
                    {
                        X = random.nextInt(3) - 1;
                        Y = random.nextInt(3) - 1;
                    } while (!worldIsIn.isMoveCorrect(X, Y, 1));
                    executedCorrectly = worldIsIn.setCell(pointer, x + X, y + Y);
                    if (executedCorrectly) {
                        resetCooldown();
                        stationary.resetCooldown();
                        break; //setCell() returns 0 if the coordinates are outside of the board
                    }

                }
                if (executedCorrectly == false) pointer = null;
                else System.out.println(this.name() + " breed " + worldIsIn.getCell(x + X, y + Y).name());
            }
        }
        else if (stationary.deflectedAttack(strength) == false) {
            if (stationary.escapedAttack()) {
                System.out.println(stationary.name() + " escaped " + this.name() + " attack");
                stationary.action();
            } else {
                if (strength > stationary.getStrength()) {
                    System.out.println(this.name() + " killed " + stationary.name());
                    int tempX = stationary.getX();
                    int tempY = stationary.getY();
                    worldIsIn.freeCell(stationary.getX(), stationary.getY());
                    worldIsIn.moveOrganism(x, y, tempX, tempY);
                } else {
                    System.out.println(this.name() + " died attacking " + stationary.name());
                    worldIsIn.freeCell(x, y);
                }
            }
        }
        else
        {
            System.out.println(stationary.name()+" deflected "+ this.name() + " attack");
        }
    }
}