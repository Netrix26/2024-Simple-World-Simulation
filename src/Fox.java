import java.util.Random;

public class Fox extends Animal{
    private Random random = new Random();
    public Fox()
    {
        age = 0;
        initiative = 7;
        strength = 3;
        type = Organisms.FOX;
    }

    @Override
    public String name() {
        return "Fox ( "+x+", "+y+" )";
    }

    @Override
     public void action()
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
            if (worldIsIn.getCell(x + moveX, y + moveY) != null &&
                    worldIsIn.getCell(x + moveX, y + moveY).getStrength() > strength)
            {
                System.out.println(this.name()+" sniffed out "+worldIsIn.getCell(x + moveX, y + moveY).name());
            }
            else executedCorrectly = worldIsIn.moveOrganism(x, y, x + moveX, y + moveY);
            //moveOrganism() returns 0 if the coordinates are outside of the board
        }
    }

    @Override
    public Organism replicate()
    {
        return new Fox();
    }

    @Override
    public String imagePath()
    {
        return "Images/Fox.png";
    }
}
