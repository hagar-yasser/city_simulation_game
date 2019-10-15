package view;

import javax.swing.*;

public class collapseGif extends JFrame {
    public collapseGif(){
        super();
        this.setSize(956,537);
        this.setTitle("Collapse disaster has struk");
        JPanel panel=new JPanel();
        panel.add(new JLabel(new ImageIcon("collapse.gif")));
        this.add(panel);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(false);
    }
}
