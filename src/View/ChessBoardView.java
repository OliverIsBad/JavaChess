package View;
import javax.swing.*;

import Model.ChessBoard;

public class ChessBoardView extends JPanel {
    private ChessBoard board;

    public ChessBoardView(ChessBoard board) {
        this.board = board;
        // Setup GUI
    }

    public void updateView() {
        // Redraw board
    }
}
