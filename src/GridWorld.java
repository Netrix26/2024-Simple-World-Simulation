import javax.swing.*;

public class GridWorld extends World{
    public GridWorld() {
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
    public GridWorld(int x, int y) {
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
        return true;
    }

    @Override
    public boolean isHex(){return false;}

    @Override
    public MyFrame configurePanel()
    {
        MyFrame panel = new MyFrame();
        panel.setWorld(this);
        for(int i = 0; i<Constants.BOARD_WIDTH; i++) {
            for (int j = 0; j < Constants.BOARD_HEIGHT; j++) {
                panel.getCell(i, j).setBounds(i * MyFrame.SIZE,j * MyFrame.SIZE, MyFrame.SIZE, MyFrame.SIZE);
                panel.setWorld(this);
            }
        }
        int newZero = (1+sizeX)*MyFrame.SIZE;
        panel.getLoad().setBounds(newZero, 0,MyFrame.SIZE,MyFrame.SIZE);
        panel.getSave().setBounds(newZero+MyFrame.SIZE, 0,MyFrame.SIZE,MyFrame.SIZE);
        panel.getSelect().setBounds(newZero+2*MyFrame.SIZE, 0,MyFrame.SIZE,MyFrame.SIZE);
        panel.getQuit().setBounds(newZero+3*MyFrame.SIZE, 0,MyFrame.SIZE,MyFrame.SIZE);
        panel.getShield().setBounds(newZero+MyFrame.SIZE*3/2, 8*MyFrame.SIZE,MyFrame.SIZE,MyFrame.SIZE);
        ImageIcon icon = new ImageIcon("Images/UP.png");
        panel.getMove(0).setBounds(newZero+MyFrame.SIZE*3/2, 7*MyFrame.SIZE,MyFrame.SIZE,MyFrame.SIZE);
        panel.getMove(0).setBounds(newZero+MyFrame.SIZE*3/2, 7*MyFrame.SIZE,MyFrame.SIZE,MyFrame.SIZE);
        panel.getMove(0).setIcon(icon);
        icon = new ImageIcon("Images/RIGHT.png");
        panel.getMove(1).setBounds(newZero+MyFrame.SIZE*5/2, 8*MyFrame.SIZE,MyFrame.SIZE,MyFrame.SIZE);
        panel.getMove(1).setBounds(newZero+MyFrame.SIZE*5/2, 8*MyFrame.SIZE,MyFrame.SIZE,MyFrame.SIZE);
        panel.getMove(1).setIcon(icon);
        icon = new ImageIcon("Images/LEFT.png");
        panel.getMove(2).setBounds(newZero+MyFrame.SIZE*1/2, 8*MyFrame.SIZE,MyFrame.SIZE,MyFrame.SIZE);
        panel.getMove(2).setBounds(newZero+MyFrame.SIZE*1/2, 8*MyFrame.SIZE,MyFrame.SIZE,MyFrame.SIZE);
        panel.getMove(2).setIcon(icon);
        icon = new ImageIcon("Images/DOWN.png");
        panel.getMove(3).setBounds(newZero+MyFrame.SIZE*3/2, 9*MyFrame.SIZE,MyFrame.SIZE,MyFrame.SIZE);
        panel.getMove(3).setBounds(newZero+MyFrame.SIZE*3/2, 9*MyFrame.SIZE,MyFrame.SIZE,MyFrame.SIZE);
        panel.getMove(3).setIcon(icon);
        panel.refreshBoard();
        panel.setSize(MyFrame.WIDTH-8*MyFrame.SIZE, MyFrame.HEIGHT-MyFrame.SIZE*5/2);
        return panel;
    }
}
