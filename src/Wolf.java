public class Wolf extends Animal{
    Wolf()
    {
        age = 0;
        initiative = 5;
        strength = 9;
        type = Organisms.WOLF;
    }
    @Override
    public String name() {
        return "Wolf ( "+x+", "+y+" )";
    }

    @Override
    public Organism replicate() {
        return new Wolf();
    }

    @Override
    public String imagePath()
    {
        return "Images/Wolf.png";
    }
}
