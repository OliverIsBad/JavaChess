package Model;

public class ChessBoard {
    private ChessPiece[][] board;

    public ChessBoard() {
        board = new ChessPiece[8][8];
    }

    public ChessPiece getPieceAt(int x, int y) {
        return board[x][y];
    }

    public void movePiece(int fromX, int fromY, int toX, int toY) {
        // Move Logic
    }

    // Additional methods for board logic
}
