import java.util.Random;

public abstract class Plant extends Organism{
    private Random random = new Random();
    public void action()
    {

        int roll = random.nextInt(100);
        if (roll < Constants.SOW_THRESHOLD)
        {

            Organism pointer = replicate();
            int sowX, sowY;
            boolean executedCorrectly = false;
            for (int i = 0; i < Constants.TRIES; i++)
            {
                do //loop prevents "sowing" to the same cell
                {
                    sowX = random.nextInt(3) - 1;
                    sowY = random.nextInt(3) - 1;
                } while (!worldIsIn.isMoveCorrect(sowX, sowY, 1));
                if (worldIsIn.getCell(x + sowX, y + sowY) == null)
                {
                    executedCorrectly = worldIsIn.setCell(pointer, x + sowX, y + sowY);
                    if (executedCorrectly)
                    {
                        System.out.println(this.name()+ " sow " + worldIsIn.getCell(x + sowX, y + sowY).name());
                        break; //setCell() returns 0 if the coordinates are outside of the board
                    }

                }
            }
            if (executedCorrectly == false)pointer = null;
        }
    }

    public void collision(Organism stationary)
    {
        //does nothing, should never be called, plants can't move
        //all properties of plant's are passed to colision() function trough
        //functions like isPoisionous(), isStrenghtening()
    }

    @Override
    public boolean isPlant()
    {
        return true;
    }
}
