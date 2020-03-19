package tictactoe;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

public class Game extends GridPane {
    private Button[][] buttons;

    public Game() {
        setVgap(10);
        setHgap(10);
        setAlignment(Pos.CENTER);
        buttons = new Button[3][3];
        for (int i = 0; i < 3; i++) {
            RowConstraints rowConstraints = new RowConstraints();
            ColumnConstraints columnConstraints = new ColumnConstraints();
            rowConstraints.setFillHeight(true);
            columnConstraints.setFillWidth(true);
            rowConstraints.setVgrow(Priority.ALWAYS);
            columnConstraints.setHgrow(Priority.ALWAYS);
            getRowConstraints().add(rowConstraints);
            getColumnConstraints().add(columnConstraints);
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new Button(Integer.toString(3 * i + j + 1));
                buttons[i][j].setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                add(buttons[i][j], j, i);
            }
        }
    }
}
