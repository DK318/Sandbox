import java.util.ArrayList;

public abstract class AbstractMazeGenerator {
    protected ArrayList<ArrayList<MazeCell>> cells;
    protected int cntCells;

    public AbstractMazeGenerator(int width, int height, int cntCells) {
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
    }

    public abstract void generate();

    protected abstract ArrayList<MazeCell> available(int x, int y);

    protected void destroyWall(int x, int y, int newX, int newY) {
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

    public ArrayList<ArrayList<MazeCell>> getCells() {
        return cells;
    }
}
