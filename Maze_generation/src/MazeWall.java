import java.util.List;
import java.util.Vector;

public class MazeWall {
    private Vector<Integer> start;
    private Vector<Integer> end;
    private boolean visible;

    public MazeWall(int x1, int y1, int x2, int y2) {
        start = new Vector<>(List.of(x1, y1));
        end = new Vector<>(List.of(x2, y2));
        visible = true;
    }

    public boolean isVisible() {
        return visible;
    }

    public Vector<Integer> getStart() {
        return start;
    }

    public Vector<Integer> getEnd() {
        return end;
    }

    public void setInvisible() {
        visible = false;
    }
}
