package view;

import javax.swing.*;
import java.awt.*;

public class UnitsButton extends JButton {

    String name;
    ImageIcon icon;
    public UnitsButton(String name,ImageIcon icon,MainLook m){
        super();
        this.setIcon(icon);
        this.name=name;
        this.setPreferredSize(new Dimension(50,50));
        this.addActionListener(m);


    }
    @Override
    public String getName() {
        return name;
    }

}
