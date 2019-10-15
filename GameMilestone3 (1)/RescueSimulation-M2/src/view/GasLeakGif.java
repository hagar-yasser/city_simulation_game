package view;

import javax.swing.*;

public class GasLeakGif extends JFrame {
    public GasLeakGif(){
        super();
        this.setSize(956,537);
        this.setTitle("Gas is leaking in a building or more");
        JPanel panel=new JPanel();
        panel.add(new JLabel(new ImageIcon("gasLeak.gif")));
        this.add(panel);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(false);

    }
}
