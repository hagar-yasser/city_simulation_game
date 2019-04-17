package views;

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
    public TheGridsPanel (MainLook e){
        super();
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
        TheNorthforTarget =new JPanel(new FlowLayout());
        RescButton=new TargetsButton("target");
        RescButton.addActionListener(e);
        RescButton.setPreferredSize(new Dimension(80,20));
      JButton UnitsButton =new JButton("Unit");
        TheNorthforTarget.add(RescButton);

        //TheSouthForUnits.add(MyUnitCB);
        TheCenterForUnits=new JPanel( new FlowLayout());
        TheCenterForUnits.add(new JButton("unit"));
        //TheSouthForUnits= new JPanel(new FlowLayout());

        this.add(TheNorthforTarget,BorderLayout.NORTH);
        this.add(TheCenterForUnits,BorderLayout.CENTER);
        //this.add(TheSouthForUnits,BorderLayout.SOUTH);
        //TheSouthForUnits.add(new JLabel("Un'sIm"));





    }

}
