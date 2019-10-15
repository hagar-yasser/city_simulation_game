package view;

import javax.swing.*;

public class InjuryGif extends JFrame {
    public InjuryGif(){
        super();
        this.setSize(956,537);
        this.setTitle("Citizens are getting injured");
        JPanel panel=new JPanel();
        panel.add(new JLabel(new ImageIcon("injury.gif")));
        this.add(panel);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(false);

    }
}
