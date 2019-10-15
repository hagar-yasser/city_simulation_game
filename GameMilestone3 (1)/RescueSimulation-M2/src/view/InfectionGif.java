package view;

import javax.swing.*;

public class InfectionGif extends JFrame {
    public InfectionGif(){
        super();
        this.setSize(956,537);
        this.setTitle("More citizens are getting infected");
        JPanel panel=new JPanel();
        panel.add(new JLabel(new ImageIcon("infection.gif")));
        this.add(panel);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(false);

    }
}
