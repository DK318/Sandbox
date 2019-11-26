import java.util.ArrayList;
import java.util.Stack;

public class StackMazeGenerator extends AbstractMazeGenerator {
    private Stack<MazeCell> stack;
    private ArrayList<ArrayList<Boolean>> isUsed;
    private boolean isBuilt;

    public StackMazeGenerator(int width, int height, int cntCells) {
        super(width, height, cntCells);
        stack = new Stack<>();
        stack.push(cells.get(0).get(0));
        isUsed = new ArrayList<>();
        for (int i = 0; i < cntCells; i++) {
            isUsed.add(new ArrayList<>());
            for (int j = 0; j < cntCells; j++) {
                isUsed.get(i).add(false);
            }
        }
        isBuilt = false;
        isUsed.get(0).set(0, true);
    }

    @Override
    public void generate() {
        if (!stack.empty()) {
            MazeCell curCell = stack.peek();
            ArrayList<MazeCell> neighbours = available(curCell.getX(), curCell.getY());
            if (neighbours.size() == 0) {
                stack.pop();
                if (stack.size() == 0) {
                    isBuilt = true;
                }
            } else {
                int randPos = (int) (Math.random() * 10) % neighbours.size();
                MazeCell randCell = neighbours.get(randPos);
                destroyWall(curCell.getX(), curCell.getY(), randCell.getX(), randCell.getY());
                curCell = randCell;
                stack.push(curCell);
                isUsed.get(curCell.getX()).set(curCell.getY(), true);
            }
        }
    }

    @Override
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
                int newX = x + dx, newY = y + dy;
                if (newX < 0 || newX >= cntCells || newY < 0 || newY >= cntCells) {
                    continue;
                }
                if (!isUsed.get(newX).get(newY)) {
                    res.add(cells.get(newX).get(newY));
                }
            }
        }
        return res;
    }

    public boolean used(int x, int y) {
        return isUsed.get(x).get(y);
    }

    public MazeCell current() {
        return stack.peek();
    }

    public boolean isBuilt() {
        return isBuilt;
    }
}
