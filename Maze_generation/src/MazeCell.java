import java.util.ArrayList;
import java.util.List;

public class MazeCell {
    private ArrayList<MazeWall> walls;
    private int x;
    private int y;

    public MazeCell(int x1, int y1, int x2, int y2, int x, int y) {
        this.x = x;
        this.y = y;
        MazeWall up = new MazeWall(x1, y1, x2, y1);
        MazeWall down = new MazeWall(x1, y2, x2, y2);
        MazeWall left = new MazeWall(x1, y1, x1, y2);
        MazeWall right = new MazeWall(x2, y1, x2, y2);
        walls = new ArrayList<>(List.of(up, down, left, right));
    }

    public ArrayList<MazeWall> getWalls() {
        ArrayList<MazeWall> res = new ArrayList<>();
        for (MazeWall wall : walls) {
            if (wall.isVisible()) {
                res.add(wall);
            }
        }
        return res;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setInvisible(int wallIndex) {
        walls.get(wallIndex).setInvisible();
    }
}
