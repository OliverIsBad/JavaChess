package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a chess piece.
 * 
 * Each chess piece has a type (pawn, rook, knight, bishop, queen, king) and a color (white or black).
 * 
 * The ChessPiece class provides methods to retrieve the type, color, and image file name of the piece.
 * It also provides a method to get the legal moves for the piece on a given chess board.
 * 
 * @author https://github.com/OliverIsBad
 */
public class ChessPiece {
    private String type; // pawn, rook, knight ...
    private final String color; // white or black

    private boolean moved = false; // Whether the piece has moved

    public ChessPiece(String type, String color) {
        this.type = type;
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public String getColor() {
        return color;
    }

    public boolean hasMoved() {
        return moved;
    }
    
    public String getImageFileName() {
        return color + "-" + type + ".png";
    }

    /**
     * Get the legal moves for the piece
     * @param x x coordinate of the piece
     * @param y y coordinate of the piece
     * @param board The chess board
     * @return List of legal moves for the piece
     * @author https://github.com/OliverIsBad
     */
    public List<int[]> getLegalMoves(int x, int y, ChessBoard board) {
        List<int[]> legalMoves = new ArrayList<>();
        // Logic to get valid moves for the piece
        switch (type) {
            case "pawn":
                legalMoves = getPawnMoves(x, y, board);
                break;
            case "rook":
                legalMoves = getRookMoves(x, y, board);
                break;
            case "knight":
                legalMoves = getKnightMoves(x, y, board);
                break;
            case "bishop":
                legalMoves = getBishopMoves(x, y, board);
                break;
            case "queen":
                legalMoves = getQueenMoves(x, y, board);
                break;
            case "king":
                legalMoves = getKingMoves(x, y, board);
                break;
        }
        return legalMoves;
    }

    /**
     * Get the legal moves for a pawn piece
     * @param x x coordinate of the piece
     * @param y y coordinate of the piece
     * @param board The chess board
     * @return List of legal moves for the pawn piece
     * @author https://github.com/OliverIsBad
     */
    private List<int[]> getPawnMoves(int x, int y, ChessBoard board) {
        List<int[]> moves = new ArrayList<>();
        // Pawn moves in the direction of the color
        int direction = color.equals("white") ? 1 : -1;
        int startRow = color.equals("white") ? 1 : 6;

        // Standard one-step move
        if (board.getPieceAt(x, y + direction) == null) {
            moves.add(new int[]{x, y + direction});
            // Two-step move from starting position
            if (y == startRow && board.getPieceAt(x, y + 2 * direction) == null) {
                moves.add(new int[]{x, y + 2 * direction});
            }
        }

        // Captures
        if (x > 0 && board.getPieceAt(x - 1, y + direction) != null 
            && !board.getPieceAt(x - 1, y + direction).getColor().equals(this.color)) {
            moves.add(new int[]{x - 1, y + direction});
        }
        if (x < 7 && board.getPieceAt(x + 1, y + direction) != null 
            && !board.getPieceAt(x + 1, y + direction).getColor().equals(this.color)) {
            moves.add(new int[]{x + 1, y + direction});
        }

        return moves;
    }

    /**
     * Get the legal moves for a knight piece
     * @param x x coordinate of the piece
     * @param y y coordinate of the piece
     * @param board The chess board
     * @return List of legal moves for the knight piece
     * @author https://github.com/OliverIsBad
     */
    private List<int[]> getKnightMoves(int x, int y, ChessBoard board) {
        List<int[]> moves = new ArrayList<>();

        int[][] possibleMoves = {
            {x + 1, y + 2}, {x + 2, y + 1}, {x + 2, y - 1}, {x + 1, y - 2},
            {x - 1, y - 2}, {x - 2, y - 1}, {x - 2, y + 1}, {x - 1, y + 2}
        };

        for (int[] move : possibleMoves) {
            if (move[0] >= 0 && move[0] <= 7 && move[1] >= 0 && move[1] <= 7) {
                ChessPiece piece = board.getPieceAt(move[0], move[1]);
                if (piece == null || !piece.getColor().equals(this.color)) {
                    moves.add(move);
                }
            }
        }
        return moves;
    }

    /**
     * Get the legal moves for a king piece
     * @param x x coordinate of the piece
     * @param y y coordinate of the piece
     * @param board The chess board     
     * @return List of legal moves for the king piece
     * @author https://github.com/OliverIsBad
     */
    private List<int[]> getKingMoves(int x, int y, ChessBoard board) {
        List<int[]> moves = new ArrayList<>();
        int[][] directions = {
            {1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}
        };
    
        for (int[] dir : directions) {
            int newX = x + dir[0];
            int newY = y + dir[1];
    
            if (isValidSquare(newX, newY) && (board.getPieceAt(newX, newY) == null || 
                !board.getPieceAt(newX, newY).getColor().equals(this.color))) {
                moves.add(new int[]{newX, newY});
            }
        }
    
        // Castling
        if (!this.hasMoved()) {
            // King side castling
            if (board.getPieceAt(x, y).getColor().equals("white") && x == 4 && y == 0) {
                if (isCastlingPossible(board, x, y, 7, 0, new int[][]{{5, 0}, {6, 0}})) {
                    moves.add(new int[]{6, 0});
                }
                if (isCastlingPossible(board, x, y, 0, 0, new int[][]{{3, 0}, {2, 0}, {1, 0}})) {
                    moves.add(new int[]{2, 0});
                }
            } else if (board.getPieceAt(x, y).getColor().equals("black") && x == 4 && y == 7) {
                if (isCastlingPossible(board, x, y, 7, 7, new int[][]{{5, 7}, {6, 7}})) {
                    moves.add(new int[]{6, 7});
                }
                if (isCastlingPossible(board, x, y, 0, 7, new int[][]{{3, 7}, {2, 7}, {1, 7}})) {
                    moves.add(new int[]{2, 7});
                }
            }
        }
    
        return moves;
    }
    
    private boolean isCastlingPossible(ChessBoard board, int kingX, int kingY, int rookX, int rookY, int[][] path) {
        ChessPiece rook = board.getPieceAt(rookX, rookY);
        if (rook == null || !rook.getType().equals("rook") || rook.hasMoved()) {
            return false;
        }
        for (int[] square : path) {
            if (board.getPieceAt(square[0], square[1]) != null || isSquareUnderAttack(square[0], square[1], board, this.color)) {
                return false;
            }
        }
        return true;
    }
    
    private boolean isValidSquare(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }
    
    private boolean isSquareUnderAttack(int x, int y, ChessBoard board, String color) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                ChessPiece piece = board.getPieceAt(col, row);
                if (piece != null && !piece.getColor().equals(color)) {
                    List<int[]> opponentMoves = piece.getLegalMoves(col, row, board);
                    for (int[] move : opponentMoves) {
                        if (move[0] == x && move[1] == y) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    

    /**
     * Get the legal moves for a rook piece
     * @param x x coordinate of the piece
     * @param y y coordinate of the piece
     * @param board The chess board
     * @return List of legal moves for the rook piece
     * @author https://github.com/OliverIsBad
     */
    private List<int[]> getRookMoves(int x, int y, ChessBoard board) {
        List<int[]> moves = new ArrayList<>();

        // Check moves in the x direction
        for (int i = x + 1; i < 8; i++) {
            ChessPiece piece = board.getPieceAt(i, y);
            if (piece == null) {
                moves.add(new int[]{i, y});
            } else {
                if (!piece.getColor().equals(this.color)) {
                    moves.add(new int[]{i, y});
                }
                break;
            }
        }

        // Check moves in the -x direction
        for (int i = x - 1; i >= 0; i--) {
            ChessPiece piece = board.getPieceAt(i, y);
            if (piece == null) {
                moves.add(new int[]{i, y});
            } else {
                if (!piece.getColor().equals(this.color)) {
                    moves.add(new int[]{i, y});
                }
                break;
            }
        }

        // Check moves in the y direction
        for (int i = y + 1; i < 8; i++) {
            ChessPiece piece = board.getPieceAt(x, i);
            if (piece == null) {
                moves.add(new int[]{x, i});
            } else {
                if (!piece.getColor().equals(this.color)) {
                    moves.add(new int[]{x, i});
                }
                break;
            }
        }

        // Check moves in the -y direction
        for (int i = y - 1; i >= 0; i--) {
            ChessPiece piece = board.getPieceAt(x, i);
            if (piece == null) {
                moves.add(new int[]{x, i});
            } else {
                if (!piece.getColor().equals(this.color)) {
                    moves.add(new int[]{x, i});
                }
                break;
            }
        }
        return moves;
    }

    /**
     * Get the legal moves for a bishop piece
     * @param x x coordinate of the piece
     * @param y y coordinate of the piece
     * @param board The chess board
     * @return List of legal moves for the bishop piece
     * @author https://github.com/OliverIsBad
     */
    private List<int[]> getBishopMoves(int x, int y, ChessBoard board) {
        List<int[]> moves = new ArrayList<>();

        // Check moves in the +x, +y direction
        for (int i = 1; x + i < 8 && y + i < 8; i++) {
            ChessPiece piece = board.getPieceAt(x + i, y + i);
            if (piece == null) {
                moves.add(new int[]{x + i, y + i});
            } else {
                if (!piece.getColor().equals(this.color)) {
                    moves.add(new int[]{x + i, y + i});
                }
                break;
            }
        }

        // Check moves in the -x, +y direction
        for (int i = 1; x - i >= 0 && y + i < 8; i++) {
            ChessPiece piece = board.getPieceAt(x - i, y + i);
            if (piece == null) {
                moves.add(new int[]{x - i, y + i});
            } else {
                if (!piece.getColor().equals(this.color)) {
                    moves.add(new int[]{x - i, y + i});
                }
                break;
            }
        }

        // Check moves in the +x, -y direction
        for (int i = 1; x + i < 8 && y - i >= 0; i++) {
            ChessPiece piece = board.getPieceAt(x + i, y - i);
            if (piece == null) {
                moves.add(new int[]{x + i, y - i});
            } else {
                if (!piece.getColor().equals(this.color)) {
                    moves.add(new int[]{x + i, y - i});
                }
                break;
            }
        }

        // Check moves in the -x, -y direction
        for (int i = 1; x - i >= 0 && y - i >= 0; i++) {
            ChessPiece piece = board.getPieceAt(x - i, y - i);
            if (piece == null) {
                moves.add(new int[]{x - i, y - i});
            } else {
                if (!piece.getColor().equals(this.color)) {
                    moves.add(new int[]{x - i, y - i});
                }
                break;
            }
        }
        return moves;
    }

    /**
     * Get the legal moves for a queen piece
     * @param x x coordinate of the piece
     * @param y y coordinate of the piece
     * @param board The chess board
     * @return List of legal moves for the queen piece
     * @author https://github.com/OliverIsBad
     */
    private List<int[]> getQueenMoves(int x, int y, ChessBoard board) {
        List<int[]> moves = new ArrayList<>();

        // Get rook moves
        moves.addAll(getRookMoves(x, y, board));

        // Get bishop moves
        moves.addAll(getBishopMoves(x, y, board));

        return moves;
    }
}
