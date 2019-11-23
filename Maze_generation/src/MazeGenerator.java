import java.util.ArrayList;
import java.util.Collections;

public class MazeGenerator {
    private ArrayList<ArrayList<MazeCell>> cells;
    private MazeDSU dsu;
    private int width;
    private int height;
    private int cntCells;

    public MazeGenerator(int width, int height, int cntCells) {
        this.width = width;
        this.height = height;
        this.cntCells = cntCells;
        cells = new ArrayList<>();
        int dx = width / cntCells;
        int dy = height / cntCells;
        for (int x = 0; x < width; x += dx) {
            int i = x / dx;
            cells.add(new ArrayList<>());
            for (int y = 0; y < height; y += dy) {
                int j = y / dy;
                cells.get(i).add(new MazeCell(x, y, x + dx, y + dy, i, j));
            }
        }
        dsu = new MazeDSU(cntCells);
    }

    private ArrayList<MazeCell> available(int x, int y) {
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

    private void destroyWall(int x, int y, int newX, int newY) {
        int dir;
        if (x - newX == 0) {
            if (y - newY == 1) {
                dir = 0;
            } else {
                dir = 1;
            }
        } else {
            if (x - newX == 1) {
                dir = 2;
            } else {
                dir = 3;
            }
        }
        cells.get(x).get(y).setInvisible(dir);
        switch (dir) {
            case 0 : dir = 1; break;
            case 1 : dir = 0; break;
            case 2 : dir = 3; break;
            case 3 : dir = 2; break;
        }
        cells.get(newX).get(newY).setInvisible(dir);
    }

    public void generate() {
        ArrayList<MazeCell> shuffledCells = new ArrayList<>();
        for (int i = 0; i < cells.size(); i++) {
            for (int j = 0; j < cells.get(i).size(); j++) {
                shuffledCells.add(cells.get(i).get(j));
            }
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
                destroyWall(x, y, newX, newY);
                dsu.unite(dsu.convert(x, y), dsu.convert(newX, newY));
            }
        }
    }

    public ArrayList<ArrayList<MazeCell>> getCells() {
        return cells;
    }
}
