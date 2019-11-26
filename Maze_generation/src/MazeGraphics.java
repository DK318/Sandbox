import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

public class MazeGraphics extends JComponent {
    private ArrayList<ArrayList<MazeCell>> cells;
    private StackMazeGenerator generator;
    private int width;
    private int height;

    public MazeGraphics(ArrayList<ArrayList<MazeCell>> cells, StackMazeGenerator generator, int width, int height) {
        this.cells = cells;
        this.generator = generator;
        this.width = width;
        this.height = height;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(3.5f));
        cells = generator.getCells();
        //System.out.println(x + " " + y + " " + dx + " " + dy);
        for (ArrayList<MazeCell> cellArrayList : cells) {
            for (MazeCell cell : cellArrayList) {
                int x = cell.getX();
                int y = cell.getY();
                int dx = width / cells.size();
                int dy = height / cells.size();
                if (generator.used(x, y)) {
                    g2d.setColor(Color.MAGENTA);
                    g2d.fillRect(x * dx, y * dy, dx, dy);
                    g2d.setColor(Color.BLACK);
                }
                if (!generator.isBuilt()) {
                    MazeCell cur = generator.current();
                    x = cur.getX() * dx;
                    y = cur.getY() * dy;
                } else {
                    x = 0;
                    y = 0;
                }
                g2d.setColor(Color.GREEN);
                g2d.fillRect(x, y, dx, dy);
                g2d.setColor(Color.BLACK);
                ArrayList<MazeWall> walls = cell.getWalls();
                for (MazeWall wall : walls) {
                    Vector<Integer> start = wall.getStart();
                    Vector<Integer> end = wall.getEnd();
                    g2d.drawLine(start.get(0), start.get(1), end.get(0), end.get(1));
                }
            }
        }
        generator.generate();
    }
}
