import java.util.Random;

public class SowThistle extends Plant{
    public SowThistle()
    {
        age = 0;
        initiative = 0;
        strength = 0;
        type = Organisms.SOW_THISTLE;
    }

    @Override
    public String name()
    {
        return "Sow Thistle ("+x+", "+y+" )";
    }

    @Override
    public Organism replicate()
    {
        return new SowThistle();
    }

    @Override
    public void action()
    {
        for(int i = 0; i<3; i++)
        {
            super.action();
        }
    }

    @Override
    public String imagePath()
    {
        return "Images/Sow_Thistle.png";
    }

}
