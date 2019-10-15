package view;

import model.disasters.*;
import model.infrastructure.ResidentialBuilding;
import model.people.Citizen;
import model.people.CitizenState;
import model.units.Unit;
import simulation.Address;
import simulation.Rescuable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

public class MainLooksEast extends JPanel {
    JPanel NorthLabels = new JPanel(new GridLayout(0, 1, 8, 8));
    JLabel causalities =new JLabel();
    JLabel cycle=new JLabel();
    
    JPanel RescuablesDataP = new JPanel(new BorderLayout());//why not flowLayout? Aren't we going to put two scroll panes; one for citizens and one for the building?
    JPanel UnitsDataP = new JPanel(new BorderLayout());
    Vector<String> RescData;
    Vector<String> occu;
    ResidentialBuilding building;
    JList<String> Citizentarget;
    JList<String> buildingTarget;
    
    JList<String> unitList;
    UnitsButton amb ;
    UnitsButton eva ;
    UnitsButton fireTruck ;
    UnitsButton gasCont;
    UnitsButton diseaseCont;

    JButton nextCycle=new JButton("NextCycle");


    public MainLooksEast(MainLook e) {
        super();
         amb =new UnitsButton("ambulance",new ImageIcon("ambulance.jpg"),e);
         eva =new UnitsButton("evacuator",new ImageIcon("eva.jpg"),e);
         fireTruck =new UnitsButton("FireTruck",new ImageIcon("fireTruck.png"),e);
         gasCont =new UnitsButton("gasControl",new ImageIcon("gascontrol.jpg"),e);
         diseaseCont =new UnitsButton("diseasecontrol",new ImageIcon("diseasecontrol.jpg"),e);

        RescData = new Vector<>();
        occu = new Vector<>();
        Citizentarget = new JList<>(RescData);
        buildingTarget = new JList<>(occu);
        NorthLabels.add(nextCycle);
        NorthLabels.add(causalities);
        NorthLabels.add(cycle);
        nextCycle.addActionListener(e);
        this.setLayout(new BorderLayout());
        this.add(NorthLabels, BorderLayout.NORTH);
        this.add(RescuablesDataP, BorderLayout.CENTER);
        //RescuablesDataP.setPreferredSize(new Dimension(0,100));
        //RescuablesDataP.setMaximumSize(new Dimension(0,100));
        this.add(UnitsDataP, BorderLayout.SOUTH);
        RescuablesDataP.add(new JScrollPane(Citizentarget), BorderLayout.NORTH);
        RescuablesDataP.add(new JScrollPane(buildingTarget), BorderLayout.CENTER);

        Citizentarget.setVisibleRowCount(3);
        buildingTarget.setVisibleRowCount(3);
        unitList=new JList<>(RescData);
        unitList.setVisibleRowCount(15);

        UnitsDataP.add(new JScrollPane(unitList),BorderLayout.SOUTH);
       // UnitsDataP.setPreferredSize(3);
        JPanel availableUnits=new JPanel(new FlowLayout());
        availableUnits.setPreferredSize(new Dimension(297,60));

        availableUnits.add(amb);
        availableUnits.add(eva);
        availableUnits.add(diseaseCont);
        availableUnits.add(fireTruck);
        availableUnits.add(gasCont);
        UnitsDataP.add(availableUnits,BorderLayout.NORTH);
        UnitsDataP.setPreferredSize(new Dimension(297,350));
        UnitsDataP.setMaximumSize(new Dimension(297,350));
    }

    public void setNorthLabels(int causalities, int cycle) {
    	this.causalities.setText(causalities + "  citizens passed away");
    	this.cycle.setText("We are in cycle # " + cycle);
       // NorthLabels.add(new JLabel(causalities + "  citizens passed away"));
        //NorthLabels.add(new JLabel("We are in cycle # " + cycle));
    	

    }

    public void setRescuablesDataforCitizen(ArrayList<Rescuable> r) {
        RescData.clear();
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
                RescData.add("DisasterOnTarget:-" +getDisasterType( citizen.getDisaster())/*.getName()*/);
            }else {
            	ResidentialBuilding rb = (ResidentialBuilding) r.get(i);
            	Vector<String> v = toString(rb);
            	this.getOccu(rb);
            	for(String s : v) {
            		occu.add(s);
            	}
            }
        }
        Citizentarget.setListData(RescData);
        buildingTarget.setListData(occu);
    }
    public static String getDisasterType(Disaster d){
        if(d instanceof Fire){
            return"Fire";
        }
        if(d instanceof Collapse)
            return "Collapse";
        if(d instanceof GasLeak)
            return "GasLeak";
        if(d instanceof Infection)
            return "Infection";
        return "Injury";

    }

    public static Vector<String> toString(ResidentialBuilding rb) {
    	Vector<String> v = new Vector<>();
		v.add("Structural Integrity: " + rb.getStructuralIntegrity());

		v.add("Fire Damage: " + rb.getFireDamage());
		v.add("Gas Level: "+ rb.getGasLevel());
		v.add("Foundation Damage: " + rb.getFoundationDamage());
		v.add("Current Disaster: " + getDisasterType(rb.getDisaster()));
		v.add("No. of occupants: "+ rb.getOccupants().size());
		return v;
	}

	public void getOccu(ResidentialBuilding rb) {
		occu.clear();
		for (int i = 0; i < rb.getOccupants().size(); i++) {
				Citizen citizen = (Citizen) rb.getOccupants().get(i);
				occu.add("Name :-" + citizen.getName());
				occu.add("ID number:-" + citizen.getNationalID());
				occu.add("HP:-" + citizen.getHp());
				occu.add("Age:-" + citizen.getAge());
				occu.add("toxicityLevel:-" + citizen.getToxicity());
				occu.add("BloodLossLevel:-" + citizen.getBloodLoss());
				occu.add("Citizen's State" + citizen.getState());
				occu.add("Location:- (" + citizen.getLocation().getX() + "," + citizen.getLocation().getY() + ")");
				occu.add("DisasterOnTarget:-" + getDisasterType(citizen.getDisaster()));
				occu.add("------------------------------");
		}

	}
	
    public void setUnitsDataP(Vector<Unit> u) {
        Vector<String>data=new Vector<>();
        for(Unit ux:u) {

            for(String s: ux.toStringU())
            data.add(s);
        }
        unitList.setListData(data);
    }
    public JList<String> getCitizentarget() {
        return Citizentarget;
    }

    public JList<String> getBuildingTarget() {
        return buildingTarget;
    }

    public JList<String> getUnitList() {
        return unitList;
    }

}