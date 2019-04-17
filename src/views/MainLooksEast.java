package views;

import model.people.Citizen;
import model.people.CitizenState;
import simulation.Address;
import simulation.Rescuable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

public class MainLooksEast extends JPanel {
    JPanel NorthLabels = new JPanel(new GridLayout(0, 1, 8, 8));

    JPanel RescuablesDataP = new JPanel(new BorderLayout());
    JPanel UnitsDataP = new JPanel(new GridLayout(0, 1, 8, 8));
    Vector<String> RescData;
    JList<String> targetList;
    JList<String> unitList;
    JButton nextCycle=new JButton("NextCycle");

    public MainLooksEast(MainLook e) {

        super();
        NorthLabels.add(nextCycle);
        nextCycle.addActionListener(e);
        this.setLayout(new BorderLayout());
        this.add(NorthLabels, BorderLayout.NORTH);
        this.add(RescuablesDataP, BorderLayout.CENTER);
        //RescuablesDataP.setPreferredSize(new Dimension(0,100));
        //RescuablesDataP.setMaximumSize(new Dimension(0,100));
        this.add(UnitsDataP, BorderLayout.SOUTH);

    }

    public void setNorthLabels(int causalities, int cycle) {
        NorthLabels.add(new JLabel(causalities + "  citizens passed away"));
        NorthLabels.add(new JLabel("We are in cycle # " + cycle));

    }

    public void setRescuablesDataforCitizen(ArrayList<Rescuable> r) {
        RescData = new Vector<>();
        for (int i = 0; i < r.size(); i++) {
            if (r.get(i) instanceof Citizen) {

                Citizen citizen = (Citizen) r.get(i);
                RescData.add("Name :-" + citizen.getName());
                RescData.add("ID number:-" + citizen.getNationalID());
                RescData.add("HP:-" + citizen.getHp());
                RescData.add("Age:-" + citizen.getAge());
                RescData.add("toxicityLevel:-" + citizen.getToxicity());
                RescData.add("BloodLossLevel:-" + citizen.getBloodLoss());
                RescData.add("Citizen's State" + citizen.getState());
                RescData.add("Location:- (" + citizen.getLocation().getX() + "," + citizen.getLocation().getY() + ")");
                RescData.add("DisasterOnTarget:-" + citizen.getDisaster().getName());
            }
        }
        targetList = new JList<>(RescData);
        targetList.setVisibleRowCount(3);
        RescuablesDataP.add(new JScrollPane(targetList), BorderLayout.NORTH);
        //RescuablesDataP.add(new JLabel("HP:-" + hp));
        //RescuablesDataP.add(new JLabel("Age:-"+age));
        //RescuablesDataP.add(new JLabel("toxicityLevel:-"+toxicity));
        //RescuablesDataP.add(new JLabel("BloodLossLevel:-"+bloodloss));

//        RescuablesDataP.add(new JLabel("Citizen's State" + c));
//        RescuablesDataP.add(new JLabel("ID number:-"+id));
//        RescuablesDataP.add(new JLabel("Location:- (" + location.getX()+","+location.getY()+")"));
//        RescuablesDataP.add(new JLabel("Name :-"+name));
//        RescuablesDataP.add(new JLabel("DisasterOnTarget:-"+disasterName));

    }

    public void setUnitsDataP() {
        UnitsDataP.add(new JLabel("hey" + '\n' + '\n'));
        UnitsDataP.add(new JLabel("hey"));
        UnitsDataP.add(new JLabel("hey"));
        UnitsDataP.add(new JLabel("hey"));
        UnitsDataP.add(new JLabel("hey"));
        UnitsDataP.add(new JLabel("hey"));
        UnitsDataP.add(new JLabel("hey"));
        UnitsDataP.add(new JLabel("hey"));
        UnitsDataP.add(new JLabel("hey" + '\n' + '\n'));
        UnitsDataP.add(new JLabel("hey"));
        UnitsDataP.add(new JLabel("hey"));
        UnitsDataP.add(new JLabel("hey"));
        UnitsDataP.add(new JLabel("hey"));
        UnitsDataP.add(new JLabel("hey"));
        UnitsDataP.add(new JLabel("hey"));
        UnitsDataP.add(new JLabel("hey"));
    }
}
