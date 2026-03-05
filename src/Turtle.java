import java.util.Random;

public class Turtle extends Animal{
    private Random random = new Random();
    public Turtle()
    {
        age = 0;
        initiative = 1;
        strength = 2;
        type = Organisms.TURTLE;
    }

    @Override
    public void action()
    {
        if (random.nextInt(100) > 75)
        {
            int moveX, moveY;
            boolean executedCorrectly = false;
            while (executedCorrectly == false)
            {
                do //loop prevents "moving" to the same cell
                {
                    moveX = random.nextInt(3) - 1;
                    moveY = random.nextInt(3) - 1;
                } while (!worldIsIn.isMoveCorrect(moveX,moveY,1));
                executedCorrectly = worldIsIn.moveOrganism(x, y, x + moveX, y + moveY);
                //moveOrganism() returns 0 if the coordinates are outside of the board
            }
        }
    }
    @Override
    public Organism replicate()
    {
        return new Turtle();
    }

    @Override
    public boolean deflectedAttack(int attackerStrength)
    {
        if (attackerStrength < 5)
        {
            return true;
        }
        return false;
    }

    @Override
    public String name() {
        return "Turtle ( "+x+", "+y+" )";
    }

    @Override
    public String imagePath()
    {
        return "Images/Turtle.png";
    }
}
