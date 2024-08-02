package Controller;

import Model.ChessBoard;
import View.ChessBoardView;

public class ChessController {
    private ChessBoard board;
    private ChessBoardView view;

    public ChessController(ChessBoard board, ChessBoardView view) {
        this.board = board;
        this.view = view;
        // Setup event handling
    }

    public void handleMove(int fromX, int fromY,int toX, int toY) {
        board.movePiece(fromX, fromY, toX, toY);
        
    }

}
