package controller;

import java.util.ArrayList;

import model.events.SOSListener;
import model.infrastructure.ResidentialBuilding;
import model.people.Citizen;
import model.units.Unit;
import simulation.Rescuable;
import simulation.Simulator;
import views.MainLook;

public class CommandCenter implements SOSListener {

	private Simulator engine;
	private ArrayList<ResidentialBuilding> visibleBuildings;
	private ArrayList<Citizen> visibleCitizens;

	@SuppressWarnings("unused")
	private ArrayList<Unit> emergencyUnits;
	private MainLook mainLook;

	public CommandCenter() throws Exception {
	    mainLook=new MainLook();
		engine = new Simulator(this);
		engine.setMainLookListener(mainLook);
		visibleBuildings = new ArrayList<ResidentialBuilding>();
		visibleCitizens = new ArrayList<Citizen>();
		emergencyUnits = engine.getEmergencyUnits();

	}

	@Override
	public void receiveSOSCall(Rescuable r) {
		
		if (r instanceof ResidentialBuilding) {
			
			if (!visibleBuildings.contains(r)) {
                visibleBuildings.add((ResidentialBuilding) r);

                mainLook.getPanelsinGrid()[r.getLocation().getX()][r.getLocation().getY()].getRescButton().addTarget(r);
                mainLook.getPanelsinGrid()[r.getLocation().getX()][r.getLocation().getY()].getRescButton().addDisaster(r.getDisaster());
            }

			
		} else {
			
			if (!visibleCitizens.contains(r)) {
                visibleCitizens.add((Citizen) r);
                mainLook.getPanelsinGrid()[r.getLocation().getX()][r.getLocation().getY()].getRescButton().addTarget(r);
                mainLook.getPanelsinGrid()[r.getLocation().getX()][r.getLocation().getY()].getRescButton().addDisaster(r.getDisaster());
            }

		}

	}

}
