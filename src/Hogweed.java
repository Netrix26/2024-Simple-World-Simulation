public class Hogweed extends Plant{
    Hogweed()
    {
        age = 0;
        initiative = 0;
        strength = 0;
        type = Organisms.HOGWEED;
    }

    @Override
    public void action()
    {
        for (int i = x-1; i < x+2; i++)
        {
            for (int j = y-1; j < y+2; j++)
            {

                if (i >= 0 && j >= 0 && i < Constants.BOARD_WIDTH && j < Constants.BOARD_HEIGHT && worldIsIn.getCell(i, j) != null)
                {
                    if (worldIsIn.isMoveCorrect(i-x, j-y, 1) && !worldIsIn.getCell(i, j).isPlant())
                    {
                        System.out.println(worldIsIn.getCell(i, j).name() +" got killed by "+ this.name());
                        worldIsIn.freeCell(i, j);
                    }
                }
            }
        }
    }

    @Override
    public Organism replicate()
    {
        return new Hogweed();
    }

    @Override
    public boolean isPoisonous()
    {
        return true;
    }

    @Override
    public String name()
    {
        return "Hogweed ("+x+", "+y+" )";
    }

    @Override
    public String imagePath()
    {
        return "Images/Hogweed.png";
    }
}
