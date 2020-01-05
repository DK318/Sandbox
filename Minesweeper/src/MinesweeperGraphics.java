import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class MinesweeperGraphics {
    private JFrame window;
    private MinesweeperBoard board;
    private ArrayList<ArrayList<JButton>> buttons;

    public MinesweeperGraphics(JFrame window) {
        this.window = window;
        board = new MinesweeperBoard();
        buttons = new ArrayList<>();
        for (int i = 0; i < MinesweeperConst.boardSize; i++) {
            buttons.add(new ArrayList<>());
            for (int j = 0; j < MinesweeperConst.boardSize; j++) {
                JButton button = new JButton();
                int finalI = i;
                int finalJ = j;
                button.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent mouseEvent) {
                        int type = 0;
                        if (mouseEvent.getButton() == MouseEvent.BUTTON3) {
                            type = 1;
                        }
                        buttonPressed(finalI, finalJ, type);
                    }

                    @Override
                    public void mousePressed(MouseEvent mouseEvent) {

                    }

                    @Override
                    public void mouseReleased(MouseEvent mouseEvent) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent mouseEvent) {

                    }

                    @Override
                    public void mouseExited(MouseEvent mouseEvent) {

                    }
                });
                buttons.get(i).add(button);
            }
        }
        fillWindow();
    }

    private void fillWindow() {
        for (int i = 0; i < buttons.size(); i++) {
            for (int j = 0; j < buttons.size(); j++) {
                window.add(buttons.get(i).get(j));
            }
        }
    }

    private void clearWindow() {
        for (int i = 0; i < buttons.size(); i++) {
            for (int j = 0; j < buttons.size(); j++) {
                window.remove(buttons.get(i).get(j));
            }
        }
    }

    private void resetWindow() {
        clearWindow();
        fillWindow();
        window.revalidate();
        window.repaint();
    }

    private void updateButtons() {
        for (int i = 0; i < MinesweeperConst.boardSize; i++) {
            for (int j = 0; j < MinesweeperConst.boardSize; j++) {
                GameCell curCell = board.getCell(i, j);
                JButton button = buttons.get(i).get(j);
                int minesAround = curCell.getMinesAround();
                String str = "";
                if (curCell.getCell() == Cell.Mine) {
                    str = "M";
                }
                if (minesAround != 0) {
                    str = Integer.toString(minesAround);
                }
                if (curCell.isOpened()) {
                    button.setText(str);
                    button.setEnabled(false);
                } else if (curCell.isMarked()) {
                    button.setText("?");
                    button.setEnabled(true);
                } else {
                    button.setText("");
                    button.setEnabled(true);
                }
                buttons.get(i).set(j, button);
            }
        }
    }

    private void agree(String title, String labelText) {
        updateButtons();
        resetWindow();
        JFrame dialog = new JFrame(title);
        dialog.setSize(500, 250);
        dialog.setLocationRelativeTo(window);
        dialog.setVisible(true);
        dialog.setLayout(new GridLayout(2, 1));
        dialog.setResizable(false);
        dialog.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        JLabel label = new JLabel(labelText);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);
        JPanel panel = new JPanel();
        JButton yesButton = new JButton("Yes");
        yesButton.addActionListener(actionEvent -> {
            board = new MinesweeperBoard();
            updateButtons();
            resetWindow();
            dialog.setVisible(false);
        });
        JButton noButton = new JButton("No");
        noButton.addActionListener(actionEvent -> System.exit(0));
        panel.add(yesButton);
        panel.add(noButton);
        dialog.add(label);
        dialog.add(panel);
    }

    private void buttonPressed(int x, int y, int type) {
        updateButtons();
        resetWindow();
        if (board.makeMove(x, y, type)) {
            GameCell curCell = board.getCell(x, y);
            if (curCell.getCell() == Cell.Mine && type == 0) {
                board.openAll();
                agree("Lose", "You lose. Would you like to play again?");
                return;
            }
            int countClosed = 0;
            for (int i = 0; i < MinesweeperConst.boardSize; i++) {
                for (int j = 0; j < MinesweeperConst.boardSize; j++) {
                    if (!board.getCell(i, j).isOpened()) {
                        countClosed++;
                    }
                }
            }
            if (countClosed == MinesweeperConst.minesCount) {
                agree("Win", "Congratulations. Would you like to play again?");
                return;
            }
        }
        updateButtons();
        resetWindow();
    }
}
