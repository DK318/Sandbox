public class GameCell {
    private Cell cell;
    private int minesAround;
    private boolean isMarked;
    private boolean isOpened;

    public GameCell() {
        this.cell = Cell.Empty;
        this.minesAround = 0;
        this.isMarked = false;
        this.isOpened = false;
    }

    public void switchMark() {
        isMarked = !isMarked;
    }

    public void open() {
        isOpened = true;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public boolean isMarked() {
        return isMarked;
    }

    public void setMinesAround(int cnt) {
        minesAround = cnt;
    }

    public int getMinesAround() {
        return minesAround;
    }
}
