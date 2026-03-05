public class Guarana extends Plant{
    public Guarana()
    {
        age = 0;
        initiative = 0;
        strength = 0;
        type = Organisms.GUARANA;
    }
    @Override
    public String name()
    {
        return "Guarana ("+x+", "+y+" )";
    }
    @Override
    public Organism replicate()
    {
        return new Guarana();
    }
    @Override
    public int isStrenghtening()
    {
        return 3;
    }

    @Override
    public String imagePath()
    {
        return "Images/Guarana.png";
    }
}
