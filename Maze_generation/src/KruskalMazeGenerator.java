import java.util.ArrayList;
import java.util.Collections;

public class KruskalMazeGenerator extends AbstractMazeGenerator {
    private MazeDSU dsu;

    public KruskalMazeGenerator(int width, int height, int cntCells) {
        super(width, height, cntCells);
        dsu = new MazeDSU(cntCells);
    }

    protected ArrayList<MazeCell> available(int x, int y) {
        ArrayList<MazeCell> res = new ArrayList<>();
        for (int dx = -1; dx < 2; dx++) {
            for (int dy = -1; dy < 2; dy++) {
                if (dx != 0 && dy != 0) {
                    continue;
                }
                if (dx == 0 && dy == 0) {
                    continue;
                }
                int newX = x + dx;
                int newY = y + dy;
                if (newX < 0 || newX >= cntCells || newY < 0 || newY >= cntCells) {
                    continue;
                }
                if (!dsu.inOneSet(dsu.convert(x, y), dsu.convert(newX, newY))) {
                    res.add(cells.get(newX).get(newY));
                }
            }
        }
        return res;
    }

    public void generate() {
        ArrayList<MazeCell> shuffledCells = new ArrayList<>();
        for (ArrayList<MazeCell> mazeCells : cells) {
            shuffledCells.addAll(mazeCells);
        }
        for (int it = 0; it < 10; it++) {
            Collections.shuffle(shuffledCells);
            for (MazeCell cell : shuffledCells) {
                int x = cell.getX();
                int y = cell.getY();
                ArrayList<MazeCell> availableCells = available(x, y);
                if (availableCells.size() == 0) {
                    continue;
                }
                int randomCellPos = (int) (Math.random() * 10) % availableCells.size();
                int newX = availableCells.get(randomCellPos).getX();
                int newY = availableCells.get(randomCellPos).getY();
                super.destroyWall(x, y, newX, newY);
                dsu.unite(dsu.convert(x, y), dsu.convert(newX, newY));
            }
        }
    }
}
