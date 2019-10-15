package view;

import javax.swing.*;

public class deathGif extends JFrame{
     public deathGif(){
         super();
         this.setSize(956,537);
         this.setTitle("More citizens are dead");
         JPanel panel=new JPanel();
         panel.add(new JLabel(new ImageIcon("death.gif")));
         this.add(panel);
         this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
         this.setVisible(false);

     }
}
