import java.util.HashSet;
import java.util.Set;

public class MinesweeperBoard {
    private GameCell[][] board;

    public MinesweeperBoard() {
        board = new GameCell[MinesweeperConst.boardSize][MinesweeperConst.boardSize];
        for (int i = 0; i < MinesweeperConst.boardSize; i++) {
            for (int j = 0; j < MinesweeperConst.boardSize; j++) {
                board[i][j] = new GameCell();
            }
        }
        int topRange = MinesweeperConst.boardSize * MinesweeperConst.boardSize;
        Set<Integer> mines = new HashSet<>();
        while (mines.size() != MinesweeperConst.minesCount) {
            int num = (int) (Math.random() * 1000) % topRange;
            mines.add(num);
        }
        for (Integer num : mines) {
            int x = num / MinesweeperConst.boardSize;
            int y = num % MinesweeperConst.boardSize;
            board[x][y].setCell(Cell.Mine);
        }
        for (int i = 0; i < MinesweeperConst.boardSize; i++) {
            for (int j = 0; j < MinesweeperConst.boardSize; j++) {
                if (board[i][j].getCell() == Cell.Empty) {
                    int cntMines = 0;
                    for (int dx = -1; dx <= 1; dx++) {
                        for (int dy = -1; dy <= 1; dy++) {
                            if (dx == 0 && dy == 0) {
                                continue;
                            }
                            int newX = i + dx;
                            int newY = j + dy;
                            if (newX < 0 || newX >= MinesweeperConst.boardSize || newY < 0 || newY >= MinesweeperConst.boardSize) {
                                continue;
                            }
                            if (board[newX][newY].getCell() == Cell.Mine) {
                                cntMines++;
                            }
                        }
                    }
                    board[i][j].setMinesAround(cntMines);
                }
            }
        }
    }

    public void openZeros(int x, int y) {
        if (x < 0 || x >= board.length || y < 0 || y >= board.length) {
            return;
        }
        if (board[x][y].isOpened()) {
            return;
        }
        board[x][y].open();
        if (board[x][y].getMinesAround() != 0) {
            return;
        }
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0) {
                    continue;
                }
                openZeros(x + dx, y + dy);
            }
        }
    }

    public boolean makeMove(int x, int y, int type) {
        if (board[x][y].isOpened()) {
            return false;
        }
        if (board[x][y].isMarked() && type == 0) {
            return false;
        }
        if (type == 1) {
            board[x][y].switchMark();
            return true;
        }
        GameCell curCell = board[x][y];
        if (curCell.getCell() != Cell.Mine && curCell.getMinesAround() == 0) {
            openZeros(x, y);
            return true;
        } else {
            board[x][y].open();
            return true;
        }
    }

    public void openAll() {
        for (GameCell[] gameCells : board) {
            for (int j = 0; j < board.length; j++) {
                gameCells[j].open();
            }
        }
    }

    public GameCell getCell(int x, int y) {
        return board[x][y];
    }
}
