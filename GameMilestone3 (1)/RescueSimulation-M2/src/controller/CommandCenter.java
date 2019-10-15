package controller;

import java.util.ArrayList;

import model.events.SOSListener;
import model.infrastructure.ResidentialBuilding;
import model.people.Citizen;
import model.units.*;
import simulation.Rescuable;
import simulation.Simulator;
import view.MainLook;

public class CommandCenter implements SOSListener {

    private Simulator engine;
    private ArrayList<ResidentialBuilding> visibleBuildings;
    private ArrayList<Citizen> visibleCitizens;

    @SuppressWarnings("unused")
    private ArrayList<Unit> emergencyUnits;
    private MainLook mainLook;

    public CommandCenter() throws Exception {
        engine = new Simulator(this);
        mainLook = new MainLook();
        mainLook.setCc(this);

        // mainLook.setWorldListener(engine);
        engine.setMainLookListener(mainLook);
        visibleBuildings = new ArrayList<ResidentialBuilding>();
        visibleCitizens = new ArrayList<Citizen>();
        emergencyUnits = engine.getEmergencyUnits();
        for (Unit u: emergencyUnits) {
            if(u instanceof Ambulance)
                mainLook.getAmb().add((Ambulance) u);
            if(u instanceof DiseaseControlUnit)
                mainLook.getDiseasecon().add((DiseaseControlUnit)u);
            if(u instanceof GasControlUnit)
                mainLook.getGascont().add((GasControlUnit)u);
            if(u instanceof FireTruck)
                mainLook.getFireTrucks().add((FireTruck)u);
            if(u instanceof Evacuator)
                mainLook.getEvac().add((Evacuator)u);
        }

    }

    public Simulator getEngine() {
        return engine;
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

    public static void main(String[] args) throws Exception {
        CommandCenter c = new CommandCenter();

    }


}
