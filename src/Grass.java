public class Grass extends Plant{
    public Grass()
    {
        age = 0;
        initiative = 0;
        strength = 0;
        type = Organisms.GRASS;
    }

    public String name()
    {
        return "Grass ("+x+", "+y+" )";
    }
    @Override
    public Organism replicate()
    {
        return new Grass();
    }

    @Override
    public String imagePath()
    {
        return "Images/Grass.png";
    }
}
