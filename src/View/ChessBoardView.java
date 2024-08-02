package View;
import Model.ChessBoard;
import Model.ChessPiece;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.*;

public class ChessBoardView extends JPanel {
    private static final int TILE_SIZE = 60;
    private ChessBoard board;
    private Map<String, Image> pieceImages;
    private int selectedX = -1;
    private int selectedY = -1;

    public ChessBoardView(ChessBoard board) {
        this.board = board;
        this.pieceImages = loadPieceImages();

        // Sets preferred size of the panel to 8 x 8
        setPreferredSize(new Dimension(8 * TILE_SIZE, 8 * TILE_SIZE));

        // Mouse Listener to get the x and y coordinates on click
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX() / TILE_SIZE;
                int y = e.getY() / TILE_SIZE;

                handleSquareClick(x, y);
            }
        });
    }

    /**
     * Load the images of the pieces
     * @return Map of piece images with the key being the piece name 
     * and the value being the image of the piece
     * @author https://github.com/OliverIsBad
     */
    private Map<String, Image> loadPieceImages() {
        Map<String, Image> images = new HashMap<>();
        String[] pieceNames = {"pawn", "rook", "knight", "bishop", "queen", "king"};
        String[] colors = {"white", "black"};

        for (String color : colors) {
            for (String piece : pieceNames) {
                String fileName = color + "-" + piece + ".png";
                try {
                    Image img = ImageIO.read(new File("assets/" + fileName));
                    images.put(fileName, img);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return images;
    }

    /**
     * Handle logic for when a square is clicked
     * @param x x coordinates
     * @param y y coordinates
     * @author https://github.com/OliverIsBad
     */
    public void handleSquareClick(int x, int y) {
        if (selectedX == -1 && selectedY == -1) {
            // No piece is selected yet
            if (board.getPieceAt(x, y) != null) {
                selectedX = x;
                selectedY = y;
                System.out.println("Piece selected at: " + x + " , " + y);
            }
        } else {
            // Move the selected piece to the new square
            board.movePiece(selectedX, selectedY, x, y);
            selectedX = -1;
            selectedY = -1;
            repaint();
        }
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
                    drawPiece(g, piece, col * TILE_SIZE, row * TILE_SIZE); // if a piece is present call drawPiece
                }
            }
        }

        // Highlight the selected square
        if (selectedX != -1 && selectedY != -1) {
            g.setColor(Color.RED);
            g.drawRect(selectedX * TILE_SIZE, selectedY * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            g.drawRect(selectedX * TILE_SIZE + 1, selectedY * TILE_SIZE + 1, TILE_SIZE - 2, TILE_SIZE - 2);
        }
    }

    /**
     * Draws the piece on the board
     * @param g Graphics component
     * @param piece Chess piece
     * @param x x coordinates
     * @param y y coordinates
     * @author https://github.com/OliverIsBad
     */
    private void drawPiece(Graphics g, ChessPiece piece, int x, int y) {
        String imageFileName = piece.getImageFileName();
        Image image = pieceImages.get(imageFileName);
        if (image != null) {
            g.drawImage(image, x, y, TILE_SIZE, TILE_SIZE, this);
        }
        else System.out.println("Image not found for piece: " + piece);
    }
}
