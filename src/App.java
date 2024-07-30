import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class App { 
    public static void main(String[] args) throws Exception {
        
        JFrame frame = new JFrame("Java Chess");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,200);

        JPanel panel = new JPanel();
        JLabel label = new JLabel("Hello World!");
        panel.add(label);

        JButton button = new JButton("Click Me");
        panel.add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                label.setText("Button Clicked");
            }
        });

        frame.add(panel);
        frame.setVisible(true);
       
    }
}
