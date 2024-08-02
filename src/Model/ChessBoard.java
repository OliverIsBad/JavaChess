package Model;

public class ChessBoard {
    private ChessPiece[][] board;

    public ChessBoard() {
        board = new ChessPiece[8][8];
        initializeBoard();
    }

    private void initializeBoard() {
        // Set up pawns
        for (int col = 0; col < 8; col++) {
            board[col][1] = new ChessPiece("pawn", "white");
            board[col][6] = new ChessPiece("pawn", "black");
        }
        
        // Set up rooks
        board[0][0] = new ChessPiece("rook", "white");
        board[7][0] = new ChessPiece("rook", "white");
        board[0][7] = new ChessPiece("rook", "black");
        board[7][7] = new ChessPiece("rook", "black");

        // Set up knights
        board[1][0] = new ChessPiece("knight", "white");
        board[6][0] = new ChessPiece("knight", "white");
        board[1][7] = new ChessPiece("knight", "black");
        board[6][7] = new ChessPiece("knight", "black");

        // Set up bishops
        board[2][0] = new ChessPiece("bishop", "white");
        board[5][0] = new ChessPiece("bishop", "white");
        board[2][7] = new ChessPiece("bishop", "black");
        board[5][7] = new ChessPiece("bishop", "black");

        // Set up queens
        board[4][0] = new ChessPiece("queen", "white");
        board[4][7] = new ChessPiece("queen", "black");

        // Set up kings
        board[3][0] = new ChessPiece("king", "white");
        board[3][7] = new ChessPiece("king", "black");
    }

    public ChessPiece getPieceAt(int x, int y) {
        return board[x][y];
    }

    public void movePiece(int fromX, int fromY, int toX, int toY) {
        if (isLegalMove(fromX, fromY, toX, toY)) {
            board[toX][toY] = board[fromX][fromY];
            board[fromX][fromY] = null;
        }
    }

    public boolean isLegalMove(int fromX, int fromY, int toX, int toY) {
        ChessPiece piece = board[fromX][fromY];
        if (piece == null) return false;

        // Check if the move is in the list of legal moves for the piece
        for (int[] move : piece.getLegalMoves(fromX, fromY, this)) {
            if (move[0] == toX && move[1] == toY) {
                return true;
            }
        }
        return false;
    }
}
