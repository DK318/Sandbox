import javax.swing.*;

public class MazeMain {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Maze Generator");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        StackMazeGenerator generator = new StackMazeGenerator(frame.getWidth(), frame.getHeight(), 25);
        MazeGraphics mazeGraphics = new MazeGraphics(generator.getCells(), generator, frame.getWidth(), frame.getHeight());
        frame.setVisible(true);
        frame.add(mazeGraphics);
        while (true) {
            frame.revalidate();
            frame.repaint();
        }
    }
}
