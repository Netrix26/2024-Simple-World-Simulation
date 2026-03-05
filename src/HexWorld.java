import javax.swing.*;

public class HexWorld extends World{

    public HexWorld() {
        this.resetTour();
        this.sizeX = Constants.BOARD_WIDTH;
        this.sizeY = Constants.BOARD_HEIGHT;
        this.organisms = new Organism[sizeX][sizeY];
        for (int i = 0; i < sizeX; i++)
        {
            for (int j = 0; j < sizeY; j++)
            {
                organisms[i][j] = null;
            }
        }
    }
    public HexWorld(int x, int y) {
        this.sizeX = x;
        this.sizeY = y;
        this.organisms = new Organism[sizeX][sizeY];
        for (int i = 0; i < sizeX; i++)
        {
            for (int j = 0; j < sizeY; j++)
            {
                organisms[i][j] = null;
            }
        }
    }
    @Override
    boolean isMoveCorrect(int x, int y, int range) {
        if(x==0 && y==0)return false;
        //if(range==1 && ((x==1&&y==-1)||(x==-1&&y==1)))return false;
        if((Math.abs(y) + Math.abs(y - x) + Math.abs(x)) / 2 > range)return false;
        return true;
    }

    @Override
    public boolean isHex(){return true;}

    @Override
    public MyFrame configurePanel()
    {
        MyFrame panel = new MyFrame();
        panel.setWorld(this);
        for(int i = 0; i<Constants.BOARD_WIDTH; i++) {
            for (int j = 0; j < Constants.BOARD_HEIGHT; j++) {
                panel.getCell(i, j).setBounds((sizeY-1)*MyFrame.SIZE+MyFrame.SIZE*i-MyFrame.SIZE*j,
                        MyFrame.SIZE*j/2+MyFrame.SIZE*i/2, MyFrame.SIZE, MyFrame.SIZE);
            }
        }
        int newZero = 30*MyFrame.SIZE;
        panel.getLoad().setBounds(0, 0,MyFrame.SIZE,MyFrame.SIZE);
        panel.getSave().setBounds(MyFrame.SIZE, 0,MyFrame.SIZE,MyFrame.SIZE);
        panel.getSelect().setBounds(2*MyFrame.SIZE, 0,MyFrame.SIZE,MyFrame.SIZE);
        panel.getQuit().setBounds(3*MyFrame.SIZE, 0,MyFrame.SIZE,MyFrame.SIZE);
        panel.getShield().setBounds(newZero, 4*MyFrame.SIZE,MyFrame.SIZE,MyFrame.SIZE);
        ImageIcon icon = new ImageIcon("Images/RIGHT.png");
        panel.getMove(0).setBounds(newZero+MyFrame.SIZE, MyFrame.SIZE*7/2,MyFrame.SIZE,MyFrame.SIZE);
        panel.getMove(0).setIcon(icon);
        icon = new ImageIcon("Images/RIGHT.png");
        panel.getMove(1).setBounds(newZero+MyFrame.SIZE, MyFrame.SIZE*9/2,MyFrame.SIZE,MyFrame.SIZE);
        panel.getMove(1).setIcon(icon);
        icon = new ImageIcon("Images/LEFT.png");
        panel.getMove(2).setBounds(newZero-MyFrame.SIZE, MyFrame.SIZE*7/2,MyFrame.SIZE,MyFrame.SIZE);
        panel.getMove(2).setIcon(icon);
        icon = new ImageIcon("Images/LEFT.png");
        panel.getMove(3).setBounds(newZero-MyFrame.SIZE, MyFrame.SIZE*9/2,MyFrame.SIZE,MyFrame.SIZE);
        panel.getMove(3).setIcon(icon);
        icon = new ImageIcon("Images/UP.png");
        panel.getMove(4).setBounds(newZero, MyFrame.SIZE*3,MyFrame.SIZE,MyFrame.SIZE);
        panel.getMove(4).setIcon(icon);
        icon = new ImageIcon("Images/DOWN.png");
        panel.getMove(5).setBounds(newZero, MyFrame.SIZE*5,MyFrame.SIZE,MyFrame.SIZE);
        panel.getMove(5).setIcon(icon);
        panel.refreshBoard();
        return panel;
    }
}
