public abstract class Organism {
    protected int x;
    protected int y;
    protected Organisms type;
    protected int age;
    protected int initiative;
    protected int strength;
    protected int tourCounter;
    protected boolean hasMoved;
    protected boolean ability;
    protected World worldIsIn;

    public void setAll(int newX, int newY, int newStr, int newAge, int newCnt, boolean newAbil)
    {
        x = newX;
        y = newY;
        age = newAge;
        tourCounter = newCnt;
        strength = newStr;
        ability = newAbil;
    }

    public void setPosition(int newX, int newY)
    {
        x = newX;
        y = newY;
    }

    public void setWorld(World givenWorld)
    {
        worldIsIn = givenWorld;
    }

    public void useMove()
    {
        hasMoved = true;
    }

    public void count()
    {
        age++;
        if (tourCounter > 0)tourCounter--;
        hasMoved = false;
    }

    public void resetCooldown()
    {
        tourCounter = Constants.BREEDING_COOLDOWN;
    }

    public int getStrength()
    {
        return strength;
    }

    public void increaseStrenght(int s)
    {
        strength += s;
    }

    public int getInitiative()
    {
        return initiative;
    }

    public int getAge()
    {
        return age;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public boolean isAbility() {
        return ability;
    }

    public Organisms getType()
    {
        return type;
    }

    public int getCooldown()
    {
        return tourCounter;
    }

    public boolean deflectedAttack(int strength)
    {
        return false;
    }

    public boolean escapedAttack()
    {
        return false;
    }

    public boolean canMove()
    {
        return !hasMoved;
    }

    public boolean isPoisonous()
    {
        return false;
    }
    public boolean isPlant(){return false;}
    public int isStrenghtening() {
        return 0;
    }
    public abstract String name();
    public abstract void collision (Organism stationary);
    public abstract void action ();

    public abstract String imagePath();

    public abstract Organism replicate();
}
