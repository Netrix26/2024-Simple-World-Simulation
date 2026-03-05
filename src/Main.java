import javax.swing.*;

public class Main
{
    public static void main(String[] args) {
        String boardType;
        World world;
        do {
            boardType = JOptionPane.showInputDialog("Enter board type (grid or hex): ");
        } while (!boardType.equals("grid") && !boardType.equals("hex"));
        if (boardType.equals("hex"))world = new HexWorld(Constants.BOARD_WIDTH, Constants.BOARD_HEIGHT);
        else world = new GridWorld(Constants.BOARD_WIDTH, Constants.BOARD_HEIGHT);
        world.placeOrganisms();

        MyFrame frame = world.configurePanel();
    }
}