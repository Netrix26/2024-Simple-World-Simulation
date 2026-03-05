import javax.swing.*;
import java.io.*;
import java.util.Scanner;

public abstract class World {
    protected Organism[][] organisms;
    protected int sizeX;
    protected int sizeY;

    protected int tourCounter;

    protected Key key;

    boolean moveOrganism(int fromX, int fromY, int toX, int toY)
    {
        if(fromX==toX && fromY==toY)return false;
        if (toX < 0 || toX >= sizeX || toY < 0 || toY >= sizeY)
        {
            return false;
        }
        if (organisms[toX][toY] != null)
        {
            organisms[fromX][fromY].collision(organisms[toX][toY]);
        }
        else
        {
            if(organisms[fromX][fromY]!=null)
            {
                System.out.println(organisms[fromX][fromY].name() + " moved to ( "+toX+", "+toY+" )");
                organisms[toX][toY] = organisms[fromX][fromY];
                organisms[fromX][fromY] = null;
                organisms[toX][toY].setPosition(toX, toY);
            }
        }
        return true;
    }

    public void placeOrganisms()
    {
       Organism pointer;
        pointer = new Hogweed();
        setCell(pointer, 0, 0);
        pointer = new Wolf();
        setCell(pointer, 1, 1);
        pointer = new Human();
        setCell(pointer, 4, 3);
        pointer = new Wolf();
        setCell(pointer, 9, 6);
        pointer = new Sheep();
        setCell(pointer, 4, 2);
        pointer = new Sheep();
        setCell(pointer, 8, 1);
        pointer = new Sheep();
        setCell(pointer, 9, 3);
        pointer = new Grass();
        setCell(pointer, 2, 10);
        pointer = new Grass();
        setCell(pointer, 4, 10);
        pointer = new Grass();
        setCell(pointer, 6, 10);
        pointer = new Hogweed();
        setCell(pointer, 11, 10);
        pointer = new Hogweed();
        setCell(pointer, 1, 14);
        pointer = new SowThistle();
        setCell(pointer, 10, 10);
        pointer = new SowThistle();
        setCell(pointer, 12, 10);
        pointer = new SowThistle();
        setCell(pointer, 14, 10);
        pointer = new Guarana();
        setCell(pointer, 17, 1);
        pointer = new Guarana();
        setCell(pointer, 18, 2);
        pointer = new Balladona();
        setCell(pointer, 15, 13);
        pointer = new Balladona();
        setCell(pointer, 16, 14);
        pointer = new Balladona();
        setCell(pointer, 16, 14);
        pointer = new Fox();
        setCell(pointer, 8, 2);
        pointer = new Fox();
        setCell(pointer, 9, 2);
        pointer = new Turtle();
        setCell(pointer, 5, 3);
        pointer = new Turtle();
        setCell(pointer, 3, 2);
        pointer = new Turtle();
        setCell(pointer, 21, 4);
        pointer = new Anthelope();
        setCell(pointer, 11, 11);
        pointer = new Anthelope();
        setCell(pointer, 15, 11);
        pointer = new Anthelope();
        setCell(pointer, 18, 2);
    }

    void makeTurn()
    {
        countTour();
        System.out.println("Tour: "+tourCounter);
        int length = sizeX * sizeY + 1;
        int[] orderX = new int[length];
        int[] orderY = new int[length];
        orderX[0] = 0;
        orderY[0] = 0;
        boolean allNull, alreadyDone;
        int i = 1, firstX, firstY, maxInit;
        firstX = 0;
        firstY = 0;
        do
        {
            maxInit = -1;
            allNull = true;
            for (int x = 0; x < sizeX; x++)
            {
                for (int y = 0; y < sizeY; y++)
                {
                    if (organisms[x][y] != null)
                    {
                        alreadyDone = false;
                        for (int k = i; k >= 0; k--)
                        {
                            if (x == orderX[k] && y == orderY[k])
                            {
                                alreadyDone = true;
                                break;
                            }
                        }
                        if (alreadyDone == false)
                        {
                            allNull = false;
                            if (organisms[x][y].getInitiative() > maxInit)
                            {
                                maxInit = organisms[x][y].getInitiative();
                                firstX = x;
                                firstY = y;
                            }
						else if (organisms[x][y].getInitiative() == maxInit)
                            {
                                if (organisms[x][y].getAge() >= organisms[firstX][firstY].getAge())
                                {
                                    firstX = x;
                                    firstY = y;
                                }
                            }
                        }
                    }
                }
            }
            if (allNull == false)
            {
                orderX[i] = firstX;
                orderY[i] = firstY;
                i++;
            }

        } while (allNull != true);
        orderX[i] = Constants.END_OF_ORGANISMS;
        orderY[i] = Constants.END_OF_ORGANISMS;
        for (int j = 0; j < length; j++)
        {
            if (orderX[j] == Constants.END_OF_ORGANISMS)break;
            if (organisms[orderX[j]][orderY[j]] != null && organisms[orderX[j]][orderY[j]].canMove())
            {
                organisms[orderX[j]][orderY[j]].useMove();
                organisms[orderX[j]][orderY[j]].action();
            }
        }
        manageCounters();
    }

    void manageCounters()
    {
        for (int i = 0; i < sizeY; i++)
        {
            for (int j = 0; j < sizeX; j++)
            {
                if (organisms[j][i] != null)
                {
                    organisms[j][i].count();
                }
            }
        }
    }

    boolean setCell(Organism given, int x, int y)
    {
        if (x < 0 || x >= sizeX || y < 0 || y >= sizeY)
        {
            return false;
        }
        if (organisms[x][y] != null)freeCell(x, y);//prevents memory leak
        given.setPosition(x, y);
        organisms[x][y] = given;
        organisms[x][y].setWorld(this);
        return true;
    }

    void setKey(Key newKey)
    {
        key = newKey;
    }

    Key getKey()
    {
        return key;
    }

    void freeCell(int givenX, int givenY)
    {
        if (givenX >= 0 && givenX < sizeX && givenY >= 0 && givenY < sizeY)
        {
            if (organisms[givenX][givenY] != null)
            {
                organisms[givenX][givenY] = null;
            }
            organisms[givenX][givenY] = null;
        }
    }

    Organism getCell(int x, int y)
    {
        if(x < 0 || x >= sizeX || y < 0 || y >= sizeY)return null;
        return organisms[x][y];
    }

    boolean isMoveCorrect(int x, int y, int range)
    {
        return false;
    }

    public static Organism createOfType(Organisms type)
    {
        Organism ptr = null;
        if (type == Organisms.GRASS)ptr = new Grass();
        if (type == Organisms.SOW_THISTLE)ptr = new SowThistle();
        if (type == Organisms.GUARANA)ptr = new Guarana();
        if (type == Organisms.BALLADONNA)ptr = new Balladona();
        if (type == Organisms.HOGWEED)ptr = new Hogweed();
        if (type == Organisms.WOLF)ptr = new Wolf();
        if (type == Organisms.SHEEP)ptr = new Sheep();
        if (type == Organisms.FOX)ptr = new Fox();
        if (type == Organisms.TURTLE)ptr = new Turtle();
        if (type == Organisms.ANTELOPE)ptr = new Anthelope();
        if (type == Organisms.HUMAN)ptr = new Human();
        return ptr;
    }

    public void save() {
        try (PrintWriter save = new PrintWriter(new File("save.txt"))) {
            Organism pointer;
            for (int i = 0; i < Constants.BOARD_WIDTH; i++) {
                for (int j = 0; j < Constants.BOARD_HEIGHT; j++) {
                    pointer = getCell(i, j);
                    if (pointer != null) {
                        save.printf(" %s ", pointer.getType());
                        save.printf(" %d %d %d %d %d %b ",
                                pointer.getX(), pointer.getY(), pointer.getAge(), pointer.getStrength(),
                                pointer.getCooldown(), pointer.isAbility());
                    }
                }
            }
            save.printf(" %s ", Organisms.FILE_END);
            System.out.println("\nGAME SAVED\n\npress L to load");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void load() {
        int x, y, age, strength, tourCounter;
        boolean hasMoved;
        Organisms type;
        Organism pointer;

        // Clear the world board
        for (int i = 0; i < Constants.BOARD_WIDTH; i++) {
            for (int j = 0; j < Constants.BOARD_HEIGHT; j++) {
                if (getCell(i, j) != null) {
                    freeCell(i, j);
                }
            }
        }


        // Read from the file
        try (Scanner scanner = new Scanner(new File("save.txt"))) {
            while (true) {
                String typeStr = scanner.next();
                System.out.println(typeStr);
                type = Organisms.valueOf(typeStr);
                if (type == Organisms.FILE_END) {
                    break;
                }
                pointer = createOfType(type);
                x = scanner.nextInt();
                y = scanner.nextInt();
                age = scanner.nextInt();
                strength = scanner.nextInt();
                tourCounter = scanner.nextInt();
                hasMoved = scanner.nextBoolean();
                pointer.setAll(x, y, strength, age, tourCounter, hasMoved);
                System.out.println("loaded " + pointer);
                setCell(pointer, x, y);
            }
        } catch (FileNotFoundException e) {
            System.out.println("can't open file");
            System.exit(3);
        }
    }

    public int getTour()
    {
        return tourCounter;
    }

    public void resetTour()
    {
        tourCounter=0;
    }

    public void countTour()
    {
        tourCounter++;
    }
    public abstract MyFrame configurePanel();

    public abstract boolean isHex();

}


