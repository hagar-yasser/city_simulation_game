package view;

import javax.swing.*;

public class fireGifFrame extends JFrame {
    public fireGifFrame(){
        super();
        this.setSize(956,537);
        this.setTitle("Fire disaster has struck a building ");
        JPanel panel=new JPanel();
        panel.add(new JLabel(new ImageIcon("/home/omar/Desktop/Hagar's Desktop/fire.gif")));
        this.add(panel);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(false);

    }
}
