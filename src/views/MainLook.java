package views;

import model.disasters.*;
import model.events.WorldListener;
import model.people.Citizen;
import model.people.CitizenState;
import simulation.Address;
import simulation.Simulator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Vector;

public class MainLook extends JFrame implements ActionListener {
    MainLooksEast labels = new MainLooksEast(this);
    JFrame frame = new JFrame("Hello");
    TheGridsPanel[][] panelsinGrid = new TheGridsPanel[10][10];
    WorldListener worldListener;
    Vector<Disaster> exd=new Vector<>();
    Vector<Disaster>pland=new Vector<>();
    JPanel west = new JPanel(new GridLayout(0, 1, 8, 8));
    JList<String>execdisasterJList=new JList<>();
    JList<String>plandisasterJList=new JList<>();

    public void addPland(Disaster d) {
        this.pland.add(d);
    }


    public void removeplan(Disaster d){
        pland.remove(d);
    }
    public void addexec(Disaster d){
        exd.add(d);
    }

    public void setWorldListener(WorldListener worldListener) {
        this.worldListener = worldListener;
    }

    public WorldListener getWorldListener() {
        return worldListener;
    }

    public MainLooksEast getLabels() {
        return labels;
    }

    public TheGridsPanel[][] getPanelsinGrid() {

        return panelsinGrid;
    }

    public MainLook() {


        frame.setSize(1650, 1080);
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        JPanel gridComponents = new JPanel(new GridLayout(10, 10));






        JPanel east = new JPanel(new GridLayout(0, 1, 8, 8));

        labels.setNorthLabels(0, 0);
        //((MainLooksEast) labels).setRescuablesDataforCitizen(100,10,19,19,CitizenState.IN_TROUBLE,768,new Address(10,3)
        //,"GOGZ","Toxicity");
        labels.setUnitsDataP();
        labels.setBorder(BorderFactory.createEmptyBorder(8, 8, 30, 30));
        frame.add(labels, BorderLayout.EAST);


        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                panelsinGrid[i][j] = new TheGridsPanel(this);


                gridComponents.add(panelsinGrid[i][j]);
            }
        }


        gridComponents.setPreferredSize(new Dimension(1000, 1080));
        gridComponents.setMaximumSize(gridComponents.getPreferredSize());
        frame.add(gridComponents, BorderLayout.CENTER);
        frame.add(west,BorderLayout.WEST);

        frame.validate();

    }
    public void SetDisastersjlists(){
        Vector<String>ex=new Vector<>();
        Vector<String>pla=new Vector<>();
        for (int i = 0; i <exd.size() ; i++) {
            if(exd.get(i)instanceof Collapse) {
                ex.add("Collapse");
            }
            if(exd.get(i)instanceof Fire) {
                ex.add("Fire");
            }
            if(exd.get(i)instanceof GasLeak) {
                ex.add("GasLeak");
            }
            if(exd.get(i)instanceof Infection) {
                ex.add("Infection and its target's name is " + ((Citizen)(exd.get(i).getTarget())).getName());

            }
            if(exd.get(i)instanceof Injury) {
                ex.add("injury and its target's name is " + ((Citizen)(exd.get(i).getTarget())).getName());
            }
        }
        execdisasterJList=new JList<>(ex);
        for (int i = 0; i <pland.size() ; i++) {
            if(pland.get(i)instanceof Collapse) {
                pla.add("Collapse");
            }
            if(pland.get(i)instanceof Fire) {
                pla.add("Fire");
            }
            if(pland.get(i)instanceof GasLeak) {
                pla.add("GasLeak");
            }
            if(pland.get(i)instanceof Infection) {
                pla.add("Infection and its target's name is " + ((Citizen)(exd.get(i).getTarget())).getName());

            }
            if(pland.get(i)instanceof Injury) {
                pla.add("injury and its target's name is " + ((Citizen)(exd.get(i).getTarget())).getName());
            }
        }
       plandisasterJList=new JList<>(pla);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("target")) {
            System.out.print("i'm here");
            TargetsButton b = (TargetsButton) ae.getSource();
            for (int i = 0; i < b.target.size(); i++) {


                labels.setRescuablesDataforCitizen(b.target);

            }
        }
        if (ae.getActionCommand().equals("NextCycle")) {
            ((Simulator) worldListener).nextCycle();
            west.add(new JScrollPane(execdisasterJList));
            west.add(new JScrollPane(plandisasterJList));
        }
        frame.validate();
    }

    public static void main(String[] args) {
        MainLook f=new MainLook();
    }
}
