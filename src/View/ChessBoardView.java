package View;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

import Model.ChessBoard;
import Model.ChessPiece;

public class ChessBoardView extends JPanel {
    private static final int TILE_SIZE = 60;
    private ChessBoard board;

    public ChessBoardView(ChessBoard board) {
        this.board = board;
        JButton button = new JButton();

        // Sets preferred size of the panel to 8 x 8
        setPreferredSize(new Dimension(8 * TILE_SIZE, 8 * TILE_SIZE));

        // Mouse Listener to get the x and y coordinates on click
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX() / TILE_SIZE;
                int y = e.getY() / TILE_SIZE;

                handleSquareClick(x,y);
            }
        });
    }

    /*
     * Handle logic for when a square is clicked
     * @author https://github.com/OliverIsBad
     */
    public void handleSquareClick(int x, int y) {
        System.out.println("Square Clicked: " + x + " , " + y);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                boolean isLightSquare = (row + col) % 2 == 0; // Determine color of square
                g.setColor(isLightSquare ? Color.WHITE : Color.GRAY); // Set Color of square
                g.fillRect(col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);

                ChessPiece piece = board.getPieceAt(col, row); // Get piece at the current square
                if (piece != null) {
                    drawPiece(g, piece, col * TILE_SIZE, row * TILE_SIZE);
                }
            }
        }
    }

    private void drawPiece(Graphics g, ChessPiece piece, int x, int y) {
        String pieceText = piece.getType().substring(0, 1).toUpperCase();
        g.setColor(piece.getColor().equals("white") ? Color.BLACK : Color.WHITE);
        g.drawString(pieceText, x + TILE_SIZE / 2, y + TILE_SIZE / 2);
    }

}
