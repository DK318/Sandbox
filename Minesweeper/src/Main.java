import javax.swing.*;
import java.awt.*;

public class Main {
    private static void startGame() {
        JFrame window = new JFrame("Minesweeper");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(600, 600);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setLayout(new GridLayout(MinesweeperConst.boardSize, MinesweeperConst.boardSize));
        MinesweeperGraphics graphics = new MinesweeperGraphics(window);
        window.setVisible(true);
    }

    private static void errorMsg() {
        JFrame errorWindow = new JFrame("Error");
        errorWindow.setSize(250, 125);
        errorWindow.setResizable(false);
        errorWindow.setLocationRelativeTo(null);
        errorWindow.setLayout(new GridLayout(2, 1));
        errorWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        JLabel errorLabel = new JLabel("Bad input");
        errorLabel.setHorizontalTextPosition(JLabel.CENTER);
        errorLabel.setHorizontalAlignment(JLabel.CENTER);
        errorWindow.add(errorLabel);
        JButton okButton = new JButton("OK");
        okButton.addActionListener(actionEvent -> System.exit(0));
        errorWindow.add(okButton);
        errorWindow.setVisible(true);
    }

    public static void main(String[] args) {
        JFrame helloWindow = new JFrame("Hello");
        helloWindow.setSize(500, 250);
        helloWindow.setResizable(false);
        helloWindow.setLocationRelativeTo(null);
        helloWindow.setLayout(new GridLayout(6, 1));
        helloWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel greetLabel = new JLabel("Left click - open, right click - mark");
        greetLabel.setHorizontalAlignment(JLabel.CENTER);
        greetLabel.setHorizontalTextPosition(JLabel.CENTER);
        helloWindow.add(greetLabel);
        JLabel boardLabel = new JLabel("Board size");
        boardLabel.setHorizontalTextPosition(JLabel.LEFT);
        boardLabel.setHorizontalAlignment(JLabel.LEFT);
        helloWindow.add(boardLabel);
        JTextField boardField = new JTextField();
        helloWindow.add(boardField);
        JLabel minesLabel = new JLabel("Mines count");
        minesLabel.setHorizontalAlignment(JLabel.LEFT);
        minesLabel.setHorizontalTextPosition(JLabel.LEFT);
        helloWindow.add(minesLabel);
        JTextField minesField = new JTextField();
        helloWindow.add(minesField);
        JButton okButton = new JButton("OK");
        okButton.addActionListener(actionEvent -> {
            helloWindow.setVisible(false);
            String boardText = boardField.getText();
            String minesText = minesField.getText();
            try {
                MinesweeperConst.boardSize = Integer.parseInt(boardText);
                MinesweeperConst.minesCount = Integer.parseInt(minesText);
            } catch (NumberFormatException e) {
                errorMsg();
                return;
            }
            if (MinesweeperConst.minesCount > MinesweeperConst.boardSize * MinesweeperConst.boardSize) {
                errorMsg();
                return;
            }
            if (MinesweeperConst.minesCount <= 0 || MinesweeperConst.boardSize <= 0) {
                errorMsg();
                return;
            }
            startGame();
        });
        helloWindow.add(okButton);
        helloWindow.setVisible(true);
    }
}
