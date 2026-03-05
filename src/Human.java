public class Human extends Animal{

    Human()
    {
        age = 0;
        initiative = 4;
        strength = 5;
        type = Organisms.HUMAN;
    }

    @Override
    public void count()
    {
        age++;
        if (tourCounter > 0)
        {
            if (ability)
            {
                System.out.println(this.name()+ " ability active "+(tourCounter-1)+" turns");
            }
            else
            {
                System.out.println(this.name()+ " ability cooldown "+(tourCounter-1)+" turns");
            }
            tourCounter--;
        }
        if (ability == true && tourCounter == 0)
        {
            tourCounter = Constants.ABILITY_COOLDOWN+1;
            ability = false;
        }
        hasMoved = false;
    }

    @Override
    public void action()
    {
        Key key = worldIsIn.getKey();
        if (key == key.UP)worldIsIn.moveOrganism(x, y, x, y-1);
        else if (key == key.DOWN)worldIsIn.moveOrganism(x, y, x, y+1);
        else if (key == key.RIGHT)worldIsIn.moveOrganism(x, y, x + 1, y);
        else if (key == key.LEFT)worldIsIn.moveOrganism(x, y, x - 1, y);
        else if (key == key.BOT_RIGHT)worldIsIn.moveOrganism(x, y, x + 1, y+1);
        else if (key == key.TOP_LEFT)worldIsIn.moveOrganism(x, y, x - 1, y-1);
        else if (key == key.ABILITY && ability == false && tourCounter == 0)
        {
            ability = true;
            tourCounter = Constants.ABILITY_DURATION+1;
            System.out.println(this.name()+" activated ability ");
        }
    }

    @Override
    public Organism replicate()
    {
        //does nothing, there can only be one player
        return null;
    }

    @Override
    public String name()
    {
        return "Human ( "+x+", "+y+" )";
    }

    @Override
    public boolean deflectedAttack(int attackerStrength)
    {
        if (ability)
        {
            System.out.println("Alzur's shield worked");
            return true;
        }
        return false;
    }

    @Override
    public String imagePath()
    {
        if(ability) return "Images/Ability.png";
        else return "Images/Human.png";

    }
}
