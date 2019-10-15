package view;

import exceptions.DisasterException;
import exceptions.UnitException;
import model.disasters.*;
import model.events.SOSListener;
import model.events.WorldListener;
import model.people.Citizen;
import model.people.CitizenState;
import model.units.*;
import simulation.Address;
import simulation.Rescuable;
import simulation.Simulator;

import javax.swing.*;

import controller.CommandCenter;

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
    SOSListener cc;
    Vector<Disaster> exd = new Vector<>();
    Vector<Disaster> pland = new Vector<>();
    Vector<Unit> amb;
    Vector<Unit> evac;
    Vector<Unit> diseasecon;
    Vector<Unit> gascont;
    Vector<Unit> fireTrucks;
    boolean assigned = false;
    Rescuable assResc;
    ArrayList<JButton> UB;

    public Vector<Unit> getAmb() {
        return amb;
    }

    public Vector<Unit> getEvac() {
        return evac;
    }

    public Vector<Unit> getDiseasecon() {
        return diseasecon;
    }

    public Vector<Unit> getGascont() {
        return gascont;
    }

    public Vector<Unit> getFireTrucks() {
        return fireTrucks;
    }


    JList<String> execdisasterJList = new JList<>();
    JList<String> plandisasterJList = new JList<>();
    JPanel west = new JPanel(new BorderLayout());
    collapseGif collapsegif = new collapseGif();
    deathGif death = new deathGif();
    fireGifFrame firegif = new fireGifFrame();
    GameOverGif gameOver;
    GasLeakGif gasLeakGif = new GasLeakGif();
    InfectionGif infectionGif = new InfectionGif();
    InjuryGif injuryGif = new InjuryGif();

    public collapseGif getCollapsegif() {
        return collapsegif;
    }

    public deathGif getDeath() {
        return death;
    }

    public fireGifFrame getFiregif() {
        return firegif;
    }

    public GameOverGif getGameOver() {
        return gameOver;
    }

    public GasLeakGif getGasLeakGif() {
        return gasLeakGif;
    }

    public InfectionGif getInfectionGif() {
        return infectionGif;
    }

    public InjuryGif getInjuryGif() {
        return injuryGif;
    }

    public Vector<Disaster> getExd() {
        return exd;
    }


    public Vector<Disaster> getPland() {
        return pland;
    }


    public SOSListener getCc() {
        return cc;
    }


    public void setCc(SOSListener cc) {
        this.cc = cc;
    }


    public void setPland(Vector<Disaster> pland) {
        this.pland = pland;
    }


    public void addPland(Disaster d) {
        this.pland.add(d);
    }


    public void removeplan(Disaster d) {
        pland.remove(d);
    }

    public void addexec(Disaster d) {
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

    public MainLook() throws Exception {

        frame.setSize(1650, 1080);
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        JPanel gridComponents = new JPanel(new GridLayout(10, 10));

        JPanel east = new JPanel(new GridLayout(0, 1, 8, 8));
        amb = new Vector<Unit>();
        evac = new Vector<Unit>();
        fireTrucks = new Vector<Unit>();
        diseasecon = new Vector<Unit>();
        gascont = new Vector<Unit>();
        labels.setNorthLabels(0, 0);
        //((MainLooksEast) labels).setRescuablesDataforCitizen(100,10,19,19,CitizenState.IN_TROUBLE,768,new Address(10,3)
        //,"GOGZ","Toxicity");
        labels.setUnitsDataP(amb);
        labels.setBorder(BorderFactory.createEmptyBorder(8, 8, 30, 30));

        frame.add(labels, BorderLayout.EAST);
        west.add(new JScrollPane(execdisasterJList), BorderLayout.NORTH);
        west.add(new JScrollPane(plandisasterJList), BorderLayout.CENTER);


        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                panelsinGrid[i][j] = new TheGridsPanel(this);


                gridComponents.add(panelsinGrid[i][j]);
            }
        }


        gridComponents.setPreferredSize(new Dimension(1000, 1080));
        gridComponents.setMaximumSize(gridComponents.getPreferredSize());
        frame.add(gridComponents, BorderLayout.CENTER);
        frame.add(west, BorderLayout.WEST);

        frame.validate();
        UB = new ArrayList<JButton>();

    }

    public void SetDisastersjlists() {
        Vector<String> ex = new Vector<>();
        Vector<String> pla = new Vector<>();

        for (int i = 0; i < exd.size(); i++) {
            ex.add(getDisasterLocation(exd.get(i)));
            ex.add("Activity : " + exd.get(i).isActive());
            if (exd.get(i) instanceof Collapse) {
                ex.add("Collapse");
            }
            if (exd.get(i) instanceof Fire) {
                ex.add("Fire");
            }
            if (exd.get(i) instanceof GasLeak) {
                ex.add("GasLeak");
            }
            if (exd.get(i) instanceof Infection) {
                ex.add("Infection and its target's name is " + ((Citizen) (exd.get(i).getTarget())).getName());

            }
            if (exd.get(i) instanceof Injury) {
                ex.add("injury and its target's name is " + ((Citizen) (exd.get(i).getTarget())).getName());
            }
            ex.add("------------------------------");
        }
        execdisasterJList.setListData(ex);

        for (int i = 0; i < pland.size(); i++) {
            pla.add(getDisasterLocation(pland.get(i)));
            if (pland.get(i) instanceof Collapse) {
                pla.add("Collapse");
            }
            if (pland.get(i) instanceof Fire) {
                pla.add("Fire");
            }
            if (pland.get(i) instanceof GasLeak) {
                pla.add("GasLeak");
            }
            if (pland.get(i) instanceof Infection) {
                pla.add("Infection and its target's name is " + ((Citizen) (pland.get(i).getTarget())).getName());

            }
            if (pland.get(i) instanceof Injury) {
                pla.add("injury and its target's name is " + ((Citizen) (pland.get(i).getTarget())).getName());
            }
            ex.add("------------------------------");

        }
        plandisasterJList.setListData(pla);
        //plandisasterJList=new JList<>(pla);
        //frame.validate();
    }

    public static String getDisasterLocation(Disaster d) {
        return "Location of disaster : (" + d.getTarget().getLocation().getX() + "," + d.getTarget().getLocation().getY() + ")";
    }

    public static String getUnitIcon(Unit u) {
        if (u instanceof Ambulance)
            return "smallAmb.jpg";
        if (u instanceof DiseaseControlUnit)
            return "smalldis.jpg";
        if (u instanceof Evacuator)
            return "smallEvac.jpg";
        if (u instanceof FireTruck)
            return "smallfire.png";
        return "smallgas.jpg";

    }

    public static int checkLocation(Unit u) {
        int xt = u.getTarget().getLocation().getX();
        int yt = u.getTarget().getLocation().getY();
        int x = u.getLocation().getX();
        int y = u.getLocation().getY();
        return (Math.abs((x - xt)) + Math.abs(y - yt)) - (u.getStepsPerCycle());
    }


    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("NextCycle")) {
            try {
                ((CommandCenter) cc).getEngine().nextCycle();
            } catch (DisasterException e) {

                JDialog d = new JDialog(this, "DisasterException", Dialog.ModalityType.DOCUMENT_MODAL);
                d.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                d.setPreferredSize(new Dimension(800, 200));
                d.setLocationRelativeTo(this);
                d.add(new JLabel(e.getMessage()));
                d.pack();
                d.setVisible(true);
            }


            this.SetDisastersjlists();
            if (((CommandCenter) cc).getEngine().checkGameOver()) {
                gameOver = new GameOverGif(((CommandCenter) cc).getEngine().calculateCasualties(), ((CommandCenter) cc).getEngine().calculateRescued());
                gameOver.setVisible(true);
            }


            labels.getBuildingTarget().setListData(new Vector<>());
            labels.getUnitList().setListData(new Vector<>());
            labels.getCitizentarget().setListData(new Vector<>());

            for (JButton ju : UB) {
                ju.setIcon(null);

            }
            UB.clear();

            for (Unit u : ((CommandCenter) cc).getEngine().getEmergencyUnits()) {

                if (u.getTarget() != null && u.getState() != UnitState.TREATING) {
                    if (checkLocation(u) <= 0) {
                        UB.add(panelsinGrid[u.getLocation().getX()][u.getLocation().getY()].UnitsButton);
                    }

                }
                if (u.getState() == UnitState.TREATING) {
                    panelsinGrid[u.getLocation().getX()][u.getLocation().getY()].UnitsButton.setIcon(new ImageIcon(getUnitIcon(u)));
                }

            }
        }


        //((Simulator) worldListener).nextCycle();


        else {
            if (ae.getSource() instanceof UnitsButton) {

                UnitsButton u = (UnitsButton) ae.getSource();
                if (u.getName().equals("ambulance")) {
                    labels.setUnitsDataP(amb);
                    if (assResc != null) {
                        try {
                            amb.get(0).respond(assResc);
                        } catch (UnitException e) {


                            JDialog d = new JDialog(this, "UnitException", Dialog.ModalityType.DOCUMENT_MODAL);


                            d.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                            d.setPreferredSize(new Dimension(800, 200));
                            d.setLocationRelativeTo(this);
                            d.add(new JLabel(e.getMessage()));
                            d.pack();
                            d.setVisible(true);
                        }

                        amb.add(amb.remove(0));
                        assResc = null;
                    }
                }
                if (u.getName().equals("evacuator")) {
                    labels.setUnitsDataP(evac);
                    if (assResc != null) {
                        try {
                            evac.get(0).respond(assResc);
                        } catch (UnitException e) {
                            JDialog d = new JDialog(this, "UnitException", Dialog.ModalityType.DOCUMENT_MODAL);
                            d.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                            d.setPreferredSize(new Dimension(800, 200));
                            d.setLocationRelativeTo(this);
                            d.add(new JLabel(e.getMessage()));
                            d.pack();
                            d.setVisible(true);
                        }
                        evac.add(evac.remove(0));
                        assResc = null;
                    }


                }
                if (u.getName().equals("gasControl")) {
                    labels.setUnitsDataP(gascont);
                    if (assResc != null) {
                        try {
                            gascont.get(0).respond(assResc);
                        } catch (UnitException e) {
                            JDialog d = new JDialog(this, "UnitException", Dialog.ModalityType.DOCUMENT_MODAL);
                            d.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                            d.setPreferredSize(new Dimension(800, 200));
                            d.setLocationRelativeTo(this);
                            d.add(new JLabel(e.getMessage()));
                            d.pack();
                            d.setVisible(true);
                        }
                        gascont.add(gascont.remove(0));
                        assResc = null;
                    }
                }
                if (u.getName().equals("FireTruck")) {
                    labels.setUnitsDataP(fireTrucks);
                    if (assResc != null) {
                        try {
                            fireTrucks.get(0).respond(assResc);
                        } catch (UnitException e) {
                            JDialog d = new JDialog(this, "UnitException", Dialog.ModalityType.DOCUMENT_MODAL);
                            d.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                            d.setPreferredSize(new Dimension(800, 200));
                            d.setLocationRelativeTo(this);
                            d.add(new JLabel(e.getMessage()));
                            d.pack();
                            d.setVisible(true);
                        }
                        fireTrucks.add(fireTrucks.remove(0));
                        assResc = null;
                    }
                }
                if (u.getName().equals("diseasecontrol")) {
                    labels.setUnitsDataP(diseasecon);
                    if (assResc != null) {
                        try {
                            diseasecon.get(0).respond(assResc);
                        } catch (UnitException e) {
                            JDialog d = new JDialog(this, "UnitException", Dialog.ModalityType.DOCUMENT_MODAL);
                            d.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                            d.setPreferredSize(new Dimension(800, 200));
                            d.setLocationRelativeTo(this);
                            d.add(new JLabel(e.getMessage()));
                            d.pack();
                            d.setVisible(true);
                        }
                        diseasecon.add(diseasecon.remove(0));
                        assResc = null;
                    }
                }
            } else {

                if (ae.getSource() instanceof TargetsButton) {
                    TargetsButton b = (TargetsButton) ae.getSource();
                    assigned = true;
                    if (b.target.size() != 0)
                        assResc = b.target.get(0);
                    for (int i = 0; i < b.target.size(); i++) {
                        labels.setRescuablesDataforCitizen(b.target);
                    }
                } else {
                    JButton b = (JButton) ae.getSource();
                    System.out.print("hey");
                    int x = -1;
                    int y = -1;
                    for (int i = 0; i < 10; i++) {
                        int j;
                        for (j = 0; j < 10; j++) {
                            if (panelsinGrid[i][j].UnitsButton.equals(b)) {
                                x = i;
                                y = j;
                                break;
                            }
                        }
                        if (j != 10)
                            break;

                    }
                    for (Unit u:  ((CommandCenter)cc).getEngine().getEmergencyUnits()) {
                        if(u.getLocation().getX()==x&&u.getLocation().getY()==y){
                            labels.getUnitList().setListData(u.toStringU());
                        }

                    }

                }
            }
        }

        frame.validate();
    }


}
