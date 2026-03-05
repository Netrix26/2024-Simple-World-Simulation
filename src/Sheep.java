public class Sheep extends Animal{

    Sheep()
    {
        age = 0;
        initiative = 4;
        strength = 4;
        type = Organisms.SHEEP;
    }
    @Override
    public String name() {
        return "Sheep ( "+x+", "+y+" )";
    }

    @Override
    public Organism replicate() {
        return new Sheep();
    }

    @Override
    public String imagePath()
    {
        return "Images/Sheep.png";
    }
}
