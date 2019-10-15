package view;

import simulation.Rescuable;

import javax.swing.*;

import java.awt.*;

public class TheGridsPanel extends JPanel {
    public TargetsButton getRescButton() {
        return RescButton;
    }

    JPanel TheNorthforTarget;
    JPanel TheSouthForUnits;
    JPanel TheCenterForUnits;
    TargetsButton RescButton;
    Rescuable MyRescuable;
    JComboBox MyRescuableCB = new JComboBox();
    JComboBox MyUnitCB =new JComboBox();
    JLabel MyRescuablesImage;
    JLabel MyUnitsImage;
    JButton UnitsButton ;
    public TheGridsPanel (MainLook e){
        super();
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
        TheNorthforTarget =new JPanel(new FlowLayout());
        RescButton=new TargetsButton();
        //RescButton.setIcon(new ImageIcon("/home/omar/Desktop/Hagar's Desktop/Webp.net-resizeimage (2).jpg"));
        RescButton.addActionListener(e);
        RescButton.setPreferredSize(new Dimension(80,20));
      UnitsButton =new JButton();
      UnitsButton.setPreferredSize(new Dimension(80,20));
      UnitsButton.addActionListener(e);
        TheNorthforTarget.add(RescButton);
        //TheNorthforTarget.add(new JLabel("/home/omar/Desktop/Hagar's Desktop/Webp.net-resizeimage.jpg") );
        //TheSouthForUnits.add(MyUnitCB);
        TheCenterForUnits=new JPanel( new FlowLayout());
        TheCenterForUnits.add(UnitsButton);
        //TheSouthForUnits= new JPanel(new FlowLayout());

        this.add(TheNorthforTarget,BorderLayout.NORTH);
        this.add(TheCenterForUnits,BorderLayout.CENTER);
        //this.add(TheSouthForUnits,BorderLayout.SOUTH);
        //TheSouthForUnits.add(new JLabel("Un'sIm"));

    }

}