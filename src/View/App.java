package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import com.formdev.flatlaf.FlatDarculaLaf;

import Model.ChessBoard;

public class App { 
    public static void main(String[] args) throws Exception {
        // Apply FlatLaf look and feel
        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create and show the GUI
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Java Chess");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(480, 480);

            ChessBoard board = new ChessBoard();
            ChessBoardView boardView = new ChessBoardView(board);
            frame.add(boardView);

            frame.pack();
            frame.setVisible(true);
        });
    }
}
