import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

public class MazeGraphics extends JComponent {
    private ArrayList<ArrayList<MazeCell>> cells;

    public MazeGraphics(ArrayList<ArrayList<MazeCell>> cells) {
        this.cells = cells;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(3.5f));
        for (ArrayList<MazeCell> cellArrayList : cells) {
            for (MazeCell cell : cellArrayList) {
                ArrayList<MazeWall> walls = cell.getWalls();
                for (MazeWall wall : walls) {
                    Vector<Integer> start = wall.getStart();
                    Vector<Integer> end = wall.getEnd();
                    g2d.drawLine(start.get(0), start.get(1), end.get(0), end.get(1));
                }
            }
        }
    }
}
