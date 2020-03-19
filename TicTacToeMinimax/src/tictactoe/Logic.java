package tictactoe;

public class Logic {
    private boolean turn;
    private String[][] table;

    public Logic() {
        turn = (int) (Math.random() * 2) % 2 == 1;
        table = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                table[i][j] = "";
            }
        }
    }

    public boolean move(int x, int y, String player) {
        if ((player.equals("X") && !turn) || (player.equals("O") && turn)) {
            return false;
        }
        if (!table[x][y].equals("")) {
            return false;
        }
        table[x][y] = player;
        turn = !turn;
        return true;
    }

    public boolean checkWin(int x, int y, String player) {

    }
}
