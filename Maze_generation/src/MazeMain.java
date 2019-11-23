import javax.swing.*;

public class MazeMain {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Maze Generator");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        MazeGenerator generator = new MazeGenerator(frame.getWidth(), frame.getHeight(), 50);
        generator.generate();
        MazeGraphics mazeGraphics = new MazeGraphics(generator.getCells());
        frame.add(mazeGraphics);
        frame.setVisible(true);
    }
}
