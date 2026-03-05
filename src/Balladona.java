public class Balladona extends Plant{
    public Balladona()
    {
        age = 0;
        initiative = 0;
        strength = 10;
        type = Organisms.BALLADONNA;
    }
    @Override
    public String name()
    {
        return "Balladona ("+x+", "+y+" )";
    }
    @Override
    public Organism replicate()
    {
        return new Balladona();
    }
    @Override
    public boolean isPoisonous()
    {
        return true;
    }

    @Override
    public String imagePath()
    {
        return "Images/Balladona.png";
    }
}
