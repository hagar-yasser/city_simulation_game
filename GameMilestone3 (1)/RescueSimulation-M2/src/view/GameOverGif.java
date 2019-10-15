package view;

import javax.swing.*;
import java.awt.*;

public class GameOverGif extends JFrame {
    public GameOverGif( int causalities,int rescued){
        super();
        this.setSize(1650,1080);
        this.setTitle("GAME OVER");
        JPanel panel=new JPanel(new BorderLayout());
        panel.add(new JLabel(causalities+" citizens are dead and "+ rescued+" are rescued"),BorderLayout.NORTH);
        panel.add(new JLabel(new ImageIcon("gameOver1.gif")),BorderLayout.CENTER);
        this.add(panel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(false);

    }
}
