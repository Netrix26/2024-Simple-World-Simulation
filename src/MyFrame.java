import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class MyFrame extends JFrame implements ActionListener {
    public static final int SIZE = 40;
    public static final int WIDTH = 1374;
    public static final int HEIGHT = 736;
    private JButton[][] cell = new JButton[Constants.BOARD_WIDTH][Constants.BOARD_HEIGHT];

    private JButton[] move = new JButton[6];
    private JButton save;
    private JButton load;
    private JButton shield;
    private JButton quit;
    private JButton select;

    private Organisms selected;

    private int tourCounter;

    private World world;

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public MyFrame()
    {
        setTitle("Szymon Wisniewski 198280");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setResizable(false);
        setLayout(null);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        selected = Organisms.GRASS;

        save = new JButton();
        load = new JButton();
        shield = new JButton();
        quit = new JButton();
        select = new JButton();

        ImageIcon image = new ImageIcon("Images/SAVE.png");
        save.setBounds(WIDTH-SIZE,HEIGHT-SIZE,SIZE,SIZE);
        save.setIcon(image);
        save.setFocusable(false);
        save.setBorder(BorderFactory.createLineBorder(Color.darkGray, 2));
        save.addActionListener(this);
        add(save);

        image = new ImageIcon("Images/LOAD.png");
        load.setBounds(WIDTH-SIZE,0,SIZE,SIZE);
        load.setIcon(image);
        load.setFocusable(false);
        load.setBorder(BorderFactory.createLineBorder(Color.darkGray, 2));
        load.addActionListener(this);
        add(load);

        image = new ImageIcon("Images/Shield.png");
        shield.setIcon(image);
        shield.setFocusable(false);
        shield.setBorder(BorderFactory.createLineBorder(Color.darkGray, 2));
        shield.addActionListener(this);
        add(shield);

        image = new ImageIcon("Images/QUIT.png");
        quit.setIcon(image);
        quit.setFocusable(false);
        quit.setBorder(BorderFactory.createLineBorder(Color.darkGray, 2));
        quit.addActionListener(this);
        add(quit);


        select.setBounds(WIDTH-SIZE,0,SIZE,SIZE);
        select.setFocusable(false);
        select.setBorder(BorderFactory.createLineBorder(Color.darkGray, 2));
        select.addActionListener(this);
        add(select);

        for(int i = 0; i<6; i++)
        {
            move[i]=new JButton();
            move[i].setFocusable(false);
            move[i].setBorder(BorderFactory.createLineBorder(Color.darkGray, 2));
            move[i].addActionListener(this);
            add(move[i]);
        }
        for(int i = 0; i<Constants.BOARD_WIDTH; i++)
        {
            for(int j = 0; j<Constants.BOARD_HEIGHT; j++)
            {

                cell[i][j] = new JButton();
                cell[i][j].setBackground(Color.white);
                cell[i][j].setFocusable(false);
                cell[i][j].setBorder(BorderFactory.createLineBorder(Color.darkGray, 2));
                cell[i][j].addActionListener(this);
                add(cell[i][j]);
            }
        }
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==save)world.save();
        else if(e.getSource()==load)world.load();
        else if(e.getSource()==quit)dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        else if(e.getSource()==shield)
        {
            world.setKey(Key.ABILITY);
            world.makeTurn();
        }
        else if(e.getSource()==move[0])
        {
            world.setKey(Key.UP);
            world.makeTurn();
        }
        else if(e.getSource()==move[1])
        {
            world.setKey(Key.RIGHT);
            world.makeTurn();
        }
        else if(e.getSource()==move[2])
        {
            world.setKey(Key.LEFT);
            world.makeTurn();
        }
        else if(e.getSource()==move[3])
        {
            world.setKey(Key.DOWN);
            world.makeTurn();
        }
        else if(e.getSource()==move[4])
        {
            world.setKey(Key.TOP_LEFT);
            world.makeTurn();
        }
        else if(e.getSource()==move[5])
        {
            world.setKey(Key.BOT_RIGHT);
            world.makeTurn();
        }
        else if(e.getSource()==select)
        {
            cycle();
        }
        for(int i = 0; i<Constants.BOARD_WIDTH; i++) {
            for (int j = 0; j < Constants.BOARD_HEIGHT; j++) {
                if (e.getSource()==cell[i][j] && world.getCell(i, j) == null) {
                    world.setCell(World.createOfType(selected), i, j);
                }
            }
        }
        refreshBoard();
    }

    public JButton getCell(int x, int y) {
        if(x<0 || y<0 || x>=Constants.BOARD_WIDTH || y >=Constants.BOARD_HEIGHT)return null;
        return cell[x][y];
    }

    public JButton getMove(int i) {
        if(i<0  || i>=6 )return null;
        return move[i];
    }

    public void refreshBoard()
    {
        Organism organism = World.createOfType(selected);
        ImageIcon image = new ImageIcon(organism.imagePath());
        select.setIcon(image);
        for(int i = 0; i<Constants.BOARD_WIDTH; i++)
        {
            for(int j = 0; j<Constants.BOARD_HEIGHT; j++)
            {
                if(world.getCell(i,j)==null)
                {
                    cell[i][j].setIcon(null);
                }
                else
                {
                    cell[i][j].setIcon(new ImageIcon(world.getCell(i,j).imagePath()));
                }
            }
        }
    }

    public JButton getSave() {
        return save;
    }

    public JButton getLoad() {
        return load;
    }

    public JButton getShield() {
        return shield;
    }

    public JButton getQuit() {
        return quit;
    }

    public JButton getSelect() {
        return select;
    }

    public void cycle()
    {
        Organisms temp = selected;
        if( selected==Organisms.GRASS) temp = Organisms.SOW_THISTLE;
        else if( selected==Organisms.SOW_THISTLE) temp = Organisms.GUARANA;
        else if( selected==Organisms.GUARANA) temp = Organisms.BALLADONNA;
        else if( selected==Organisms.BALLADONNA) temp = Organisms.HOGWEED;
        else if( selected==Organisms.HOGWEED) temp = Organisms.SHEEP;
        else if( selected==Organisms.SHEEP) temp = Organisms.WOLF;
        else if( selected==Organisms.WOLF) temp = Organisms.FOX;
        else if( selected==Organisms.FOX) temp = Organisms.ANTELOPE;
        else if( selected==Organisms.ANTELOPE) temp = Organisms.TURTLE;
        else if( selected==Organisms.TURTLE) temp = Organisms.HUMAN;
        else if( selected==Organisms.HUMAN) temp = Organisms.GRASS;
        selected = temp;
    }
}
